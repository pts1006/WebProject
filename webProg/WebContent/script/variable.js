/**
 * variable.js
 */

document.write("<h1 style = 'background-color : yellow'>Hellow</h1>");
console.log('hello');
'<br>'
var ulTag = "<ul><li>A</li> <li>B</li>"
document.write(ulTag);

ulTag = 10;
ulTag = null;
ulTag = 10 + 20;
console.log(ulTag);

var Ntable = '<table border = "1">';

for(var i = 2; i < 3; i++){
    for(var j = 1; j < 10; j++){
        Ntable += '<tr><td>' + i  + "*" + j + '</td><td>' + "=" + '</td><td>' + i * j + '</td></tr>';
    }
    
}

Ntable += '</table>';
document.write(Ntable);