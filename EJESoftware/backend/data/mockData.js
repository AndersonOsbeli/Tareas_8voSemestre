// Datos simulados para las 10 APIs
// Cada API cuenta con exactamente 10 campos por registro

const api1_clientes = [
  {
    id: 1,
    nombre: "Carlos Mendoza",
    edad: 34,
    correo: "carlos.mendoza@email.com",
    direccion: "Av. Reforma 1234",
    telefono: "+52 55 1234 5678",
    pais: "México",
    ciudad: "Ciudad de México",
    estado: "CDMX",
    codigo_postal: "06500"
  },
  {
    id: 2,
    nombre: "María Elena Gómez",
    edad: 29,
    correo: "maria.gomez@email.com",
    direccion: "Calle 50 #12-45",
    telefono: "+57 1 765 4321",
    pais: "Colombia",
    ciudad: "Bogotá",
    estado: "Cundinamarca",
    codigo_postal: "110111"
  },
  {
    id: 3,
    nombre: "Javier Hernández",
    edad: 42,
    correo: "j.hernandez@email.com",
    direccion: "Gran Vía 88, 3°B",
    telefono: "+34 91 555 0192",
    pais: "España",
    ciudad: "Madrid",
    estado: "Comunidad de Madrid",
    codigo_postal: "28013"
  },
  {
    id: 4,
    nombre: "Lucía Morales",
    edad: 25,
    correo: "lucia.morales@email.com",
    direccion: "Av. Corrientes 2450",
    telefono: "+54 11 4433 2211",
    pais: "Argentina",
    ciudad: "Buenos Aires",
    estado: "CABA",
    codigo_postal: "C1046"
  },
  {
    id: 5,
    nombre: "Roberto Silva",
    edad: 38,
    correo: "roberto.silva@email.com",
    direccion: "Rua Augusta 450",
    telefono: "+55 11 9876 5432",
    pais: "Brasil",
    ciudad: "São Paulo",
    estado: "SP",
    codigo_postal: "01305-000"
  },
  {
    id: 6,
    nombre: "Fernanda Castillo",
    edad: 31,
    correo: "fer.castillo@email.com",
    direccion: "Av. Providencia 1020",
    telefono: "+56 2 2345 6789",
    pais: "Chile",
    ciudad: "Santiago",
    estado: "Región Metropolitana",
    codigo_postal: "7500000"
  }
];

const api2_productos = [
  {
    id: 101,
    nombre_producto: "Laptop ThinkPad X1 Carbon",
    categoria: "Computación",
    precio: 1499.99,
    stock: 24,
    sku: "LPT-X1C-G10",
    proveedor: "Lenovo Corp",
    marca: "ThinkPad",
    garantia: "36 meses",
    estado: "Disponible"
  },
  {
    id: 102,
    nombre_producto: "Monitor Curvo UltraWide 34\"",
    categoria: "Periféricos",
    precio: 589.50,
    stock: 15,
    sku: "MON-LG34-CURV",
    proveedor: "LG Electronics",
    marca: "UltraGear",
    garantia: "24 meses",
    estado: "Disponible"
  },
  {
    id: 103,
    nombre_producto: "Teclado Mecánico RGB Pro",
    categoria: "Accesorios",
    precio: 129.99,
    stock: 45,
    sku: "TEC-MEC-RGB90",
    proveedor: "Logitech",
    marca: "Logitech G",
    garantia: "12 meses",
    estado: "Disponible"
  },
  {
    id: 104,
    nombre_producto: "Mouse Inalámbrico Master 3S",
    categoria: "Accesorios",
    precio: 99.00,
    stock: 60,
    sku: "MOU-LOGI-MX3S",
    proveedor: "Logitech",
    marca: "MX Master",
    garantia: "12 meses",
    estado: "Disponible"
  },
  {
    id: 105,
    nombre_producto: "Disco SSD NVMe 2TB Gen4",
    categoria: "Almacenamiento",
    precio: 179.90,
    stock: 8,
    sku: "SSD-SAM-990P2",
    proveedor: "Samsung",
    marca: "990 Pro",
    garantia: "60 meses",
    estado: "Stock Bajo"
  },
  {
    id: 106,
    nombre_producto: "Auriculares Noise Cancelling 700",
    categoria: "Audio",
    precio: 349.00,
    stock: 0,
    sku: "AUD-BOSE-NC700",
    proveedor: "Bose Corporation",
    marca: "Bose",
    garantia: "12 meses",
    estado: "Agotado"
  }
];

