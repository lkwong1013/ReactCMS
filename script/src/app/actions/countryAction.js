/**
 * Created by LKW on 2017/7/18.
 */
import axios from "axios";

export function submitCountry() {
    return function(dispatch) {
        axios.post('http://localhost:8123/react-cms/test/', {
            countryCode: 'SSS',
            countryName: 'Flintstone'
        }).then((response) => {
            dispatch({type: "FETCH_COUNTRY_FULFILLED", payload: response.data})
            console.log(response.data);
        })
        .catch((err) => {
            dispatch({type: "FETCH_COUNTRY_REJECTED", payload: 'Error'})
            console.log(err);
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
