const Joi = require("joi");

const UserSchema = Joi.object({
    usu_name: Joi.string().empty().required().max(70),
    usu_last: Joi.string().empty().required().max(70),
    usu_email: Joi.string().empty().required().email().max(70),
    usu_tel: Joi.string().optional().length(10),
    usu_pass: Joi.string().empty().required().min(6)
});

const LoginSchema = Joi.object({
    usu_email: Joi.string().empty().required().email().max(70),
    usu_pass: Joi.string().empty().required().min(6)
});

module.exports = { UserSchema, LoginSchema };