const api3_empleados = [
  {
    id: 201,
    nombre_completo: "Alejandro Ruiz Torres",
    puesto: "Arquitecto de Software",
    departamento: "Tecnología",
    salario: 4800.00,
    fecha_ingreso: "2021-03-15",
    turno: "Matutino",
    email_corporativo: "aruiz@enterprise.com",
    extension: "4102",
    sede: "Torre Central CDMX"
  },
  {
    id: 202,
    nombre_completo: "Valeria Sofía Paz",
    puesto: "Líder de Producto (PO)",
    departamento: "Innovación y Producto",
    salario: 4200.00,
    fecha_ingreso: "2022-07-01",
    turno: "Matutino",
    email_corporativo: "vpaz@enterprise.com",
    extension: "4108",
    sede: "Campus Guadalajara"
  },
  {
    id: 203,
    nombre_completo: "Daniel Ortega Meza",
    puesto: "Ingeniero DevOps Senior",
    departamento: "Infraestructura Cloud",
    salario: 4500.00,
    fecha_ingreso: "2020-11-20",
    turno: "Rotativo",
    email_corporativo: "dortega@enterprise.com",
    extension: "4125",
    sede: "Remoto / Global"
  },
  {
    id: 204,
    nombre_completo: "Mariana Luna Rojas",
    puesto: "Diseñadora UX/UI Lead",
    departamento: "Experiencia de Usuario",
    salario: 3700.00,
    fecha_ingreso: "2023-02-10",
    turno: "Matutino",
    email_corporativo: "mluna@enterprise.com",
    extension: "4133",
    sede: "Torre Central CDMX"
  },
  {
    id: 205,
    nombre_completo: "Héctor Silva Benítez",
    puesto: "Gerente de Finanzas",
    departamento: "Contabilidad y Finanzas",
    salario: 5200.00,
    fecha_ingreso: "2019-05-18",
    turno: "Matutino",
    email_corporativo: "hsilva@enterprise.com",
    extension: "4150",
    sede: "Campus Monterrey"
  }
];

const api4_estudiantes = [
  {
    id: 301,
    matricula: "EST-2022-0841",
    nombre: "Gabriel Montesinos",
    carrera: "Ingeniería en Software",
    semestre: 8,
    promedio: 9.4,
    creditos: 210,
    tutor: "Dr. Ramón Valdez",
    facultad: "Facultad de Ingeniería y Ciencias",
    estado_academico: "Regular"
  },
  {
    id: 302,
    matricula: "EST-2023-0199",
    nombre: "Andrea Pamela Reyes",
    carrera: "Ciencia de Datos",
    semestre: 6,
    promedio: 9.8,
    creditos: 175,
    tutor: "Dra. Claudia Domínguez",
    facultad: "Facultad de Matemáticas",
    estado_academico: "Honorífico"
  },
  {
    id: 303,
    matricula: "EST-2021-1120",
    nombre: "Kevin Arturo Soto",
    carrera: "Ciberseguridad",
    semestre: 9,
    promedio: 8.7,
    creditos: 235,
    tutor: "Mtro. Fernando Castillo",
    facultad: "Facultad de Ingeniería y Ciencias",
    estado_academico: "Regular"
  },
  {
    id: 304,
    matricula: "EST-2024-0045",
    nombre: "Camila Duarte Cruz",
    carrera: "Inteligencia Artificial",
    semestre: 4,
    promedio: 9.2,
    creditos: 110,
    tutor: "Dr. Ernesto Sotomayor",
    facultad: "Centro de Investigación Tecnológica",
    estado_academico: "Regular"
  },
  {
    id: 305,
    matricula: "EST-2022-0678",
    nombre: "Samuel Bravo Garza",
    carrera: "Sistemas Computacionales",
    semestre: 7,
    promedio: 7.9,
    creditos: 180,
    tutor: "Ing. Guillermo Pérez",
    facultad: "Facultad de Ingeniería y Ciencias",
    estado_academico: "En Condicional"
  }
];

