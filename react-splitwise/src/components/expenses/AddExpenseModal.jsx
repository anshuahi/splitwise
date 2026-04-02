import { useEffect, useState } from "react";
import { useAuth } from "../../context/AuthContext";
import { Button, Checkbox, FormControl, FormControlLabel, FormGroup, FormLabel, InputLabel, MenuItem, Radio, RadioGroup, Select, TextField } from '@mui/material';

const AddExpenseModal = ({ group, isOpen, onClose, onCreate, expense = null }) => {
    const { user } = useAuth();
    const [description, setDescription] = useState("");
    const [amount, setAmount] = useState("");
    const [paidBy, setPaidBy] = useState(user.id);
    const [selectedMembers, setSelectedMembers] = useState([]);
    const [splitType, setSplitType] = useState("Equal");


    useEffect(() => {
        if (expense) {
            setDescription(expense.description);
            setAmount(expense.totalAmount);
            setPaidBy(expense.paidBy.id);
            setSplitType(expense.splitType);

            setSelectedMembers(
                expense.memberContribution.map(m => m.user.id)
            );
        }
        else {
            setDescription("");
            setAmount("");
            setPaidBy(user.id);
            setSplitType("Equal");
            setSelectedMembers(group.members.map(m => m.id));
        }
    }, [expense, group, user])

    const handleSubmit = (e) => {
        e.preventDefault();

        const membersContributed = selectedMembers.map(memberId => ({
            memberId,
            amount:
                splitType === "Equal"
                    ? -amount / selectedMembers.length
                    : null
        }));

        const payload = {
            id: expense?.id, // 👈 important for edit
            description,
            totalAmount: amount,
            groupId: group.id,
            addedBy: user.id,
            paidBy,
            membersContributed,
            splitType
        };

        onCreate(payload); // same function handles both

        onClose();
    }

    const handleChange = (e, memberId) => {
        if (e.target.checked) {
            setSelectedMembers([...selectedMembers, memberId]);
        } else {
            setSelectedMembers(selectedMembers.filter(id => id !== memberId));
        }
    }

    if (!isOpen) return null;
    return (
        <div style={styles.overlay}>
            <div style={styles.modal}>

                <div style={{ display: "flex", justifyContent: "space-between" }}>
                    <h2 style={{ margin: "0px" }}>Add Expense Modal</h2>
                    <Button
                        onClick={onClose}
                        variant="filled"
                        color="secondary"
                    >
                        X
                    </Button>
                </div>
                <hr />
                <div>
                    <form>
                        <TextField
                            style={{ width: "60%", marginTop: "10px" }}
                            value={description}
                            id="outlined-basic"
                            label="Description"
                            variant="outlined"
                            size="small"
                            onChange={(e) => setDescription(e.target.value)}
                        />
                        <br />
                        <TextField
                            style={{ width: "60%", marginTop: "10px" }}
                            value={amount}
                            id="outlined-basic"
                            label="Amount"
                            variant="outlined"
                            size="small"
                            type="number"
                            onChange={(e) => setAmount(e.target.value)}
                        />
                        <hr />
                        <div >
                            <div>
                                <FormControl>
                                    <FormLabel id="demo-radio-buttons-group-label">Select Split</FormLabel>
                                    <RadioGroup
                                        aria-labelledby="demo-radio-buttons-group-label"
                                        defaultValue="female"
                                        name="radio-buttons-group"
                                        row
                                        value={splitType}
                                        onChange={(e) => setSplitType(e.target.value)}
                                    >
                                        <FormControlLabel value="Equal" control={<Radio />} label="Equal" />
                                        <FormControlLabel value="Percentage" control={<Radio />} label="Percentage" />
                                        <FormControlLabel value="Fixed" control={<Radio />} label="Fixed" />
                                    </RadioGroup>
                                </FormControl>
                            </div>

                            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                                <h4> Split between: </h4>
                                <div>
                                    <FormControl size="small" sx={{ m: 1, minWidth: 120 }}>
                                        <InputLabel id="demo-simple-select-label">Paid By</InputLabel>
                                        <Select
                                            labelId="demo-simple-select-label"
                                            id="demo-simple-select"
                                            value={paidBy}
                                            label="Paid By"
                                            onChange={(e) => setPaidBy(e.target.value)}
                                        >
                                            {group.members.map(member => (
                                                <MenuItem key={member.id} value={member.id}>
                                                    {member.name}
                                                </MenuItem>
                                            ))}
                                        </Select>
                                    </FormControl>
                                </div>
                            </div>
                            <FormGroup style={{ maxHeight: "100px", flexFlow: "wrap", overflowY: "scroll" }}>
                                {group.members.map(member => (
                                    <FormControlLabel
                                        key={member.id}
                                        control={<Checkbox />}
                                        label={member.name}
                                        checked={selectedMembers.includes(member.id)}
                                        onChange={(e) => handleChange(e, member.id)}
                                    />
                                ))}
                            </FormGroup>
                        </div>


                        <div style={{ marginTop: "20px" }}>
                            <Button
                                variant="contained"
                                onClick={handleSubmit}
                                color="primary"
                            >
                                Add Expense
                            </Button>
                            <Button
                                variant="outlined"
                                onClick={onClose}
                                color="red"
                                style={{ marginLeft: "20px" }}
                            >
                                Cancel
                            </Button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}

const styles = {
    overlay: {
        zIndex:1,
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