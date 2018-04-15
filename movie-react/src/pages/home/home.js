import React, { Component } from 'react';
import { SWIPER_LIST } from '../../server/api';
import { debounce } from '../../common/dom';

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
                    <HomeContent></HomeContent>
                </div>
                <Footer></Footer>
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