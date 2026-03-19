import { useState } from "react";
import api from "../../api";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

function Login() {
  const navigate = useNavigate();
  const { login } = useAuth();
    const [form, setForm] = useState({
        phone: "",
        password: "",
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    const handleSubmit = async(e) => {
        e.preventDefault();
        // setForm({...form, [e.target.name]: e.target.value});
        console.log(form);
        try {
            const response = await api.post("/auth/login", form);
            login(response.data);
            navigate("/");
        } catch (error) {
            console.error(error);
        }
    }


    return (
        <div style={{margin:"auto", width:"fit-content"}}>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div style={{display: "flex", flexDirection:"column"}}>
                    <label htmlFor="phone">Phone</label>
                    <input style={{width:"200px"}} type="phone" id="phone" name="phone" placeholder="phone" value={form.phone} onChange={handleChange} />
                    <br />
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Password" value={form.password} onChange={handleChange} />
                    <br />
                    <button style={{width:"100px", margin:"10px auto"}} type="submit">Login</button>
                </div>
            </form>
        </div>
    )
}

export default Login;