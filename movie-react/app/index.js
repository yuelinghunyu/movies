import React from 'react';
import { render } from 'react-dom';
import { AppContainer } from 'react-hot-loader';
import Root from './root';

render(
    <AppContainer>
        <Root/>
    </AppContainer>,
    document.getElementById('root')
)
if(module.hot){
    module.hot.accept('./root',()=>{
        const App = require('./root').default;
        render(
            <AppContainer>
                <App/>
            </AppContainer>,
            document.getElementById('root')
        )
    })
}