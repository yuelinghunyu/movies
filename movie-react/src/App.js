import React, { Component } from 'react';
import {Router,Link,Route,hashHistory} from 'react-router';
import Home from './pages/home/home';
import List from './pages/list/list';
import Person from './pages/person/person';
import logo from './logo.svg';
import './App.css';
import initReactFastclick from 'react-fastclick';
initReactFastclick();

class App extends Component {
  render() {
    return (
      <Router history={hashHistory}>
        <Route path="/" component={Home}></Route>
        <Route path="/list" component={List}></Route>
        <Route path="/person" component={Person}></Route>
      </Router>
    );
  }
}

export default App;
