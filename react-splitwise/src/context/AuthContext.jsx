import { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(null);

    // Load user from localStorage on refresh
    useEffect(() => {
        const storedUser = localStorage.getItem("user");
        const storedToken = localStorage.getItem("token");
        // console.log("Stored user:", storedUser);
        // console.log("Stored token:", storedToken);  
        if (storedUser) {
            console.log("Stored uwweser:", storedUser);
            setUser(JSON.parse(storedUser));
        }
        if (storedToken) {
            // console.log("Stored token:", storedToken);
            setToken(storedToken);
        }
    }, []);

    const login = (data) => {
        localStorage.setItem("user", JSON.stringify(data.user));
        localStorage.setItem("token", data.token);
        setUser(data.user);
        setToken(data.token);
    }

    const logout = () => {
        console.log("Logging out user:", user);
        localStorage.removeItem("user");
        localStorage.removeItem("token");
        setUser(null);
    }

    return <AuthContext.Provider value={{ user, token, login, logout }} >
        {children}
    </AuthContext.Provider>
}

export const useAuth = () => useContext(AuthContext)
