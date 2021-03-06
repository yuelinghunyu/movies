import React,{Component} from 'react';
import LazyLoad from 'react-lazy-load';
import './commonList.scss';
import { FLAG } from "../../common/content";
import PropTypes from 'prop-types';

class CommonList extends Component{
    static contextTypes = {
        router:PropTypes.object.isRequired,
    }
    constructor(props){
        super(props)
    }
    render(){
        let liList = [];
        this.props.contentList.map((item,index)=>{
           liList.push( 
                <li 
                    className={(index%3===1)?"list-item-center":"list-item "} 
                    key={item.id}
                    id={item.id}
                    onClick={this.movieDetailEvent.bind(this,item)}
                >
                    <div className="logo-container">
                        <LazyLoad height={368}>
                            <img src={item.picUrl} alt={item.title}/>
                        </LazyLoad>
                        {/* <span>{0}</span>     */}
                    </div>
                    <div className="logo-content">
                        <p className="logo-title">{item.title}</p>
                        <p className="logo-discription">{item.description}</p>
                    </div>
                </li>
            );
        });
        return(
            <ul className="content-list">
                {liList}
            </ul>
        )
    }
    movieDetailEvent(movie){
        const path = '/movie-detail/'+movie.id;
        this.context.router.history.push(path);
    }
}

export default CommonList;