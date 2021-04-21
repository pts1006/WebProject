var buttons = document.getElementsByTagName('button');
for (var i = 0; i < buttons.length; i++) {
    buttons[i].setAttribute('onclick', 'showTable()');
}

var p1 = {
    name: '성진아',
    score: 80,
    gender: '여'
}
var p2 = {
    name: '김수민',
    score: 83,
    gender: '남'
}
var p3 = {
    name: '장재우',
    score: 85,
    gender: '남'
}

var persons = [p1, p2, p3];

/*
    <table border = '1'><tr><td>성진아</td><td>80</td> ...
*/

// var tbl = '<table border = "1">';
// for (var p of persons) {
//     tbl += '<th>' + p.name + '</th>';
// }
// tbl += '</tr><tr>';
// for (var p of persons) {
//     tbl += '<th>' + p.score + '</th>';
// }
// tbl += '</tr>';
// tbl += '</table>';

function showTable() {
    var tableTag = document.createElement('table');
    tableTag.setAttribute('border', '1');
    for (var person of persons) { //배열에서 반복
        var tr1 = document.createElement('tr');
        for (var field in person) { //object에서 반복
            var th1 = document.createElement('th');
            th1.innerHTML = person[field];
            tr1.appendChild(th1);
        }
        tableTag.appendChild(tr1);
    }
    var show = document.getElementById('show');
    show.appendChild(tableTag);
}