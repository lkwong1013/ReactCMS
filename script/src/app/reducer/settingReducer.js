/**
 * Created by LKW on 2017/7/15.
 */

export default function reducer(state={
    setting: {
        id: null,
        name: null,
        age: null,
        openDialog: false,
        dialogMessage: "",
        dialogHeader: "",
        openCircularProgress : 'none',
        apiPath : 'http://localhost:8123/react-cms',
    },
    dialog: {
        dialogHeader: "",
        dialogMessage: "",
    },
    fetching: false,
    fetched: false,
    error: null,

}, action) {

    switch (action.type) {
        case "FETCH_USER": {
            return {...state, fetching: true}
        }
        case "FETCH_USER_REJECTED": {
            return {...state, fetching: false, error: action.payload}
        }
        case "FETCH_USER_FULFILLED": {
            return {
                ...state,
                fetching: false,
                fetched: true,
                user: action.payload,
            }
        }
        case "SET_USER_NAME": {
            return {
                ...state,
                setting: {...state.setting, name: action.payload},
            }
        }
        case "SET_USER_AGE": {
            return {
                ...state,
                setting: {...state.setting, age: action.payload},
            }
        }
        case "SET_CIRCULAR_PROGRESS": {
            return {
                ...state,
                setting: {...state.setting, openCircularProgress: action.payload},
            }
        }
        case "SET_OPEN_DIALOG": {
            return {
                ...state,
                setting: {...state.setting, openDialog: action.payload},
            }
        }
        case "SET_DIALOG" : {
            return {
                ...state,
                dialog: action.payload,
            }
        }
    }

    return state
}
