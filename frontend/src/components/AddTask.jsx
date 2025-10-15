import { useState } from "react";

export default function AddTask({ onAdd }) {
  const [title, setTitle] = useState("");

  const submit = async (e) => {
    e.preventDefault();
    const v = title.trim();
    if (!v) return;
    await onAdd(v);
    setTitle("");
  };

  return (
    <form className="add-form" onSubmit={submit}>
      <input
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="Escribe una nueva tarea…"
        aria-label="Nueva tarea"
      />
      <button type="submit">Agregar</button>
    </form>
  );
}
