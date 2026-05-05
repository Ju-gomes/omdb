// src/services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080'
});

export const searchMovies = async (params) => {
  const response = await api.get('/api/omdb/filtro', { params });
  return response.data;
};

export const getMovieById = async (id) => {
  const response = await api.get(`/api/omdb/filme/${id}`);
  return response.data;
};