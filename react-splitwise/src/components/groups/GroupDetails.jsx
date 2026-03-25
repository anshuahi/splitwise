import { useEffect, useState } from "react";
import api from "../../api";
import { useParams } from "react-router-dom";
import AddExpenseModal from "../expenses/AddExpenseModal";
import { Card, Chip, Typography } from "@mui/material";

function GroupDetails() {
    const { id } = useParams();
    const [group, setGroup] = useState(null);
    const [expenses, setExpenses] = useState([]);
    const [isAddExpenseModalOpen, setIsAddExpenseModalOpen] = useState(false);


    const fetchGroupDetails = async (id) => {
        try {
            const response = await api.get(`/groups/${id}`);
            setGroup(response.data);
        } catch (error) {
            console.error("Error fetching group details:", error);
        }
    };

    const fetchGroupExpenses = async (id) => {
        try {
            const response = await api.get(`/expenses/group-expenses/${id}`);
            setExpenses(response.data);
        } catch (error) {
            console.error("Error fetching group expenses:", error);
        }
    }
    useEffect(() => {
        fetchGroupDetails(id);
        fetchGroupExpenses(id);
    }, [id]);

    const addExpense = (
        description,
        amount,
        groupId,
        paidBy,
        splitBetween,
        splitType
    ) => {
        // Logic to add a new expense to the group
        const formdata = {
            description,
            amount,
            groupId,
            paidBy,
            addedBy: paidBy,
            splitBetween,
            splitType
        }

    
        const addExpenseToGroup = async () => {
            try {
                const response = await api.post("/expenses/add-expense", {
                    description,
                    groupId,
                    totalAmount: amount,
                    paidBy,
                    membersContributed: splitBetween,
                    splitType,
                    addedBy: paidBy
                }
                );
                fetchGroupExpenses(id);
                // Optionally, you can fetch the updated group details here to reflect the new expense
            } catch (error) {
                console.error("Error adding expense:", error);
            }
        };
        addExpenseToGroup();
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
                            onClose={() => { setIsAddExpenseModalOpen(false) }}
                            onCreate={addExpense}
                        />
                    </div>
                    <div>
                        {expenses.map((expense) => (
                            <Card
                                key={expense.id}
                                style={{ padding: "10px", margin: "30px", width: "400px" }}
                            >
                                {/* Header */}
                                <div style={{ display: "flex", justifyContent: "space-between" }}>
                                    <Typography variant="h6" style={{ fontWeight: "900" }}>
                                        {expense.description}
                                    </Typography>

                                    <Typography>
                                        ₹{expense.totalAmount} (Paid By: {expense.paidBy?.name})
                                    </Typography>
                                </div>

                                {/* Members */}
                                <div style={{ marginTop: "10px" }}>
                                    {expense.memberContribution?.map((member) => (
                                        <Chip key={member.memberId} label={`${member.user.name} : ₹${member.amount}`} style={{ margin: "5px" }}>

                                        </Chip>
                                    ))}
                                </div>
                            </Card>
                        ))}

                    </div>
                </div>
                <div style={{ margin: "0px 50px" }}>
                    <h3>Members:</h3>
                    <ul>
                        {group.members.map(member => (
                            <Card key={member.id} style={{ padding: "10px", margin: "10px 0" }}>
                                {member.name}
                            </Card>
                        ))}
                    </ul>
                </div>
            </div>

        </div>
    );
}

export default GroupDetails;