const api5_vehiculos = [
  {
    id: 401,
    placa: "XYZ-789-A",
    marca: "Toyota",
    modelo: "Hilux Doble Cabina 4x4",
    anio: 2023,
    color: "Blanco Glaciar",
    kilometraje: 32500,
    tipo_combustible: "Diésel",
    estado_mantenimiento: "Excelente",
    seguro: "Póliza Amplia GNP #98231"
  },
  {
    id: 402,
    placa: "ABC-123-K",
    marca: "Tesla",
    modelo: "Model Y Long Range",
    anio: 2024,
    color: "Gris Medianoche",
    kilometraje: 14200,
    tipo_combustible: "100% Eléctrico",
    estado_mantenimiento: "Excelente",
    seguro: "Póliza Premium AXA #77124"
  },
  {
    id: 403,
    placa: "MNP-456-D",
    marca: "Ford",
    modelo: "Transit Van Carga",
    anio: 2022,
    color: "Azul Marino",
    kilometraje: 68900,
    tipo_combustible: "Gasolina",
    estado_mantenimiento: "Servicio Pendiente",
    seguro: "Póliza Comercial Qualitas #55412"
  },
  {
    id: 404,
    placa: "JHG-998-C",
    marca: "Nissan",
    modelo: "Versa Exclusive CVT",
    anio: 2021,
    color: "Plata Brillante",
    kilometraje: 84300,
    tipo_combustible: "Gasolina",
    estado_mantenimiento: "Bueno",
    seguro: "Póliza Amplia Mapfre #33890"
  },
  {
    id: 405,
    placa: "LKM-332-P",
    marca: "Volvo",
    modelo: "XC60 Recharge Híbrido",
    anio: 2024,
    color: "Negro Ónix",
    kilometraje: 9800,
    tipo_combustible: "Híbrido Enchufable",
    estado_mantenimiento: "Nuevo",
    seguro: "Póliza Vip Zurich #11459"
  }
];

const api6_pedidos = [
  {
    id: 501,
    codigo_orden: "ORD-2025-901",
    cliente: "Guillermo Navarro",
    total: 2450.75,
    metodo_pago: "Tarjeta de Crédito (Visa)",
    fecha_compra: "2025-05-14 14:32:00",
    estado_envio: "Entregado",
    repartidor: "DHL Express",
    articulos_totales: 4,
    descuento: "10% Cupón BIENVENIDA"
  },
  {
    id: 502,
    codigo_orden: "ORD-2025-902",
    cliente: "Paola Villarreal",
    total: 780.00,
    metodo_pago: "PayPal",
    fecha_compra: "2025-05-15 09:15:20",
    estado_envio: "En Tránsito",
    repartidor: "FedEx Logística",
    articulos_totales: 2,
    descuento: "Sin descuento"
  },
  {
    id: 503,
    codigo_orden: "ORD-2025-903",
    cliente: "Esteban Quiroga",
    total: 3600.00,
    metodo_pago: "Transferencia SPEI",
    fecha_compra: "2025-05-15 11:40:10",
    estado_envio: "Preparando en Almacén",
    repartidor: "Estafeta Cargo",
    articulos_totales: 6,
    descuento: "15% Mayorista"
  },
  {
    id: 504,
    codigo_orden: "ORD-2025-904",
    cliente: "Renata Cárdenas",
    total: 420.50,
    metodo_pago: "Apple Pay",
    fecha_compra: "2025-05-15 16:02:45",
    estado_envio: "Recolectado",
    repartidor: "Uber Direct",
    articulos_totales: 1,
    descuento: "Envío Gratis"
  },
  {
    id: 505,
    codigo_orden: "ORD-2025-905",
    cliente: "Rodrigo Albarrán",
    total: 1890.20,
    metodo_pago: "Mastercard Débito",
    fecha_compra: "2025-05-15 18:25:33",
    estado_envio: "Pendiente de Pago",
    repartidor: "Por Asignar",
    articulos_totales: 3,
    descuento: "Sin descuento"
  }
];

const api7_facturas = [
  {
    id: 601,
    numero_factura: "FAC-2025-00104",
    rfc_nit: "CORP880214KJ3",
    razon_social: "Soluciones Digitales Globales S.A. de C.V.",
    subtotal: 12500.00,
    iva: 2000.00,
    total: 14500.00,
    fecha_emision: "2025-05-01",
    estado_pago: "Pagada",
    metodo_facturacion: "CFDI 4.0 Ingreso"
  },
  {
    id: 602,
    numero_factura: "FAC-2025-00105",
    rfc_nit: "LOGI950628MN1",
    razon_social: "Transportes y Logística Frontera S. de R.L.",
    subtotal: 8400.00,
    iva: 1344.00,
    total: 9744.00,
    fecha_emision: "2025-05-03",
    estado_pago: "Pagada",
    metodo_facturacion: "CFDI 4.0 Ingreso"
  },
  {
    id: 603,
    numero_factura: "FAC-2025-00106",
    rfc_nit: "RETA010319H78",
    razon_social: "Comercializadora Retail Nacional S.A.",
    subtotal: 31200.00,
    iva: 4992.00,
    total: 36192.00,
    fecha_emision: "2025-05-08",
    estado_pago: "Pendiente",
    metodo_facturacion: "CFDI 4.0 Crédito 30 días"
  },
  {
    id: 604,
    numero_factura: "FAC-2025-00107",
    rfc_nit: "CONS120711AA9",
    razon_social: "Consultores y Auditores Asociados SC",
    subtotal: 5600.00,
    iva: 896.00,
    total: 6496.00,
    fecha_emision: "2025-05-12",
    estado_pago: "Cancelada",
    metodo_facturacion: "CFDI 4.0 Anulado"
  },
  {
    id: 605,
    numero_factura: "FAC-2025-00108",
    rfc_nit: "AGRO831005TT2",
    razon_social: "Agroindustrias del Norte S.A.P.I.",
    subtotal: 19800.00,
    iva: 3168.00,
    total: 22968.00,
    fecha_emision: "2025-05-14",
    estado_pago: "Pagada",
    metodo_facturacion: "CFDI 4.0 Transferencia"
  }
];

