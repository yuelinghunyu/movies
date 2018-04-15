import React,{Component} from 'react';
import {Link} from 'react-router'
import './footer.scss';

class Footer extends Component{
    constructor(props){
        super(props)
    }
    render(){
        return(
            <div className="footer-container">
                <p className="home-page">
                    <Link to="/">
                        <span className="active"></span>
                        <span className="text">首页</span>
                    </Link>
                </p>
                <p className="competitive-page">
                    <Link to="/list">
                        <span className="normal"></span>
                        <span className="text">精品</span>
                    </Link>
                </p>
                <p className="person-page">
                    <Link to="/person">
                        <span className="normal"></span>
                        <span className="text">我的</span>
                    </Link>
                </p>
            </div>
        )
    }
}

export default Footer;