import React,{Component} from 'react';
import {fadeInRightBig} from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import Types from './../classify-types/types'
import CommonList from './../list/commonList';
import './classify.scss';
import { ERROR_OK } from '../../plugin/utils'
import PropTypes from 'prop-types';
import axios from 'axios'
import {funcGetAreaList,getMovieList,funcGetTotal} from '../../server/server';
import Loading from '../../components/loading/loading';
import NoData from '../../components/noData/noData'
import "../../static/fonts/iconfont.css"

const styles = {
    fadeInRightBig:{
        animation: 'x .3s',
        animationName: Radium.keyframes(fadeInRightBig, 'fadeInRightBig')
    }
}
class Classify extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props)
        this.state = {
            classifyType:[],
            activeVal:'全部',
            movieList:[],
            movieField:{
                area:-1,
                type:-1,
                movieType:-1
            },
            loading:false
        }
    }
    backToLastEvent(event){//返回上一层
        event.preventDefault();
        window.history.go(-1);
    }
    classifyTypeActiveEvent(item){//选中改变样式
        this.setState({
            loading:false
        })
        let totalParam = {}
        if(item.id !== "all"){
            totalParam={
                area:item.area,
                type:this.state.movieField.type,
                movieType:this.state.movieField.movieType
            }
            this.setState({
                movieField:{
                    area:item.area,
                    type:this.state.movieField.type,
                    movieType:this.state.movieField.movieType
                }
            })
        }else{
            totalParam={
                area:-1,
                type:this.state.movieField.type,
                movieType:this.state.movieField.movieType
            }
            this.setState({
                movieField:{
                    area:-1,
                    type:this.state.movieField.type,
                    movieType:this.state.movieField.movieType
                }
            })
        }
        axios.all([funcGetTotal(totalParam)]).then(
            axios.spread((total)=>{
                const movieAreaTotal = total.data.data.total
                let movieParam = {}
                if(item.id !== "all"){
                    movieParam={
                        area:item.area,
                        type:this.state.movieField.type,
                        movieType:this.state.movieField.movieType,
                        page:1,
                        limit:movieAreaTotal
                    }
                }else{
                    movieParam={
                        area:-1,
                        type:this.state.movieField.type,
                        movieType:this.state.movieField.movieType,
                        page:1,
                        limit:movieAreaTotal
                    }
                }
                getMovieList(movieParam).then(res=>{
                    if(res.code === ERROR_OK){
                        this.setState({
                            movieList:res.data.list
                        },()=>{
                            this.setState({
                                loading:true
                            })
                        })
                    }
                })
            })
        )
        this.setState({
            activeVal:item.title
        });
    }
    redirectToSelect(event){//跳转到搜索页面
        event.preventDefault();
        const path = '/select';
        this.context.router.history.push(path);
    }
    componentWillMount(){
        axios.all([funcGetAreaList({}),funcGetTotal({})]).then(
            axios.spread((area,total)=>{
                if(area.data.data.list.length>0){
                    const all = {
                        id:'all',
                        area:this.state.movieField.area,
                        title:'全部'
                    }
                    let list = area.data.data.list;
                    list.unshift(all)
                    getMovieList({page:1,limit:total.data.data.total}).then(res=>{
                        if(res.code === ERROR_OK){
                            this.setState({
                                classifyType:list,
                                movieList:res.data.list
                            },()=>{
                                this.setState({
                                    loading:true
                                })
                            })
                        }
                    })
                }else{
                    this.setState({
                        loading:true
                    })
                }
            })
        )
    }

    //父子组件通信业务
    selectTypeCallback(item){
        this.setState({
            loading:false
        })
        let totalParam = {}
        const flag = item.type === undefined ? 'movieType':'type'
        if(item.id !== "all"){
            if(flag === 'movieType'){
                totalParam={
                    area:this.state.movieField.area,
                    type:this.state.movieField.type,
                    movieType:item.movieType
                }
                this.setState({
                    movieField:{
                        area:this.state.movieField.area,
                        type:this.state.movieField.type,
                        movieType:item.movieType
                    }
                })
            }else{
                totalParam={
                    area:this.state.movieField.area,
                    type:item.type,
                    movieType:this.state.movieField.movieType
                }
                this.setState({
                    movieField:{
                        area:this.state.movieField.area,
                        type:item.type,
                        movieType:this.state.movieField.movieType
                    }
                })
            }
            
        }else{
            if(flag === 'movieType'){
                totalParam={
                    area:this.state.movieField.area,
                    type:this.state.movieField.type,
                    movieType:-1
                }
                this.setState({
                    movieField:{
                        area:this.state.movieField.area,
                        type:this.state.movieField.type,
                        movieType:-1
                    }
                })
            }else{
                totalParam={
                    area:this.state.movieField.area,
                    type:-1,
                    movieType:this.state.movieField.movieType
                }
                this.setState({
                    movieField:{
                        area:this.state.movieField.area,
                        type:-1,
                        movieType:this.state.movieField.movieType
                    }
                })
            }
        }
        axios.all([funcGetTotal(totalParam)]).then(
            axios.spread((total)=>{
                const movieAreaTotal = total.data.data.total
                let movieParam = {}
                if(item.id !== "all"){
                    if(flag === 'movieType'){
                        movieParam={
                            area:this.state.movieField.area,
                            type:this.state.movieField.type,
                            movieType:item.movieType,
                            page:1,
                            limit:movieAreaTotal
                        }
                    }else{
                        movieParam={
                            area:this.state.movieField.area,
                            type:item.type,
                            movieType:this.state.movieField.movieType,
                            page:1,
                            limit:movieAreaTotal
                        }
                    }
                }else{
                    if(flag === 'movieType'){
                        movieParam={
                            area:this.state.movieField.area,
                            type:this.state.movieField.type,
                            movieType:-1,
                            page:1,
                            limit:movieAreaTotal
                        }
                    }else{
                        movieParam={
                            area:this.state.movieField.area,
                            type:-1,
                            movieType:this.state.movieField.movieType,
                            page:1,
                            limit:movieAreaTotal
                        }
                    }
                }
                getMovieList(movieParam).then(res=>{
                    if(res.code === ERROR_OK){
                        this.setState({
                            movieList:res.data.list
                        },()=>{
                            this.setState({
                                loading:true
                            })
                        })
                    }
                })
            })
        )
    }
    render(){
        let liList = [];
        this.state.classifyType.map((item,index)=>{
            liList.push(
                <li className={this.state.activeVal===item.title?'classify-select-item-active':''}
                    key={index}
                    area = {item.area}
                    onClick={this.classifyTypeActiveEvent.bind(this,item)}
                >
                    {item.title}
                </li>
            );
        })
        let content = null
        if(this.state.loading){
            if(this.state.movieList.length>0){
                content = <CommonList contentList={this.state.movieList}></CommonList>
            }else{
                content = <NoData></NoData>
            }
        }else{
            content = <Loading></Loading>
        }
        return(
            <StyleRoot className = "cssRoot">
                <div className='classify-container'style={styles.fadeInRightBig}>
                    <div className='classify-header'>
                        <span onClick={this.backToLastEvent.bind(this)}>
                            <i className="icon iconfont icon-left"></i>
                        </span>
                        <span>资源</span>
                        <span onClick={this.redirectToSelect.bind(this)}>
                            <i className="icon iconfont icon-sousuo"></i>
                        </span>
                    </div>
                    <ul className='classify-select'>
                        {liList}
                    </ul>
                    <div className='classify-content'>
                        <Types selectTypeCallback = {this.selectTypeCallback.bind(this)}></Types>
                        {content}
                    </div>
                </div>
            </StyleRoot>        
        )
    }
}

export default Classify;