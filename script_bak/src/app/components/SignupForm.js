import React from 'react'
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import map from 'lodash/map';
import timezones from '../data/timezone';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import Snackbar from 'material-ui/Snackbar';
import signupValidate from '../validations/signUp';
import TextFieldGroup from '../components/common/TextFieldGroup';


class SignupForm extends React.Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            userName: "",
            password: "",
            email: "",
            timezone: "",
            errors: {},
            isLoading: false,
            // openDialog: false,
            // dialogMessage: "",
            // dialogHeader: "",
            // successCreation : "",
            // countryName : "",
            // countryCode : "",
            // handleRequestCloseParent : this.handleRequestCloseParent.bind(this),
            // openCircularProgress : 'none',
            // apiPath : props.apiPath,
        };

        this.onChange = this.onChange.bind(this);
        this.onSave = this.onSave.bind(this);
        this.onHandleSelectionChange = this.onHandleSelectionChange.bind(this);
    }

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        })
    }


    isValid() {

        const { errors, isValid } = signupValidate(this.state);
        if (!isValid) {
            this.setState({ errors })
        }

        return isValid;
    }

    onSave(e) {
        e.preventDefault();
        console.log(this.state);

        if (this.isValid()) {
            this.setState({
                errors: {},
                isLoading: true
            }); // Reset error msg on every submit

            this.props.userSignUpRequest(this.state).then(
                () => {
                    this.props.addFlashMessage({
                        type: 'success',
                        text: 'Registered'
                    })
                    this.context.router.push('/firstPage') },
                ({data}) => this.setState({errors: data, isLoading: false})
            );
        }
        // axios.put('/auth/register', {
        //     user: this.state
        // })
    }

    onHandleSelectionChange(id, name, evt, key, payload) {
        this.setState({
            [name]: payload
        })

        // console.log("id - " + id);
        // console.log("name - " + name);
        // console.log(evt.value);
        // console.log("key - " + key);
        // console.log("payload - " + payload)
    }

    render() {

        const { errors } = this.state;
        const options = map(timezones, (val, key) =>
            <MenuItem key={val} value={val} primaryText={key} />
        )

        return (
            <div>
                <form id="countryForm" onSubmit={this.onSave}>
                    <div className="col-md-12">
                        { errors.data &&
                        <div>
                            <Snackbar
                                open={ this.state.isLoading }
                                message="Loading..."
                            />
                        </div>}

                        <Snackbar
                            open={ this.state.isLoading }
                            message="Loading..."
                        />
                        <div className="row">
                            <TextFieldGroup
                                field="userName"
                                id="userName"
                                label="User Name"
                                hintText="E.g. apple"
                                value={this.state.userName}
                                error={errors.username || errors.data}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className="row">
                            <TextFieldGroup
                                field="email"
                                id="email"
                                label="E-mail"
                                hintText="E.g. xxx@xxx.com"
                                value={this.state.email}
                                error={errors.email}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className="row">
                            <TextFieldGroup
                                field="password"
                                id="password"
                                type="password"
                                hintText="Password"
                                label="Password"
                                value={this.state.password}
                                error={errors.password}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className="row">
                            <SelectField
                                name="timezone"
                                floatingLabelText="Time Zone"
                                onChange={this.onHandleSelectionChange.bind(null,null,"timezone")}
                                errorText={errors.timezone}
                                value={this.state.timezone}
                            >
                                <MenuItem value="" disabled>Choose Your Timezone</MenuItem>
                                {options}
                            </SelectField>
                        </div>
                        <div className="row">
                            <RaisedButton
                                // onClick={this.onSave.bind(this)}
                                disabled={ this.state.isLoading }
                                label="Submit"
                                secondary={true}
                                type="submit"/>
                        </div>
                    </div>
                </form>

            </div>
        )
    }

}

SignupForm.propTypes = {
    userSignUpRequest: React.PropTypes.func.isRequired,
    addFlashMessage: React.PropTypes.func.isRequired
}

SignupForm.contextTypes = {
    router: React.PropTypes.object.isRequired
}

export default SignupForm;