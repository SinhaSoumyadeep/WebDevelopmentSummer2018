
$(document).ready(function(){

    var userService = new UserServiceClient();
    jQuery(main);

    function main() {

        var defaultUser = new User("","John Doe","","John","Doe","STUDENT")
        var UserObj = userService.findUserById(defaultUser);
        populateProfile(UserObj);

        $("#updateBtn").click(updateProfile);
        $("#logoutBtn").click(logout);


    }

    function logout() {

        var response = userService.logout();

        if(response == 'logout')
        {
            window.location.replace("../client-Login/login.template.client.html")
            return
        }
    }

    function populateProfile(UserObj) {
        $("#idFld").val(UserObj.id)
        $("#usernameFld").val(UserObj.user)
        $("#passwordFld").val(UserObj.password)
        $("#firstNameFld").val(UserObj.first)
        $("#lastNameFld").val(UserObj.last)
        $("#roleFld").val(UserObj.role)
        $("#emailFld").val(UserObj.email)
        $("#phoneFld").val(UserObj.phone)
        $("#datepicker").val(UserObj.dob)

    }

    function updateProfile() {
        var id = $("#idFld").val()
        var username = $("#usernameFld").val()
        var password = $("#passwordFld").val()
        var firstname = $("#firstNameFld").val()
        var lastName = $("#lastNameFld").val()
        var role = $("#roleFld").val()
        var email = $("#emailFld").val()
        var phone = $("#phoneFld").val()
        var date = $("#datepicker").val()

        if(email!="")
        {

            if(!validateEmail(email))
            {
                infoMsgs("INVALID EMAIL ADDRESS")
                return
            }
        }
        if(phone!="")
        {
            if(!validatePhonenumber(phone))
            {
                infoMsgs("INVALID PHONE NUMBER")
                return
            }
        }




        var updateUserObj = new User(id,username,password,firstname,lastName,role,email,date,phone)

        var response = userService.profile(updateUserObj);
        if(response.id == id)
        {
            infoMsgs("SUCCESSFULLY UPDATED")
        }
        else
        {
            infoMsgs("SOMETHING WENT WRONG")
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
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 4600);
    }

    function validateEmail(mail)
    {
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))
        {
            return true
        }

        return false
    }

    function validatePhonenumber(phone)
    {
        var phoneno = /^\d{10}$/;
        if(phone.match(phoneno))
        {
            return true;
        }
        else
        {
            return false;
        }
    }



});




