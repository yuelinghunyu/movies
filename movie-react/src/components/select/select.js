import React,{Component} from 'react';
import { fadeInUpBig,fadeOutRightBig } from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import {getMovieLikeList} from '../../server/server'
import { ERROR_OK,throttle} from '../../plugin/utils'
import PropTypes from 'prop-types';
import _ from 'lodash'
import './select.scss';

const styles = {
    fadeInUpBig: {
      animation: 'x .3s',
      animationName: Radium.keyframes(fadeInUpBig, 'fadeInUpBig')
    },
    fadeOutRightBig: {
        animation: 'x .3s',
        animationName: Radium.keyframes(fadeOutRightBig, 'fadeOutRightBig')
    }
}
class Select extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props);
        this.state = {
            focusFlag:false,
            history:false,
            movieList:[]
        }
    }
    cancelEvent(event){
        event.preventDefault();
        window.history.go(-1);
    }
    focusEvent(){
        this.setState({
            focusFlag:true
        })
    }
    changeEvent(ev){
        const target = ev.target.value
        if(target !== ""){
            const param = {
                title:target
            }
            getMovieLikeList(param).then(res=>{
                if(res.code === ERROR_OK){
                  this.setState({
                      movieList:res.data.list
                  })
                   
                }
            })
        }
       
    }
    localStorageOperate(newMovieList){
        let movieList = localStorage.getItem('movieList')
        if(movieList === null || movieList === undefined){//首次
            localStorage.setItem('movieList',JSON.stringify(newMovieList))
        }else{
            movieList = JSON.parse(movieList)
            movieList.map((item)=>{
                if(item.id === newMovieList[0].id){
                    return false
                }else{
                    localStorage.setItem('movieList',JSON.stringify(_.uniq(_.concat(movieList,newMovieList))))
                }
            })
        }
    }
    clearHistory(){
        localStorage.removeItem('movieList')
        this.setState({
            history:true
        })
    }
    selectMovieRedirect(movie,type){
        let array = []
        array.push(movie)
        if(type === "list"){
            this.localStorageOperate(array)
        }
        this.setState({
            history:false
        })
        const path = '/movie-detail/'+movie.id;
        this.context.router.history.push(path);
    }
    render(){
        let input = <div className = 'input-container'>
                        <p>
                            <span></span>
                            <input 
                                type='text' 
                                placeholder='权利的游戏' 
                                onFocus={this.focusEvent.bind(this)}
                                onChange={(ev)=>{
                                    throttle(this.changeEvent(ev),1000)
                                }}
                            />
                        </p>
                        <span className='inputCancel' onClick={this.cancelEvent.bind(this)}>取消</span>
                    </div>;
        let historyList = null
        if(localStorage.getItem('movieList')){
            let liList = [];
            if(JSON.parse(localStorage.getItem('movieList')).length>0){
                const movieList = JSON.parse(localStorage.getItem('movieList'))
                movieList.map((movie,index)=>{
                    liList.push(
                        <li 
                            id={movie.id} 
                            key={index}
                            onClick={this.selectMovieRedirect.bind(this,movie,"list")}
                        >{movie.title}</li>
                    )
                })
            }
            historyList = <ul className='history-container'>{liList}</ul> 
        }
        let movieList = null;
        if(this.state.focusFlag){
            let spanList = [];
            if(this.state.movieList.length > 0){
                this.state.movieList.map((movie,index)=>{
                    spanList.push(
                        <span 
                            id={movie.id} 
                            key={index}
                            onClick={this.selectMovieRedirect.bind(this,movie,"history")}
                        >{movie.title}</span>
                    )
                })
              
            }
            movieList = <p className='down-btn'>{spanList}</p>
        }
        return(
            <StyleRoot className = "cssRoot">
                <div className="select-container" style={styles.fadeInUpBig}>
                    {input}
                    {movieList}
                    <div className='clear-btn'>
                        <span>搜索历史</span>
                        <span onClick={this.clearHistory.bind(this)}>清空</span>
                    </div>
                    {historyList}
                </div> 
            </StyleRoot>    
        )
    }
}
export default Select;