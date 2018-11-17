import React, { Component } from 'react';
import ReactSwipe from 'react-swipe'
import './swiper.scss'

class Swiper extends Component{
    constructor(props){
        super(props);
        this.state = {
            currentIndex:0,
            option:{
                speed: 400,
                auto: 3000,
                continuous: true,
                transitionEnd: ((index, elem)=>{
                   this.transitionEndCallback(index,elem);
                }),
            }
        }
    }
    render(){
        let divList = [],spanList=[];
        this.props.swiperList.map((item,index)=>{
            divList.push(
                <div className="banner-slide" key={item.id}>
                    <img src={item.href} alt={item.title}/>
                </div>
            );
            spanList.push(<span className={this.state.currentIndex==index?"active":"normal"} key={index}></span>);
        })
        return (
            <div className = "swiper-container">
                <ReactSwipe className="carousel" swipeOptions={this.state.option}>
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