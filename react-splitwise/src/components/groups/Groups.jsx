import { useEffect, useState } from "react";
import CreateGroupModal from "./CreateGroupModal";
import api from "../../api";
import { useAuth } from "../../context/AuthContext";
import GroupCard from "./GroupCard";

function Groups() {
    const { user } = useAuth();
    const [groups, setGroups] = useState([]);
    const [editingGroup, setEditingGroup] = useState(null);
    const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);

    const fetchGroups = async () => {
        try {
            const response = await api.get("/groups/my-groups?id=" + user.id);
            setGroups(response.data);
            console.log("Fetched groups:", response.data);
        } catch (error) {
            console.error("Error fetching groups:", error);
        }
    }

    const deleteGroup = (groupId) => async () => {
        try {
            await api.delete(`/groups/delete-group/${groupId}`);
            console.log("Group deleted successfully");
            fetchGroups(); // Refresh the group list after deletion
        } catch (error) {
            console.error("Error deleting group:", error);
        }
    }

    const createNewGroup = async (groupName, members, groupId = null) => {
        try {
            // update group if groupId is provided, else create new group
            if (groupId) {
                const response = await api.put(`/groups/update-group`, {
                    name: groupName,
                    id: groupId,
                    userIds: members.map(member => member.id),
                });
                console.log("Group updated successfully:", response.data);
            }
            else {
                const response = await api.post("/groups/create-group", {
                    name: groupName,
                    createdBy: user.id,
                    userIds: members.map(member => member.id),
                });
                console.log("Group created successfully:", response.data);
            }

            fetchGroups(); // Refresh the group list after creating a new group
            setEditingGroup(null); // Reset editing state
            setIsCreateModalOpen(false); // Close the modal
        }
        catch (error) {
            console.error("Error creating group:", error);
        }
    }

    useEffect(() => {
        if (user == null) return;

        fetchGroups();
    }, [user])

    return (
        <div>
            <div >
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
                        isEdit={!!editingGroup}
                        groupData={editingGroup}
                    />
                </div>
                <div style={{ display: "flex", justifyContent: "center", flexDirection: "row", flexWrap: "wrap", margin: "20px" }}>

                    {groups.length > 0 ? (
                        groups.map(group => (
                            <GroupCard key={group.id} group={group} 
                            onEdit={(g) => {
                                setEditingGroup(g);
                                setIsCreateModalOpen(true);
                            }} 
                            onDelete={deleteGroup(group.id)}
                            />
                        ))
                    ) : (
                        <p>No groups found. Please create one.</p>
                    )}
                </div>

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