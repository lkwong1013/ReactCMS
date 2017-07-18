/**
 * Created by LKW on 2017/3/4.
 */
/**
 * Created by LKW on 2017/2/26.
 */
/**
 * Created by LKW on 2017/2/26.
 */
import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import BasicComponent from './BaiscComponent';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import CircularProgress from 'material-ui/CircularProgress';
import { connect } from "react-redux"
import { setting } from "./actions"
import { country } from "./actions"

// Initial the state from reducer
@connect((store) => {
    return {
        setting: store.setting.setting,
        dialog: store.setting.dialog,
        userFetched: store.setting.fetched,
        country: store.country.country,
    };
})
export default class CountryForm extends React.Component {

    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            openDialog: this.props.setting.openDialog,
            successCreation : "",
            countryName : "",
            countryCode : "",
            handleRequestCloseParent : this.handleRequestCloseParent.bind(this),
            //openCircularProgress : this.props.setting.openCircularProgress,
            //apiPath : props.apiPath,
        };
    }

    componentWillMount() {
        this.props.dispatch(setting.fetchUser())
        this.props.dispatch(setting.setUserName('Max'))
        this.props.dispatch(setting.fetchUser())
        //this.props.dispatch(setCircularProgress('inline-block'))
    }

    handleTouchTap = () => {
        // this.setState({
        //     openDialog: true,
        // });
        this.props.dispatch(setting.setIsOpenDialog(true))
    }

    handleRequestCloseParent = () => {
        /*this.setState({
            openDialog: false,
        });*/
        // this.props.dispatch(setting.setIsOpenDialog(false))
        // Closed by BasicComponent
    }

    onCountryCodeChange = (e) => {
        this.setState({countryCode: e.target.value});
    }

    onCountryNameChange = (e) => {
        this.setState({countryName: e.target.value});
    }

    onSave = (e) => {
        console.log("API Path :" + this.state.apiPath);
        this.props.dispatch(setting.setCircularProgress('inline-block'))
        console.log("Redux Test : fetched - " + this.props.userFetched);
        console.log("Redux Test - openCircularProgress " + this.props.setting.openCircularProgress);
        // this.setState({
        //     openCircularProgress : 'inline-block',
        // });

        // var x = this.child.getAPIPath();
        // console.log(x.apiPath);

        // fetch(x.apiPath + "/country/api/addSubmit/", {
        // fetch(this.props.setting.apiPath + "/country/api/addSubmit/", {
        this.props.dispatch(country.submitCountry('',''))
            .then(function(returnedValue) {
            console.log(returnedValue);
        });

        fetch(this.props.setting.apiPath + "/test/", {
                method: "POST",
                body: new FormData(document.getElementById('countryForm')),
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
                // openDialog: true,
                countryCode: json.status == 200 ? "" : this.state.countryCode,        // Clear input field
                countryName: json.status == 200 ? "" : this.state.countryName,
                openCircularProgress : 'none',
            })
            this.props.dispatch(setting.setDialog(json.status == 200 ? "Success" : "Warning", json.message))
            this.props.dispatch(setting.setIsOpenDialog(true))
            this.props.dispatch(setting.setCircularProgress('none'))

        }).catch((error) => {
            this.setState({
                // dialogMessage: "Server error",
                dialogHeader: "Warning",
                // openDialog: true,
                openCircularProgress : 'none',
            })

            this.props.dispatch(setting.setDialog("Warning", "Server Error"))
            this.props.dispatch(setting.setIsOpenDialog(true))
            this.props.dispatch(setting.setCircularProgress('none'))
            console.log(error);
        });

        e.preventDefault();
    }

    componentDidMount() {
        this.props.dispatch(setting.setIsOpenDialog(false))
        console.log("Country Form : Open Dialog - " + this.props.setting.openDialog);
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
                            Country was saved.
                        </div>
                        : null
                }

                {
                    this.state.successCreation == "false" ?
                        <div className='alert alert-danger'>
                            Unable to save country. Please try again.
                        </div>
                        : null
                }

                <BasicComponent state={this.state}
                    ref={(child) => {this.child = child;}}/> {/* Pass state to BasicComponent to control component config*/}
                <h1>Country</h1>
                <h2>Create New Country</h2>

                <form id="countryForm" onSubmit={this.onSave}>
                    <div className="col-md-12">
                        <div className="row"><span>Country Code</span></div>
                        <div className="row">
                            <TextField
                                name="countryCode"
                                id="countryCode"
                                required = {true}
                                hintText="Hint Text 1"
                                ref="countryCode"
                                onChange={this.onCountryCodeChange}
                                value={this.state.countryCode}
                                />
                        </div>
                        <div className="row"><span>Country Name</span></div>
                        <div className="row">
                            <TextField
                                name="countryName"
                                id="countryName"
                                required = {true}
                                type="text"
                                hintText="Hint Text 2"
                                ref="countryName"
                                onChange={this.onCountryNameChange}
                                value={this.state.countryName}
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
                                style={{display : this.props.setting.openCircularProgress}}
                            />
                        </div>
                    </div>
                </form>
            </div>

        )

    }
}