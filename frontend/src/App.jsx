import { Routes, Route, NavLink } from "react-router-dom";
import TasksPage from "./pages/TaskPages";

export default function App() {
  return (
    <div className="container">
      <header className="app-header">
        <h1>Task Manager</h1>
        <nav>
          <NavLink to="/" className="nav-link">Tareas</NavLink>
        </nav>
      </header>

      <main>
        <Routes>
          <Route path="/" element={<TasksPage />} />
        </Routes>
      </main>
    </div>
  );
}
