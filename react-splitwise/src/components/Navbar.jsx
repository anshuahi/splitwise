import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useEffect } from "react";

function Navbar() {
    const navigate = useNavigate();
    const { user, logout } = useAuth();
    const handleLogout = () => {
        logout();
        navigate("/login");
    }

    useEffect(() => {
        console.log("Navbar user:", user); 
    }, [user]);

    return (
        <nav style={styles.nav}>
            <Link to="/" style={styles.link}>SplitWise</Link>
            {user && <div style={styles.links}>
                <Link to="/groups" style={styles.link}>Groups</Link>
                <Link to="/expenses" style={styles.link}>Expenses</Link>

            </div>}
            <div style={styles.links}>
                {user && <p style={{margin:"0 10px"}}>Hello {user.name}</p>}
                {user && <button onClick={handleLogout}>Logout</button>}
                {!user && <Link to="/login" style={styles.link}>Login</Link>}
                {!user && <Link to="/register" style={styles.link}>Signup</Link>}
            </div>
        </nav>
    );
}

const styles = {
    nav: {
        display: "flex",
        justifyContent: "space-between",
        padding: "10px 20px",
        background: "#282c34",
        color: "white",
    },
    logo: {
        margin: 0,
    },
    links: {
        display: "flex",
        gap: "15px",
    },
    link: {
        color: "white",
        textDecoration: "none",
    },
};

export default Navbar;