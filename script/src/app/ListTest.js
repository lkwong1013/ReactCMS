/**
 * Created by LKW on 2017/2/26.
 */
import React, {Component} from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import BasicComponent from './BaiscComponent';
import FlatButton from 'material-ui/FlatButton';
import Divider from 'material-ui/Divider';
import TextField from 'material-ui/TextField';
import CircularProgress from 'material-ui/CircularProgress';
import Pagination from 'materialui-pagination';
import {Card} from 'material-ui/Card';

import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';

const tableData = [
    {
        name: 'John Smith',
        status: 'Employed',
    },
    {
        name: 'Randal White',
        status: 'Unemployed',
    },
    {
        name: 'Stephanie Sanders',
        status: 'Employed',
    },
    {
        name: 'Steve Brown',
        status: 'Employed',
    },
    {
        name: 'Joyce Whitten',
        status: 'Employed',
    },
    {
        name: 'Samuel Roberts',
        status: 'Employed',
    },
    {
        name: 'Adam Moore',
        status: 'Employed',
    },{
        name: 'Randal White',
        status: 'Unemployed',
    },
    {
        name: 'Stephanie Sanders',
        status: 'Employed',
    },
    {
        name: 'Steve Brown',
        status: 'Employed',
    },
    {
        name: 'Joyce Whitten',
        status: 'Employed',
    },
    {
        name: 'Samuel Roberts',
        status: 'Employed',
    },
    {
        name: 'Adam Moore',
        status: 'Employed',
    },{
        name: 'Randal White',
        status: 'Unemployed',
    },
    {
        name: 'Stephanie Sanders',
        status: 'Employed',
    },
    {
        name: 'Steve Brown',
        status: 'Employed',
    },
    {
        name: 'Joyce Whitten',
        status: 'Employed',
    },
    {
        name: 'Samuel Roberts',
        status: 'Employed',
    },
    {
        name: 'Adam Moore',
        status: 'Employed',
    },
];

export default class ListTest extends React.Component {

    constructor(props) {
        super(props);
        console.log("Props" + props.test);
        this.state = {
            openDialog: false,
            dialogMessage: "",
            dialogHeader: "",
            successCreation : "",
            countryName : "",
            countryCode : "",
            handleRequestCloseParent : this.handleRequestCloseParent.bind(this),
            openCircularProgress : 'none',
            apiPath : props.apiPath,
            showTableCheckBox : false,



        };
        this.updateRows = this.updateRows.bind(this);
    }

    updateRows(state) {
        this.setState({
            page: state.page,
            numberOfRows: state.numberOfRows,
        });
        console.log(state);

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

    onCountryCodeChange = (e) => {
        this.setState({countryCode: e.target.value});
    }

    onCountryNameChange = (e) => {
        this.setState({countryName: e.target.value});
    }

    onSave = (e) => {
        console.log("API Path :" + this.state.apiPath);

        this.setState({
            openCircularProgress : 'inline-block',
        });

        var x = this.child.getParent();
        console.log(x.apiPath);

        // fetch(x.apiPath + "/country/api/addSubmit/", {
        fetch(x.apiPath + "/test", {        // Modified for internal app call
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
                openDialog: true,
                countryCode: json.status == 200 ? "" : this.state.countryCode,        // Clear input field
                countryName: json.status == 200 ? "" : this.state.countryName,
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
        var x = this.child.getParent();
        this.setState({
            openDialog : false,
            rowsPerPage: x.defaultRowPerPage,
            rows: [],
            numberOfRows: x.defaultNumberofRows,
            page: 1,
            total: tableData.length
        });
        console.log("Country Form : " + this.state.openDialog);
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
                <h1>List Test</h1>
                {/*<h2>Create New Country</h2>*/}

                <Table>
                    <TableHeader
                        displaySelectAll={this.state.showTableCheckBox}
                        adjustForCheckbox={this.state.showTableCheckBox}>
                        <TableRow>
                            <TableHeaderColumn>ID</TableHeaderColumn>
                            <TableHeaderColumn>Name</TableHeaderColumn>
                            <TableHeaderColumn>Status</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody displayRowCheckbox={this.state.showTableCheckBox}>
                        {tableData.map( (row, index) => (
                            <TableRow key={index}>
                                <TableRowColumn>{index}</TableRowColumn>
                                <TableRowColumn>{row.name}</TableRowColumn>
                                <TableRowColumn>{row.status}</TableRowColumn>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                <Divider />
                <Pagination
                    total={this.state.total}
                    rowsPerPage={this.state.rowsPerPage}
                    page={this.state.page}
                    numberOfRows={this.state.numberOfRows}
                    updateRows={this.updateRows}
                />
            </div>

        )

    }
}