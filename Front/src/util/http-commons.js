import axios from 'axios';
<<<<<<< HEAD

const { VITE_VUE_API_URL } = import.meta.env;

=======
>>>>>>> eae5f170fa800f9fece9628269b8568f1913840c
// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: '/api',
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
  });
  return instance;
}

export { localAxios };
