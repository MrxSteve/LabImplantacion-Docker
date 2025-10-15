import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8090/api",
  headers: { "Content-Type": "application/json" }
});

// Endpoints
export const listTasks = (page = 0, size = 10) =>
  api.get(`/tasks`, { params: { page, size } }); 

export const createTask = (title) =>
  api.post(`/tasks`, { title });

export const updateTaskCompleted = (id, completed) =>
  api.put(`/tasks/${id}`, { completed });

export const deleteTask = (id) =>
  api.delete(`/tasks/${id}`);

export const getTaskById = (id) => api.get(`/tasks/${id}`);

