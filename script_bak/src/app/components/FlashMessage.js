import React from "react";
import Snackbar from 'material-ui/Snackbar';

class FlashMessage extends React.Component {
    render() {
        const { id, type, text } = this.props.message;
        const { isShow } = false;
        return (
            <div>
                <Snackbar
                    open={ text && true }
                    message={text}
                    autoHideDuration={4000}
                    onRequestClose={this.handleRequestClose}
                />
            </div>
        );
    }
}

FlashMessage.propTypes = {
    message: React.PropTypes.object.isRequired
}

export default FlashMessage;