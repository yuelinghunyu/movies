const guid =()=> {    
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {        
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);        
        return v.toString(16);    
    });
}
const ERROR_OK = 0;

const indexOf = (array,item)=>{
    if(array.length === 0) return -1;
    for(var i=0;i<array.length;i++){
        if(array[i].id === item) return i;
    }
    return -1;
}

const remove = (array,item)=>{
    const index = indexOf(array,item);
    if(index > -1){
        array.splice(index,1);
    }
    return array;
}
const add = (array,item)=>{
    const index = indexOf(array,item);
    let newArray = [];
    if(index > -1){
        newArray.push(array[index]);
    }
    return newArray;
}

const throttle = (fn, delay)=>{
    let args     = arguments,
        context  = this,
        timer    = null,
        remaining   = 0,
        previous = new Date();

    return function () {
        let now = new Date();
        remaining = now - previous;

        if (remaining >= delay) {
            if (timer) {
                clearTimeout(timer);
            }

            fn.apply(context, args);
            previous = now;
        } else {
            if (!timer) {
                timer = setTimeout(function () {
                    fn.apply(context, args);
                    previous = new Date();
                }, delay - remaining);
            }
        }
    };
}
export {
    guid,
    ERROR_OK,
    remove,
    add,
    throttle,
    indexOf
}