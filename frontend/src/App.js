import { ThemeProvider } from "@emotion/react";
import "./App.css";
import Navbar from "./components/NavBar/Navbar";
import { darkTheme } from "./Theme/DarkTheme";
import { CssBaseline } from "@mui/material";
import Home from "./components/Home/Home";
import RestaurantDetails from "./components/Restaurant/RestaurantDetails";
import Cart from "./components/Cart/Cart";
import Profile from "./components/Profile/Profile";
import CustomRoute from "./routes/CustomRoute";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./components/State/Authentication/Action";

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);

  useEffect(() => {
    dispatch(getUser(auth.jwt || jwt));
  }, [auth.jwt]);

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      {/* <Navbar /> */}
      {/* <Home/> */}
      {/* <RestaurantDetails /> */}
      {/* <Cart/> */}
      {/* <Profile /> */}
      <CustomRoute />
    </ThemeProvider>
  );
}
export default App;
