import { useEffect, useState } from "react";
import api from "../../api";
import { useParams } from "react-router-dom";
import AddExpenseModal from "../expenses/AddExpenseModal";

function GroupDetails() {
    const { id } = useParams();
    const [group, setGroup] = useState(null);
    const [expenses, setExpenses] = useState([]);
    const [isAddExpenseModalOpen, setIsAddExpenseModalOpen] = useState(false);

    useEffect(() => {
        const fetchGroupDetails = async () => {
            try {
                const response = await api.get(`/groups/${id}`);
                console.log(response.data);
                setGroup(response.data.expenseGroupDto);
            } catch (error) {
                console.error("Error fetching group details:", error);
            }
        };

        fetchGroupDetails();
    }, [id]);

    const addExpense = () => {
        // Logic to add a new expense to the group
        console.log("Add expense clicked for group:", id);
    }

    if (!group) return <p>Loading...</p>;

    return (
        <div style={{ margin: "50px" }}>
            <h2>{group.groupName}</h2>
            <div style={{ display: "flex", justifyContent: "space-between" }}>
                <div>
                    <div style={{ display: "flex", justifyContent: "space-between" }}>
                        <h3>Expenses:</h3>
                        <button
                            onClick={() => setIsAddExpenseModalOpen(true)}
                            style={{ 
                                height: "40px", 
                                margin: "auto 50px", 
                                borderRadius: "15px", 
                                padding: "10px 20px",
                                background: "#4CAF50",
                            }}
                        >
                            Add Expense
                        </button>
                        <AddExpenseModal
                            group={group}
                            isOpen={isAddExpenseModalOpen}
                            onClose={() => {setIsAddExpenseModalOpen(false)}}
                            onCreate={() => {addExpense}}
                        />
                    </div>
                    <ul>
                        {/* {group.expenses.map(expense => (
                            <li key={expense.id}>{expense.description} - ${expense.amount}</li>
                        ))} */}
                    </ul>
                </div>
                <div style={{ margin: "0px 50px" }}>
                    <h3>Members:</h3>
                    <ul>
                        {group.members.map(member => (
                            <li key={member.id}>{member.name}</li>
                        ))}
                    </ul>
                </div>
            </div>

        </div>
    );
}

export default GroupDetails;