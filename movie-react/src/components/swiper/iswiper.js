import React, { Component } from 'react';
import './iswiper.scss'
import SwipeableViews from 'react-swipeable-views';
import { autoPlay } from 'react-swipeable-views-utils';
const AutoPlaySwipeableViews = autoPlay(SwipeableViews);

class Iswiper extends Component{
    constructor(props){
        super(props)
        this.state = {
            currentIndex:0,
        }
    }
    handleChangeIndex(index){
        this.setState({
            currentIndex:index
        });
    }
    render(){
        const { currentIndex } = this.state;
        let divList = [],spanList=[];
        this.props.swiperList.map((item,index)=>{
            divList.push(
                <div className="banner-slide" key={item.id}>
                    <img src={item.href} alt={item.title}/>
                </div>
            );
            spanList.push(<span className={this.state.currentIndex==index?"active":"normal"} key={index}></span>);
        })
        return(
            <div className = "swiper-container">
                <AutoPlaySwipeableViews
                   index={currentIndex} onChangeIndex={this.handleChangeIndex.bind(this)}
                >
                    {divList}
                </AutoPlaySwipeableViews>
                <p className="dot">{spanList}</p>
            </div>
        )
    }
}

export default Iswiper
