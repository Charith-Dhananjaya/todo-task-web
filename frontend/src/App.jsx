import React, { useState } from "react";
import AddTaskForm from "./components/AddTaskForm";
import TaskList from "./components/TaskList";
import "./App.css";

const App = () => {
  const [tasks, setTasks] = useState([
    { id: 1, title: "Buy books", description: "Buy books for the next school year", completed: false },
    { id: 2, title: "Clean home", description: "Need to clean the bed room", completed: false },
    { id: 3, title: "Takehome assignment", description: "Finish the mid-term assignment", completed: false },
    { id: 4, title: "Play Cricket", description: "Plan the soft ball cricket match on next Sunday", completed: false },
    { id: 5, title: "Help Saman", description: "Saman need help with his software project", completed: false },
  ]);

  const handleMarkDone = (id) => {
    setTasks((prev) => prev.filter((task) => task.id !== id));
  };

  const handleAddTask = (title, description) => {
    if (!title.trim()) return;
    const newTask = {
      id: Date.now(),
      title,
      description,
      completed: false,
    };
    setTasks((prev) => {
      const updated = [...prev, newTask];
      return updated.slice(-5);
    });
  };

  return (
    <div className="app-container">
      <div className="form-section">
        <AddTaskForm onAddTask={handleAddTask} />
      </div>
      <div className="task-section">
        <TaskList tasks={tasks} onMarkDone={handleMarkDone} />
      </div>
    </div>
  );
};

export default App;
