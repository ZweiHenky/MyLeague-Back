const Joi = require("joi");

const UserSchema = Joi.object({
    nombre: Joi.string().empty().required().max(70),
    apellido: Joi.string().empty().required().max(70),
    email: Joi.string().empty().required().email().max(70),
    telefono: Joi.string().optional().length(10),
    password: Joi.string().empty().required().min(6)
});

const LoginSchema = Joi.object({
    email: Joi.string().empty().required().email().max(70),
    password: Joi.string().empty().required().min(6)
});

module.exports = { UserSchema, LoginSchema };
