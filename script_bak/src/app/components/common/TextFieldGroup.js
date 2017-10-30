import React from 'react';
import TextField from 'material-ui/TextField';

const TextFieldGroup = ( { field, value, label, hintText, error, type, onChange } ) => {

    return(
        <div>
            <TextField
                name={field}
                id={field}
                type={type}
                floatingLabelText={label}
                hintText={hintText}
                value={value}
                errorText={error}
                onChange={onChange}
            />
        </div>
    );
}


TextFieldGroup.propTypes = {
    field: React.PropTypes.string.isRequired,
    value: React.PropTypes.string.isRequired,
    label: React.PropTypes.string.isRequired,
    error: React.PropTypes.string,
    hintText: React.PropTypes.string,
    type: React.PropTypes.string.isRequired,
    onChange: React.PropTypes.func.isRequired,
}


TextFieldGroup.defaultProps = {
    type: 'text'
}

export default TextFieldGroup;