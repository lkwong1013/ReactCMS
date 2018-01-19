
import React from 'react';
import { Router, Route, IndexRoute, hashHistory } from "react-router";
import { Main } from "./Main";
import SecondPage from "./SecondPage";
import FirstPage from "./FirstPage";
import CountryForm from "./CountryForm";
import UserForm from "./UserForm";
import ListTest from "./ListTest";

export default (
    <Route path="/" component={Main}>
        <IndexRoute component={ListTest} />
        {/*<Route path="secondPage" name="secondPage" component={<SecondPage/>} />*/}
        {/*<Route path="firstPage" name="firstPage" component={<FirstPage/>} />*/}
        {/*<Route path="countryForm" name="countryForm" component={<CountryForm/>} />*/}
        {/*<Route path="userForm" name="userForm" component={<UserForm/>} />*/}
        <Route path="listTest" name="listTest" component={ListTest} />
    </Route>
)
