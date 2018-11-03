import React,{Component} from 'react';
import { BrowserRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import './header.scss';
import CommonHeader from './commonHeader'

class Header extends Component{
    static contextTypes = {
        router:PropTypes.object,
    }
    constructor(props){
        super(props);
       
    }
    skipRouteEvent(event){//跳转到搜索组件
        event.preventDefault();
        const path = '/select';
        this.context.router.history.push(path);
    }
    skipClassifyEvent(event){//跳转到分类界面
        event.preventDefault();
        const path = '/classify';
        this.context.router.history.push(path);
    }
    render(){
        return(
            <div className="header-container">
                <CommonHeader list={this.props.types}></CommonHeader>
                <div className={`header-select ${this.props.scrollTop<290?'nor':'cur'}`}>
                    <p className="select-movie" onClick={this.skipRouteEvent.bind(this)}>
                        <span className="select-logo"></span>
                        <span>权利的游戏</span>
                    </p>
                    <p className="select-child-type" onClick={this.skipClassifyEvent.bind(this)}>
                        <span>史诗</span>
                        <span>惊悚</span>
                        <span className="select-all"></span>
                        <span className="select-all-text">全部</span>
                    </p>
                </div>
            </div>
        )
    }
    componentDidMount(){
        
    }
}

export default Header;