const api8_cursos = [
  {
    id: 701,
    titulo_curso: "Master en Arquitectura de Microservicios con Node.js",
    instructor: "Ing. Miguel Ángel Durán",
    duracion_horas: 45,
    nivel: "Avanzado",
    precio: 89.99,
    inscritos: 1840,
    calificacion: 4.9,
    idioma: "Español",
    certificado: "Verificado Blockchain"
  },
  {
    id: 702,
    titulo_curso: "Desarrollo Frontend Profesional con React 19 y Next.js",
    instructor: "Lic. Miriam Santiago",
    duracion_horas: 60,
    nivel: "Intermedio",
    precio: 79.50,
    inscritos: 3200,
    calificacion: 4.8,
    idioma: "Español",
    certificado: "Digital Oficial"
  },
  {
    id: 703,
    titulo_curso: "Machine Learning e Inteligencia Artificial en Python",
    instructor: "Dr. Andrew K. Smith",
    duracion_horas: 80,
    nivel: "Avanzado",
    precio: 120.00,
    inscritos: 4550,
    calificacion: 4.95,
    idioma: "Inglés con subtítulos",
    certificado: "Acreditado Institucional"
  },
  {
    id: 704,
    titulo_curso: "Fundamentos de Ciberseguridad y Ethical Hacking",
    instructor: "David Alonso",
    duracion_horas: 35,
    nivel: "Principiante a Intermedio",
    precio: 49.99,
    inscritos: 2150,
    calificacion: 4.7,
    idioma: "Español",
    certificado: "Digital Oficial"
  },
  {
    id: 705,
    titulo_curso: "Diseño de Sistemas Distribuidos y Alta Disponibilidad",
    instructor: "Ing. Sandra Riquelme",
    duracion_horas: 50,
    nivel: "Experto",
    precio: 99.00,
    inscritos: 980,
    calificacion: 4.85,
    idioma: "Español",
    certificado: "Verificado Blockchain"
  }
];

const api9_dispositivos = [
  {
    id: 801,
    hostname: "prod-db-cluster-primary",
    ip_address: "10.0.1.50",
    mac_address: "00:1A:2B:3C:4D:5E",
    sistema_operativo: "Ubuntu Server 24.04 LTS",
    cpu_cores: 32,
    ram_gb: 128,
    almacenamiento_gb: 4000,
    uptime_dias: 245,
    estado_red: "Operativo / Alta Carga"
  },
  {
    id: 802,
    hostname: "k8s-master-node-01",
    ip_address: "10.0.2.10",
    mac_address: "00:1A:2B:6F:8G:9H",
    sistema_operativo: "Red Hat Enterprise Linux 9",
    cpu_cores: 16,
    ram_gb: 64,
    almacenamiento_gb: 1000,
    uptime_dias: 180,
    estado_red: "Operativo"
  },
  {
    id: 803,
    hostname: "edge-gateway-router-mx",
    ip_address: "192.168.100.1",
    mac_address: "1C:B7:2C:90:11:AA",
    sistema_operativo: "Cisco IOS-XE 17.6",
    cpu_cores: 8,
    ram_gb: 16,
    almacenamiento_gb: 128,
    uptime_dias: 412,
    estado_red: "Operativo"
  },
  {
    id: 804,
    hostname: "backup-vault-s3-sync",
    ip_address: "10.0.5.99",
    mac_address: "44:39:C4:88:BB:CC",
    sistema_operativo: "Debian 12 Bookworm",
    cpu_cores: 12,
    ram_gb: 32,
    almacenamiento_gb: 16000,
    uptime_dias: 92,
    estado_red: "Sincronizando"
  },
  {
    id: 805,
    hostname: "firewall-paloalto-ha",
    ip_address: "10.0.0.1",
    mac_address: "AA:BB:CC:DD:EE:FF",
    sistema_operativo: "PAN-OS 11.1",
    cpu_cores: 16,
    ram_gb: 32,
    almacenamiento_gb: 512,
    uptime_dias: 300,
    estado_red: "Operativo / Blindado"
  }
];

