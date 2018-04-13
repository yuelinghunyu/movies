'use strict';

var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    devtool: 'source-map',
    entry: [
        'webpack-dev-server/client?http://localhost:3000',
        'webpack/hot/only-dev-server',
        'react-hot-loader/patch',
        path.join(__dirname, 'app/index.js')
    ],
    output: {
        path: path.join(__dirname, '/dist/'),
        filename: '[name].js',
        publicPath: '/'
    },
    plugins: [
        new HtmlWebpackPlugin({
          template: './index.tpl.html',
          inject: 'body',
          filename: './index.html'
        }),
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin(),
        new webpack.DefinePlugin({
          'process.env.NODE_ENV': JSON.stringify('development')
        })
    ],
    module: {
        resolve:{
            extensions:['','.js','.json']
        },        
        loaders: [
            {
              test: /\.js$/,
              exclude: /node_modules/,
              loader: "babel-loader",
              query:
                {
                  presets:['react','es2015']
                }
            },
            {
                test: /\.json?$/,
                loader: 'json'
            },
            {
                test: /\.css$/,
                loader: "style!css"
            },
            {
                test: /\.scss/,
                loader: 'style-loader!css-loader!sass-loader'
            }
        ]
    },
    devServer: {//注意：网上很多都有colors属性，但是实际上的webpack2.x已经不支持该属性了  
        contentBase: "./build",//本地服务器所加载的页面所在的目录  
        historyApiFallback: true,//不跳转  
        inline: true//实时刷新  
    }  
};