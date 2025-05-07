import { type } from "@testing-library/user-event/dist/type";
import { api } from "../../config/api";
import {
  CREATE_MENU_ITEM_FAILURE,
  CREATE_MENU_ITEM_REQUEST,
  CREATE_MENU_ITEM_SUCCESS,
  DELETE_MENU_ITEM_FAILURE,
  DELETE_MENU_ITEM_REQUEST,
  DELETE_MENU_ITEM_SUCCESS,
  GET_MENU_ITEMS_BY_RESTAURANTS_ID_FAILURE,
  GET_MENU_ITEMS_BY_RESTAURANTS_ID_REQUEST,
  GET_MENU_ITEMS_BY_RESTAURANTS_ID_SUCCESS,
  SEARCH_MENU_ITEM_FAILURE,
  SEARCH_MENU_ITEM_REQUEST,
  SEARCH_MENU_ITEM_SUCCESS,
  UPDATE_MENU_ITEMS_AVAILABILITY_FAILURE,
  UPDATE_MENU_ITEMS_AVAILABILITY_REQUEST,
  UPDATE_MENU_ITEMS_AVAILABILITY_SUCCESS,
} from "./ActionType";

export const createMenuItem = ({ menu, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: CREATE_MENU_ITEM_REQUEST });
    try {
      const { data } = await api.post("api/admin/food", menu, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      console.log("created menu ", data);
      dispatch({ type: CREATE_MENU_ITEM_SUCCESS, payload: data });
    } catch (error) {
      console.log("catch error ", error);
      dispatch({ type: CREATE_MENU_ITEM_FAILURE ,error:error});
    }
  };
};

export const getMenuItemsByRestaurantsID = (requestData) => {
  return async (dispatch) => {
    dispatch({ type: GET_MENU_ITEMS_BY_RESTAURANTS_ID_REQUEST });
    try {
      const { data } = await api.get(
        `/api/food/restaurant/${requestData.restaurantId}?vegetarian=${requestData.vegetarian}
        &nonveg=${requestData.nonveg}
        &food_category=${requestData.foodCategory}`,
        {
          headers: {
            Authorization: `Bearer ${requestData.jwt}`,
          },
        }
      );
      console.log("menu items by restaurant ", data);
      dispatch({
        type: GET_MENU_ITEMS_BY_RESTAURANTS_ID_SUCCESS,
        payload: data,
      });
    } catch (error) {
      console.log("catch error ", error);
      dispatch({ type: GET_MENU_ITEMS_BY_RESTAURANTS_ID_FAILURE,error:error });
    }
  };
};

export const searchMenuItem = ({ keyword, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: SEARCH_MENU_ITEM_REQUEST });
    try {
      const { data } = await api.get(`api/food/search?name=${keyword}`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });

      console.log("Date ----- ", data);
      dispatch({ type: SEARCH_MENU_ITEM_SUCCESS, payload: data });
    } catch (error) {
      console.log(error);
      dispatch({ type: SEARCH_MENU_ITEM_FAILURE,error:error });
    }
  };
};

// export const getAllIngredientsOfMenuItem = (requestData) => {
//     return async (dispatch) => {
//       dispatch({ type: GET_MENU });
//       try {
//         const { data } = await api.get(`api/food/restaurant/${requestData.restaurantId}`, {
//           headers: {
//             Authorization: `Bearer ${requestData.jwt}`,
//           },
//         });

//         console.log("Date ----- ", data);
//         dispatch({ type: SEARCH_MENU_ITEM_SUCCESS ,payload:data});
//       } catch (error) {
//         console.log(error);
//         dispatch({ type: SEARCH_MENU_ITEM_FAILURE });
//       }
//     };
//   };

export const updateMenuItemAvailability = ({ foodId, jwt }) => {
  return async (dispatch) => {
    dispatch({ type: UPDATE_MENU_ITEMS_AVAILABILITY_REQUEST });
    try {
      const { data } = await api.get(
        `/api/admin/food/${foodId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );
      console.log("Update MenuItem Availability ",data);
      dispatch({type:UPDATE_MENU_ITEMS_AVAILABILITY_SUCCESS,payload:data});
    } catch (error) {
        console.log("Error : ",error);
        dispatch({type:UPDATE_MENU_ITEMS_AVAILABILITY_FAILURE,error:error});
    }
  };
};

export const deleteFoodAction = ({ foodId, jwt }) => {
    return async (dispatch) => {
      dispatch({ type: DELETE_MENU_ITEM_REQUEST });
      try {
        const { data } = await api.get(
          `/api/admin/food/${foodId}`,
          {},
          {
            headers: {
              Authorization: `Bearer ${jwt}`,
            },
          }
        );
        console.log("Update MenuItem Availability ",data);
        dispatch({type:DELETE_MENU_ITEM_SUCCESS,payload:foodId});
      } catch (error) {
          console.log("Error : ",error);
          dispatch({type:DELETE_MENU_ITEM_FAILURE,error:error});
      }
    };
  };
  
