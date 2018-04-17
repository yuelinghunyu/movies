import React,{Component} from 'react';
import ContentList from '../../components/list/contentList';
import './homeContent.scss';

class HomeContent extends Component{
    constructor(props){
        super(props)
    }
    render(){
        return(
            <div className="home-content-container">
                 <ContentList list={this.props.hotSeriesList}></ContentList>
                 <ContentList list={this.props.classicalSeriesList}></ContentList>
            </div>
        )
    }
}
export default HomeContent;