import React,{Component} from 'react';
import './contentList.scss';

class ContentList extends Component{
    constructor(props){
        super(props);
    }
    render(){
        let liList = [];
        this.props.list.map((item,index)=>{
           liList.push( <li className={(index%3===1)?"list-item-center":"list-item "} key={item.id}>
                <div className="logo-container">
                    <img src={item.picUrl} alt={item.title}/>
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
                        <span className="list-title">最新热剧</span>
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