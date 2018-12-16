import React, { Component } from 'react';
import {Route,BrowserRouter as Router,Switch} from 'react-router-dom';
import Home from './pages/home/home';
import Classical from './pages/classical/classical';
import Person from './pages/person/person';
import Select from './components/select/select';
import Classify from './components/classify/classify';
import Footer from './components/footer/footer';
import FeedBack from './components/feedback/feedback'
import Movie from './components/movie/movie'
import './App.scss';
import initReactFastclick from 'react-fastclick';
import {getUserInfo,getUser,setAttention} from './server/server'
import { ERROR_OK } from './plugin/utils'

initReactFastclick();
class App extends Component {
  render() {
    return (
      <Router>
        <div className='container'>
          <Switch>
            <Route exact path="/" component={Home}/>
            <Route path="/home" component={Home}/>
            <Route path="/classical" component={Classical}/>
            <Route path="/person" component={Person}/>
          </Switch> 
          <Footer></Footer>
          <Route path="/select" component={Select}/>
          <Route path="/classify" component={Classify}/>
          <Route path="/feed-back/:wechatId/:wechatName" component={FeedBack}/>
          <Route path='/movie-detail/:id' component={Movie} />
        </div>
      </Router>
    );
  }
  componentDidMount(){
    //关注微信公众号；
    const param = {
      wechatId:getUser().wechatId,
      wechatName:getUser().wechatName,
      wechatLogo:getUser().wechatLogo
    }
    const userParam = {
      wechatId:getUser().wechatId
    }
    getUserInfo(userParam).then(res=>{//查询有没有
      if(res.data.code === ERROR_OK && res.data.data.total === 0){
          setAttention(param).then(res=>{
              if(res.data.code === ERROR_OK){
                  console.log("关注微信公众号、插入数据库成功")
              }
          })
      }
    })
  }
}

export default App;
