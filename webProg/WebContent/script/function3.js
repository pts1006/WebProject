/* function3.js
     함수를 함수의 매개 값으로 올 수 있다.
*/

var sum = function (a, b) {
    var num1 = 10;
    var num2 = 20;
    console.log(a + (num1 + num2) + b);
}
// console.log(sum);
sum('결과는 ', ' 입니다.');

var factorial = function fac(n) {

    if (n === 1) {
        return 1;
    }
    return n * fac(n - 1);
}

var result = factorial(5);
console.log(result);

function sumVal(a, b) {
    return a + b;
}

function mulVal(a, b) {
    return a * b;
}

function executeFunc(callBack, num1, num2) {
    var result = callBack(num1, num2);
    return result;
    // 여기 result = 지역변수
}

// 여기 result = 전역변수. 즉, 다른 놈이다.
result = executeFunc(mulVal, 3, 5);
console.log(result);

