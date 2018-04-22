import React,{Component} from 'react';
import './contentList.scss';
import { FLAG } from "../../common/content";
import CommonList from './commonList';

class ContentList extends Component{
    constructor(props){
        super(props);
        this.state={
            flag:this.props.list[0].flag,
        }
    }
    render(){
        return(
            <div className="content-list-container">
                <div className="content-list-title">
                    <p className="content-list-type">
                        <span className="dot"></span>
                        <span className="list-title">{FLAG[this.state.flag]}</span>
                    </p>
                    <p>查看更多</p>
                </div>
                <CommonList contentList={this.props.list}></CommonList>
            </div>
        )
    }
}

export default ContentList;