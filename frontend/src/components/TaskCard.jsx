import React from "react";

const TaskCard = ({ task, onMarkDone }) => {
  return (
    <div className="task-card">
      <div className="task-card-content">
        <h4>{task.title}</h4>
        <p className="task-card-desc">{task.description}</p>
      </div>
      <button className="done-btn" onClick={() => onMarkDone(task.id)}>
        Done
      </button>
    </div>
  );
};

export default TaskCard;