import React,{Component} from 'react';
import './types.scss';
import { CLASSIFY_TYPES } from '../../server/api';
import {getTypeList} from '../../server/server'
import { ERROR_OK } from '../../plugin/utils'
import { TYPES } from "../../common/content";
class Types extends Component{
    constructor(props){
        super(props)
        this.state={
            typesList:[],
            typesItemList:TYPES
        }
    }
    typesEvent(index,item){
        this.props.selectTypeCallback(item)
        TYPES.splice(index,1,item.title);
        this.setState({
            typesItemList:TYPES
        })
    }
    componentWillMount(){
        getTypeList({}).then(res=>{
            if(res.code === ERROR_OK){
                let array = []
                const all = {
                    id:'all',
                    type:0,
                    title:'全部类型'
                }
                let list = res.data.list
                list.unshift(all)

                const typeItem = {
                    types:1,
                    list:list
                }
                array.push(typeItem);
                array.push(CLASSIFY_TYPES)
                this.setState({
                    typesList:array
                })
            }
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
                        className={`type-item ${this.state.typesItemList[index]===item.title?'type-item-active':''}`} 
                        key={item.type || item.movieType}
                        onClick={this.typesEvent.bind(this,index,item)}
                    >
                    {item.title}
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