const api10_eventos = [
  {
    id: 901,
    nombre_evento: "Congreso Internacional de Ingeniería de Software 2025",
    organizador: "IEEE Computer Society",
    fecha: "2025-10-15 al 2025-10-18",
    ubicacion: "Centro de Convenciones Santa Fe, CDMX",
    capacidad: 2500,
    boletos_vendidos: 2180,
    tipo_evento: "Híbrido (Presencial + Streaming)",
    estado: "Inscripciones Abiertas",
    patrocinador_principal: "Microsoft Azure"
  },
  {
    id: 902,
    nombre_evento: "Data Science & AI Summit Latam",
    organizador: "Comunidad Iberoamericana de IA",
    fecha: "2025-11-05 al 2025-11-07",
    ubicacion: "Hotel Tequendama, Bogotá, Colombia",
    capacidad: 1800,
    boletos_vendidos: 1650,
    tipo_evento: "Presencial",
    estado: "Últimos Cupos",
    patrocinador_principal: "Google Cloud"
  },
  {
    id: 903,
    nombre_evento: "Global CyberSec Expo 2025",
    organizador: "Open Web Application Security Project (OWASP)",
    fecha: "2025-11-20 al 2025-11-22",
    ubicacion: "Palacio Municipal de Congresos, Madrid, España",
    capacidad: 3200,
    boletos_vendidos: 3200,
    tipo_evento: "Presencial",
    estado: "Agotado",
    patrocinador_principal: "CrowdStrike"
  },
  {
    id: 904,
    nombre_evento: "Hackathon Universitario InnovaTech",
    organizador: "Red de Universidades Tecnológicas",
    fecha: "2025-12-01 al 2025-12-03",
    ubicacion: "Campus Tecnológico de Innovación, Monterrey",
    capacidad: 600,
    boletos_vendidos: 580,
    tipo_evento: "Presencial intensivo 48h",
    estado: "Equipos Confirmados",
    patrocinador_principal: "Amazon Web Services"
  },
  {
    id: 905,
    nombre_evento: "DevOps & Cloud Native Virtual Conf",
    organizador: "Cloud Native Computing Foundation (CNCF)",
    fecha: "2025-12-10",
    ubicacion: "Plataforma Virtual Global",
    capacidad: 10000,
    boletos_vendidos: 8430,
    tipo_evento: "100% Online",
    estado: "Inscripciones Abiertas",
    patrocinador_principal: "Red Hat & IBM"
  }
];

// Metadatos descriptivos de cada API para visualización enriquecida en la interfaz
const apiMetadata = [
  { id: 1, path: "/api1", title: "API 1: Clientes y Usuarios", desc: "Datos de contacto, dirección y demografía de usuarios", icon: "Users" },
  { id: 2, path: "/api2", title: "API 2: Catálogo de Productos", desc: "Inventario de productos tecnológicos, precios y stock", icon: "Package" },
  { id: 3, path: "/api3", title: "API 3: Empleados y Talento", desc: "Nómina, cargos, extensiones y departamentos de la empresa", icon: "Briefcase" },
  { id: 4, path: "/api4", title: "API 4: Estudiantes Universitarios", desc: "Matrículas, facultades, promedios y carreras académicas", icon: "GraduationCap" },
  { id: 5, path: "/api5", title: "API 5: Flota de Vehículos", desc: "Vehículos corporativos, placas, kilometraje y pólizas", icon: "Car" },
  { id: 6, path: "/api6", title: "API 6: Pedidos y Ventas", desc: "Órdenes de compra en línea, métodos de pago y despacho", icon: "ShoppingCart" },
  { id: 7, path: "/api7", title: "API 7: Facturación Electrónica", desc: "Comprobantes fiscales, impuestos, totales y estados", icon: "Receipt" },
  { id: 8, path: "/api8", title: "API 8: Cursos y Capacitación", desc: "Oferta académica virtual, instructores, horas y reseñas", icon: "BookOpen" },
  { id: 9, path: "/api9", title: "API 9: Infraestructura TI & Servidores", desc: "Nodos de cómputo, direcciones IP, hardware y monitoreo", icon: "Server" },
  { id: 10, path: "/api10", title: "API 10: Eventos y Conferencias", desc: "Congresos tecnológicos, fechas, aforos y patrocinios", icon: "Calendar" }
];

module.exports = {
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
};
