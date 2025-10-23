import React from "react";
import TaskCard from "./TaskCard";
import NoTaskFound from "./NoTaskFound";

const TaskList = ({ tasks, onMarkDone }) => {
  if (tasks.length === 0) return <NoTaskFound />;

  return (
    <div className="task-list">
      {tasks.map((task) => (
        <TaskCard key={task.id} task={task} onMarkDone={onMarkDone} />
      ))}
    </div>
  );
};

export default TaskList;
