const { LeagueByNameService } = require("../services/LeagueService")

exports.LeagueByName = async(req, res, next) =>{
    try {
        const {nombre, page} = req.query
        const data = await LeagueByNameService(nombre, page)

        return res.status(200).json(data)
    } catch (error) {
        next(error)
    }
}