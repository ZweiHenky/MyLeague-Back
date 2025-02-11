// src/middlewares/errorMiddleware.js
exports.errorHandler = (err, req, res, next) => {
    console.error(err);

    if (err.message) {
      return res.status(400).json({ message: err.message});
    }

    res.status(500).json({ message: 'Error interno del servidor' });
  };
  