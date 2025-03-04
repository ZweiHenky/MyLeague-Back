require('dotenv').config();

const express = require('express')
const UserRoutes = require('./routes/UserRoutes');
const AuthRoutes = require('./routes/AuthRoutes');
const errorMiddleware = require('./middlewares/errorMiddleware');


const app = express()
const port = 3000

app.use(express.json());

// Rutas
app.use('/api/users', UserRoutes);
app.use('/api/auth', AuthRoutes);

// Middleware de errores
app.use(errorMiddleware.errorHandler);

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})