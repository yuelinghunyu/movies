import React, { Component } from 'react';
import './noData.scss'

class NoData extends Component{
    constructor(props){
        super(props)
    }

    render(){
        return(
            <div className='no-data-container'>
                <span></span>
                <span></span>
            </div>
        )
    }
}

export default NoData