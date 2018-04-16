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
                        <span className={this.props.status==="home"?"home-active":"home-normal"}></span>
                        <span className={this.props.status==="home"?"active":"normal"}>首页</span>
                    </Link>
                </p>
                <p className="competitive-page">
                    <Link to="/list">
                        <span className={this.props.status==="competitive"?"competitive-active":"competitive-normal"}></span>
                        <span className={this.props.status==="competitive"?"active":"normal"}>精品</span>
                    </Link>
                </p>
                <p className="person-page">
                    <Link to="/person">
                        <span className={this.props.status==="person"?"person-active":"person-normal"}></span>
                        <span className={this.props.status==="person"?"active":"normal"}>我的</span>
                    </Link>
                </p>
            </div>
        )
    }
}

export default Footer;