import React,{Component} from 'react';
import { MOVIE_TYPES } from '../../server/api';
import './header.scss';

class Header extends Component{
    constructor(props){
        super(props);
        this.state = {
            movie_types:MOVIE_TYPES,
            currentType:1,//当前选项
        }
    }
    selectTypeItem(type){
        this.setState({
            currentType:type
        })
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
                <div className="header-select"></div>
            </div>
        )
    }
}

export default Header;