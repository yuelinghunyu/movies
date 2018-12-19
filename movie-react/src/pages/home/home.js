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
import Loading from '../../components/loading/loading';
import NoData from '../../components/noData/noData'

class Home  extends Component{
    constructor(){
        super();
        this.state = {
            swiperList:[],
            scrollTopVal:0,
            flag:HOME_FLAG,
            movie_types:[],
            movieList:[],
            loading:false
        }
    }
    componentWillMount(){
        axios.all([funcGetBannerList({type:1}),funcGetAreaList({}),funcGetMovieList({})]).then(
            axios.spread((banner, area,movie)=>{
                if(area.data.data.list.length>0){
                    const all = {
                        id:'all',
                        area:0,
                        title:'全部'
                    }
                    let list = area.data.data.list
                    list.unshift(all)
                    this.setState({
                        movie_types:list
                    })
                }
                if(banner.data.data.list.length>0){
                    this.setState({
                        swiperList:banner.data.data.list,
                    })
                }
                if(movie.data.data.list.length>0){
                    this.setState({
                        movieList:movie.data.data.list
                    },()=>{
                        this.setState({
                            loading:true
                        })
                    })
                }else{
                    this.setState({
                        loading:true
                    })
                }
                
            })
        )
    }
    render(){
        let content = null
        if(this.state.loading){
            if(this.state.movieList.length>0){
                content = <ContentList list={this.state.movieList} flag={this.state.flag}></ContentList>
            }else{
                content = <NoData></NoData>
            }
        }else{
            content = <Loading></Loading>
        }
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
                        {content}
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
            this.setState({
                loading:false
            })
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
                    },()=>{
                        this.setState({
                            loading:true
                        })
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