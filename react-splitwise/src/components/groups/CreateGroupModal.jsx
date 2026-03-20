import { useEffect, useState } from "react";
import api from "../../api";
import { useAuth } from "../../context/AuthContext";

function CreateGroupModal({ isOpen, onClose, onCreate }) {
    const {user} = useAuth();
    const [groupName, setGroupName] = useState("");
    const [members, setMembers] = useState([]);
    const [query, setQuery] = useState("");
    const [results, setResults] = useState([]);

    const handleSubmit = (e) => {
        e.preventDefault();
        onCreate(groupName, members);
        setGroupName("");
        setMembers([user]);
        onClose();
    }

    useEffect(() => {
        setMembers([user]);
    }, [user])

    const handleSearch = async (value) => {
        setQuery(value);
        console.log("searching for member:", value);
        if (value.trim().length <= 1) {
            setResults([]);
            return;
        }
        try {
            const response = await api.get(`/users/search?prefix=${value}`);
            const result = response.data.filter(
                (user) => !members.some((member) => member.id === user.id)
            )
            setResults(result);
            console.log(result);
        }
        catch (error) {
            console.error("Error searching members:", error);
        }
    }

    // add member to group
    const addMember = (member) => {
        setMembers([...members, member]);
        setQuery("");
        setResults([]);
    }

    // remove member from group
    const removeMember = (id) => {
        setMembers(members.filter(member => member.id !== id));
    }

    if (!isOpen) return null;
    return (
        <div style={styles.overlay}>
            <div style={styles.modal}>
                <h2>Create Expense Group</h2>

                <form onSubmit={handleSubmit} style={styles.formGroup}>
                    <input
                        style={{ width: "200px", margin: "10px 0" }}
                        type="text"
                        placeholder="Group Name"
                        value={groupName}
                        onChange={(e) => setGroupName(e.target.value)}
                        required
                    />
                    <input type="text"
                        placeholder="search members..."
                        style={{ width: "200px", margin: "10px 0" }}
                        value={query}
                        onChange={(e) => handleSearch(e.target.value)}
                    />
                    {/* Dropdown */}
                    {results.length > 0 && (
                        <div style={styles.dropdown}>
                            {results.map((user) => (
                                <div
                                    key={user.id}
                                    style={styles.dropdownItem}
                                    onClick={() => addMember(user)}
                                >
                                    {user.name} ({user.phone})
                                </div>
                            ))}
                        </div>
                    )}

                    {/* Selected Members */}
                    <div>
                        {members.map((m) => (
                            <span key={m.id} style={styles.tag}>
                                {m.name}
                                <button style={{marginLeft:"5px", }} onClick={() => removeMember(m.id)}>x</button>
                            </span>
                        ))}
                    </div>

                    <div style={styles.actions}>
                        <button type="submit">Create</button>
                        <button type="button" onClick={onClose}>Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
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
    width: "350px",
  },
  dropdown: {
    border: "1px solid #ccc",
    maxHeight: "150px",
    overflowY: "auto",
    background: "white",
  },
  dropdownItem: {
    padding: "8px",
    cursor: "pointer",
  },
  tag: {
    display: "inline-block",
    padding: "5px",
    margin: "5px",
    background: "#eee",
    borderRadius: "5px",
  },
  actions: {
    marginTop: "10px",
    display: "flex",
    gap: "10px",
  },
};
export default CreateGroupModal;