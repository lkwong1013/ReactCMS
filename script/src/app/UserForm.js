/**
 * Created by LKW on 2017/4/9.
 */
import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import BasicComponent from './BaiscComponent';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import CircularProgress from 'material-ui/CircularProgress';

export default class UserForm extends React.Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            openDialog: false,
            dialogMessage: "",
            dialogHeader: "",
            successCreation : "",
            handleRequestCloseParent : this.handleRequestCloseParent.bind(this),
            openCircularProgress : 'none',
            userName : "",
            firstName : "",
            lastName : "",
            password : "",
            role : "",
            email : "",
        };
    }

    handleTouchTap = () => {
        this.setState({
            openDialog: true,
        });
    }

    handleRequestCloseParent = () => {
        this.setState({
            openDialog: false,
        });
    }

    onUserNameChange = (e) => {
        this.setState({userName: e.target.value});
    }

    onFirstNameChange = (e) => {
        this.setState({firstName: e.target.value});
    }

    onLastNameChange = (e) => {
        this.setState({lastName: e.target.value});
    }

    onPasswordChange = (e) => {
        this.setState({password: e.target.value});
    }

    onRoleChange = (e) => {
        this.setState({role: e.target.value});
    }

    onEmailChange = (e) => {
        this.setState({email: e.target.value});
    }

    onSave = (e) => {
        console.log(this.state);

        this.setState({
            openCircularProgress : 'inline-block',
        });

        var x = this.child.getAPIPath();

        fetch(x.apiPath + "/mongotest/api/addSubmit/", {
                method: "POST",
                body: new FormData(document.getElementById('userForm')),
            }
        ).then(function(returnedValue) {
            return returnedValue.json();
        }).then((json) => {
            console.log(json);

            if (json.status != 200) {

            }

            this.setState({
                dialogMessage: json.message,
                dialogHeader: json.status == 200 ? "Success" : "Warning",
                openDialog: true,
                userName: json.status == 200 ? "" : this.state.userName,        // Clear input field
                firstName: json.status == 200 ? "" : this.state.firstName,
                lastName: json.status == 200 ? "" : this.state.lastName,
                password: json.status == 200 ? "" : this.state.password,
                role: json.status == 200 ? "" : this.state.role,
                email: json.status == 200 ? "" : this.state.email,
                openCircularProgress : 'none',
            })

        }).catch((error) => {
            this.setState({
                dialogMessage: "Server error",
                dialogHeader: "Warning",
                openDialog: true,
                openCircularProgress : 'none',
            })
            console.log(error);
        });

        e.preventDefault();
    }

    componentDidMount() {

        this.setState({
            openDialog : false,
        });
        console.log("User Form : " + this.state.openDialog);
    }

    render() {

        const standardActions = (
            <FlatButton
                label="Ok"
                primary={true}
                onTouchTap={this.handleRequestClose}
            />
        );

        return (
            <div>

                {
                    this.state.successCreation == "true" ?
                        <div className='alert alert-success'>
                            User was saved.
                        </div>
                        : null
                }

                {
                    this.state.successCreation == "false" ?
                        <div className='alert alert-danger'>
                            Unable to save User. Please try again.
                        </div>
                        : null
                }

                <BasicComponent state={this.state}
                    ref={(child) => {this.child = child;}}/> {/* Pass state to BasicComponent to control component config*/}
                <h1>User</h1>
                <h2>Create New User</h2>

                <form id="userForm" onSubmit={this.onSave}>
                    <div className="col-md-12">
                        <div className="row"><span>User Name</span></div>
                        <div className="row">
                            <TextField
                                name="userName"
                                id="userName"
                                required = {true}
                                hintText="e.g. Test"
                                ref="userName"
                                onChange={this.onUserNameChange}
                                value={this.state.userName}
                            />
                        </div>
                        <div className="row"><span>First Name</span></div>
                        <div className="row">
                            <TextField
                                name="firstName"
                                id="firstName"
                                required = {true}
                                type="text"
                                hintText="e.g. Mo"
                                ref="firstName"
                                onChange={this.onFirstNameChange}
                                value={this.state.firstName}
                            />
                        </div>
                        <div className="row"><span>Last Name</span></div>
                        <div className="row">
                            <TextField
                                name="lastName"
                                id="lastName"
                                required = {true}
                                type="text"
                                hintText="e.g. Fuk"
                                ref="lastName"
                                onChange={this.onLastNameChange}
                                value={this.state.lastName}
                            />
                        </div>
                        <div className="row"><span>Password</span></div>
                        <div className="row">
                            <TextField
                                name="password"
                                id="password"
                                required = {true}
                                type="password"
                                ref="password"
                                onChange={this.onPasswordChange}
                                value={this.state.password}
                            />
                        </div>
                        <div className="row"><span>Role</span></div>
                        <div className="row">
                            <TextField
                                name="role"
                                id="role"
                                required = {true}
                                type="text"
                                hintText="e.g. Role 1"
                                ref="firstName"
                                onChange={this.onRoleChange}
                                value={this.state.role}
                            />
                        </div>
                        <div className="row"><span>E-mail</span></div>
                        <div className="row">
                            <TextField
                                name="email"
                                id="email"
                                required = {true}
                                type="text"
                                hintText="e.g. xxx@xxx.com"
                                ref="email"
                                onChange={this.onEmailChange}
                                value={this.state.email}
                            />
                        </div>
                        <div className="row">
                            <RaisedButton
                                onClick={this.onSave.bind(this)}
                                label="Submit"
                                secondary={true}
                                type="submit" />
                        </div>
                        <div className="row">
                            <br/>
                            <CircularProgress
                                style={{display : this.state.openCircularProgress}}
                            />
                        </div>
                    </div>
                </form>
            </div>

        )

    }
}