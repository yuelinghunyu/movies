import React, { Component } from 'react';
import {fadeInRightBig} from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import {throttle} from "../../plugin/utils";
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
        this.state = {
            submitConfig:{
                submiting:false,
                submittext:"提交"
            }
        }
    }
    activeEvent(ev,index){
        ev.preventDefault();
        let circles = document.getElementsByClassName("select-circle");
        for(let i=0;i<circles.length;i++){
            circles[i].setAttribute("class","select-circle");
        }
        circles[index].setAttribute("class","select-circle select-active");
    }
    submitEvent(){
        this.setState({
            submitConfig:{
                submiting:true,
                submittext:"正在提交,请稍后~"
            }
        });
       
    }
    render(){
        return(
            <StyleRoot className = "cssRoot">
                <div className='feedback-container'style={styles.fadeInRightBig}>
                    <textarea placeholder="请写下您的意见或建议"></textarea>
                    <div className="select-type-container">
                        <h4>请选择反馈类型</h4>
                        <div className="select-type-group">
                            <p className="select-type-item" onClick={(ev)=>{this.activeEvent(ev,0)}}>
                                <span className="select-circle select-active"></span>
                                <span className="select-text">功能建议</span>
                            </p>
                            <p className="select-type-item" onClick={(ev)=>{this.activeEvent(ev,1)}}>
                                <span className="select-circle"></span>
                                <span className="select-text">程序bug</span>
                            </p>
                            <p className="select-type-item" onClick={(ev)=>{this.activeEvent(ev,2)}}>
                                <span className="select-circle"></span>
                                <span className="select-text">其他</span>
                            </p>
                        </div>
                    </div>
                    <div className="submit-btn" onClick={throttle(this.submitEvent.bind(this),1000)}>{this.state.submitConfig.submittext}</div>
                </div>
            </StyleRoot>  
        )
    }
}

export default FeedBack