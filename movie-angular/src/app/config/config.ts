
export class Config{
    ERROR_OK:number = 0;

    DEBOUNCE(fn, delay) {
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
}
