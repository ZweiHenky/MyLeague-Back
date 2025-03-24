const { sql } = require("../config/db")


exports.UpdateMatch = async(req, res, next) => {
    try {
        const {idMatch} = req.params
        const {fecha,horaInicio,horaFin,golesLocal,golesVisitante,} = req.body

        const result = await sql`update partidos set gol_Local = ${golesLocal}, gol_Visitante = ${golesVisitante} where id = ${idMatch}`
        return res.status(200).json({message:"se actualizo con exito", status:true})
    } catch (error) {
        next(error)
    }

   
}