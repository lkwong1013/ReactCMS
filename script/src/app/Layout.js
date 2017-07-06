/**
 * Created by LKW on 2017/2/26.
 */
import React from "react";
// import { Link } from "react-router";

// import Footer from "../components/layout/Footer";
// import Nav from "../components/layout/Nav";
// import Header from "../components/layout/Header";

export default class Layout extends React.Component {

    constructor(props, context) {
        super(props, context);

        const CLOSED_DRAWER_CONTENT_LEFT_MARGIN = '50px';
        const OPENED_DRAWER_CONTENT_LEFT_MARGIN = '300px';

        this.state = {
            openDialog: false,
            openDrawer: true,
            contentMarginLeft: (window.innerWidth < 600) ? CLOSED_DRAWER_CONTENT_LEFT_MARGIN : OPENED_DRAWER_CONTENT_LEFT_MARGIN,
        };
    }

    render() {
        const { location } = this.props;
        const containerStyle = {
            marginTop: "60px"
        };
        //console.log("layout");
        return (

            <div>

                {/*<Nav location={location} />*/}
                {/*<Header />*/}

                <div className="row">
                    <div style={{marginLeft:this.state.contentMarginLeft}}>
                        {/*<h1>KillerNews.net</h1>*/}
                        {this.props.children}
                    </div>
                </div>
                {/*<Footer/>*/}
            </div>


        );
    }
}
