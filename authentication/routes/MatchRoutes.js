const express = require("express")
const { UpdateMatch } = require("../controllers/MatchController")

const router = express.Router()

router.put("/match/:idMatch", UpdateMatch)

module.exports = router