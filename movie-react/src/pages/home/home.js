import React, { Component } from 'react';
import './home.scss';
import Header from '../../components/header/header';
import Swiper from '../../components/swiper/swiper';

class Home  extends Component{
    constructor(){
        super();
    }
    render(){
        return (
            <div className="home-container">
                <Header></Header>
                <div className="home-banner">
                   <Swiper></Swiper> 
                </div>
            </div>
        );
    }
};

export default Home;