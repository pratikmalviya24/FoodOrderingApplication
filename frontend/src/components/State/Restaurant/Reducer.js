const initialState = {
  restaurants: [],
  usersRestaurants: null,
  restaurant: null,
  loading: false,
  error: null,
  events: [],
  restaurantsEvents: [],
  categories: [],
};

const restaurantReducer = (state = initialState, action) => {
  switch (action.type) {
    case action.type.CREATE_RESTAURANT_REQUEST:
    case action.type.GET_ALL_RESTAURANT_REQUEST:
    case action.type.DELETE_RESTAURANT_REQUEST:
    case action.type.UPDATE_RESTAURANT_REQUEST:
    case action.type.GET_RESTAURANT_BY_ID_REQUEST:
    case action.type.CREATE_CATEGORY_REQUEST:
    case action.type.GET_RESTAURANTS_CATEGORY_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };

    case action.type.CREATE_RESTAURANT_SUCCESS:
      return {
        ...state,
        loading: false,
        usersRestaurants: action.payload,
      };

    case action.type.GET_ALL_RESTAURANT_SUCCESS:
      return {
        ...state,
        loading: false,
        restaurants: action.payload,
      };

    case action.type.GET_RESTAURANT_BY_ID_SUCCESS:
      return {
        ...state,
        loading: false,
        restaurant: action.payload,
      };

    case action.type.GET_RESTAURANT_BY_USER_ID_REQUEST:
    case action.type.UPDATE_RESTAURANT_STATUS_REQUEST:
    case action.type.UPDATE_RESTAURANT_SUCCESS:
      return {
        ...state,
        loading: false,
        usersRestaurants: action.payload,
      };

    case action.type.DELETE_RESTAURANT_SUCCESS:
      return {
        ...state,
        error: null,
        loading: false,
        restaurants: state.restaurants.filter(
          (restaurant) => restaurant.id !== action.payload.id
        ),
        usersRestaurants: state.usersRestaurants.filter(
          (item) => item.id !== action.payload
        ),
      };

    case action.type.CREATE_EVENTS_SUCCESS:
      return {
        ...state,
        loading: false,
        events: [...state.events, action.payload],
        restaurantsEvents: [...state.restaurantsEvents, action.payload],
      };

    case action.type.GET_ALL_EVENTS_SUCCESS:
      return {
        ...state,
        loading: false,
        restaurantsEvents: action.payload,
      };

    case action.type.GET_RESTAURANT_EVENTS_SUCCESS:
      return {
        ...state,
        loading: false,
        restaurantsEvents: action.payload,
      };

    case action.type.DELETE_EVENTS_SUCCESS:
      return {
        ...state,
        loading: false,
        events: state.events.filter((item) => item.id !== action.payload),
        restaurantsEvents: state.restaurantsEvents.filter(
          (item) => item.id !== action.payload
        ),
      };

    case action.type.CREATE_CATEGORY_SUCCESS:
      return {
        ...state,
        loading: false,
        categories: [...state.categories, action.payload],
      };

    case action.type.GET_RESTAURANTS_CATEGORY_SUCCESS:
      return {
        ...state,
        loading: false,
        categories: action.payload,
      };

    case action.type.CREATE_RESTAURANT_FAILURE:
    case action.type.GET_ALL_RESTAURANT_FAILURE:
    case action.type.DELETE_RESTAURANT_FAILURE:
    case action.type.UPDATE_RESTAURANT_FAILURE:
    case action.type.GET_RESTAURANT_BY_ID_FAILURE:
    case action.type.CREATE_CATEGORY_FAILURE:
    case action.type.CREATE_EVENTS_FAILURE:
    case action.type.GET_RESTAURANTS_CATEGORY_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload,
      };

    default:
      return state;
  }
};

export default restaurantReducer;
