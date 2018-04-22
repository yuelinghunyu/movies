import React,{Component} from 'react';
import './types.scss';
import { CLASSIFY_TYPES } from '../../server/api';
import { TYPES } from "../../common/content";
class Types extends Component{
    constructor(props){
        super(props)
        this.state={
            typesList:CLASSIFY_TYPES,
            typesItemList:TYPES
        }
    }
    typesEvent(index,item){
        TYPES.splice(index,1,item);
        this.setState({
            typesItemList:TYPES
        })
    }
    render(){
        let outLiList = [];
        this.state.typesList.map((types,index)=>{
            let list = types.list;
            let listItem = [];
            list.map((item)=>{
                listItem.push(
                    <li 
                        className={`type-item ${this.state.typesItemList[index]===item.typeVal?'type-item-active':''}`} 
                        key={item.type}
                        onClick={this.typesEvent.bind(this,index,item.typeVal)}
                    >
                    {item.typeVal}
                    </li>
                )
            });
           outLiList.push(
            <li className='types-item-container' key={types.types}>
                <ul className='type-item-list'>
                    {listItem}
                </ul>
            </li>
           )
        })
        return(
            <ul className='types-container'>
               {outLiList}
            </ul>
        )
    }
}
export default Types;