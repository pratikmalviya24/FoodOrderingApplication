import {
  Divider,
  FormControl,
  FormControlLabel,
  Grid,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import PlaceIcon from "@mui/icons-material/Place";
import EventIcon from "@mui/icons-material/Event";
import MenuCard from "./MenuCard";

const categories = ["pizza", "biryani", "burger", "chicken", "rice"];
const foodTypes = [
  { label: "All", value: "all" },
  { label: "Vegetarian only", value: "vegetarian" },
  { label: "Non-Vegetarian", value: "non-vegetarian" },
  { label: "Seasonal", value: "seasonal" },
];

const menu = [
  {
    id: 1,
    name: "Burger",
    price: 499,
    description: "Delicious burger with cheese",
    imgSrc:
      "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
  },
  {
    id: 2,
    name: "Burger",
    price: 499,
    description: "Delicious burger with cheese",
    imgSrc:
      "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
  },
  {
    id: 3,
    name: "Burger",
    price: 499,
    description: "Delicious burger with cheese",
    imgSrc:
      "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
  },
  {
    id: 4,
    name: "Burger",
    price: 499,
    description: "Delicious burger with cheese",
    imgSrc:
      "https://www.foodiesfeed.com/wp-content/uploads/2023/06/burger-with-melted-cheese.jpg",
  },
];

const RestaurantDetails = () => {
  const [foodType, setFoodType] = useState("all");
  const [category, setCategory] = useState("");

  const handleFoodTypeChange = (e) => {
    setFoodType(e.target.value);
  };

  const handleCategoryChange = (e) => {
    setCategory(e.target.value);
  };

  return (
    <div className="px-5 lg:px-20 mt-5">
      {/* Restaurant Details */}
      <section>
        <div>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <img
                className="w-full h-[40vh] object-cover"
                src="https://assets.cntraveller.in/photos/63d8e5103d7229d4cf308f01/16:9/w_1024%2Cc_limit/Prequel-lead.jpg"
                alt="Restaurant exterior"
              />
            </Grid>
            <Grid item xs={12} lg={6}>
              <img
                className="w-full h-[40vh] object-cover"
                src="https://b.zmtcdn.com/data/pictures/9/113249/0c361b1041d8d0c7e7e139a9db4ab26f.jpg?fit=around|960:500&crop=960:500;*,*"
                alt="Restaurant interior 1"
              />
            </Grid>
            <Grid item xs={12} lg={6}>
              <img
                className="w-full h-[40vh] object-cover"
                src="https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg"
                alt="Restaurant interior 2"
              />
            </Grid>
          </Grid>
        </div>
        <div className="pt-3 pb-5 flex flex-col gap-3">
          <h1 className="text-4xl font-semibold">Dinner Point</h1>
          <p className="text-gray-500 mt-1">
            Lorem, ipsum dolor sit amet consectetur adipisicing elit.
            Reprehenderit dolorum quam molestias. Mollitia laborum cum incidunt
            voluptas voluptatibus odit qui magnam minima id a ut temporibus,
            inventore dolor exercitationem iure?
          </p>
          <div className="space-y-3 mt-3">
            <p className="text-gray-500 flex items-center gap-3">
              <PlaceIcon />
              <span>Maninagar, Ahmedabad</span>
            </p>
            <p className="text-gray-500 flex items-center gap-3">
              <EventIcon />
              <span>Mon-Sun 9:00 AM - 9:00 PM (Today)</span>
            </p>
          </div>
        </div>
      </section>
      <Divider />

      {/* Restaurant Food Details */}
      <section className="pt-[2rem] lg:flex relative">
        <div className="space-y-10 lg:w-[20%] filter">
          <div className="box space-y-5 lg:sticky top-28 p-10 shadow-md">
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Food Type
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup
                  name="foodType"
                  value={foodType}
                  onChange={handleFoodTypeChange}
                >
                  {foodTypes.map((item) => (
                    <FormControlLabel
                      key={item.value}
                      value={item.value}
                      control={<Radio />}
                      label={item.label}
                    />
                  ))}
                </RadioGroup>
              </FormControl>
            </div>
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Category
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup
                  name="category"
                  value={category}
                  onChange={handleCategoryChange}
                >
                  {categories.map((item) => (
                    <FormControlLabel
                      key={item}
                      value={item}
                      control={<Radio />}
                      label={item}
                    />
                  ))}
                </RadioGroup>
              </FormControl>
            </div>
          </div>
        </div>

        <div className="space-y-5 lg:w-[80%] lg:pl-10">
          {menu.map((item) => (
            <MenuCard
              key={item.id}
              name={item.name}
              price={item.price}
              description={item.description}
              imgSrc={item.imgSrc}
            />
          ))}
        </div>
      </section>
    </div>
  );
};

export default RestaurantDetails;
