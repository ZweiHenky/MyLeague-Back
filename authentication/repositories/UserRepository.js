const {sql} = require("../config/db")

exports.create = async (data) => {
    
    const {nombre, apellido, email, telefono, password} = data
    await sql`INSERT INTO usuarios (nombre, apellido, email, telefono, password) VALUES (${nombre}, ${apellido}, ${email}, ${telefono}, ${password})`

}

exports.get = async (data) =>{

    const {email} = data
    const [result] = await sql`SELECT * FROM usuarios WHERE email = ${email}`

    return result
}

exports.getByEmail = async (email) =>{

    const [result] = await sql`SELECT * FROM usuarios WHERE email = ${email}`

    return result
}

exports.getByPhone = async (telefono) =>{

    const [result] = await sql`SELECT * FROM usuarios WHERE telefono = ${telefono}`

    return result
}