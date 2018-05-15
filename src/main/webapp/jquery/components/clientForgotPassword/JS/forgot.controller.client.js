$(document).ready(function () {

    var userService = new UserServiceClient();
    $(main)


    function main() {

        $("#resetBtn").click(sendEmail);

    }

    function sendEmail() {

        var email = $("#emailFld").val();
        if(!validateEmail(email))
        {
            infoMsgs("You have entered invalid email address!")
            return
        }
        var response = userService.sendEmail(email);

        if(response == 'SUCCESSFULLY SENT')
        {
            infoMsgs("EMAIL SUCCESSFULLY SENT")

            setTimeout(function () {
                window.location.replace("../client-Login/login.template.client.html")
            }, 2800);

            return
        }
        else
        {
            infoMsgs(response)
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

        if(!msg.includes("SUCCESSFULLY") && !msg.includes("Available")){
            x.style.backgroundColor = "rgb(217, 56, 26)";
        }
        else
        {
            x.style.backgroundColor = "rgba(113, 217, 41, 1)";
        }
        x.innerHTML=msg;
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 2600);
    }

    function validateEmail(mail)
    {
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))
        {
            return true
        }

        return false
    }



})