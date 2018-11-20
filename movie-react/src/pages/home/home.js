import React, { Component } from 'react';
import {funcGetBannerList,funcGetAreaList,getMovieList,funcGetMovieList} from '../../server/server'
import { ERROR_OK } from '../../plugin/utils'
import axios from 'axios'
import emitter from "../../plugin/ev"


import { HOME_FLAG } from "../../common/content";
import './home.scss';
import Header from '../../components/header/header';
import Iswiper from '../../components/swiper/iswiper';
import ContentList from '../../components/list/contentList';

class Home  extends Component{
    constructor(){
        super();
        this.state = {
            swiperList:[],
            scrollTopVal:0,
            flag:HOME_FLAG,
            movie_types:[],
            movieList:[]
        }
    }
    componentWillMount(){
        axios.all([funcGetBannerList({type:1}),funcGetAreaList({}),funcGetMovieList({})]).then(
            axios.spread((banner, area,movie)=>{
                const all = {
                    id:'all',
                    area:0,
                    title:'全部'
                }
                let list = area.data.data.list
                list.unshift(all)
                this.setState({
                    swiperList:banner.data.data.list,
                    movie_types:list,
                    movieList:movie.data.data.list
                })
            })
        )
    }
    render(){
        return (
            <div className="home-container">
                <Header 
                    scrollTop={this.state.scrollTopVal}
                    types={this.state.movie_types}
                ></Header>
                <div className="home-scroll">
                    <div className="home-banner">
                        <Iswiper swiperList={this.state.swiperList}></Iswiper>
                    </div>
                    <div className="common-content-container">
                        <ContentList list={this.state.movieList} flag={this.state.flag}></ContentList>
                    </div>
                </div>
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

        this.eventEmitter  =  emitter.addListener("selectArea",(area) =>{
            let param = {}
            if(area !== 0){
                param = {
                    area:area
                }
            }
            getMovieList(param).then(res=>{
                if(res.code === ERROR_OK){
                    this.setState({
                        movieList:res.data.list
                    })
                }
            })
        })
    }
    componentWillUnmount(){
        emitter.removeAllListeners(this.eventEmitter);
    }
};
export default Home;