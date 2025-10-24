import axios from "./axiosClient";

export const fetchTasks = async () => {

  const { data } = await axios.get('/task', { params: { limit: 5 } });

  return data.map(t => ({
    id: t.id,
    title: t.title,
    description: t.description,
    completed: !!t.status
  }));
};

export const createTask = async ({ title, description }) => {

  const { data } = await axios.post("/task", { title, description });

  return {
    id: data.id,
    title: data.title,
    description: data.description,
    completed: !!data.status
  };
};

export const markTaskDone = async (id) => {

  await axios.patch(`/task/${id}`, { status: true });
  
  return id;
};
