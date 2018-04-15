import React, { Component } from 'react';
import { SWIPER_LIST } from '../../server/api';

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
                <Header></Header>
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
};

export default Home;