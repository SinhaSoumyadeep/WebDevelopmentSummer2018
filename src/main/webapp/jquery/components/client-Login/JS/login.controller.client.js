
$(document).ready(function(){

    var userService = new UserServiceClient();

    jQuery(main);

    function main() {

        $("#loginBtn").click(login);
    }

    function login() {

        var user = $("#usernameFld").val();
        var pass = $("#passwordFld").val();


        if (user == '')
        {
            infoMsgs("Please Enter Username")
            return
        }
        if ( pass == '')
        {
            infoMsgs("Please Enter Password")
            return
        }

        var userObj = new User("",user,pass,"","","");
        var res = userService.findByUsername(user);

        var response = userService.login(userObj);

        if(res == 'success')
        {
            infoMsgs("USER DOESNOT EXIST")
            return
        }

        if(res == 'fail' && response.password != pass)
        {
            infoMsgs("PASSWORD YOU HAVE ENTERED IS INCORRECT")
            return
        }

        if(response.user == user && response.password == pass)
        {

            window.location.replace("../client-Profile/profile.template.client.html")
        }
        else
        {
            infoMsgs("PASSWORD YOU HAVE ENTERED IS INCORRECT")
            return
        }




    }


    /*
    this function is used to display success or failure information. //finalized.
*/
    function infoMsgs(msg)
    {
        var x = document.getElementById("info")
        x.className = "show";

        if(!msg.startsWith("SUCCESSFULLY") && !msg.includes("Available")){
            x.style.backgroundColor = "rgb(217, 56, 26)";
        }
        else
        {
            x.style.backgroundColor = "rgba(113, 217, 41, 1)";
        }
        x.innerHTML=msg;
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 2600);
    }


});




