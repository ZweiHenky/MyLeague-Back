const UserRepository = require("../repositories/UserRepository");
const UserSchema = require("../schemas/UserSchema");

exports.createUser = async (data) =>{

    const {error, value} = UserSchema.validate(data)

    if (error) {
        throw new Error( error.details[0].message)
    }
    
     await UserRepository.create(value)
}