import React,{Component} from 'react'
import {fadeInRightBig} from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import './movie.scss'

const styles = {
    fadeInRightBig:{
        animation: 'x .3s',
        animationName: Radium.keyframes(fadeInRightBig, 'fadeInRightBig')
    }
}

class Movie extends Component{
    constructor(props){
        super(props)
        this.state = {
            logo:require('../../static/image/b.jpg')
        }
    }
    backToLastEvent(event){//返回上一层
        event.preventDefault();
        window.history.go(-1);
    }
    render(){
        return(
            <StyleRoot className = "cssRoot">
                <div className='movie-detail-container' style={styles.fadeInRightBig}>
                    <div className='movie-header'>
                        <span onClick={this.backToLastEvent.bind(this)}></span>
                        <span>电影主题</span>
                        <span></span>
                    </div>
                    <div className="movie-detail">
                        <div className='movie-detail-top'>
                            <img src={this.state.logo} alt='movie-logo'/>
                            <p className='movie-other'>
                                <label for='movie-area'>
                                    地区：<span id='movie-area'>欧美</span>
                                </label>
                                <label for='movie-type'>
                                    类型：<span id='movie-type'>惊悚</span>
                                </label>
                                <label for='movie-role'>
                                    主演：<span id='movie-type'>杰克斯坦森</span>
                                </label>
                            </p>
                        </div>
                        <p className='movie-detail-description'>
                            <label>电影简介：</label>
                            <span>
                            每个人都不易，
                            我始终谨记三条：一是得寸别进尺，
                            别人对自己好是人家大气，
                            不是因为自己多么有魅力。
                            二是对人客客气气不要破坏彼此的距离，近则不逊。
                            三是求人不抱期待，得助则喜，无助亦感激，因为每个人都有自己的难处。
                            </span>
                        </p>
                    </div>
                    <div className='cloud-href'>
                        <label>电影链接：</label>
                        <p className='movie-link'>
                            <label for='movie-href'>
                                电影资源：<span id='movie-type'>百度云盘</span>
                            </label>
                            <label for='movie-password'>
                                密码：<span id='movie-type'>杰克斯坦森</span>
                            </label>
                        </p>
                    </div>
                </div>
            </StyleRoot>
        )
    }
}

export default Movie