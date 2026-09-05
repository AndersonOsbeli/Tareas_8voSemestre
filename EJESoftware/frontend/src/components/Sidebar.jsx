import React from 'react';
import { 
  Users, 
  Package, 
  Briefcase, 
  GraduationCap, 
  Car, 
  ShoppingCart, 
  Receipt, 
  BookOpen, 
  Server, 
  Calendar,
  Activity,
  Layers,
  ChevronRight
} from 'lucide-react';

const ICON_MAP = {
  1: Users,
  2: Package,
  3: Briefcase,
  4: GraduationCap,
  5: Car,
  6: ShoppingCart,
  7: Receipt,
  8: BookOpen,
  9: Server,
  10: Calendar,
};

export default function Sidebar({ 
  apis, 
  selectedApiId, 
  onSelectApi, 
  isServerOnline,
  isMobileOpen,
  setIsMobileOpen
}) {
  return (
    <>
      {/* Backdrop for mobile */}
      {isMobileOpen && (
        <div 
          className="sidebar-backdrop"
          onClick={() => setIsMobileOpen(false)}
        />
      )}

      <aside className={`sidebar ${isMobileOpen ? 'mobile-open' : ''}`}>
        {/* Brand Header */}
        <div className="sidebar-brand">
          <div className="brand-icon-wrapper">
            <Layers className="brand-icon" size={26} />
          </div>
          <div className="brand-text">
            <h2>REST Explorer</h2>
            <span className="brand-badge">10 APIs Express</span>
          </div>
        </div>

        {/* Server status pill */}
        <div className="server-status-pill">
          <span className={`status-dot ${isServerOnline ? 'online' : 'offline'}`}></span>
          <span className="status-label">
            {isServerOnline ? 'Backend Online (Port 5000)' : 'Backend Desconectado'}
          </span>
        </div>

        {/* Navigation Menu (10 Options) */}
        <div className="sidebar-section-title">
          <span>CATÁLOGO DE APIS (10 ENDPOINTS)</span>
        </div>

        <nav className="sidebar-nav" id="api-navigation-menu">
          {apis.map((api) => {
            const IconComponent = ICON_MAP[api.id] || Activity;
            const isSelected = selectedApiId === api.id;

            return (
              <button
                key={api.id}
                id={`menu-item-api-${api.id}`}
                className={`nav-item ${isSelected ? 'active' : ''}`}
                onClick={() => {
                  onSelectApi(api.id);
                  if (setIsMobileOpen) setIsMobileOpen(false);
                }}
                title={api.title}
              >
                <div className="nav-item-icon-box">
                  <IconComponent size={19} />
                </div>
                
                <div className="nav-item-info">
                  <div className="nav-item-header">
                    <span className="nav-tag">API {String(api.id).padStart(2, '0')}</span>
                    <code className="nav-path">{api.path}</code>
                  </div>
                  <span className="nav-title">{api.name}</span>
                </div>

                <ChevronRight className="nav-arrow" size={16} />
              </button>
            );
          })}
        </nav>

        {/* Sidebar Footer */}
        <div className="sidebar-footer">
          <div className="footer-spec">
            <span className="spec-item">
              <strong>10</strong> Campos / Registro
            </span>
            <span className="spec-divider">•</span>
            <span className="spec-item">
              <strong>JSON</strong> Nativo
            </span>
          </div>
        </div>
      </aside>
    </>
  );
}
