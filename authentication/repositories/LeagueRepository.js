const { sql } = require("../config/db")


exports.getByName = async(nombre, page) =>{
    
    if (nombre == '') {

        const offset = (page-1) * 1
        
        // Consulta segura con parámetros preparados
        const query = 'SELECT * FROM ligas WHERE LOWER(nombre) LIKE LOWER($1) LIMIT 1 OFFSET $2';
        const values = [`%${nombre}%`, offset]; // Usa comodines (%) en el valor

        // Ejecuta la consulta
        const result = await sql(query, values);
        return result
    }

    // Consulta segura con parámetros preparados
    const query = 'SELECT * FROM ligas WHERE LOWER(nombre) LIKE LOWER($1)';
    const values = [`%${nombre}%`]; // Usa comodines (%) en el valor

    // Ejecuta la consulta
    const result = await sql(query, values);
    console.log('====================================');
    console.log(result);
    console.log('====================================');

    return result
}