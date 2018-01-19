import React from 'react';
import {render} from 'react-dom';
import injectTapEventPlugin from 'react-tap-event-plugin';
import Main from './Main'; // Our custom react component

import { Router, Route, IndexRoute, hashHistory } from "react-router";
import SecondPage from "./SecondPage";
import FirstPage from "./FirstPage";
import CountryForm from "./CountryForm";
import UserForm from "./UserForm";
import { Provider } from "react-redux"
import store from "./base/store"
import Layout from "./Layout";
import routes from "./CustomRoutes";
// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

// Render the main app react component into the app div.
// For more details see: https://facebook.github.io/react/docs/top-level-api.html#react.render
render(
    <Provider store={store}>
        <Router history={hashHistory} routes={routes} />
            {/*<Route path="/" component={Main}>*/}
                {/*<IndexRoute component={SecondPage}></IndexRoute>*/}
                {/*<Route path="secondPage" name="secondPage" component={SecondPage}></Route>*/}
                {/*<Route path="firstPage" name="firstPage" component={FirstPage}></Route>*/}
                {/*<Route path="countryForm" name="countryForm" component={CountryForm}></Route>*/}
                {/*<Route path="userForm" name="userForm" component={UserForm}></Route>*/}
            {/*</Route>*/}
        {/*</Router>*/}

    </Provider>,
    document.getElementById('app')
);
