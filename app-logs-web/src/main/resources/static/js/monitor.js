var monitor = {
    params: {},
    debug: false,
    log: function (msg) {
        if (this.debug) {
            console.log(msg);
        }
    },
    getCurrentTime: function () {
        var time = new Date().getTime()
        this.log(time)
        return time
    },
    monitorClick: function () {
        that = this
        $('button').click(function (e) {
            var msg = $(this).text();
            that.log(msg)
        })
    }

}
$(function () {
    monitor.debug = true
    monitor.getCurrentTime()
    monitor.monitorClick()
})
