
import { Card, Chip, IconButton, Typography } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import CreateIcon from '@mui/icons-material/Create';
import ConfirmationDialog from "../../dialog/ConfirmationDialog";
import { useState } from "react";
import api from "../../api";
import AddExpenseModal from "./AddExpenseModal";

function ExpenseCard({ expense, handleDeleteExpense, addExpense, group }) {
    const [isEditExpenseModalOpen, setIsEditExpenseModalOpen] = useState(false);
    const [isDeleteDialogOpen, setIsDeleteDialogOpen] = useState(false);
    const roundOffAmount = (amount) => {
        return Math.round(amount * 100) / 100;
    }
    return (
        <Card
            key={expense.id}
            style={{ padding: "10px", margin: "30px", width: "400px" }}
        >
            {/* Header */}
            <div style={{ display: "flex", justifyContent: "space-between" }}>
                <Typography variant="h6" style={{ fontWeight: "900" }}>
                    {expense.description}
                </Typography>
                <div style={{ display: "flex", gap: "10px" }}>
                    <IconButton onClick={() => setIsEditExpenseModalOpen(true)}>
                        <CreateIcon sx={{ color: "black" }} />
                    </IconButton>

                        <AddExpenseModal
                            group={group}
                            isOpen={isEditExpenseModalOpen}
                            onClose={() => { setIsEditExpenseModalOpen(false) }}
                            onCreate={addExpense}
                            expense={expense}
                        />
                    <IconButton onClick={() => setIsDeleteDialogOpen(true)}>
                        <DeleteIcon sx={{ color: "rgb(165 5 5)" }} />
                    </IconButton>
                    <ConfirmationDialog
                        open={isDeleteDialogOpen}
                        title="Confirm Deletion"
                        message="Are you sure you want to delete this expense?"
                        onConfirm={() => {
                            setIsDeleteDialogOpen(false);
                            handleDeleteExpense(expense.id);
                        }}
                        onCancel={() => {
                            setIsDeleteDialogOpen(false)
                        }}
                    />
                </div>
            </div>

            <div style={{ marginLeft: "10px", color: "green" }}>

                <Typography>
                    ₹{expense.totalAmount} (Paid By: {expense.paidBy?.name})
                </Typography>
            </div>

            {/* Members */}
            <div style={{ marginTop: "10px" }}>
                {expense.memberContribution?.map((member) => (
                    <Chip key={member.memberId} label={`${member.user.name} : ₹${roundOffAmount(member.amount)}`} style={{ margin: "5px" }} />

                ))}
            </div>
        </Card>
    )
}


export default ExpenseCard;