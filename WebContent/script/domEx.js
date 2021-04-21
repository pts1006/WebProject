//위에서 만든 테이블 가져오기 


/*
        //항목명 생성   '버튼' 미포함
        var strTitle = document.createElement('tr');
        for (var title in p1) { // for-in : p1에 들어 있는 요소 개수만큼 반복시킴
            var sth = document.createElement('th');
            sth.innerHTML = title;
            strTitle.appendChild(sth);
        }
        vTable.appendChild(strTitle);
*/

//항목명 생성 (ver.교수) '버튼' 포함
function createTitle() {

    var strTitle = document.createElement('tr');
    for (var i = 0; i < arguments.length; i++) {
        var sth = document.createElement('th');
        sth.innerHTML = arguments[i];
        strTitle.appendChild(sth);
    }
    vTable.appendChild(strTitle);
}
//테이블 성분 넣기
function createDate() { // 함수화 (밑에서 생성자 써 줘야 함)
    for (var person of persons) {
        var trTag = document.createElement('tr');
        trTag.setAttribute('id', person.id);
        trTag.onmouseover = mouseOverFnc;
        trTag.onmouseout = mouseOutFnc;

        for (var field in person) {
            if (field == 'id') {
                var tdTag = document.createElement('td');
                tdTag.onclick = modifyFunc;
                var text = document.createTextNode(person[field]);
                trTag.appendChild(tdTag);
                tdTag.appendChild(text);
            } else if(field =='name'){
                var tdTag = document.createElement('td');
                var link = document.createElement('a');
                link.setAttribute('href', 'dom.jsp?name=Hong&id=user1&score=80&gender=여');
                link.innerHTML = 'Hong';
                tdTag.appendChild(link);
                trTag.appendChild(tdTag);
            } else {
                var tdTag = document.createElement('td');
                var text = document.createTextNode(person[field]);
                trTag.appendChild(tdTag);
                tdTag.appendChild(text);
            }
        }
        var btn = document.createElement('button');
        btn.innerHTML = '삭제';
        btn.onclick = deleteRow;

        var tdTag = document.createElement('td');
        tdTag.appendChild(btn);
        trTag.appendChild(tdTag);
        vTable.appendChild(trTag);
    }
}
// createTitle('Name', 'Id', 'Score', 'Gender', 'Option'); // 생성자
// //출력
// createDate();
// var show = document.getElementById('tbl');
// show.appendChild(vTable);

function mouseOverFnc() {
    this.style.backgroundColor = 'yellow';
}

function mouseOutFnc() {
    this.style.backgroundColor = '';
}

function deleteRow() {
    this.parentNode.parentNode.remove();
}

function modifyFunc() {
    console.log(this);
    var idVal = this.innerHTML;
    var nameVal = this.previousSibling.firstChild.innerHTML;
    var scoreVal = this.nextSibling.innerHTML;
    var genVal = this.parentNode.childNodes[3].innerHTML;
    console.log(idVal, nameVal, scoreVal, genVal);

    document.getElementById('name').value = nameVal;
    document.getElementById('id').value = idVal;
    document.getElementById('score').value = scoreVal;
    var genders = document.querySelectorAll('input[name="gender"]');
    for (var i = 0; i < genders.length; i++) {
        if (genders[i].value == genVal) {
            genders[i].checked = true;
        }
    }
}

function saveBtnFnc() {
    //사용자 입력 정보
    var iName = document.getElementById('name') // 여기에 .value를 안 적으면 단지 엘리먼트라서 cosole해 보면 document~~~를 다 읽어 들임. 밑에 iName.value를 적는 것과 같음
    var iId = document.querySelector('input[name="id"]');
    var iScore = document.getElementsByTagName('input')[2];
    var iGender = document.querySelector('input[name="gender"]:checked'); // :checked를 붙이면 체크된 값만 결과로 출력된다.
    // console.log(iGender.value);  확인용으로 썼던 것.
    //드래그 기능
    var trTag = document.createElement('tr');
    trTag.onmouseover = mouseOverFnc;
    trTag.onmouseout = mouseOutFnc;

    //위를 통해 받게 된 값을 새로운 행 안에 집어 넣는다.
    //이름
    var tdTag = document.createElement('td');
    tdTag.innerHTML = iName.value; // tdTag에 iName의 밸류 값을 HTML형식으로 넣는다?
    trTag.appendChild(tdTag); // trTag의 자식으로 tdTag를 갖다 넣는다.

    //id
    tdTag = document.createElement('td');
    tdTag.innerHTML = iId.value;
    trTag.appendChild(tdTag);

    //점수
    tdTag = document.createElement('td');
    tdTag.innerHTML = iScore.value;
    trTag.appendChild(tdTag);

    // 성별
    tdTag = document.createElement('td');
    tdTag.innerHTML = iGender.value;
    trTag.appendChild(tdTag);

    //삭제 버튼
    tdTag = document.createElement('td');
    var btn = document.createElement('button');
    btn.innerHTML = '삭제';
    btn.onclick = deleteRow;

    tdTag = document.createElement('td');
    tdTag.appendChild(btn);
    trTag.appendChild(tdTag);

    tbl.appendChild(trTag); // 집어넣기.

}

function modifyBtnFnc() {
    var id = document.getElementById('id').value;
    var name = document.getElementById('name').value;
    var score = document.getElementById('score').value;
    var gender = document.querySelector('input[name="gender"]:checked').value;
    var targetTr = document.getElementById(id);
    // 아이디 값을 기준으로 가져오니까 유저 아이디는 안 바꾼다.
    targetTr.children[0].innerHTML = '<a href ="dom.jsp?name=' + name + '&id=' + id + '&score=' + score + '&gender=' + gender + '">' + name +'</a>';
        
    targetTr.children[2].innerHTML = score;
        
    targetTr.children[3].innerHTML = gender;
        
}