import React, { Component } from 'react';
import ReactSwipe from 'react-swipe'
import './swiper.scss'

class Swiper extends Component{
    constructor(props){
        super(props);
        this.state = {
            swiperList:this.props.swiperList,
        }
    }
    render(){
        let option = {
            speed: 400,
            auto: 3000,
            continuous: true,
        };
        let divList = this.state.swiperList.map((item)=>
            <div className="banner-slide"key={item.id}>
                <img src={item.picUrl} />
            </div>
        )
        return (
            <ReactSwipe className="carousel" swipeOptions={option}>
              {divList}
            </ReactSwipe>
        );
    }
};

export default Swiper;