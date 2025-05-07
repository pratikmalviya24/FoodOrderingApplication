import axios from "axios";
import { api, API_URL } from "../../config/api";
import {
  ADD_TO_FAVOURITE_SUCCESS,
  GET_USER_FAILURE,
  GET_USER_SUCCESS,
  LOGIN_FAILURE,
  LOGIN_SUCCESS,
  LOGOUT,
  REGISTER_FAILURE,
  REGISTER_SUCCESS,
} from "./ActionType";


export const registerUser = (reqData) => async (dispatch) => {
  dispatch({ type: "REGISTER_REQUEST" });

  try {
    const { data } = await axios.post(
      `${API_URL}/auth/signup`,
      reqData.userData
    );

    if (data.jwt) {
      localStorage.setItem("jwt", data.jwt);
    }

    const redirectPath = data.role === "ROLE_RESTAURANT_OWNER" 
      ? "/admin/restaurant" 
      : "/";

    reqData.navigate(redirectPath);

    dispatch({ type: REGISTER_SUCCESS, payload: data.jwt });
    console.log("Register Done!!");
  } catch (error) {
    const errorMessage = error.response?.data?.message || error.message || "Registration failed.";
    dispatch({ type: REGISTER_FAILURE, payload: errorMessage });
    console.error("Registration Error:", errorMessage); 
  }
};

export const loginUser = (reqData) => async (dispatch) => {
  dispatch({ type: "LOGIN_REQUEST" });
  try {
    const { data } = await axios.post(
      `${API_URL}/auth/signin`,
      reqData.userData
    );

    if (data.jwt) localStorage.setItem("jwt", data.jwt);

    if (data.role === "ROLE_RESTAURANT_OWNER") {
      reqData.navigate("/admin/restaurant");
    } else {
      reqData.navigate("/");
    }

    dispatch({ type: LOGIN_SUCCESS, payload: data.jwt });
  } catch (error) {
    dispatch({type:LOGIN_FAILURE,payload:error})
    console.log("Error : ", error);
  }
};

export const getUser = (jwt) => async (dispatch) => {
  dispatch({ type: "GET_USER_REQUEST" });
  try {
    const { data } = await api.get(`/api/users/profile`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });

    dispatch({ type: GET_USER_SUCCESS, payload: data });
    console.log("User_Profile :", data);
  } catch (error) {
    dispatch({type:GET_USER_FAILURE,payload:error})
    console.log("Error : ", error);
  }
};

export const addToFavourite =({ jwt, restaurantId }) =>
  async (dispatch) => {
    dispatch({ type: "ADD_TO_FAVOURITE_REQUEST" });
    try {
      const { data } = await api.put(
        `/api/restaurants/${restaurantId}/add-favourite`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      dispatch({ type: ADD_TO_FAVOURITE_SUCCESS, payload: data });
      console.log("ADD_TO_FAVOURITE_SUCCESS :", data);
    } catch (error) {
      console.log("Error : ", error);
    }
  };

export const logout = () => async (dispatch) => {
  dispatch({ type: "LOGIN_REQUEST" });
  try {
    localStorage.clear(); 
    dispatch({ type: LOGOUT });

    console.log("LOGOUT_SUCCESS")
  } catch (error) {
    console.log("Error : ", error);
  }
};
