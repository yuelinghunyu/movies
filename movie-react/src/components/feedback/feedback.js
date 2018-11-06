import React, { Component } from 'react';
import {fadeInRightBig} from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import './feedback.scss'

const styles = {
    fadeInRightBig:{
        animation: 'x .3s',
        animationName: Radium.keyframes(fadeInRightBig, 'fadeInRightBig')
    }
}


class FeedBack extends Component{
    constructor(props){
        super(props)
    }
    render(){
        return(
            <StyleRoot className = "cssRoot">
                <div className='feedback-container'style={styles.fadeInRightBig}>这是意见反馈页面</div>
            </StyleRoot>  
        )
    }
}

export default FeedBack