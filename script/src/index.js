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


import { Provider } from "react-redux"
import { Routes } from "/routes";
import store from "./app/base/store"
import './www/main.css';

import registerServiceWorker from './registerServiceWorker';
// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

// Render the main app react component into the app div.
// For more details see: https://facebook.github.io/react/docs/top-level-api.html#react.render
// Might obsoleted
render(
    <Provider store={store}>
        <Routes />
    </Provider>,
    document.getElementById('root')
);
registerServiceWorker();
