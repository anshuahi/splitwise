import { useEffect, useState } from "react";
import CreateGroupModal from "./CreateGroupModal";
import api from "../../api";
import { useAuth } from "../../context/AuthContext";
import GroupCard from "./GroupCard";

function Groups() {
    const { user } = useAuth();
    const [groups, setGroups] = useState([]);
    const createNewGroup = async (groupName, members) => {
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

    useEffect(() => {
        if (user == null) return;
        const fetchGroups = async () => {
            try {
                const response = await api.get("/groups/my-groups?id=" + user.id);
                setGroups(response.data);
                console.log("Fetched groups:", response.data);
            } catch (error) {
                console.error("Error fetching groups:", error);
            }
        }
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
                    />
                </div>
                <div style={{height:"400px"}}>

                {groups.length > 0 ? (
                    groups.map(group => (
                        <GroupCard key={group.id} group={group} />
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