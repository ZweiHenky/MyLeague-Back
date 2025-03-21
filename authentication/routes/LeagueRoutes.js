const express = require("express")
const { LeagueByName } = require("../controllers/LeagueController")

const router = express.Router()

router.get("/search", LeagueByName)

module.exports = router