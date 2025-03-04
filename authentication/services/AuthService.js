const {UserSchema, LoginSchema} = require("../schemas/UserSchema")

const bcrypt =  require("bcryptjs")
const { create, get, getByEmail, getByPhone } = require("../repositories/UserRepository")
const { generateToken, generateRefreshToken } = require("../utils/jwtUtils")


exports.RegisterService = async (data) =>{
    const {error, value} = UserSchema.validate(data)
    
    if (error) {
        throw new Error(error.details[0].message)
    }

    const validate_email = await getByEmail(value.email)

    if (validate_email) {
        throw new Error("El correo ya esta en uso")
    }

    const validate_phone = await getByPhone(value.telefono)

    if (validate_phone) {
        throw new Error("El telefono ya esta en uso")
    }

    const salt  = bcrypt.genSaltSync(10)
    const hashPass = bcrypt.hashSync(value.password, salt)
    value.password = hashPass

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

    const status = bcrypt.compareSync(value.password, user.password)

    if (!status) {
        throw new Error("La contraseña no coincide")
    }

    delete user.password

    // Generar token JWT
    const token = generateToken(user);
    const refreshToken = generateRefreshToken(user);

    return {
        token, 
        refreshToken,
        user
    }
}