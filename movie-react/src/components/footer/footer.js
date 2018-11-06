import React,{Component} from 'react';
import {Link} from 'react-router-dom';
import './footer.scss';

class Footer extends Component{
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
    render(){
        return(
            <div className="footer-container">
                <p className="home-page">
                    <Link to="/"  onClick={this.pageHandle.bind(this,'home')}>
                        <span className={this.state.status==="home"?"home-active":"home-normal"}></span>
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
                        <span className={this.state.status==="person"?"person-active":"person-normal"}></span>
                        <span className={this.state.status==="person"?"active":"normal"}>我的</span>
                    </Link>
                </p>
            </div>
        )
    }
}

export default Footer;