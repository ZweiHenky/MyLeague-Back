const { getByName } = require("../repositories/LeagueRepository")

exports.LeagueByNameService = async(nombre, page) =>{
    return await getByName(nombre, page)
}