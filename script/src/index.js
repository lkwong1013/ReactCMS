// import React from 'react';
// import ReactDOM from 'react-dom';
// import './www/main.css';
// import App from './app/app';
// import registerServiceWorker from './registerServiceWorker';
//
// ReactDOM.render(<App />, document.getElementById('root'));
// registerServiceWorker();


import React from 'react';
import {render} from 'react-dom';
import injectTapEventPlugin from 'react-tap-event-plugin';
import Main from './app/Main'; // Our custom react component

import { Router, Route, IndexRoute, hashHistory } from "react-router";
import SecondPage from "./app/SecondPage";
import FirstPage from "./app/FirstPage";
import CountryForm from "./app/CountryForm";
import UserForm from "./app/UserForm";
import ListTest from "./app/ListTest";
import { Provider } from "react-redux"
import store from "./app/base/store"
import './www/main.css';

import registerServiceWorker from './registerServiceWorker';
// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

// Render the main app react component into the app div.
// For more details see: https://facebook.github.io/react/docs/top-level-api.html#react.render
render(
    <Provider store={store}>
        <Router history={hashHistory}>
            <Route path="/" component={Main}>
                <IndexRoute component={SecondPage}></IndexRoute>
                <Route path="secondPage" name="secondPage" component={SecondPage}></Route>
                <Route path="firstPage" name="firstPage" component={FirstPage}></Route>
                <Route path="countryForm" name="countryForm" component={CountryForm}></Route>
                <Route path="userForm" name="userForm" component={UserForm}></Route>
                <Route path="listTest" name="listTest" component={ListTest}></Route>
            </Route>
        </Router>
    </Provider>,
    document.getElementById('root')
);
registerServiceWorker();
