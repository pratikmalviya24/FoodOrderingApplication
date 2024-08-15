import { api } from "../../config/api";
import {
  CREATE_INGREDIENT_CATEGORY_SUCCESS,
  GET_INGREDIENTS,
  UPDATE_STOCK,
} from "./ActionType";

export const getIngredientsOfRestaurant = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      const response = await api.get(
        `/api/admin/ingredients/restaurant/${id}`,
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      console.log("Get All Ingrediets ", response.data);

      dispatch({
        type: GET_INGREDIENTS,
        payload: response.data,
      });
    } catch (error) {
      console.log("Error : ", error);
    }
  };
};

export const createIngredient = ({ data, jwt }) => {
  return async (dispatch) => {
    try {
      const response = await api.post(`/api/admin/ingredients`, data, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });

      console.log("Create Ingredient ", response.data);
      dispatch({
        type: CREATE_INGREDIENT_CATEGORY_SUCCESS,
        paylaod: response.data,
      });
    } catch (error) {
      console.log("Error : ", error);
    }
  };
};

export const updateStockOfIngredient = ({ id, jwt }) => {
  return async (dispatch) => {
    try {
      const { data } = await api.put(
        `/api/admin/ingredients/${id}/stoke`,
        {},
        {
          headers: {
            Authorization: `Bearer ${jwt}`,
          },
        }
      );

      dispatch({
        type: UPDATE_STOCK,
        paylaod: data,
      });

      console.log("Update Ingredient stock : ", data);
    } catch (error) {
      console.log("Error : ", error);
    }
  };
};


