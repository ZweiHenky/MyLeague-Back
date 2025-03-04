// utils/jwtUtils.js
const jwt = require('jsonwebtoken');

const generateToken = (user) => {
  
  return jwt.sign(user, process.env.JWT_SECRET, {
    expiresIn: '1h',
  });
};

const generateRefreshToken = (user) => {
  return jwt.sign(user, process.env.JWT_SECRET_REFRESH);
};

module.exports = {
  generateToken,
  generateRefreshToken
};
