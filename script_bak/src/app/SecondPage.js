/**
 * Created by LKW on 2017/2/26.
 */
import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import Dialog from 'material-ui/Dialog';
import {deepOrange500} from 'material-ui/styles/colors';
import {cyan500} from 'material-ui/styles/colors';
import FlatButton from 'material-ui/FlatButton';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import MenuItem from 'material-ui/MenuItem';
import Drawer from 'material-ui/Drawer';


export default class SecondPage extends React.Component {

    constructor(prop) {
        super(prop);

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
                <h1>Second Page</h1>
                <h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                    Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                    Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</h2>
                <RaisedButton
                    label="Super Secret Password"
                    secondary={true}
                    onTouchTap={this.handleTouchTap}
                />
            </div>
        )

    }
}