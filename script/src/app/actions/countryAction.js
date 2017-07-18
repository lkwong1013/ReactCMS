/**
 * Created by LKW on 2017/7/18.
 */
import axios from "axios";

export function submitCountry(apiPath, data) {
    return function(dispatch) {
        axios.post('http://localhost:8123/react-cms/test/', {
            firstName: 'SSS',
            lastName: 'Flintstone'
        }).then((response) => {
            dispatch({type: "FETCH_COUNTRY_SUBMIT", payload: response.data})
        })
        .catch((err) => {
            dispatch({type: "FETCH_COUNTRY_REJECTED", payload: err})
        })


        // axios.get("http://rest.learncode.academy/api/test123/tweets")
        //     .then((response) => {
        //         dispatch({type: "FETCH_TWEETS_FULFILLED", payload: response.data})
        //     })
        //     .catch((err) => {
        //         dispatch({type: "FETCH_TWEETS_REJECTED", payload: err})
        //     })
    }
}
