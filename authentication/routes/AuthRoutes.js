// src/routes/leagueRoutes.js
const express = require('express');
const {login, register,verifyToken, verifyRefresh} = require('../controllers/AuthController');

const router = express.Router();

// Rutas protegidas con middleware
router.post('/login', login);
router.post('/register', register);
router.get('/verifyToken', verifyToken);
router.post('/verifyRefresh', verifyRefresh);

module.exports = router;
