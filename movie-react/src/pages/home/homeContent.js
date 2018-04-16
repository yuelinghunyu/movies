import React,{Component} from 'react';
import ContentList from '../../components/list/contentList';
import './homeContent.scss';

class HomeContent extends Component{
    constructor(porps){
        super(porps)
    }
    render(){
        return(
            <div className="home-content-container">
                 <ContentList list={this.props.hotSeriesList}></ContentList>
            </div>
        )
    }
}
export default HomeContent;