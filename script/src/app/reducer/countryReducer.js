/**
 * Created by LKW on 2017/7/18.
 */
export default function reducer(state={
    country: {
        countryCode: null,
        countryName: null,
    },
    fetching: false,
    error: null,
    result: null,

}, action) {

    switch (action.type) {
        case "FETCH_COUNTRY": {
            return {...state, fetching: true}
        }
        case "FETCH_COUNTRY_FULFILLED": {
            return {...state, fetching: true, result: action.payload}
        }
        case "FETCH_COUNTRY_REJECTED": {
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

    }

    return state
}