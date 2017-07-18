/**
 * Created by LKW on 2017/3/4.
 */
import React from "react";
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import CircularProgress from 'material-ui/CircularProgress';
import { connect } from "react-redux"
import { setting } from "./actions"
// Initial the state from reducer
@connect((store) => {
    return {
        setting: store.setting.setting,
        dialog: store.setting.dialog,
        userFetched: store.setting.fetched,
    };
})
export default class BasicComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            //openDialog : false,
            dialogHeader : this.props.state.dialogHeader,
            //dialogMessage : this.props.state.dialogMessage,
            openCircularProgress : this.props.setting.openCircularProgress,
            //apiPath : "http://localhost:8080/BaseProject",
        }
    }

    handleRequestClose = () => {
        // this.setState({
        //     openDialog: false,
        // });
        //this.props.state.handleRequestCloseParent();
        this.props.dispatch(setting.setIsOpenDialog(false))
    }

    componentDidMount() {

        this.setState({
            // openDialog : this.props.state.openDialog,
            dialogHeader : this.props.state.dialogHeader,
            //dialogMessage : this.props.state.dialogMessage,
        });

    }

    componentWillReceiveProps(props) {
        // this.setState({
        //     openDialog: props.state.openDialog
        // });
        //this.props.dispatch(setIsOpenDialog(false))
    }

    getAPIPath() {
        return this.state;
    }


    render() {
        // console.log("this.props.state.openDialog : " + this.props.state.openDialog);
        console.log("this.props.dialog : " + this.props.dialog.dialogHeader);
        console.log("this.state.apiPath : " + this.state.apiPath);
        // console.log("this.state.openDialog : " + this.state.openDialog);
        const standardActions = (
            <FlatButton
                label="Okay"
                primary={true}
                onTouchTap={this.handleRequestClose}
            />
        );

        return (

            <div>
                {/*<CircularProgress*/}
                    {/*style={{display : this.props.state.openCircularProgress}}*/}
                {/*/>*/}
                <Dialog
                    open={this.props.setting.openDialog}
                    title={this.props.dialog.dialogHeader}
                    actions={standardActions}
                    onRequestClose={this.handleRequestClose}
                >
                    {/*{this.props.state.dialogMessage}*/}
                    {this.props.dialog.dialogMessage}
                </Dialog>
            </div>
        );
    }
}