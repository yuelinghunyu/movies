import React,{Component} from 'react';
import LazyLoad from 'react-lazy-load';
import './contentList.scss';
import { FLAG } from "../../common/content";

class ContentList extends Component{
    constructor(props){
        super(props);
        this.state={
            flag:this.props.list[0].flag,
        }
    }
    render(){
        let liList = [];
        this.props.list.map((item,index)=>{
           liList.push( <li className={(index%3===1)?"list-item-center":"list-item "} key={item.id}>
                <div className="logo-container">
                    <LazyLoad height={368}>
                        <img src={item.picUrl} alt={item.title}/>
                    </LazyLoad>
                    <span>{item.length}</span>    
                </div>
                <div className="logo-content">
                    <p className="logo-title">{item.title}</p>
                    <p className="logo-discription">{item.discription}</p>
                </div>
           </li>);
        });
        return(
            <div className="content-list-container">
                <div className="content-list-title">
                    <p className="content-list-type">
                        <span className="dot"></span>
                        <span className="list-title">{FLAG[this.state.flag]}</span>
                    </p>
                    <p>查看更多</p>
                </div>
                <ul className="content-list">
                    {liList}
                </ul>
            </div>
        )
    }
}

export default ContentList;