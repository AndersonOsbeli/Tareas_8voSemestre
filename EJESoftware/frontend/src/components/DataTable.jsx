import React, { useState, useMemo } from 'react';
import { 
  Search, 
  ArrowUpDown, 
  ArrowUp, 
  ArrowDown, 
  Mail, 
  CheckCircle, 
  AlertTriangle, 
  XCircle, 
  Info, 
  Hash,
  FilterX
} from 'lucide-react';

export default function DataTable({ 
  data, 
  loading, 
  error, 
  currentApi,
  onRetry 
}) {
  const [searchTerm, setSearchTerm] = useState('');
  const [sortColumn, setSortColumn] = useState(null);
  const [sortDirection, setSortDirection] = useState('asc');

  // Obtener los nombres de las 10 columnas dinámicamente del primer objeto
  const columns = useMemo(() => {
    if (!data || !Array.isArray(data) || data.length === 0) return [];
    return Object.keys(data[0]);
  }, [data]);

  // Filtrado de filas por término de búsqueda en cualquiera de los 10 campos
  const filteredData = useMemo(() => {
    if (!data || !Array.isArray(data)) return [];
    if (!searchTerm.trim()) return data;

    const term = searchTerm.toLowerCase();
    return data.filter(row => 
      Object.values(row).some(val => 
        String(val).toLowerCase().includes(term)
      )
    );
  }, [data, searchTerm]);

  // Ordenamiento de filas por columna seleccionada
  const sortedData = useMemo(() => {
    if (!sortColumn) return filteredData;

    return [...filteredData].sort((a, b) => {
      const valA = a[sortColumn];
      const valB = b[sortColumn];

      if (valA === valB) return 0;
      if (valA === null || valA === undefined) return 1;
      if (valB === null || valB === undefined) return -1;

      // Comparación numérica o textual
      const isNum = !isNaN(Number(valA)) && !isNaN(Number(valB));
      if (isNum) {
        return sortDirection === 'asc' 
          ? Number(valA) - Number(valB) 
          : Number(valB) - Number(valA);
      }

      const strA = String(valA).toLowerCase();
      const strB = String(valB).toLowerCase();
      return sortDirection === 'asc' 
        ? strA.localeCompare(strB) 
        : strB.localeCompare(strA);
    });
  }, [filteredData, sortColumn, sortDirection]);

  const handleSort = (col) => {
    if (sortColumn === col) {
      if (sortDirection === 'asc') {
        setSortDirection('desc');
      } else {
        setSortColumn(null);
        setSortDirection('asc');
      }
    } else {
      setSortColumn(col);
      setSortDirection('asc');
    }
  };

  // Formateo inteligente de valores de celda
  const renderCellValue = (key, value) => {
    if (value === null || value === undefined) {
      return <span className="cell-muted">—</span>;
    }

    const strVal = String(value);

    // ID o Código
    if (key.toLowerCase() === 'id' || key.toLowerCase().includes('matricula') || key.toLowerCase().includes('sku') || key.toLowerCase().includes('codigo') || key.toLowerCase().includes('placa')) {
      return (
        <span className="cell-code">
          <Hash size={12} className="cell-code-hash" />
          {strVal}
        </span>
      );
    }

    // Correo Electrónico
    if (key.toLowerCase().includes('correo') || key.toLowerCase().includes('email')) {
      return (
        <a href={`mailto:${strVal}`} className="cell-email" title={`Enviar correo a ${strVal}`}>
          <Mail size={13} />
          <span>{strVal}</span>
        </a>
      );
    }

    // Direcciones IP y MAC
    if (key.includes('ip_address') || key.includes('mac_address')) {
      return <span className="cell-tech-code">{strVal}</span>;
    }

    // Precios / Totales monetarios
    if (key.toLowerCase().includes('precio') || key.toLowerCase().includes('total') || key.toLowerCase().includes('salario') || key.toLowerCase().includes('subtotal') || key.toLowerCase().includes('iva')) {
      const num = Number(value);
      return (
        <span className="cell-currency">
          ${!isNaN(num) ? num.toLocaleString('es-MX', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) : strVal}
        </span>
      );
    }

    // Estados / Badges semánticos
    const lower = strVal.toLowerCase();
    if (
      lower.includes('disponible') || 
      lower.includes('regular') || 
      lower.includes('honorífico') || 
      lower.includes('excelente') || 
      lower.includes('entregado') || 
      lower.includes('pagada') || 
      lower.includes('operativo') || 
      lower.includes('abiertas')
    ) {
      return (
        <span className="status-badge success">
          <CheckCircle size={12} />
          <span>{strVal}</span>
        </span>
      );
    }

    if (
      lower.includes('tránsito') || 
      lower.includes('pendiente') || 
      lower.includes('preparando') || 
      lower.includes('bajo') || 
      lower.includes('últimos') ||
      lower.includes('condicional') ||
      lower.includes('sincronizando')
    ) {
      return (
        <span className="status-badge warning">
          <AlertTriangle size={12} />
          <span>{strVal}</span>
        </span>
      );
    }

    if (
      lower.includes('agotado') || 
      lower.includes('cancelada') || 
      lower.includes('anulado') || 
      lower.includes('inactivo')
    ) {
      return (
        <span className="status-badge danger">
          <XCircle size={12} />
          <span>{strVal}</span>
        </span>
      );
    }

    return <span>{strVal}</span>;
  };

  // Formato legible para encabezado de columna
  const formatHeaderTitle = (col) => {
    return col
      .replace(/_/g, ' ')
      .replace(/\b\w/g, char => char.toUpperCase());
  };

  // Estado de error
  if (error) {
    return (
      <div className="table-state-container error-state">
        <AlertTriangle size={48} className="state-icon danger" />
        <h3>Error al conectar con la API</h3>
        <p>{error}</p>
        <div className="error-actions">
          <button onClick={onRetry} className="action-btn primary">
            Reintentar Conexión
          </button>
        </div>
      </div>
    );
  }

  // Estado de carga con Skeleton Shimmer
  if (loading) {
    return (
      <div className="table-state-container loading-state">
        <div className="shimmer-table-header">
          <div className="skeleton-bar title"></div>
          <div className="skeleton-bar search"></div>
        </div>
        <div className="skeleton-rows">
          {[1, 2, 3, 4, 5].map((idx) => (
            <div key={idx} className="skeleton-row">
              {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10].map((c) => (
                <div key={c} className="skeleton-cell"></div>
              ))}
            </div>
          ))}
        </div>
        <p className="loading-caption">Consultando {currentApi.path} con Axios/Fetch...</p>
      </div>
    );
  }

  return (
    <div className="data-table-wrapper">
      {/* Table Toolbar */}
      <div className="table-toolbar">
        <div className="search-box">
          <Search size={17} className="search-icon" />
          <input
            id="table-search-input"
            type="text"
            placeholder={`Buscar en los 10 campos de ${currentApi.name}...`}
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          {searchTerm && (
            <button 
              className="clear-search-btn"
              onClick={() => setSearchTerm('')}
              title="Limpiar búsqueda"
            >
              ×
            </button>
          )}
        </div>

        <div className="toolbar-info">
          <span className="results-count">
            Mostrando <strong>{sortedData.length}</strong> de <strong>{data.length}</strong> filas
          </span>
          <span className="columns-badge">
            <strong>{columns.length}</strong> columnas activas
          </span>
        </div>
      </div>

      {/* Responsive Table Container */}
      <div className="table-scroll-container">
        <table className="modern-data-table" id="api-results-table">
          <thead>
            <tr>
              <th className="col-index-header">#</th>
              {columns.map((col, idx) => {
                const isSorted = sortColumn === col;
                return (
                  <th 
                    key={col}
                    className={`sortable-th ${isSorted ? 'sorted' : ''}`}
                    onClick={() => handleSort(col)}
                    title={`Hacer clic para ordenar por ${col}`}
                  >
                    <div className="th-content">
                      <div className="th-titles">
                        <span className="col-index-badge">C{idx + 1}</span>
                        <span className="th-text">{formatHeaderTitle(col)}</span>
                      </div>
                      <span className="sort-icon">
                        {isSorted ? (
                          sortDirection === 'asc' ? <ArrowUp size={14} /> : <ArrowDown size={14} />
                        ) : (
                          <ArrowUpDown size={13} className="sort-idle" />
                        )}
                      </span>
                    </div>
                  </th>
                );
              })}
            </tr>
          </thead>
          <tbody>
            {sortedData.length > 0 ? (
              sortedData.map((row, rowIdx) => (
                <tr key={row.id || rowIdx} className="table-row">
                  <td className="row-index-cell">{rowIdx + 1}</td>
                  {columns.map((col) => (
                    <td key={col} className={`cell-${col}`}>
                      {renderCellValue(col, row[col])}
                    </td>
                  ))}
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={columns.length + 1} className="no-results-cell">
                  <div className="empty-state">
                    <FilterX size={36} />
                    <p>No se encontraron registros que coincidan con "<strong>{searchTerm}</strong>"</p>
                    <button 
                      className="action-btn"
                      onClick={() => setSearchTerm('')}
                    >
                      Restablecer filtro
                    </button>
                  </div>
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* Table Footer with Column Legend */}
      <div className="table-footer-legend">
        <span className="legend-label">
          <Info size={13} />
          Estructura validada de 10 campos:
        </span>
        <div className="fields-tag-list">
          {columns.map((col, idx) => (
            <span key={col} className="field-pill">
              <span className="field-num">{idx + 1}</span>
              <code>{col}</code>
            </span>
          ))}
        </div>
      </div>
    </div>
  );
}
