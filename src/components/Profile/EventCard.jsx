import { Card, CardActions, CardContent, CardMedia, IconButton, Typography } from "@mui/material";
import React from "react";
import DeleteIcon from '@mui/icons-material/Delete';
function EventCard() {
  return <div className="">
    <Card sx={{width:345}}>
    <CardMedia 

    sx={{height:345}}
    image="https://cdn.pixabay.com/photo/2018/05/26/10/54/strawberries-3431122_640.jpg"/>
    <CardContent>
        <Typography variant="h5">
            Dinner Point 
        </Typography>
        <Typography variant="body2">
           50% off on your first order
        </Typography>
        <div className="py-2 space-y-2">
              <p>{"Mumbai"}</p>
              <p className="text-sm text-blue-500">February 14,2024 12:00 AM</p>
              <p className="text-sm text-red-500">February 14,2024 12:00 AM</p>
        </div>
    </CardContent>
    {
        false && <CardActions>
        <IconButton>
            <DeleteIcon/>
        </IconButton>
    </CardActions>
    }
    </Card>
  </div>;
}

export default EventCard;
