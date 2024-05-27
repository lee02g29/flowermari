import Axios, { AxiosInstance, AxiosResponse, InternalAxiosRequestConfig } from 'axios';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../stores/auth.ts';

const useLocalAxios = (isAuth: boolean = true): AxiosInstance => {
    const { accessToken, clearAuth } = useAuthStore();
    const navigate = useNavigate();

    const instance = Axios.create({
        baseURL: import.meta.env.VITE_API_BASE_URL,
        withCredentials: true,
    });

    if (isAuth) {
        instance.interceptors.request.use((config: InternalAxiosRequestConfig) => {
            console.log("axios accesstoken",accessToken);
            if (!accessToken) {
                console.log("로그인이 필요합니다.");
                navigate('/');
                throw new Axios.Cancel("로그인이 필요한 요청입니다.");
            }
            config.headers = config.headers || {};
            config.headers.Authorization = `Bearer ${accessToken}`;
            return config;
        });
    }

    instance.interceptors.response.use(
        (response) => response,
        async (error) => {
            if (error.response && error.response.status === 401) {
                // 401 Unauthorized 오류 처리
                clearAuth();
                navigate('/');
                return Promise.reject(error);
            }
            return Promise.reject(error);
        },
    );

    return instance;
};

export { useLocalAxios };
