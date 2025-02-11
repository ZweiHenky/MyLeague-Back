const { RegisterService, LoginService } = require("../services/AuthService")
const jwt = require('jsonwebtoken');
const { generateRefreshToken, generateToken } = require("../utils/jwtUtils");


exports.login = async (req, res, next) => {
    try {
        const {token, refreshToken, user} = await LoginService(req.body)
        res.status(200).json({

            token,
            refreshToken,
            ...user

        })
    } catch (error) {
        next(error)
    }
}

exports.register = async (req, res, next) => {
    try {
        await RegisterService(req.body)
        res.status(200).json({
            message:"El usuario ha sido registrado"
        })
    } catch (error) {
        next(error)
    }
}


exports.verifyToken = async (req, res) => {
    const authHeader = req.headers["authorization"];

    if (!authHeader || !authHeader.startsWith("Bearer ")) {
        return res.status(401).json({ message: "No token provided" });
    }

    const token = authHeader.split(" ")[1];

    try {
        const user = jwt.verify(token, process.env.JWT_SECRET);
        return res.status(200).json({ token, ...user });
    } catch (error) {
        return res.status(403).json({ message: "Invalid Token" });
    }
};
  
  // Ruta para refrescar el token
exports.verifyRefresh = async(req, res, next) => {
    const { refreshToken } = req.body;
  
    if (!refreshToken) return res.status(401).send('Refresh Token is required');
  
    try {
      jwt.verify(refreshToken, process.env.JWT_SECRET_REFRESH, (err, user) => {
        if (err) return res.status(403).send('Invalid Refresh Token');
  
            // Generar token JWT
          const token = generateToken(user);
          const refreshToken = generateRefreshToken(user);
  
          res.status(200).json({ token , refreshToken, ...user });
    });
    } catch (error) {
      next(error)
    }
  };