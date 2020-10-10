import axios from 'axios'

const httpClient = axios.create({
  baseURL: 'http://localhost:8080/testdocs/api'
})

httpClient.interceptors.request.use(function (config) {
  const userAsString = localStorage.getItem('@testdocs/user')
  if (userAsString) {
    const user = JSON.parse(userAsString)
    config.params = {
      auth: user.id
    }
  }
  return config;
}, function (error) {
  return Promise.reject(error);
});

export default httpClient