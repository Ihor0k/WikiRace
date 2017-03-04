var iframe = document.getElementById('wiki');
var clicks = document.getElementById('clicks-count');
var firstLoading = true;

iframe.onload = function () {
    if (firstLoading) {
        firstLoading = false;
        return;
    }
    var c = clicks.innerText;
    c++;
    clicks.innerText = c;
};

var goal = document.getElementById('goal');

goal.onmouseover = function () {
    document.getElementById('popup').style.display = 'block';
};

goal.onmouseout = function () {
    document.getElementById('popup').style.display = 'none';
};