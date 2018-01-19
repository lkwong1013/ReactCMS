/**
 * In this file, we create a React component
 * which incorporates components provided by Material-UI.
 */
import React, {Component} from 'react';

import RaisedButton from 'material-ui/RaisedButton';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import MenuItem from 'material-ui/MenuItem';
import Drawer from 'material-ui/Drawer';
import { IndexLink, Link } from "react-router";

import {
    cyan500, cyan700, deepOrange500,
    pinkA200,
    grey100, grey300, grey400, grey500,
    white, darkBlack, fullBlack,
} from 'material-ui/styles/colors';


const styles = {

    container: {
        //textAlign: 'center',
        // paddingTop: 200,
    },
};

const muiTheme = getMuiTheme({
    palette: {
        accent1Color: cyan500,
        accent2Color: pinkA200,
    },
});

const CLOSED_DRAWER_CONTENT_LEFT_MARGIN = '50px';
const OPENED_DRAWER_CONTENT_LEFT_MARGIN = '300px';
const APP_BAR_TOP_MARGIN = '-100px';
const CONTENT_TOP_MARGIN = '100px';

class Main extends React.Component {
    constructor(props, context) {
        super(props, context);

        this.state = {
            openDialog: false,
            openDrawer: true,
            contentMarginLeft: (window.innerWidth < 600) ? CLOSED_DRAWER_CONTENT_LEFT_MARGIN : OPENED_DRAWER_CONTENT_LEFT_MARGIN,
            theme: muiTheme,
        };

        this.handler = this.handler.bind(this);

    }

    componentDidMount() {
        this.updateWindowDimensions();
        window.addEventListener('resize', this.updateWindowDimensions.bind(this));
    }

    updateWindowDimensions() {
        if (window.innerWidth < 600) {
            this.setState({
                openDrawer: false,
                contentMarginLeft: CLOSED_DRAWER_CONTENT_LEFT_MARGIN
            });
        } else {
            this.setState({
                openDrawer: true,
                contentMarginLeft: OPENED_DRAWER_CONTENT_LEFT_MARGIN
            });
        }
    }

    handleSelectedMenu = () => {
        // this.updateWindowDimensions.bind(this);
        this.updateWindowDimensions();

    }

    handleDrawerToggle = () => {
        this.setState({
            openDrawer: !this.state.openDrawer
        })
        this.setState({
            contentMarginLeft: window.innerWidth < 600 ? CLOSED_DRAWER_CONTENT_LEFT_MARGIN : !this.state.openDrawer ? OPENED_DRAWER_CONTENT_LEFT_MARGIN : CLOSED_DRAWER_CONTENT_LEFT_MARGIN
        })
        console.log(this.state.openDrawer);
    };

    handler = (e) => {
        this.setState({
            test: 'test'
        })
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
            <MuiThemeProvider muiTheme={muiTheme}>
                <div style={styles.container}>

                    <div>
                        <AppBar
                            title="Title Title"
                            iconClassNameRight="muidocs-icon-navigation-expand-more"
                            onLeftIconButtonTouchTap={this.handleDrawerToggle}
                            zDepth={1}
                            style={{backgroundColor:'#4CAF50', zIndex: 1600, position:'fixed', marginTop:APP_BAR_TOP_MARGIN}}
                        />

                        {/*Global Dialog Box*/}
                        <Dialog
                            open={this.state.openDialog}
                            title={this.state.dialogHeader}
                            actions={standardActions}
                            onRequestClose={this.handleRequestClose}
                        >
                            {this.state.dialogMessage}
                        </Dialog>


                        <Drawer
                            open={this.state.openDrawer}
                            docked={true}
                        >
                            <div className="sidebar-menu" style={{height: '100%', backgroundColor: '#eee'}}>
                                <div style={{paddingTop: '65px'}}>
                                    <MenuItem onTouchTap={this.handleSelectedMenu}>Menu Item</MenuItem>
                                    <MenuItem onTouchTap={this.handleSelectedMenu}>Menu Item 2</MenuItem>
                                    <Link to="firstPage" >
                                        <MenuItem onTouchTap={this.handleSelectedMenu}>First Page</MenuItem>
                                    </Link>
                                    <Link to="secondPage" >
                                        <MenuItem onTouchTap={this.handleSelectedMenu}>Second Page</MenuItem>
                                    </Link>
                                    <Link to="countryForm" onClick={this.handler}>
                                        <MenuItem onTouchTap={this.handleSelectedMenu}>Country Form</MenuItem>
                                    </Link>
                                    <Link to="userForm" onClick={this.handler}>
                                        <MenuItem onTouchTap={this.handleSelectedMenu}>User Form</MenuItem>
                                    </Link>
                                    <Link to="listTest" onClick={this.handler}>
                                        <MenuItem onTouchTap={this.handleSelectedMenu}>List Test</MenuItem>
                                    </Link>
                                </div>
                            </div>
                        </Drawer>
                    </div>
                    <div className="content" style={{marginLeft:this.state.contentMarginLeft, marginTop:CONTENT_TOP_MARGIN}}>
                        {this.props.children}
                    </div>
                </div>
            </MuiThemeProvider>
        );
    }
}

export default Main;