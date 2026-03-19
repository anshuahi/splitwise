import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/auth/LoginPage";
import Navbar from "./components/Navbar";
import Registration from "./components/auth/SignupPage";
import Home from "./components/HomePage";
import Groups from "./components/Groups";
import Expenses from "./components/Expenses";



function App() {
  const token = localStorage.getItem("token");
  return (
    <BrowserRouter>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/groups" element={<Groups />} />
        <Route path="/expenses" element={<Expenses />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;