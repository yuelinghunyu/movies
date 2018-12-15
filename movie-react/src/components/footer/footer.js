import React,{Component} from 'react';
import {Link} from 'react-router-dom';
import './footer.scss';
import "../../static/fonts/iconfont.css"
import PropTypes from "prop-types";

class Footer extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props)
        this.state={
            status:'home'
        }
    }
    pageHandle(status){
        this.setState({
            status:status
        });
    }
    componentWillMount(){
        let status = ""
        if(this.context.router.route.location.pathname === "/person"){
            status = "person"
        }
        if(this.context.router.route.location.pathname === "/home" || this.context.router.route.location.pathname=== "/"){
            status = "home"
        }
        this.pageHandle(status)
    }
    render(){
        return(
            <div className="footer-container">
                <p className="home-page">
                    <Link to="/"  onClick={this.pageHandle.bind(this,'home')}>
                        <i className={this.state.status==="home"?"icon iconfont icon-shouye active":"icon iconfont icon-shouye normal"}></i>
                        {/* <span className={this.state.status==="home"?"home-active":"home-normal"}></span> */}
                        <span className={this.state.status==="home"?"active":"normal"}>首页</span>
                    </Link>
                </p>
                {/* <p className="competitive-page">
                    <Link to="/classical" onClick={this.pageHandle.bind(this,'competitive')}>
                        <span className={this.state.status==="competitive"?"competitive-active":"competitive-normal"}></span>
                        <span className={this.state.status==="competitive"?"active":"normal"}>精品</span>
                    </Link>
                </p> */}
                <p className="person-page">
                    <Link to="/person" onClick={this.pageHandle.bind(this,'person')}>
                        <i className={this.state.status==="person"?"icon iconfont icon-geren active":"icon iconfont icon-geren normal"}></i>
                        {/* <span className={this.state.status==="person"?"person-active":"person-normal"}></span> */}
                        <span className={this.state.status==="person"?"active":"normal"}>我的</span>
                    </Link>
                </p>
            </div>
        )
    }
}

export default Footer;