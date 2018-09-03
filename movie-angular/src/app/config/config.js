const ERROR_OK = 0;

const DEBOUNCE  = function(fn, delay) {
    let args    = arguments,
       context = this,
        timer   = null;

    return function () {
        if (timer) {
            clearTimeout(timer);

            timer = setTimeout(function () {
                fn.apply(context, args);
            }, delay);
        } else {
            timer = setTimeout(function () {
                fn.apply(context, args);
            }, delay);
        }
    }
}
module.exports = {
    ERROR_OK,
    DEBOUNCE
}