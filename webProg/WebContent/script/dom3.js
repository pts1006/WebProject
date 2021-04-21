var names = [];
names[0] = '유정모';
names.push('구자혁'); // 마지막 위치에 집어 넣기
names.push('석정원');
names.pop(); // 뒤에서부터 하나 지우기
delete names[0]; //요소는 남는데 안에 들어 있는 값(정보)만 지움.
names.shift(); // 앞에서부터 지움.
names.unshift('김이담'); //앞에서부터 추가
names.push('공도현');
names.push('소국진');
names.push('전형민');

var returnVal = names.map(function (val, idx, ary) {
    var person = {};
    person.name = val;
    person.num = idx;

    return person;
});
console.log(returnVal);
console.log('===========');

var returnFil = returnVal.filter(function (val, idx, ary){
    return idx % 2 == 0;
}); // 반환되는 값이 참인 녀석들만 반환

// var show = document.getElementById('show');
// var ulTag = document.createElement('ul');
// show.appendChild(ulTag);

// for(var i = 0; i < names.length; i++){} 얘는 콜백 함수 (function callbackFnc(){}) 쓸 수 없음

// names.forEach(function (val, idx, ary) {
//     console.log(`names 요소 : ${val}, ${idx}, ${ary}`);
//     // console.log('names 요소 : ' + i);

//     // var liTag = document.createElement('li');
//     // liTag.innerHTML = val; // <li>에 배열에 들어 있는 값 ㅡㅡ <li>김이담</li>
//     // ulTag.appendChild(liTag);
// }); forEach는 리턴이 안 됨 ~> 반복하니까 리턴할 필요 x

console.log(returnFil);