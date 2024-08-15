import {
  ADD_ITEM_TO_CART_FAILURE,
  ADD_ITEM_TO_CART_REQUEST,
  ADD_ITEM_TO_CART_SUCCESS,
  CLEAR_CART_FAILURE,
  CLEAR_CART_REQUEST,
  CLEAR_CART_SUCCESS,
  FIND_CART_FAILURE,
  FIND_CART_REQUEST,
  FIND_CART_SUCCESS,
  GET_ALL_CART_ITEMS_FAILURE,
  GET_ALL_CART_ITEMS_REQUEST,
  GET_ALL_CART_ITEMS_SUCCESS,
  REMOVE_CARTITEM_FAILURE,
  REMOVE_CARTITEM_REQUEST,
  REMOVE_CARTITEM_SUCCESS,
  UPDATE_CARTITEM_FAILURE,
  UPDATE_CARTITEM_REQUEST,
  UPDATE_CARTITEM_SUCCESS,
} from "./ActionTypes";
import { api } from "../../config/api";

export const findCart = (token) => {
  return async (dispatch) => {
    dispatch({ type: FIND_CART_REQUEST });
    try {
      const response = await api.get(`/api/cart/`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      dispatch({ type: FIND_CART_SUCCESS, paylaod: response.data });
    } catch (error) {
      dispatch({ type: FIND_CART_FAILURE, paylaod: error });
    }
  };
};
export const getAllCartItems = (requestData) => {
  return async (dispatch) => {
    dispatch({ type: GET_ALL_CART_ITEMS_REQUEST });
    try {
      const response = await api.get(`/api/carts/${requestData.cardId}/items`, {
        headers: {
          Authorization: `Bearer ${requestData.token}`,
        },
      });
      dispatch({ type: GET_ALL_CART_ITEMS_SUCCESS, paylaod: response.data });
    } catch (error) {
      dispatch({ type: GET_ALL_CART_ITEMS_FAILURE, paylaod: error });
    }
  };
};
export const addItemToCart = (requestData) => {
  return async (dispatch) => {
    dispatch({ type: ADD_ITEM_TO_CART_REQUEST });
    try {
      const data = await api.put(`/api/cart/add`, requestData.cartItem, {
        headers: {
          Authorization: `Bearer ${requestData.token}`,
        },
      });
      dispatch({ type: ADD_ITEM_TO_CART_SUCCESS, paylaod: data });
    } catch (error) {
      console.log("Add Item TO CArt : ", error);
      dispatch({ type: ADD_ITEM_TO_CART_FAILURE, paylaod: error.message });
    }
  };
};

export const updateCartItem = (requestData) => {
  return async (dispatch) => {
    dispatch({ type: UPDATE_CARTITEM_REQUEST });
    try {
      const { data } = await api.put(
        `/api/cart-item/update`,
        requestData.date,
        {
          headers: {
            Authorization: `Bearer ${requestData.jwt}`,
          },
        }
      );
      console.log("Update cartitem ", data);
      dispatch({ type: UPDATE_CARTITEM_SUCCESS, paylaod: data });
    } catch (error) {
      console.log("Add Item TO CArt : ", error);
      dispatch({ type: UPDATE_CARTITEM_FAILURE, paylaod: error.message });
    }
  };
};

export const removeCartItem = ({ cartItemId, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: REMOVE_CARTITEM_REQUEST });
    try {
      const { data } = await api.delete(`/api/cart-item/${cartItemId}/remove`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      console.log("remove cartitem ", data);
      dispatch({ type: REMOVE_CARTITEM_SUCCESS, paylaod: cartItemId });
    } catch (error) {
      console.log("remove TO CArt : ", error);
      dispatch({ type: REMOVE_CARTITEM_FAILURE, paylaod: error.message });
    }
  };
};

export const clearCartAction = () => {
  return async (dispatch) => {
    dispatch({ type: CLEAR_CART_REQUEST });
    try {
      const { data } = await api.put(
        `/api/cart/clear`,
        {},
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt")}`,
          },
        }
      );
      dispatch({ type: CLEAR_CART_SUCCESS, paylaod: data });
      console.log("clear cart : ", data);
    } catch (error) {
      console.log("Catch Error : ", error);
      dispatch({ type: CLEAR_CART_FAILURE, paylaod: error.message });
    }
  };
};
