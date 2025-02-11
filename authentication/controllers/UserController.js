const UserService = require("../services/UserService")

exports.createUser = async (req, res, next) =>{
    try{
        await UserService.createUser(req.body)
        res.status(200).json({
            message:"El usuario ha sido creado"
        })
    }catch(error){
        next(error)
    }
}