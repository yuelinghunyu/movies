import axios from 'axios'

const baseUrl = "/mso"
const headers = {
    'web-flag':"wexin"
}
//获取轮播图
const getBannerList = (param) =>{
    const url = baseUrl + '/banner/list';
    return axios.get(url,{
        headers:headers,
        params:param
    }).then((res)=>{
        return Promise.resolve(res.data)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}

//获取地区列表
const getAreaList = (param) => {
    const url = baseUrl + '/areas/list'
    return axios.get(url,{
        headers:headers,
        params:param
    }).then((res)=>{
        return Promise.resolve(res.data)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}
//获取类型列表
const getTypeList = (param) => {
    const url = baseUrl + '/types/list'
    return axios.get(url,{
        headers:headers,
        params:param
    }).then((res)=>{
        return Promise.resolve(res.data)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}
//获取电影列表
const getMovieList = (param) => {
    const url = baseUrl + '/movie/list'
    return axios.get(url,{
        headers:headers,
        params:param
    }).then((res)=>{
        return Promise.resolve(res.data)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}
//模糊查询电影列表
const getMovieLikeList = (param) => {
    const url = baseUrl + '/movie/listLike'
    return axios.get(url,{
        headers:headers,
        params:param
    }).then((res)=>{
        return Promise.resolve(res.data)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}
const funcGetBannerList = (param) => {
    const url = baseUrl + '/banner/list';
    return axios.get(url,{
        headers:headers,
        params:param
    })
}

const funcGetAreaList = (param) => {
    const url = baseUrl + '/areas/list'
    return axios.get(url,{
        headers:headers,
        params:param
    })
}

const funcGetTypeList = (param) => {
    const url = baseUrl + '/types/list'
    return axios.get(url,{
        headers:headers,
        params:param
    })
}

const funcGetMovieList = (param) => {
    const url = baseUrl + '/movie/list'
    return axios.get(url,{
        headers:headers,
        params:param
    })
}

const funcGetTotal = (param) => {
    const url = baseUrl + '/movie/getTotal'
    return axios.get(url,{
        headers:headers,
        params:param
    })
}

const getUserInfo = (params) => {
    let url = baseUrl + '/users/list';
    return axios.get(url,{
        headers:headers,
        params:params
    }).then((res)=>{
        return Promise.resolve(res)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}
const getUser = ()=>{
    return {
        wechatId:"6a8db3a6097d4687bf3e29fb2c24cfaf",
        wechatName:"三叶草",
        wechatLogo:"https://pic.qqtn.com/up/2016-11/2016113014090291434.jpg"
    }
}
//关注插入数据库
const setAttention = (params)=>{
    let url = baseUrl + '/users/attention';
    let formData = new FormData()
    for(let key in params){
        formData.append(key,params[key])
    }
    return axios.post(url,formData,{
        headers:headers
    }).then((res)=>{
        return Promise.resolve(res)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}
const setFeedBack = (params)=>{
    let url = baseUrl + "/feed-back/set-feed-back";
    let formData = new FormData()
    for(let key in params){
        formData.append(key,params[key])
    }
    return axios.post(url,formData,{
        headers:headers
    }).then((res)=>{
        return Promise.resolve(res)
    }).catch((error)=>{
        return Promise.reject(error);
    })
}
export {
    getBannerList,
    getAreaList,
    getTypeList,
    getMovieList,
    funcGetBannerList,
    funcGetAreaList,
    funcGetMovieList,
    funcGetTypeList,
    funcGetTotal,
    getMovieLikeList,
    getUserInfo,
    getUser,
    setFeedBack,
    setAttention
}