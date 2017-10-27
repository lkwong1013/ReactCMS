import React from 'react'
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import map from 'lodash/map';
import timezones from '../data/timezone';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import axios from 'axios';



class SignupForm extends React.Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            userName: "",
            password: "",
            email: "",
            timezone: "",
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
        console.log(e);
        this.setState({
            [e.target.name]: e.target.value,
        })
    }

    onSave(e) {
        e.preventDefault();
        console.log(this.state);
        axios.put('/auth/register', {
            user: this.state
        })
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
        const options = map(timezones, (val, key) =>
            <MenuItem key={val} value={val} primaryText={key} />
        )

        return (
            <div>
                <form id="countryForm" onSubmit={this.onSave}>
                    <div className="col-md-12">
                        <div className="row"><span>User Name</span></div>
                        <div className="row">
                            <TextField
                                name="userName"
                                id="userName"
                                required={true}
                                hintText="E.g. apple"
                                ref="userName"
                                value={this.state.userName}
                                onChange={this.onChange}
                            />
                        </div><div className="row"><span>Email</span></div>
                        <div className="row">
                            <TextField
                                name="email"
                                id="email"
                                required={true}
                                hintText="E.g. xxx@xxx.com"
                                value={this.state.email}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className="row"><span>Password</span></div>
                        <div className="row">
                            <TextField
                                name="password"
                                id="password"
                                required={true}
                                type="password"
                                hintText="Password"
                                value={this.state.password}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className="row">
                            <SelectField
                                name="timezone"
                                onChange={this.onHandleSelectionChange.bind(null,null,"timezone")}
                                value={this.state.timezone}
                            >
                                <MenuItem value="" disabled>Choose Your Timezone</MenuItem>
                                {options}
                            </SelectField>
                        </div>
                        <div className="row">
                            <RaisedButton
                                // onClick={this.onSave.bind(this)}
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

export default SignupForm;