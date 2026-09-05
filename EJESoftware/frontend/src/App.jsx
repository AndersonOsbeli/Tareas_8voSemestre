import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import { Menu, X, Globe, Radio, Sparkles } from 'lucide-react';
import Sidebar from './components/Sidebar';
import StatBar from './components/StatBar';
import DataTable from './components/DataTable';
import JsonViewer from './components/JsonViewer';
import './App.css';

const API_BASE_URL = 'http://localhost:5000';

const APIS_LIST = [
  { id: 1, path: '/api1', name: 'Clientes y Usuarios', subtitle: 'Registros de usuarios con demografía y ubicación' },
  { id: 2, path: '/api2', name: 'Productos e Inventario', subtitle: 'Catálogo de hardware, precios, stock y garantías' },
  { id: 3, path: '/api3', name: 'Empleados y Nómina', subtitle: 'Cargos corporativos, departamentos y salarios' },
  { id: 4, path: '/api4', name: 'Estudiantes Universitarios', subtitle: 'Matrículas, carreras, facultades y promedios' },
  { id: 5, path: '/api5', name: 'Flota de Vehículos', subtitle: 'Vehículos de empresa, kilometraje y pólizas' },
  { id: 6, path: '/api6', name: 'Pedidos y Despacho', subtitle: 'Órdenes de compra online y estado logístico' },
  { id: 7, path: '/api7', name: 'Facturación Electrónica', subtitle: 'Comprobantes fiscales, impuestos y estados de pago' },
  { id: 8, path: '/api8', name: 'Catálogo de Cursos', subtitle: 'Formación profesional online, horas y valoraciones' },
  { id: 9, path: '/api9', name: 'Infraestructura TI', subtitle: 'Servidores cloud, IPs, hardware y monitoreo de red' },
  { id: 10, path: '/api10', name: 'Eventos y Conferencias', subtitle: 'Congresos de tecnología, capacidad y boletaje' }
];

export default function App() {
  const [selectedApiId, setSelectedApiId] = useState(1);
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [responseTime, setResponseTime] = useState(null);
  const [isServerOnline, setIsServerOnline] = useState(true);
  const [viewMode, setViewMode] = useState('table'); // 'table' | 'json'
  const [clientMode, setClientMode] = useState('axios'); // 'axios' | 'fetch'
  const [isMobileOpen, setIsMobileOpen] = useState(false);

  const currentApi = APIS_LIST.find(a => a.id === selectedApiId) || APIS_LIST[0];

  // Función para consultar la API seleccionada usando Axios o Fetch
  const fetchData = useCallback(async (apiId, client = clientMode) => {
    setLoading(true);
    setError(null);
    const target = APIS_LIST.find(a => a.id === apiId) || APIS_LIST[0];
    const url = `${API_BASE_URL}${target.path}`;
    const startTime = performance.now();

    try {
      let resultData;

      if (client === 'axios') {
        const response = await axios.get(url, { timeout: 8000 });
        resultData = response.data;
      } else {
        const res = await fetch(url);
        if (!res.ok) {
          throw new Error(`Error HTTP: ${res.status} ${res.statusText}`);
        }
        resultData = await res.json();
      }

      const endTime = performance.now();
      setResponseTime(Math.round(endTime - startTime));
      setData(resultData);
      setIsServerOnline(true);
    } catch (err) {
      console.error(`Error al consumir ${url}:`, err);
      const endTime = performance.now();
      setResponseTime(Math.round(endTime - startTime));
      setError(
        err.response?.data?.error || 
        err.message || 
        'No fue posible conectar con el servidor Express en el puerto 5000. Asegúrate de que esté iniciado.'
      );
      setIsServerOnline(false);
      setData(null);
    } finally {
      setLoading(false);
    }
  }, [clientMode]);

  // Consumir API al cambiar de opción o de cliente
  useEffect(() => {
    fetchData(selectedApiId, clientMode);
  }, [selectedApiId, clientMode, fetchData]);

  return (
    <div className="app-layout">
      {/* Mobile Top Header */}
      <header className="mobile-top-bar">
        <button 
          id="btn-mobile-menu"
          className="mobile-menu-toggle"
          onClick={() => setIsMobileOpen(!isMobileOpen)}
          aria-label="Abrir Menú de APIs"
        >
          {isMobileOpen ? <X size={22} /> : <Menu size={22} />}
        </button>
        <div className="mobile-title">
          <span className="api-chip-sm">API {currentApi.id}</span>
          <h1>{currentApi.name}</h1>
        </div>
      </header>

      {/* Sidebar con las 10 opciones de APIs */}
      <Sidebar 
        apis={APIS_LIST}
        selectedApiId={selectedApiId}
        onSelectApi={(id) => setSelectedApiId(id)}
        isServerOnline={isServerOnline}
        isMobileOpen={isMobileOpen}
        setIsMobileOpen={setIsMobileOpen}
      />

      {/* Main Content Area */}
      <main className="main-content">
        {/* Top Controls & Title Bar */}
        <div className="page-header">
          <div className="page-header-info">
            <div className="badge-row">
              <span className="api-tag-glow">
                <Sparkles size={13} />
                API 0{currentApi.id} de 10
              </span>
              <span className="protocol-badge">REST JSON</span>
            </div>
            <h1 className="page-title">{currentApi.name}</h1>
            <p className="page-subtitle">{currentApi.subtitle}</p>
          </div>

          {/* Client Selector (Axios vs Fetch) */}
          <div className="client-selector-card">
            <span className="client-selector-label">Cliente HTTP:</span>
            <div className="client-toggle">
              <button 
                id="select-client-axios"
                className={`client-btn ${clientMode === 'axios' ? 'active' : ''}`}
                onClick={() => setClientMode('axios')}
                title="Consumir usando librería Axios"
              >
                <Radio size={12} />
                <span>Axios</span>
              </button>
              <button 
                id="select-client-fetch"
                className={`client-btn ${clientMode === 'fetch' ? 'active' : ''}`}
                onClick={() => setClientMode('fetch')}
                title="Consumir usando Fetch API nativo"
              >
                <Globe size={12} />
                <span>Fetch API</span>
              </button>
            </div>
          </div>
        </div>

        {/* Live Metrics & Action Bar */}
        <StatBar 
          currentApi={currentApi}
          data={data}
          responseTime={responseTime}
          loading={loading}
          viewMode={viewMode}
          onToggleView={(mode) => setViewMode(mode)}
          onRefresh={() => fetchData(selectedApiId, clientMode)}
        />

        {/* Content Container (Table or Raw JSON) */}
        <div className="content-display-area">
          {viewMode === 'table' ? (
            <DataTable 
              data={data}
              loading={loading}
              error={error}
              currentApi={currentApi}
              onRetry={() => fetchData(selectedApiId, clientMode)}
            />
          ) : (
            <JsonViewer 
              data={data}
              currentApi={currentApi}
            />
          )}
        </div>
      </main>
    </div>
  );
}
