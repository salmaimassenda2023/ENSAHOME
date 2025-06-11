// import axios from 'axios';

// const api = axios.create({
//   baseURL: 'http://localhost:8080',
// });

// // Intercepteur pour ajouter le token aux requêtes
// api.interceptors.request.use((config) => {
//   const token = localStorage.getItem('token');
//   if (token) {
//     config.headers.Authorization = `Bearer ${token}`;
//   }
//   return config;
// }, (error) => {
//   return Promise.reject(error);
// });

// export default api;

export const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8081";