import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Button,
  Checkbox,
  FormControlLabel,
  FormGroup,
} from "@mui/material";
import React from "react";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";

const demo = [
  {
    category: "Nuts & seeds",
    ingredients: ["Cashews"],
  },
  {
    category: "Protein",
    ingredients: ["Protein", "Bacon strips"],
  },
];

function MenuCard({ name, price, description, imgSrc }) {
  const handleCheckBoxChange = (item) => {
    console.log(item);
  };

  return (
    <div>
      <Accordion className="shadow">
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <div className="lg:flex items-center justify-between gap-5">
            <div className="lg:flex items-center lg:gap-5">
              <img
                src={imgSrc}
                alt={name}
                className="w-[7rem] h-[7rem] object-cover"
              />
            </div>
            <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
              <p className="font-semibold text-xl">{name}</p>
              <p>₹{price}</p>
              <p>{description}</p>
            </div>
          </div>
        </AccordionSummary>
        <AccordionDetails>
          <form>
            <div className="flex gap-5 flex-wrap">
              {demo.map((item, index) => (
                <div key={index}>
                  <p className="font-semibold">{item.category}</p>
                  <FormGroup>
                    {item.ingredients.map((ingredient, i) => (
                      <FormControlLabel
                        key={i}
                        control={
                          <Checkbox
                            onChange={() => {
                              handleCheckBoxChange(item);
                            }}
                          />
                        }
                        label={ingredient}
                      />
                    ))}
                  </FormGroup>
                </div>
              ))}
            </div>
            <div className="pt-5">
              <Button type="submit" variant="contained" disabled={false}>
                {true ? "Add To Cart" : "Out Of Stock"}
              </Button>
            </div>
          </form>
        </AccordionDetails>
      </Accordion>
    </div>
  );
}

export default MenuCard;
