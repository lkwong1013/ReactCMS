
import axios from 'axios';

export function userSignUpRequest(userData) {
    return dispatch => {
        return axios.post('http://localhost:8080/react-cms/api/auth/dummy', userData);
    }
}