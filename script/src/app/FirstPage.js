/**
 * Created by LKW on 2017/2/26.
 */
/**
 * Created by LKW on 2017/2/26.
 */
import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import Dialog from 'material-ui/Dialog';
import {deepOrange500} from 'material-ui/styles/colors';
import {cyan500} from 'material-ui/styles/colors';
import FlatButton from 'material-ui/FlatButton';
import AppBar from 'material-ui/AppBar';
import TextField from 'material-ui/TextField';
import Drawer from 'material-ui/Drawer';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

export default class FirstPage extends React.Component {

    constructor(props) {
        super(props);
        console.log(this.props.testData);
        this.state = {
            openDialog: false,
        };
    }

    handleRequestClose = () => {
        this.setState({
            openDialog: false,
        });
    }

    handleTouchTap = () => {
        this.setState({
            openDialog: true,
        });
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
                    <Dialog
                        open={this.state.openDialog}
                        title="Super Secret Password"
                        actions={standardActions}
                        onRequestClose={this.handleRequestClose}
                    >
                        1-2-3-4-5
                    </Dialog>
                    <h1>First Page</h1>
                    <h2>example project</h2>

                    <form>
                        <div className="col-md-12">
                            <div className="row"><span>Test 1</span></div>
                            <div className="row">
                                <TextField
                                    required = {true}
                                    hintText="Hint Text 1" />
                            </div>
                            <div className="row"><span>Test 2 Password</span></div>
                            <div className="row">
                                <TextField
                                    required = {true}
                                    type="password"
                                    hintText="Hint Text 2" />
                            </div>
                            <div className="row"><span>Test 3 Email</span></div>
                            <div className="row">
                                <TextField
                                    type="email"
                                    required = {true}
                                    hintText="Hint Text 3" />
                            </div>
                            <div className="row">
                                <RaisedButton
                                    label="Submit"
                                    secondary={true}
                                    type="submit" />
                            </div>

                        </div>
                    </form>
                </div>

        )

    }
}