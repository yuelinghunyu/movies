import React, { Component } from 'react';
import {Router,Route,browserHistory} from 'react-router';
import Home from './pages/home/home';
import List from './pages/list/list';
import Person from './pages/person/person';
import Select from './components/select/select';
import './App.css';
import initReactFastclick from 'react-fastclick';


initReactFastclick();

class App extends Component {
  render() {
    return (
      <Router history={browserHistory}>
        <Route path="/" component={Home}></Route>
        <Route path="/list" component={List}></Route>
        <Route path="/person" component={Person}></Route>
        <Route path="/select" component={Select}></Route>
      </Router>
    );
  }
  componentDidMount(){
  }
}

export default App;
