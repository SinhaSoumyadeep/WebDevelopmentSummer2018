
$(document).ready(function(){
	
	var userService = new UserServiceClient();

    jQuery(main);

/*
    the main method.
 */
    function main() {
        findAllUsers();
        $('#add').click(createUser);
        $('#update').click(updateUser);
        $('#logoutBtn').click(logout);
        $(document).on('click', '#del', function(){deleteUser(this);});
        $(document).on('click', '#edit', function(){renderUser(this);});


    }

/*
    this function is used to add users to the database. //finalized.
 */
    function createUser() {

        var user= $("#usernameFld").val();
        var pass= $("#passwordFld").val();
        var first= $("#firstNameFld").val();
        var last= $("#lastNameFld").val();
        var role= $("#roleFld").val();

        var userObj = new User("",user,pass,first,last,role);

        var response = userService.createUser(userObj);


        if(response!='success')
        {

            infoMsgs(response);
        }
        else{

            $("tbody").empty();
            findAllUsers()
            infoMsgs("SUCCESSFULLY ADDED");
            $(':input').val('');

        }


        $("#usernameFld").removeAttr("readonly");

    }


/*
    this function is used to find all users in Database. //finalized.
 */
    function findAllUsers()
    {

        var list = userService.findAllUsers();

        if(list.length==0)
        {
            var $row = $('<tr class="wbdv-template wbdv-user wbdv-hidden" id="terror">'+
                '<td id="err"> NO DATA AVAILABLE...</td>'+
                '</tr>');
            $('table> tbody:last').append($row);
        }
        else{

            renderUsers(list);

        }


    }
/*
    this function is used to render all users in the html table //finalized.
*/
    function renderUsers(listOfUsers)
    {
        listOfUsers.forEach(function(l){
            if(l.id == 1) return;

            var $row = $('<tr class="wbdv-template wbdv-user wbdv-hidden" id="trow['+l.id+']">'+
                '<td id="user['+l.id+']">'+l.user+'</td>'+
                '<td id="pass['+l.id+']">'+l.password+'</td>'+
                '<td id="first['+l.id+']">'+l.first+'</td>'+
                '<td id="last['+l.id+']">'+l.last+'</td>'+
                '<td id="role['+l.id+']">'+l.role+'</td>'+
                '<td style="padding-left: 123px;" ><button type="button" class="btn btn-danger" id="del">Delete</button>  <button type="button" style=" padding-left: 23px; padding-right: 20px;" class="btn btn-warning" id="edit">Edit</button></td>'+
                '</tr>');

            $('table> tbody:last').append($row);

        });
    }


/*
    this function is used to update users in the database //finalized.
*/
    function updateUser()
    {
        var id = $("#idFld").val();
        var user= $("#usernameFld").val();
        var pass= $("#passwordFld").val();
        var first= $("#firstNameFld").val();
        var last= $("#lastNameFld").val();
        var role= $("#roleFld").val();

        if(id == '')
        {
            infoMsgs("CANNOT BE UPDATED")
            return
        }

        var userObj = new User(id,user,pass,first,last,role);
        var response = userService.updateUser(userObj);



        if(response!='success')
        {
            infoMsgs(response);
        }
        else{

            $("tbody").empty();
            findAllUsers();
            infoMsgs("SUCCESSFULLY UPDATED");
        }

        $(':input').val('');
        $("#usernameFld").removeAttr("readonly");


    }

/*
    this function handles deletion of user from database.
 */
    function deleteUser(delObj)
    {

        var $id = $(delObj).closest('tr').attr('id');
        var response = userService.deleteUser($id);

        if(response == 'success') {
            $("tbody").empty();
            findAllUsers();
            infoMsgs("SUCCESSFULLY DELETED");
        }
        else
        {
            infoMsgs(response);
        }
        $(':input').val('');
        $("#usernameFld").removeAttr("readonly");

    }
/*
    this function handles rendering a particular user.
 */

function renderUser(renderObj)
{

    var nearestTableData = $(renderObj).closest('tr').find('td');
    var id = $(renderObj).closest('tr').attr('id');

    var res = id.match(/[\d]*/g);
    $("#idFld").val(res[5]);
    $("#usernameFld").val(nearestTableData[0].innerHTML);
    $("#passwordFld").val(nearestTableData[1].innerHTML);
    $("#firstNameFld").val(nearestTableData[2].innerHTML);
    $("#lastNameFld").val(nearestTableData[3].innerHTML);
    $("#roleFld").val(nearestTableData[4].innerHTML);

    $("#usernameFld").focus();


}



	$("#searchFld").keyup(function(){



		var searchKey= $("#searchFld").val();


        $.ajax({
        	type:"POST",
        	url: "/api/searchuser",
        	data: {

        		'search': searchKey,

        	},

        	success: function(response)
        	{

        		var list = response;
        		if(list.length==0)
    			{
        			infoMsgs("NO SEARCH RESULT");
    			}
        		else{
        			$("tbody").empty();
	        		renderUsers(list);
        		}
        		//$(':input').val('');
        		$("#usernameFld").removeAttr("readonly");

        	}
          })


	})

    /*
        this function is used to logout from admin dashboard //finalized.
     */
    function logout() {

        window.location.replace("../client-Login/login.template.client.html")
        return

    }

	 
/*
    this function is used to display success or failure information. //finalized.
 */
    function infoMsgs(msg)
    {
        var x = document.getElementById("info")
        x.className = "show";

        if(!msg.startsWith("SUCCESSFULLY")){
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




