import React, { Component } from 'react';
import ReactSwipe from 'react-swipe'
import './swiper.scss'

class Swiper extends Component{
    constructor(){
        super();
    }
    render(){
        return (
            <ReactSwipe className="carousel" swipeOptions={{continuous: false}}>
                <div className="banner-slide">PANE 1</div>
                <div className="banner-slide">PANE 2</div>
                <div className="banner-slide">PANE 3</div>
            </ReactSwipe>
        );
    }
};

export default Swiper;