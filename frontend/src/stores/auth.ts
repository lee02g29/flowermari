import { create } from 'zustand';
import { persist } from 'zustand/middleware';
interface LoginResponse {
    memberId: number;
    nickname: string;
    profileImage: string;
    accessToken: string;
}

interface AuthState {
    accessToken: string | undefined;
    memberId: number | undefined;
    nickname: string | undefined;
    profileImage: string | undefined;
    tokenExpireAt: Number | undefined;

    setAuth: (loginResponse: LoginResponse) => void;
    setAccessToken: (accessToken: string) => void;
    clearAuth: () => void;
}

const useAuthStore = create<AuthState>()(
    persist(
        (set) => ({
            accessToken: undefined,
            memberId: undefined,
            nickname: undefined,
            profileImage: undefined,
            tokenExpireAt: undefined,

            setAuth: ({ memberId, nickname, profileImage, accessToken }) => {
                set({
                    accessToken,
                    memberId,
                    nickname,
                    profileImage,
                    tokenExpireAt: Date.now() + Number(import.meta.env.VITE_JWT_ACCESS_EXPIRE_TIME),
                });
            },
            setAccessToken: (accessToken: string) => {
                set((state) => ({ ...state, accessToken, tokenExpireAt: Date.now() + Number(import.meta.env.VITE_JWT_ACCESS_EXPIRE_TIME) }));
            },
            clearAuth: () => {
                set({
                    accessToken: undefined,
                    memberId: undefined,
                    nickname: undefined,
                    profileImage: undefined,
                    tokenExpireAt: undefined,
                });
            },
        }),
        {
            name: 'auth-storage',
        },
    ),
);
export { useAuthStore };