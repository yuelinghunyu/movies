import React,{Component} from 'react'
import {fadeInRightBig} from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import PropTypes from 'prop-types';
import { getMovieList } from '../../server/server'
import { ERROR_OK } from '../../plugin/utils'
import NoData from '../../components/noData/noData'
import './movie.scss'

const styles = {
    fadeInRightBig:{
        animation: 'x .3s',
        animationName: Radium.keyframes(fadeInRightBig, 'fadeInRightBig')
    }
}

class Movie extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props)
        this.state = {
            movie:{},
            noDataFlag:false
        }
    }
    componentWillMount(){
       const param =  this.context.router.route.match.params
       getMovieList(param).then(res=>{
           if(res.code === ERROR_OK && res.data.total>0){
               this.setState({
                   movie:res.data.list[0]
               })
           }else{
            this.setState({
                noDataFlag:true
            }) 
           }
       })
    }
    backToLastEvent(event){//返回上一层
        event.preventDefault();
        window.history.go(-1);
    }
    render(){
        let content = null
        if(this.state.noDataFlag){
            content = <NoData></NoData>
        }else{
            content =  <div className="content-display">
                            <div className="movie-detail">
                                <div className='movie-detail-top'>
                                    <img src={this.state.movie.picUrl} alt='movie-logo'/>
                                    <p className='movie-other'>
                                        <label for='movie-area'>
                                            地区：<span id='movie-area'>{this.state.movie.areaTitle}</span>
                                        </label>
                                        <label for='movie-type'>
                                            类型：<span id='movie-type'>{this.state.movie.typeTitle}</span>
                                        </label>
                                        <label for='movie-role'>
                                            主演：<span id='movie-type'>{this.state.movie.actor}</span>
                                        </label>
                                    </p>
                                </div>
                                <p className='movie-detail-description'>
                                    <label>电影简介：</label>
                                    <span>
                                        {this.state.movie.description}
                                    </span>
                                </p>
                            </div>
                            <div className='cloud-href'>
                                <label>电影链接：</label>
                                <p className='movie-link'>
                                    <label for='movie-href'>
                                        电影资源：<span id='movie-type'>{this.state.movie.content}</span>
                                    </label>
                                    <label for='movie-password'>
                                        密码：<span id='movie-type'>{this.state.movie.content}</span>
                                    </label>
                                </p>
                            </div>
                        </div>
        }
        return(
            <StyleRoot className = "cssRoot">
                <div className='movie-detail-container' style={styles.fadeInRightBig}>
                    <div className='movie-header'>
                        <span onClick={this.backToLastEvent.bind(this)}></span>
                        <span>电影主题</span>
                        <span></span>
                    </div>
                    {content}
                </div>
            </StyleRoot>
        )
    }
}

export default Movie