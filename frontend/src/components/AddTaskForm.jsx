import React, { useState } from "react";

const AddTaskForm = ({ onAddTask }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!title.trim() || !description.trim()) {
    setError("Please fill in both fields before adding a task.");
    return;
  }

    onAddTask(title, description);
    setTitle("");
    setDescription("");
    setError("");
  };

  return (
    <form className="add-task-form" onSubmit={handleSubmit}>
      <h2>Add a Task</h2>
      <input
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <button type="submit" className="add-btn">Add</button>
      {error && <p className="error-message">{error}</p>}
    </form>
  );
};

export default AddTaskForm;
