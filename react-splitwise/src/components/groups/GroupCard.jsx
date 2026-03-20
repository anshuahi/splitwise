// import { Card, CardContent, Typography, Chip, Box } from "@mui/material";

import { useNavigate } from "react-router-dom";

// import { toCamelCase } from "../../pipes/toCamelCase";

function GroupCard({ group }) {
    const navigate = useNavigate();
    console.log("Rendering GroupCard for group:", group);
    const handleEdit = () => {

    }
    const handleClick = () => {
        console.log("Clicked on group:", group);
        navigate(`/groups/${group.id}`);
    }
  return (
    <div onClick={handleClick} style={{width:"500px",  margin:"10px auto", padding:"20px", border:"1px solid #ccc", borderRadius:"10px", }}>
        <div style={{display:"flex", justifyContent:"space-between"}}>
            <h3>{group.groupName}</h3> 
            <button onClick={handleEdit} style={{height:"30px", margin:"auto", float:"right"}}>Edit</button>
        </div>
        {group.members && group.members.length > 0 ? (
            <div style={{display:"flex", flexWrap:"wrap", gap:"10px"}}>
                {group.members.map(member => (
                    <span key={member.id} style={{padding:"5px 10px", background:"#eee", borderRadius:"5px"}}>
                        {member.name}
                    </span>
                ))}
            </div>
        ) : (
            <p>No members in this group.</p>
        )}
    </div>
  );
}

export default GroupCard;