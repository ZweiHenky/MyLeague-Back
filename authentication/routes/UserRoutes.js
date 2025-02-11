// src/routes/leagueRoutes.js
const express = require('express');
const UserController = require('../controllers/UserController');

const router = express.Router();

// Rutas protegidas con middleware
router.post('/', UserController.createUser);

module.exports = router;
