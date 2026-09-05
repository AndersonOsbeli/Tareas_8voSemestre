# Proyecto: 10 APIs en Express & Dashboard en React

Este proyecto implementa una solución completa Cliente-Servidor desarrollada para la materia de Ingeniería de Software:
- **Backend (Express)**: 10 APIs independientes (`/api1` a `/api10`) con exactamente 10 campos por registro cada una.
- **Frontend (React + Vite)**: Menú interactivo de 10 opciones, consumo dinámico con `fetch`/`axios`, tablas con búsqueda y ordenamiento, y visor de JSON.

---

## Requisitos Previos

- [Node.js](https://nodejs.org/) v18 o superior instalado.

---

## Instalación

En la raíz del proyecto, ejecuta el siguiente comando para instalar automáticamente todas las dependencias del proyecto raíz, del backend y del frontend:

```powershell
npm run install:all
```

---

## Ejecución

### Opción 1: Iniciar Todo en Simultáneo (Recomendado)

```powershell
npm run dev
```

Este comando utiliza `concurrently` para arrancar tanto el Backend como el Frontend al mismo tiempo con logs en colores distintivos:
- **Backend Express**: [http://localhost:5000](http://localhost:5000)
- **Frontend React**: [http://localhost:5173](http://localhost:5173)

---

### Opción 2: Iniciar en Terminales Separadas

**Terminal 1 (Backend):**
```powershell
cd backend
npm run dev
```

**Terminal 2 (Frontend):**
```powershell
cd frontend
npm run dev
```

---

## Catálogo de Rutas de las 10 APIs

Cada endpoint devuelve datos en formato JSON con **10 campos por registro**:

1. `GET http://localhost:5000/api1` - **Clientes y Usuarios**: `id`, `nombre`, `edad`, `correo`, `direccion`, `telefono`, `pais`, `ciudad`, `estado`, `codigo_postal`.
2. `GET http://localhost:5000/api2` - **Catálogo de Productos**: `id`, `nombre_producto`, `categoria`, `precio`, `stock`, `sku`, `proveedor`, `marca`, `garantia`, `estado`.
3. `GET http://localhost:5000/api3` - **Empleados y Nómina**: `id`, `nombre_completo`, `puesto`, `departamento`, `salario`, `fecha_ingreso`, `turno`, `email_corporativo`, `extension`, `sede`.
4. `GET http://localhost:5000/api4` - **Estudiantes Universitarios**: `id`, `matricula`, `nombre`, `carrera`, `semestre`, `promedio`, `creditos`, `tutor`, `facultad`, `estado_academico`.
5. `GET http://localhost:5000/api5` - **Flota de Vehículos**: `id`, `placa`, `marca`, `modelo`, `anio`, `color`, `kilometraje`, `tipo_combustible`, `estado_mantenimiento`, `seguro`.
6. `GET http://localhost:5000/api6` - **Pedidos y Ventas**: `id`, `codigo_orden`, `cliente`, `total`, `metodo_pago`, `fecha_compra`, `estado_envio`, `repartidor`, `articulos_totales`, `descuento`.
7. `GET http://localhost:5000/api7` - **Facturación Electrónica**: `id`, `numero_factura`, `rfc_nit`, `razon_social`, `subtotal`, `iva`, `total`, `fecha_emision`, `estado_pago`, `metodo_facturacion`.
8. `GET http://localhost:5000/api8` - **Catálogo de Cursos**: `id`, `titulo_curso`, `instructor`, `duracion_horas`, `nivel`, `precio`, `inscritos`, `calificacion`, `idioma`, `certificado`.
9. `GET http://localhost:5000/api9` - **Infraestructura TI & Servidores**: `id`, `hostname`, `ip_address`, `mac_address`, `sistema_operativo`, `cpu_cores`, `ram_gb`, `almacenamiento_gb`, `uptime_dias`, `estado_red`.
10. `GET http://localhost:5000/api10` - **Eventos y Conferencias**: `id`, `nombre_evento`, `organizador`, `fecha`, `ubicacion`, `capacidad`, `boletos_vendidos`, `tipo_evento`, `estado`, `patrocinador_principal`.

---

## Características de la Aplicación Frontend

- **Menú con 10 Opciones**: Navegación fluida entre los 10 endpoints con etiquetas e indicadores visuales.
- **Selector de Cliente HTTP**: Permite alternar en tiempo real entre peticiones mediante **Axios** o **Fetch API** nativo.
- **Visualización en Tabla**: Identifica automáticamente las 10 columnas (`C1` a `C10`), incluye ordenamiento al hacer clic en los encabezados y badges de formato (correos, monedas, códigos, estados).
- **Búsqueda en Vivo**: Filtra al instante filas que coincidan en cualquiera de los 10 campos.
- **Visor de JSON**: Permite alternar entre la vista de tabla y el JSON crudo con botón de copiado al portapapeles.
- **Métricas**: Visualiza el tiempo de respuesta en milisegundos, estado HTTP 200 OK y cantidad de registros.
