import { useEffect, useState, useCallback } from "react";
import { listTasks, createTask, updateTaskCompleted, deleteTask } from "../services/api";
import AddTask from "../components/AddTask";
import TaskItem from "../components/TaskItem";
import TaskModal from "../components/TaskModal";
import Swal from "sweetalert2";

export default function TaskPages() {
  const [pageData, setPageData] = useState({ content: [], number: 0, totalPages: 0, totalElements: 0, size: 10 });
  const [loading, setLoading] = useState(false);
  const [err, setErr] = useState("");
  const [page, setPage] = useState(0);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedId, setSelectedId] = useState(null);

  const size = 10;

  const load = useCallback(async () => {
    setLoading(true);
    setErr("");
    try {
      const { data } = await listTasks(page, size);
      setPageData(data);
    } catch (e) {
      setErr("Error cargando tareas");
    } finally {
      setLoading(false);
    }
  }, [page]);

  useEffect(() => { load(); }, [load]);

  const handleAdd = async (title) => {
    await createTask(title);
    await load();
  };

  const handleToggle = async (task, nextState) => {
    try {
      await updateTaskCompleted(task.id, nextState);
      Swal.fire({
        toast: true,
        position: "top-end",
        icon: nextState ? "success" : "info",
        title: nextState ? "Tarea marcada como completada" : "Tarea marcada como pendiente",
        showConfirmButton: false,
        timer: 1600,
        timerProgressBar: true
      });
      await load();
    } catch (e) {
      Swal.fire({ icon: "error", title: "Ups", text: "No se pudo actualizar el estado." });
    }
  };

  const handleDelete = async (id) => {
    await deleteTask(id);
    await load();
  };

  const openModal = (id) => {
    setSelectedId(id);
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
    setSelectedId(null);
  };

  return (
    <section>
      <AddTask onAdd={handleAdd} />
      {err && <div className="error">{err}</div>}
      {loading ? (
        <div className="loading">Cargando…</div>
      ) : (
        <>
          <ul className="tasks">
            {pageData.content?.map((t) => (
              <TaskItem
                key={t.id}
                task={t}
                onToggle={(next) => handleToggle(t, next)}
                onDelete={() => handleDelete(t.id)}
                onView={() => openModal(t.id)}
              />
            ))}
          </ul>

          <div className="pager">
            <button
              disabled={pageData.number <= 0}
              onClick={() => setPage((p) => Math.max(0, p - 1))}
            >
              « Anterior
            </button>
            <span>Página {pageData.number + 1} de {Math.max(1, pageData.totalPages)}</span>
            <button
              disabled={pageData.number >= pageData.totalPages - 1}
              onClick={() => setPage((p) => p + 1)}
            >
              Siguiente »
            </button>
          </div>

          <TaskModal taskId={selectedId} isOpen={modalOpen} onClose={closeModal} />
        </>
      )}
    </section>
  );
}
