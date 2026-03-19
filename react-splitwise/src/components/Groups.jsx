import { useState } from "react";
import CreateGroupModal from "./modals/CreateGroupModal";
import api from "../api";
import { useAuth } from "../context/AuthContext";

function Groups() {
    const {user} = useAuth();
    const createNewGroup = async(groupName, members) => {
        try {
            const response = await api.post("/groups/create-group", {
                name: groupName,
                createdBy: user.id,
                userIds: members.map(member => member.id),
            });
            console.log("Group created successfully:", response.data);
        }
        catch (error) {
            console.error("Error creating group:", error);
        }
    }
    const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
    return (
        <div>
            <div style={styles.container}>
                <h1>All groups</h1>
                <button
                    style={styles.createGroupButton}
                    onClick={() => setIsCreateModalOpen(true)}
                >
                    Create Group
                </button>
                <CreateGroupModal 
                    isOpen={isCreateModalOpen}
                    onClose={() => setIsCreateModalOpen(false)}
                    onCreate={createNewGroup}
                />
            </div>
        </div>
    );
}
const styles = {
    container: {
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        padding: "20px",
    },
    createGroupButton: {
        // height: "30px",
        margin: "auto",
        borderRadius: "10px",
        padding: "8px 15px",
        background: "#12d546",
        cursor: "pointer",
        font: "16px Arial, sans-serif",
        fontWeight: "bold",
    },
    groupList: {
        width: "100%",
        maxWidth: "600px",
        marginTop: "20px",
    },
    groupItem: {
        display: "flex",
        justifyContent: "space-between",
        padding: "10px",
        borderBottom: "1px solid #ccc",
    },
};

export default Groups;