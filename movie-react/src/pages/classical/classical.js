import React, { Component } from 'react';
import './classical.scss';
import Header from '../../components/header/header';
import { CLASSICAL_TYPES,SWIPER_LIST,HOT_SERIES_LIST,CLASSICAL_SERIES_LIST} from '../../server/api';
import { CLASSICAL_FLAG } from "../../common/content";
import ContentList from '../../components/list/contentList';
class Classical extends Component{
    constructor(){
        super();
        this.state = {
            classical_types:CLASSICAL_TYPES,
            swiperList:SWIPER_LIST,
            flag:CLASSICAL_FLAG
        }
    }
    componentWillMount(){
        this.setState({
            hotSeriesList:HOT_SERIES_LIST,//最热的剧
            classicalSeriesList:CLASSICAL_SERIES_LIST,//经典的剧
        })
    }
    render(){
        return(
            <div className="classical-container">
                <Header 
                    types={this.state.classical_types}
                >
                </Header>
                <div className="classical-scroll">
                    <div className="classical-banner">
                        {/* <Swiper swiperList={this.state.swiperList}></Swiper>  */}
                    </div>
                    <div className="common-content-container">
                        <ContentList list={this.state.hotSeriesList} flag={this.state.flag}></ContentList>
                        <ContentList list={this.state.classicalSeriesList} flag={this.state.flag}></ContentList>
                    </div>
                </div>
            </div>
        )
    }
}

export default Classical;