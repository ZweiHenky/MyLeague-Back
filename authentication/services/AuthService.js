const {UserSchema, LoginSchema} = require("../schemas/UserSchema")

const bcrypt =  require("bcryptjs")
const { create, get } = require("../repositories/UserRepository")
const { generateToken, generateRefreshToken } = require("../utils/jwtUtils")


exports.RegisterService = async (data) =>{
    const {error, value} = UserSchema.validate(data)
    
    if (error) {
        throw new Error(error.details[0].message)
    }

    const salt  = bcrypt.genSaltSync(10)
    const hashPass = bcrypt.hashSync(value.usu_pass, salt)
    value.usu_pass = hashPass

    await create(value)
    
}

exports.LoginService = async (data) =>{
    const {error, value} = LoginSchema.validate(data)
    
    if (error) {
        throw new Error(error.details[0].message)
    }

    const user = await get(value)

    if (!user) {
        throw new Error("El usuario no existe")
    }

    const status = bcrypt.compareSync(value.usu_pass, user.usu_pass)

    if (!status) {
        throw new Error("La contraseña no coincide")
    }

    delete user.usu_pass

    // Generar token JWT
    const token = generateToken(user);
    const refreshToken = generateRefreshToken(user);

    return {
        token, 
        refreshToken,
        user
    }
}