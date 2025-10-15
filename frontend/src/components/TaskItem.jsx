export default function TaskItem({ task, onToggle, onDelete, onView }) {
  const handleSwitch = (e) => {
    const next = e.target.checked;
    onToggle(next);
  };

  return (
    <li className={`task ${task.completed ? "completed" : "pending"}`}>
      <div className="task-left">
        <label className="switch">
          <input
            type="checkbox"
            checked={!!task.completed}
            onChange={handleSwitch}
            aria-label={`Cambiar estado de ${task.title}`}
          />
          <span className="slider" />
        </label>

        <div className="task-info">
          <span className="task-title">{task.title}</span>
          <span className={`chip ${task.completed ? "chip-ok" : "chip-pending"}`}>
            {task.completed ? "Completada" : "Pendiente"}
          </span>
        </div>
      </div>

      <div className="task-actions">
        <button className="outline-btn" onClick={onView}>Ver tarea</button>
        <button className="delete-btn" onClick={onDelete} aria-label={`Eliminar ${task.title}`}>
          ×
        </button>
      </div>
    </li>
  );
}
