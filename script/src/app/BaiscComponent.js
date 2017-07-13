/**
 * Created by LKW on 2017/3/4.
 */
import React from "react";
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import CircularProgress from 'material-ui/CircularProgress';

export default class BasicComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            openDialog : false,
            dialogHeader : this.props.state.dialogHeader,
            dialogMessage : this.props.state.dialogMessage,
            openCircularProgress : this.props.state.openCircularProgress,
            // apiPath : "http://localhost:8080/BaseProject",
            apiPath : "/react-cms",
            defaultRowPerPage: [5,10,15],
            defaultNumberofRows: 5,
        }
    }

    handleRequestClose = () => {
        // this.setState({
        //     openDialog: false,
        // });
        this.props.state.handleRequestCloseParent();
    }

    componentDidMount() {

        this.setState({
            // openDialog : this.props.state.openDialog,
            dialogHeader : this.props.state.dialogHeader,
            dialogMessage : this.props.state.dialogMessage,
        });

    }

    componentWillReceiveProps(props) {
        this.setState({
            openDialog: props.state.openDialog
        });
    }

    getParent() {
        return this.state;
    }

    getAPIPath() {
        return this.state;
    }


    render() {
        // console.log("this.props.state.openDialog : " + this.props.state.openDialog);
        console.log("this.props.state.openCircularProgress : " + this.props.state.openCircularProgress);
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
                    open={this.state.openDialog}
                    title={this.props.state.dialogHeader}
                    actions={standardActions}
                    onRequestClose={this.handleRequestClose}
                >
                    {this.props.state.dialogMessage}
                </Dialog>
            </div>
        );
    }
}