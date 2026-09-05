const express = require('express');
const cors = require('cors');
const {
  api1_clientes,
  api2_productos,
  api3_empleados,
  api4_estudiantes,
  api5_vehiculos,
  api6_pedidos,
  api7_facturas,
  api8_cursos,
  api9_dispositivos,
  api10_eventos,
  apiMetadata
} = require('./data/mockData');

const app = express();
const PORT = process.env.PORT || 5000;

// Habilitar CORS para permitir peticiones desde el frontend (ej. Vite en puerto 5173)
app.use(cors());
app.use(express.json());

// Logger simple para peticiones entrantes
app.use((req, res, next) => {
  const timestamp = new Date().toISOString();
  console.log(`[${timestamp}] ${req.method} ${req.url}`);
  next();
});

// Ruta raíz informativa
app.get('/', (req, res) => {
  res.json({
    mensaje: "🚀 Servidor de 10 APIs funcionando correctamente",
    total_apis: 10,
    campos_por_registro: 10,
    endpoints_disponibles: apiMetadata.map(meta => ({
      numero: meta.id,
      nombre: meta.title,
      ruta: `http://localhost:${PORT}${meta.path}`,
      descripcion: meta.desc
    }))
  });
});

// Metadatos de las 10 APIs para alimentar el menú dinámico del frontend
app.get('/api/metadata', (req, res) => {
  res.json(apiMetadata);
});

// 1. API 1 - Clientes / Usuarios
app.get('/api1', (req, res) => {
  res.json(api1_clientes);
});

// 2. API 2 - Catálogo de Productos
app.get('/api2', (req, res) => {
  res.json(api2_productos);
});

// 3. API 3 - Empleados y Talento Humano
app.get('/api3', (req, res) => {
  res.json(api3_empleados);
});

// 4. API 4 - Estudiantes Universitarios
app.get('/api4', (req, res) => {
  res.json(api4_estudiantes);
});

// 5. API 5 - Flota de Vehículos
app.get('/api5', (req, res) => {
  res.json(api5_vehiculos);
});

// 6. API 6 - Pedidos y Órdenes
app.get('/api6', (req, res) => {
  res.json(api6_pedidos);
});

// 7. API 7 - Facturación Electrónica
app.get('/api7', (req, res) => {
  res.json(api7_facturas);
});

// 8. API 8 - Catálogo de Cursos Online
app.get('/api8', (req, res) => {
  res.json(api8_cursos);
});

// 9. API 9 - Infraestructura TI & Servidores
app.get('/api9', (req, res) => {
  res.json(api9_dispositivos);
});

// 10. API 10 - Eventos y Conferencias
app.get('/api10', (req, res) => {
  res.json(api10_eventos);
});

// Manejador de rutas no encontradas
app.use((req, res) => {
  res.status(404).json({
    error: "Ruta no encontrada",
    rutas_validas: ["/api1", "/api2", "/api3", "/api4", "/api5", "/api6", "/api7", "/api8", "/api9", "/api10"]
  });
});

app.listen(PORT, () => {
  console.log(`=========================================`);
  console.log(`✅ Servidor Express activo en puerto ${PORT}`);
  console.log(`📡 URL base: http://localhost:${PORT}`);
  console.log(`📋 10 APIs disponibles desde /api1 hasta /api10`);
  console.log(`=========================================`);
});
