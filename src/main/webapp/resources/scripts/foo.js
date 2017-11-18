
//at first, we define a variable stating that an event listener has been attached onto the field


window.onload = function () {
    if (!document.getElementById || !document.createElement) {
        return;
    }
    var newjs = document.createElement('script');
    newjs.type = 'text/javascript';
    newjs.src = 'myjsfile.js';
    document.getElementsByTagName('head')[0].appendChild(newjs);
}
function loadScript(src) {
    newjs = document.createElement("script");
    newjs.src = src;
    newjs.type = 'text/javascript';
    head = document.getElementsByTagName("head")[0];
    head.appendChild(newjs);
}
function validateForm() {
    var a = document.getElementById('name');

    if (a == null || a == "")
    {

        alert('Please complete all required fields.');
    }



    //then, we attach the event listened to the field after the submit, if it has not been done so far

}



