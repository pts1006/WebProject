    //         <!-- <form action = "login.jsp" method = "get">
    //     id : <input type = 'text' name = 'id'>
    //     passwd : <input type = 'password' name = 'passwd'>
    //     <input type = 'submit' value = 'Send'>
    //     <input type = "reset" value = "Cancel">
    // </form> -->

        //form 생성
        var frm = document.createElement('form');
        frm.setAttribute('action', 'login.jsp');
        frm.setAttribute('method', 'get');
        
        // label => id : , password : 
        var id = document.createTextNode('id : ');
        var passwd = document.createTextNode('passwd : ');
        
        // input => id, passwd
        var inputId = document.createElement('input');
        inputId.setAttribute('type', 'text');
        inputId.setAttribute('name', 'id');
        inputId.setAttribute('placeholder', 'id');
        
        var inputPw = document.createElement('input');
        inputPw.setAttribute('type', 'password');
        inputPw.setAttribute('name', 'passwd');
        inputPw.setAttribute('placeholder', 'password');
        
        var cancel = document.createElement('input');
        cancel.setAttribute('type', 'reset');
        cancel.setAttribute('value', 'Cancel');
        
        var send = document.createElement('input');
        send.setAttribute('type', 'submit');
        send.setAttribute('value', 'Send');

        document.body.appendChild(frm);
        frm.appendChild(id);
        frm.appendChild(inputId);
        frm.appendChild(passwd);
        frm.appendChild(inputPw);
        frm.appendChild(send);
        frm.appendChild(cancel);