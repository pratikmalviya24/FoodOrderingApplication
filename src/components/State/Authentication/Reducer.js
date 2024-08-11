import { isPresentInFavourites } from "../../config/logic";
import {
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
  REGISTER_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  GET_USER_REQUEST,
  GET_USER_SUCCESS,
  GET_USER_FAILURE,
  ADD_TO_FAVOURITE_REQUEST,
  ADD_TO_FAVOURITE_SUCCESS,
  ADD_TO_FAVOURITE_FAILURE,
  LOGOUT,
} from "./ActionType";

const initialState = {
  user: null,
  isLoading: false,
  error: null,
  jwt: null,
  favorite: [],
  success: null,
};

export const authReducer = (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_REQUEST:
    case LOGIN_REQUEST:
    case GET_USER_REQUEST:
    case ADD_TO_FAVOURITE_REQUEST:
      return {
        ...state,
        isLoading: true,
        error: null,
        success: null,
        user: action.payload,
      };

    case REGISTER_SUCCESS:
    case LOGIN_SUCCESS:
      return {
        ...state,
        isLoading: false,
        success: "Register Success",
        jwt: action.payload,
      };

    case GET_USER_SUCCESS:
      return {
        ...state,
        isLoading: false,
        user: action.payload,
      };

    case REGISTER_FAILURE:
    case LOGIN_FAILURE:
    case GET_USER_FAILURE:
    case ADD_TO_FAVOURITE_FAILURE:
      return {
        ...state,
        isLoading: false,
        error: action.payload,
      };

    case ADD_TO_FAVOURITE_SUCCESS:
      return {
        ...state,
        isLoading: false,
        error: null,
        favorite: isPresentInFavourites(state.favorite, action.payload)
          ? state.favorite.filter((item) => item.id !== action.payload.id)
          : [action.payload, ...state.favorite],
      };

    case LOGOUT:
      return {
        ...state,
        user: null,
        jwt: null,
        favorite: [],
        success: null,
      };

    default:
      return state;
  }
};

export default authReducer;
