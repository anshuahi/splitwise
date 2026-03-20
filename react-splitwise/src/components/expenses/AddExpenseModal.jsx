import { useAuth } from "../../context/AuthContext";
import Button from '@mui/material/Button';

const AddExpenseModal = ({ group, isOpen, onClose, onCreate }) => {
    const { user } = useAuth();

    if (!isOpen) return null;
    return (
        <div style={styles.overlay}>
            <div style={styles.modal}>

                <div style={{ display: "flex", justifyContent: "space-between" }}>
                    <h2 style={{ margin: "0px" }}>Add Expense Modal</h2>
                    <button
                        style={{
                            borderRadius: "50%",
                            padding: "5px 8px",
                            margin: "auto 0px",
                            cursor: "pointer",
                            background: "white",
                            border: "none",
                            fontWeight: "bold",
                            fontSize: "20px",
                        }}
                        onClick={onClose}
                    >
                        X
                    </button>
                </div>
                <hr />
                <div>
                    <form>
                        <label>Description</label>
                        <input type="text" name="description" required /> <br/>
                        <label>Amount</label>
                        <input type="number" name="amount" required />
                    </form>
                </div>
                <button onClick={onClose}>Close</button>
            </div>
        </div>
    )
}

const styles = {
    overlay: {
        position: "fixed",
        top: 0,
        left: 0,
        width: "100%",
        height: "100%",
        background: "rgba(0,0,0,0.5)",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
    },
    modal: {
        background: "white",
        padding: "20px",
        borderRadius: "8px",
        width: "400px",
    }
}

export default AddExpenseModal;