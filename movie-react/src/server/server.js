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
export {
    getBannerList,
    getAreaList,
    getTypeList,
    getMovieList,
    funcGetBannerList,
    funcGetAreaList,
    funcGetMovieList,
    funcGetTypeList,
    funcGetTotal
}