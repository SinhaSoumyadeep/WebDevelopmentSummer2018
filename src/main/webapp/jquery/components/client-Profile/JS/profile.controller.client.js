
$(document).ready(function(){

    var userService = new UserServiceClient();
    jQuery(main);

    function main() {

        var defaultUser = new User("","John Doe","","John","Doe","STUDENT")
        var UserObj = userService.findUserById(defaultUser);
        populateProfile(UserObj);

        $("#updateBtn").click(updateProfile);


    }

    function populateProfile(UserObj) {
        $("#idFld").val(UserObj.id)
        $("#usernameFld").val(UserObj.user)
        $("#passwordFld").val(UserObj.password)
        $("#firstNameFld").val(UserObj.first)
        $("#lastNameFld").val(UserObj.last)
        $("#roleFld").val(UserObj.role)

    }

    function updateProfile() {
        var id = $("#idFld").val()
        var username = $("#usernameFld").val()
        var password = $("#passwordFld").val()
        var firstname = $("#firstNameFld").val()
        var lastName = $("#lastNameFld").val()
        var role = $("#roleFld").val()

        var updateUserObj = new User(id,username,password,firstname,lastName,role)

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







});




