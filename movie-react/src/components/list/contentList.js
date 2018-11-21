import React,{Component} from 'react';
import './contentList.scss';
import CommonList from './commonList';
import PropTypes from 'prop-types';

class ContentList extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props);
    }
    skipClassifyEvent(event){//跳转到分类界面
        event.preventDefault();
        const path = '/classify';
        this.context.router.history.push(path);
    }
    render(){
        let length = this.props.list.length;
        let p = null;
        if(length>6){
            p = <p onClick={this.skipClassifyEvent.bind(this)}>查看更多</p>
        }
        return(
            <div className="content-list-container">
                <div className="content-list-title">
                    <p className="content-list-type">
                        <span className="dot"></span>
                        <span className="list-title">资源列表</span>
                    </p>
                    {p}
                </div>
                <CommonList contentList={this.props.list}></CommonList>
            </div>
        )
    }
}

export default ContentList;