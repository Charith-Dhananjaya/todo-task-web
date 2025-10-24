import React, { useEffect, useState } from "react";
import AddTaskForm from "./components/AddTaskForm";
import TaskList from "./components/TaskList";
import { fetchTasks, createTask, markTaskDone } from "./api/tasks";
import "./App.css";

const App = () => {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

const loadTasks = async () => {
    try {
      setLoading(true);
      const list = await fetchTasks();
      setTasks(list.filter((t) => !t.completed).slice(0, 5));
      setError("");
    } catch (e) {
      setError("Failed to load tasks. Please check your connection.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadTasks();
  }, []);

   const handleAddTask = async (title, description) => {
    if (!title.trim() || !description.trim()) return;

    await createTask({ title, description });
    await loadTasks();
  };

  const handleMarkDone = async (id) => {
    
    await markTaskDone(id);
    await loadTasks();
  };

  return (
    <div className="app-container">
      <div className="form-section">
        <AddTaskForm onAddTask={handleAddTask} />
      </div>
      <div className="task-section">
        {loading ? (
          <div>Loading…</div>
        ) : (
          <TaskList tasks={tasks} onMarkDone={handleMarkDone} />
        )}
        {error && <p className="error-message">{error}</p>}
      </div>
    </div>
  );
};

export default App;
