import React from "react";
import HouseIcon from "@mui/icons-material/House";
import { Button, Card } from "@mui/material";

function AddressCart({ item, showButton,handleSelectAddress }) {

  return (
    <Card className="flex gap-5 w-64 p-5">
      <HouseIcon />
      <div className="space-y-3 text-gray-500">
        <h1 className="font-semibold text-lg text-white">Home</h1>
        <p>
          Mumbai, new shivam building, gokuldham market, 530068, Maharastra,
          India
        </p>
        {showButton && (
          <Button
            fullWidth
            onClick={handleSelectAddress(item)}
            variant="outlined"
            color="primary"
          >
            Select
          </Button>
        )}
      </div>
    </Card>
  );
}


export default AddressCart;
