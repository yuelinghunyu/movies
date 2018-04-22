import React, { Component } from 'react';
import {Route,BrowserRouter as Router,Switch} from 'react-router-dom';
import Home from './pages/home/home';
import List from './pages/list/list';
import Person from './pages/person/person';
import Select from './components/select/select';
import Classify from './components/classify/classify';
import Footer from './components/footer/footer';
import './App.scss';
import initReactFastclick from 'react-fastclick';

initReactFastclick();
class App extends Component {
  render() {
    return (
      <Router>
        <div className='container'>
          <Switch>
            <Route exact path="/" component={Home}/>
            <Route path="/list" component={List}/>
            <Route path="/person" component={Person}/>
          </Switch> 
          <Footer></Footer>
          <Route path="/select" component={Select}/>
          <Route path="/classify" component={Classify}/>
        </div>
      </Router>
    );
  }
  componentDidMount(){}
}

export default App;
