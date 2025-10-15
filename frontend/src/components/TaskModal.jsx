import { useEffect, useState } from "react";
import { getTaskById } from "../services/api";

export default function TaskModal({ taskId, isOpen, onClose }) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [err, setErr] = useState("");

  useEffect(() => {
    if (!isOpen || !taskId) return;
    (async () => {
      setLoading(true);
      setErr("");
      try {
        const { data } = await getTaskById(taskId);
        setData(data);
      } catch (e) {
        setErr("No se pudo cargar la tarea");
      } finally {
        setLoading(false);
      }
    })();
  }, [isOpen, taskId]);

  // Cerrar con ESC
  useEffect(() => {
    if (!isOpen) return;
    const onKey = (e) => e.key === "Escape" && onClose();
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [isOpen, onClose]);

  if (!isOpen) return null;

  const formatFecha = (iso) => {
    if (!iso) return "—";
    const d = new Date(iso);
    return d.toLocaleString(); // ajusta si quieres formato específico
  };

  return (
    <div className="modal-backdrop" onClick={onClose} aria-hidden="true">
      <div
        className="modal"
        role="dialog"
        aria-modal="true"
        aria-labelledby="task-modal-title"
        onClick={(e) => e.stopPropagation()} // evita cerrar al hacer click dentro
      >
        <div className="modal-header">
          <h3 id="task-modal-title">Detalle de la tarea</h3>
          <button className="modal-close" onClick={onClose} aria-label="Cerrar">×</button>
        </div>

        <div className="modal-body">
          {loading && <div className="loading">Cargando…</div>}
          {err && <div className="error">{err}</div>}

          {!loading && !err && data && (
            <div className="task-details">
              <div className="row">
                <span className="label">ID:</span>
                <span>{data.id}</span>
              </div>
              <div className="row">
                <span className="label">Título:</span>
                <span>{data.title}</span>
              </div>
              <div className="row">
                <span className="label">Estado:</span>
                <span className={`chip ${data.completed ? "chip-ok" : "chip-pending"}`}>
                  {data.completed ? "Completada" : "Pendiente"}
                </span>
              </div>
              <div className="row">
                <span className="label">Creada:</span>
                <span>{formatFecha(data.createdAt)}</span>
              </div>
            </div>
          )}
        </div>

        <div className="modal-footer">
          <button className="btn" onClick={onClose}>Cerrar</button>
        </div>
      </div>
    </div>
  );
}
