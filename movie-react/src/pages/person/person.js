import React, { Component } from 'react';
import {getUserInfo,getUser} from '../../server/server'
import { ERROR_OK } from '../../plugin/utils'
import "../../static/fonts/iconfont.css"
import './person.scss';

import PropTypes from 'prop-types';

class Person extends Component{

    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props)
        this.state = {
            user:{
                wechatId:"",
                wechatName:"",
                wechatLogo:""
            }
        }
        this.redirectFeekBack = this.redirectFeekBack.bind(this)
    }
    render(){
        return(
            <div className="person-container">
               <div className='logo-container' id={this.state.user.wechatId}>
                    <img src={this.state.user.wechatLogo} alt="logo"/>
                    <span className="username">{this.state.user.wechatName}</span>
               </div>
               <div className='feedBack-container' onClick={this.redirectFeekBack}>
                   <span>意见反馈</span>
                   <span>
                        <i className="icon iconfont icon-right"></i>
                   </span>
               </div>
            </div>
        )
    }
    redirectFeekBack(ev){
        ev.preventDefault();
        const path = "/feed-back"+"/"+this.state.user.wechatId+"/"+this.state.user.wechatName;
        this.context.router.history.push(path);
    }
    componentWillMount(){
        this.setState({
            user:{
                wechatId:getUser().wechatId,
                wechatName:getUser().wechatName,
                wechatLogo:getUser().wechatLogo
            }
        })
    }
}

export default Person;