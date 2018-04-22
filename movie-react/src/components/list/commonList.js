import React,{Component} from 'react';
import LazyLoad from 'react-lazy-load';
import './commonList.scss';
import { FLAG } from "../../common/content";

class CommonList extends Component{
    constructor(props){
        super(props)
    }
    render(){
        let liList = [];
        this.props.contentList.map((item,index)=>{
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
            <ul className="content-list">
                {liList}
            </ul>
        )
    }
}

export default CommonList;