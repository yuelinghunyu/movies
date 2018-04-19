import React,{Component} from 'react';
import { browserHistory } from 'react-router';
import { fadeInUpBig,fadeOutRightBig } from 'react-animations';
import Radium,{StyleRoot} from 'radium';
import './select.scss';

const styles = {
    fadeInUpBig: {
      animation: 'x .3s',
      animationName: Radium.keyframes(fadeInUpBig, 'fadeInUpBig')
    },
    fadeOutRightBig: {
        animation: 'x .3s',
        animationName: Radium.keyframes(fadeOutRightBig, 'fadeOutRightBig')
    }
}
class Select extends Component{
    constructor(props){
        super(props);
    }
    cancelEvent(event){
        event.preventDefault();
        window.history.go(-1);
    }
    render(){
        let input = <div className = 'input-container'>
                        <p>
                            <span></span>
                            <input type='text' placeholder='权利的游戏' />
                        </p>
                        <span className='inputCancel' onClick={this.cancelEvent.bind(this)}>取消</span>
                    </div>;
        let historyList = <ul className='history-container'>
                                <li>火影忍者</li>
                                <li>running man</li>
                          </ul>;
        return(
            <StyleRoot className = "cssRoot">
                <div className="select-container" style={styles.fadeInUpBig}>
                        {input}
                    <div className='clear-btn'>
                        <span>搜索历史</span>
                        <span>清空</span>
                    </div>
                        {historyList}
                    <p className='down-btn'></p>
                </div> 
            </StyleRoot>    
        )
    }
}
export default Select;