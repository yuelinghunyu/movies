import React, { Component } from 'react';
import { SWIPER_LIST,HOT_SERIES_LIST,CLASSICAL_SERIES_LIST } from '../../server/api';

import './home.scss';
import Header from '../../components/header/header';
import Swiper from '../../components/swiper/swiper';
import HomeContent from './homeContent';
import Footer from '../../components/footer/footer';

class Home  extends Component{
    constructor(){
        super();
        this.state = {
            swiperList:[],
            scrollTopVal:0,
        }
    }
    componentWillMount(){
        this.setState({
            swiperList:SWIPER_LIST,
            hotSeriesList:HOT_SERIES_LIST,//最热的剧
            classicalSeriesList:CLASSICAL_SERIES_LIST,//经典的剧
        })
    }
    render(){
        return (
            <div className="home-container">
                <Header scrollTop={this.state.scrollTopVal}></Header>
                <div className="home-scroll">
                    <div className="home-banner">
                    <Swiper swiperList={this.state.swiperList}></Swiper> 
                    </div>
                    <HomeContent 
                        hotSeriesList={this.state.hotSeriesList}
                        classicalSeriesList={this.state.classicalSeriesList}
                    ></HomeContent>
                </div>
                <Footer status={"home"}></Footer>
            </div>
        );
    }
    componentDidMount(){
        let homeScroll = document.getElementsByClassName("home-scroll")[0];
        homeScroll.addEventListener("scroll",(e)=>{
           let scrollTop = e.target.scrollTop
           this.setState({
               scrollTopVal:scrollTop
           })
        });
    }
};
export default Home;