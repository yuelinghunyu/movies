import React,{Component} from 'react';
import { BrowserRouter } from 'react-router-dom';
import PropTypes from 'prop-types';
import { MOVIE_TYPES } from '../../server/api';
import './header.scss';

class Header extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props);
        this.state = {
            movie_types:MOVIE_TYPES,
            currentType:1,//当前选项
        }
    }
    selectTypeItem(type){
        this.setState({
            currentType:type,
        })
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
        let listType = null;
        listType = this.state.movie_types.map((item)=>{
            return(
                <li 
                    key={item.id}
                    className={
                        `${this.state.currentType == item.type?'active':''}`
                    }
                    onClick={
                        this.selectTypeItem.bind(this,item.type)
                    }
                >
                    {item.title}
                </li>
            )
        });
        return(
            <div className="header-container">
                <div className="header-types">
                    <ul>{listType}</ul>
                </div>
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