import { useState } from "react";
import api from "../../api";

// import {react}
function SignupPage() {
    const [form, setForm] = useState({
        name: "",
        phone: "",
        password: "",
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

        const handleSubmit = async(e) => {
        e.preventDefault();
        // setForm({...form, [e.target.name]: e.target.value});
        console.log("registering...", form);
        try {
            const response = await api.post("/auth/signup", form);
            console.log(response.data);
        } catch (error) {
            console.error(error);
        }
    }
    return (
        <div style={{margin:"auto", width:"fit-content"}}>
            <h2>Signup</h2>
            <form onSubmit={handleSubmit} >
                <div style={{display: "flex", flexDirection:"column"}}>
                    <label htmlFor="name">Name</label>
                    <input style={{width:"200px"}} type="name" id="name" name="name" placeholder="Email" value={form.name} onChange={handleChange} />
                    <br />
                    <label htmlFor="phone">Phone</label>
                    <input style={{width:"200px"}} type="phone" id="phone" name="phone" placeholder="Email" value={form.phone} onChange={handleChange} />
                    <br />
                    <label htmlFor="password">Password</label>
                    <input style={{width:"200px"}} type="password" id="password" name="password" placeholder="Password" value={form.password} onChange={handleChange} />
                    <br />
                    <button style={{width:"100px", margin:"10px auto"}} type="submit">Register</button>
                </div>
            </form>
        </div>
    )
}

export default SignupPage;