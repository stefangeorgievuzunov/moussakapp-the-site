async function onSubmit() {
    debugger

    let username=document.getElementById("validationCustomUsername");
    let password=document.getElementById("validationCustomPassword");
    let rePassword=document.getElementById("validationCustomRePassword");
    let firstName=document.getElementById("validationCustomFirstName");
    let lastName=document.getElementById("validationCustomLastName");

    const  data={
        username:$("#validationCustomUsername"),
        password:$("#validationCustomPassword"),
        rePassword:$("#validationCustomRePassword"),
        firstName:$("#validationCustomFirstName"),
        lastName:$("#validationCustomLastName")
    }


   $.post("/register/authorization",function (data) {

   })
}