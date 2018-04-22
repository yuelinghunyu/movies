import React,{Component} from 'react';
import {fadeInLeftBig} from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import Types from './../classify-types/types'
import CommonList from './../list/commonList';
import './classify.scss';
import {HOT_SERIES_LIST} from '../../server/api';
import PropTypes from 'prop-types';

const styles = {
    fadeInLeftBig:{
        animation: 'x .3s',
        animationName: Radium.keyframes(fadeInLeftBig, 'fadeInLeftBig')
    }
}
class Classify extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props)
        this.state = {
            classifyType:['全部','大陆','欧美','日韩','泰国'],
            activeVal:'全部',
            typesShow:true
        }
    }
    backToLastEvent(event){//返回上一层
        event.preventDefault();
        window.history.go(-1);
    }
    classifyTypeActiveEvent(item){//选中改变样式
        this.setState({
            activeVal:item
        });
    }
    redirectToSelect(event){//跳转到搜索页面
        event.preventDefault();
        const path = '/select';
        this.context.router.history.push(path);
    }
    render(){
        let liList = [];
        let TYPES = null;
        if(this.state.activeVal === '全部'){
            TYPES = <Types></Types>
        }
        this.state.classifyType.map((item,index)=>{
            liList.push(
                <li className={this.state.activeVal===item?'classify-select-item-active':''}
                    key={index}
                    onClick={this.classifyTypeActiveEvent.bind(this,item)}
                >
                    {item}
                </li>
            );
        })
        return(
            <StyleRoot className = "cssRoot">
                <div className='classify-container'style={styles.fadeInLeftBig}>
                    <div className='classify-header'>
                        <span onClick={this.backToLastEvent.bind(this)}></span>
                        <span>电视剧</span>
                        <span onClick={this.redirectToSelect.bind(this)}></span>
                    </div>
                    <ul className='classify-select'>
                        {liList}
                    </ul>
                    <div className='classify-content'>
                        {TYPES}
                        <CommonList contentList={HOT_SERIES_LIST}></CommonList>
                    </div>
                </div>
            </StyleRoot>        
        )
    }
}

export default Classify;