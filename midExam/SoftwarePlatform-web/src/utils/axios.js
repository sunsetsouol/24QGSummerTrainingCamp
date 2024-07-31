import axios from "axios";

//创建一个axios实例
const service = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
  },
  responseType: "json",
});

//请求拦截器
service.interceptors.request.use(
  (config) => {
    //在请求前做些什么
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    //对请求错误做些什么
    return Promise.reject(error);
  }
);

//响应拦截器
service.interceptors.response.use(
  (response) => {
    //对响应数据做些什么
    return response.data;
  },
  (error) => {
    //对响应错误做些什么
    return Promise.reject(error);
  }
);

export default service;
