function check(){
    if(document.getElementById("username").value && document.getElementById("password").value){
        return true;
    }else{
        alert("账户或密码不能为空!");
        return false
    }
}
function plus(){
    document.getElementById("p-count").value++
}
function minus(){
    document.getElementById("p-count").value--;
}