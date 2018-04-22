import React,{Component} from 'react';
import './contentList.scss';
import CommonList from './commonList';

class ContentList extends Component{
    constructor(props){
        super(props);
        this.state={
            flag:this.props.list[0].flag,
            FLAG:this.props.flag
        }
    }
    render(){
        let length = this.props.list.length;
        let p = null;
        if(length>6){
            p = <p>查看更多</p>
        }
        return(
            <div className="content-list-container">
                <div className="content-list-title">
                    <p className="content-list-type">
                        <span className="dot"></span>
                        <span className="list-title">{this.state.FLAG[this.state.flag]}</span>
                    </p>
                    {p}
                </div>
                <CommonList contentList={this.props.list}></CommonList>
            </div>
        )
    }
}

export default ContentList;