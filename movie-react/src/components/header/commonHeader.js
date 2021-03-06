import React,{Component} from 'react';
import './commonHeader.scss';
import emitter from "../../plugin/ev"

class CommonHeader extends Component{
    constructor(props){
        super(props)
        this.state = {
            currentType:0,//当前选项
        }
    }
    selectTypeItem(type){
        emitter.emit("selectArea",type)
        this.setState({
            currentType:type,
        })
    }
    render(){
        let listType = null;
        listType = this.props.list.map((item)=>{
            return(
                <li 
                    key={item.id}
                    id={item.id}
                    className={
                        `${this.state.currentType == item.area?'active':''}`
                    }
                    onClick={
                        this.selectTypeItem.bind(this,item.area)
                    }
                >
                    {item.title}
                </li>
            )
        });
        return(
            <div className="header-types">
                <ul>{listType}</ul>
            </div>
        )
    }
}

export default CommonHeader;