var ps = document.querySelector('div>p');
ps.textContent = 'hello';
ps.style.backgroundColor = 'yellow';
// ps.forEach(function (val) {
//     console.log(val);
//     // val.innerHTML = '<h1>hello</h1>'; innerHTML & innerText + textContnet차이
//     // val.innerText = '<h1>hellow</h1>';
//     val.textContent = '<h1>hellow</h1>';
//     val.style.backgroundColor = 'green';
// });

        /*
            numbers ~> 1 ~ 10
            filter, map, forEach ~> 짝수만 걸러내도록 ~> evenVal;
            evenVal ~> n * 3; ~> mapVal;
            mapVal ~> console.log(각각 출력);
       */

var numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

var evenVal = numbers.filter(function (val, idx, ary) {
    return val % 2 == 0;
});
console.log(evenVal);
console.log('===========');

var mapVal = evenVal.map(function (val, idx, ary) {
    return val * 3;
});
console.log(mapVal);
console.log('===========');

var eachLoop = mapVal.forEach(function (val, idx, ary) {
    console.log(val);
});


// => : arrow function. 다음을 실행해라?
console.log('===== arrow function 이용 ======');
numbers.filter((val) => val % 2 == 0)
    .map((val) => val * 3)
    .forEach((val) => console.log(val));
// 위의 세 가지 함수를 람다식을 이용해 줄인 결과

console.log('===========');
var sum = (a, b) => a + b;
sum(10, 20);