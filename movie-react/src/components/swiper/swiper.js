import React, { Component } from 'react';
import ReactSwipe from 'react-swipe'
import './swiper.scss'

class Swiper extends Component{
    constructor(props){
        super(props);
        this.state = {
            swiperList:this.props.swiperList,
            currentIndex:0,
        }
    }
    render(){
        let option = {
            speed: 400,
            auto: 3000,
            continuous: true,
            transitionEnd: ((index, elem)=>{
               this.transitionEndCallback(index,elem);
            })
        };
        let divList = [],spanList=[];
        this.state.swiperList.map((item,index)=>{
            divList.push(<div className="banner-slide" key={item.id}>
            <img src={item.picUrl} />
            </div>);
            spanList.push(<span className={this.state.currentIndex==index?"active":"normal"} key={index}></span>);
        })
        return (
            <div className = "swiper-container">
                <ReactSwipe className="carousel" swipeOptions={option}>
                {divList}
                </ReactSwipe>
                <p className="dot">{spanList}</p>
            </div>
        );
    }
    transitionEndCallback(index, elem){
       this.setState({
           currentIndex:index
       });
    }
};

export default Swiper;