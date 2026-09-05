import React, { useState } from 'react';
import { 
  CheckCircle2, 
  Clock, 
  Database, 
  FileCode, 
  Table2, 
  RotateCw, 
  Copy, 
  Check, 
  ExternalLink 
} from 'lucide-react';

export default function StatBar({ 
  currentApi, 
  data, 
  responseTime, 
  loading, 
  viewMode, 
  onToggleView, 
  onRefresh 
}) {
  const [copied, setCopied] = useState(false);

  const recordCount = Array.isArray(data) ? data.length : 0;
  const fieldCount = Array.isArray(data) && data.length > 0 ? Object.keys(data[0]).length : 10;

  const handleCopyJson = () => {
    if (!data) return;
    navigator.clipboard.writeText(JSON.stringify(data, null, 2));
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  return (
    <div className="statbar-card">
      <div className="statbar-left">
        {/* Endpoint Pill */}
        <div className="endpoint-pill">
          <span className="http-method">GET</span>
          <span className="endpoint-url">http://localhost:5000{currentApi.path}</span>
          <a 
            href={`http://localhost:5000${currentApi.path}`} 
            target="_blank" 
            rel="noreferrer"
            className="external-link-btn"
            title="Abrir en pestaña nueva del navegador"
          >
            <ExternalLink size={14} />
          </a>
        </div>

        {/* Metrics */}
        <div className="metrics-group">
          <div className="metric-chip success">
            <CheckCircle2 size={14} />
            <span>200 OK</span>
          </div>

          <div className="metric-chip">
            <Clock size={14} />
            <span>{responseTime !== null ? `${responseTime} ms` : '--'}</span>
          </div>

          <div className="metric-chip">
            <Database size={14} />
            <span>{recordCount} Registros</span>
          </div>

          <div className="metric-chip highlight">
            <span className="count-badge">{fieldCount}</span>
            <span>Campos / Fila</span>
          </div>
        </div>
      </div>

      {/* Control Actions (View toggle, Refresh, Copy) */}
      <div className="statbar-actions">
        <div className="view-toggle-group">
          <button 
            id="view-toggle-table"
            className={`toggle-btn ${viewMode === 'table' ? 'active' : ''}`}
            onClick={() => onToggleView('table')}
            title="Vista de Tabla Dinámica"
          >
            <Table2 size={15} />
            <span>Tabla</span>
          </button>
          <button 
            id="view-toggle-json"
            className={`toggle-btn ${viewMode === 'json' ? 'active' : ''}`}
            onClick={() => onToggleView('json')}
            title="Vista de JSON Crudo"
          >
            <FileCode size={15} />
            <span>JSON</span>
          </button>
        </div>

        <button 
          id="btn-copy-json"
          className="action-btn"
          onClick={handleCopyJson}
          disabled={!data || loading}
          title="Copiar JSON al portapapeles"
        >
          {copied ? <Check size={16} className="text-success" /> : <Copy size={16} />}
          <span>{copied ? 'Copiado' : 'Copiar'}</span>
        </button>

        <button 
          id="btn-refresh-api"
          className={`action-btn primary ${loading ? 'loading' : ''}`}
          onClick={onRefresh}
          disabled={loading}
          title="Reconsultar API"
        >
          <RotateCw size={16} className={loading ? 'spin' : ''} />
          <span>Refrescar</span>
        </button>
      </div>
    </div>
  );
}
