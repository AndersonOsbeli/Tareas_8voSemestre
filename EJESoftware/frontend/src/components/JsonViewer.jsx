import React from 'react';
import { Terminal, Copy, Check } from 'lucide-react';

export default function JsonViewer({ data, currentApi }) {
  const [copied, setCopied] = React.useState(false);
  const formattedJson = JSON.stringify(data, null, 2);

  const handleCopy = () => {
    navigator.clipboard.writeText(formattedJson);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  return (
    <div className="json-viewer-container">
      <div className="json-viewer-header">
        <div className="json-header-left">
          <Terminal size={17} className="terminal-icon" />
          <span className="json-filename">response_{currentApi.path.replace('/', '')}.json</span>
          <span className="json-records-count">({Array.isArray(data) ? data.length : 0} items)</span>
        </div>
        <button 
          onClick={handleCopy} 
          className="copy-raw-btn"
          title="Copiar código JSON"
        >
          {copied ? <Check size={14} /> : <Copy size={14} />}
          <span>{copied ? '¡Copiado!' : 'Copiar JSON'}</span>
        </button>
      </div>

      <div className="json-code-wrapper">
        <pre className="json-pre">
          <code>{formattedJson}</code>
        </pre>
      </div>
    </div>
  );
}
