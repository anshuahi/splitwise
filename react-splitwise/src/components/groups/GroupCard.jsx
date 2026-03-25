import { Card, CardContent, CardActions, Button, Typography, Chip, Box } from "@mui/material";

import { useNavigate } from "react-router-dom";

// import { toCamelCase } from "../../pipes/toCamelCase";

function GroupCard({ group, onEdit, onDelete }) {
    const navigate = useNavigate();
    console.log("Rendering GroupCard for group:", group);
    const handleEdit = () => {
        console.log("Edit button clicked for group:", group);
        onEdit(group);
        
    }
    const handleClick = () => {
        console.log("Clicked on group:", group);
        navigate(`/groups/${group.id}`);
    }

    const handleDelete = () => {
        onDelete(group.id);
    }
    return (
        <div style={{ margin: "20px" }}>
            <Card sx={{ width: 300, margin: "10px auto", padding: "20px", border: "1px solid #ccc", borderRadius: "10px", cursor: "pointer" }} onClick={handleClick}>
                <CardContent>
                    <Typography variant="h5" component="div">
                        {group.groupName}
                    </Typography>
                    <Typography variant="body2">
                        {group.members && group.members.length > 0 ? (
                            <Box display="flex" flexWrap="wrap" gap="10px" mt={2}>
                                {group.members.map(member => (
                                    <Chip key={member.id} label={member.name} />
                                ))}
                            </Box>
                        ) : (
                            <Typography variant="body2" color="textSecondary">No members in this group.</Typography>
                        )}
                        <br />
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button
                        variant="contained"
                        size="small"
                        onClick={(e) => { 
                            e.stopPropagation(); 
                            handleEdit();
                        }}
                    >
                        Edit
                    </Button>

                    <Button
                        variant="outlined"
                        color="error"
                        size="small"
                        onClick={(e) => { 
                            e.stopPropagation(); 
                            handleDelete();
                        }}
                    >
                        Delete
                    </Button>
                </CardActions>
            </Card>
            {/* <Card onClick={handleClick} style={{width:"500px",  margin:"10px auto", padding:"20px", border:"1px solid #ccc", borderRadius:"10px", cursor:"pointer"}}>
        <CardContent>
            <Box display="flex" justifyContent="space-between" alignItems="center">
                <Typography variant="h5">{group.groupName}</Typography>
                <button onClick={
                    (e) => {
                        e.stopPropagation();
                        handleEdit();
                    }
                } style={{height:"30px", margin:"auto", float:"right"}}>Edit</button>
            </Box>
            {group.members && group.members.length > 0 ? (
                <Box display="flex" flexWrap="wrap" gap="10px" mt={2}>
                    {group.members.map(member => (
                        <Chip key={member.id} label={member.name} />
                    ))}
                </Box>
            ) : (
                <Typography variant="body2" color="textSecondary">No members in this group.</Typography>
            )}
        </CardContent>
    </Card>

    

    <div onClick={handleClick} style={{width:"500px",  margin:"10px auto", padding:"20px", border:"1px solid #ccc", borderRadius:"10px", }}>
        <div style={{display:"flex", justifyContent:"space-between"}}>
            <h3>{group.groupName}</h3> 
            <button onClick={
                (e) => {
                    e.stopPropagation();
                    handleEdit();
                }
            } style={{height:"30px", margin:"auto", float:"right"}}>Edit</button>
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
    </div> */}
        </div>
    );
}

export default GroupCard;