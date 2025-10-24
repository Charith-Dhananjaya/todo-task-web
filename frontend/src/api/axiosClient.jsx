import axios from "axios";

const axiosClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:3000/api",
  headers: { "Content-Type": "application/json" },
});


axiosClient.interceptors.response.use(
  (res) => res,
  (err) => {
    console.error(err?.response || err);
    return Promise.reject(err);
  }
);

export default axiosClient;
