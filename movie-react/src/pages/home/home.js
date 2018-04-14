import React, { Component } from 'react';
import './home.scss';
import Header from '../../components/header/header';

class Home  extends Component{
    constructor(){
        super();
    }
    render(){
        return (
            <div className="home-container">
                <Header></Header>
            </div>
        );
    }
};

export default Home;