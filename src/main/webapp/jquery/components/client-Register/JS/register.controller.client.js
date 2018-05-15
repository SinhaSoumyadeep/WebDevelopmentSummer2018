
$(document).ready(function(){

    var userService = new UserServiceClient();

    jQuery(main);

    function main() {

        $("#usernameFld").blur(checkUser);
        $('#passwordFld').blur(checkPassword);
        $('#verifyPassswordFld').keyup(verifyPassword);
        $('#passwordFld').focus(disableBtn);
        $("#usernameFld").focus(disableBtn);
        $("#usernameFld").keyup(enableBtn);
        $('#passwordFld').keyup(enableBtn);


        $("#registerBtn").click(register);

    }


    function enableBtn() {

        var userName = $("#usernameFld").val();
        if (userName == '')
        {
            infoMsgs("Please Enter Username")
            return
        }
        var password = $("#passwordFld").val();
        var verifyPassword = $("#verifyPassswordFld").val();

        if(password!= ''&&verifyPassword!=''&&password==verifyPassword)
        {
            $("#registerBtn").removeAttr("disabled");
        }
        else
        {
            disableBtn()
        }



    }

    function disableBtn() {

        $("#registerBtn").attr("disabled","disabled");

    }


    function checkPassword() {
        $("#registerBtn").attr("disabled","disabled");
        var userName = $("#usernameFld").val();
        if (userName == '')
        {
            infoMsgs("Please Enter Username")
            return
        }
        var password = $("#passwordFld").val();
        if (password == '')
        {
            infoMsgs("Please Enter Password")
            return

        }
        var verifyPassword = $("#verifyPassswordFld").val();

        if(password == verifyPassword)
        {
            $("#registerBtn").removeAttr("disabled");
        }


    }

    function verifyPassword() {

        var userName = $("#usernameFld").val();
        if (userName == '')
        {
            infoMsgs("Please Enter Username")
            return
        }

        var password = $("#passwordFld").val();
        if (password == '')
        {
            infoMsgs("Please Enter Password")

        }
        var verifyPassword = $("#verifyPassswordFld").val();

        if(password == verifyPassword)
        {
            $("#registerBtn").removeAttr("disabled");
        }
        else
        {
            $("#registerBtn").attr("disabled","disabled");
        }

    }

    function checkUser() {

        $("#registerBtn").attr("disabled","disabled");

        var userName = $("#usernameFld").val();
        if (userName == '')
        {
            infoMsgs("Please Enter Username")
            return
        }
        var response = userService.findByUsername(userName);

        if(response == 'success')
        {
            infoMsgs("Username Available");
        }
        else
        {
            infoMsgs("Username Unavailable");

            //$("#usernameFld").val('');
            return
        }


    }

    function register() {


        var userName = $("#usernameFld").val();
        if (userName == '')
        {
            infoMsgs("Please Enter Username")
            return
        }

        var password = $("#passwordFld").val();
        if (password == '')
        {
            infoMsgs("Please Enter Password")
            return
        }
        var verifyPassword = $("#verifyPassswordFld").val();
        if (verifyPassword == '')
        {
            infoMsgs("Please Verify Password")
            return
        }

        if(password == verifyPassword)
        {
            $("#registerBtn").removeAttr("disabled");

            var userObj = new User("",userName,password,"","","");
            var response = userService.register(userObj);



            if(response=='success')
            {

                infoMsgs("SUCCESSFULLY REGISTERED")
                $("#registerBtn").attr("disabled","disabled");
                window.location.replace("../client-Profile/profile.template.client.html")
                return

            }
            if(response == 'USER ALREADY EXISTS')
            {
                infoMsgs("USERNAME IS TAKEN")
            }
            else
            {
                infoMsgs("SOMETHING WENT WRONG!")
            }
            $(':input').val('');

        }
        else
        {
            infoMsgs("PASSWORD DOESNOT MATCH");
            $("#passwordFld").val('');
            $("#verifyPassswordFld").val('');
        }





    }

/*
    this function is used to display success or failure information.//finalized.
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




