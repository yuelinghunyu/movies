import React, { Component } from 'react';
import './person.scss';

import PropTypes from 'prop-types';

class Person extends Component{

    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props)
        this.redirectFeekBack = this.redirectFeekBack.bind(this)
    }
    render(){
        return(
            <div className="person-container">
               <div className='logo-container'>
                    <img src='' alt='logo' />
                    <span>月翎魂雨</span>
               </div>
               <div className='feedBack-container' onClick={this.redirectFeekBack}>
                   <span>意见反馈</span>
                   <span></span>
               </div>
            </div>
        )
    }
    redirectFeekBack(){
        const path = '/feed-back';
        this.context.router.history.push(path);
    }
}

export default Person;