const {sql} = require("../config/db")

exports.create = async (data) => {
    
    const {usu_name, usu_last, usu_email, usu_tel, usu_pass} = data
    await sql`INSERT INTO users (usu_name, usu_last, usu_email, usu_tel, usu_pass) VALUES (${usu_name}, ${usu_last}, ${usu_email}, ${usu_tel}, ${usu_pass})`

}

exports.get = async (data) =>{

    const {usu_email} = data
    const [result] = await sql`SELECT * FROM users WHERE usu_email = ${usu_email}`

    return result
}

exports.getByEmail = async (usu_email) =>{

    const [result] = await sql`SELECT * FROM users WHERE usu_email = ${usu_email}`

    return result
}

exports.getByPhone = async (usu_tel) =>{

    const [result] = await sql`SELECT * FROM users WHERE usu_tel = ${usu_tel}`

    return result
}