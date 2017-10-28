import React from 'react';
import SignupForm from "../components/SignupForm";
import { connect } from 'react-redux';
import { userSignUpRequest } from '../actions/userSignupActions';

class SignupPage extends React.Component {

    render() {
        const { userSignUpRequest } = this.props;
        return (
            <SignupForm userSignUpRequest={userSignUpRequest}/>
        );
    }

}

SignupPage.propTypes = {
    userSignUpRequest: React.PropTypes.func.isRequired
}


export default connect(null, { userSignUpRequest })(SignupPage);
