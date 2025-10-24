import React from "react";

const TaskCard = ({ task, onMarkDone }) => {
  return (
    <div className="task-card" data-testid="task-card">
      <div className="task-card-content">
        <h4 data-testid="task-title">{task.title}</h4>
        <p className="task-card-desc" data-testid="task-desc">{task.description}</p>
      </div>
      <button className="done-btn" data-testid={`done-btn-${task.id}`} onClick={() => onMarkDone(task.id)}>
        Done
      </button>
    </div>
  );
};

export default TaskCard;