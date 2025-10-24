import React from "react";
import TaskCard from "./TaskCard";

const TaskList = ({ tasks, onMarkDone }) => {
  if (!tasks?.length) {
    return <p className="no-task" data-testid="no-task">No tasks available.</p>;
  }

  return (
    <div className="task-list" data-testid="task-list">
      {tasks.map((task) => (
        <TaskCard key={task.id} task={task} onMarkDone={onMarkDone} />
      ))}
    </div>
  );
};

export default TaskList;
