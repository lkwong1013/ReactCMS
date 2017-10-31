import React from 'react';
import SignupForm from "../components/SignupForm";
import { connect } from 'react-redux';
import { userSignUpRequest } from '../actions/userSignupActions';
import { addFlashMessage } from "../actions/flashMessages"

class SignupPage extends React.Component {

    render() {
        const { userSignUpRequest, addFlashMessage } = this.props;
        return (
            <SignupForm userSignUpRequest={userSignUpRequest} addFlashMessage={addFlashMessage}/>
        );
    }

}

SignupPage.propTypes = {
    userSignUpRequest: React.PropTypes.func.isRequired,
    addFlashMessage: React.PropTypes.func.isRequired
}


export default connect(null, { userSignUpRequest, addFlashMessage })(SignupPage);
