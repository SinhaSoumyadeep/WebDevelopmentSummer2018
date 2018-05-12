function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.updateUser = updateUser;
    this.deleteUser = deleteUser;
    var self = this;



    function createUser(User) { 

        
    	var addUserRes = $.ajax({
            async: false,
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url: "/api/adduser",
            data: JSON.stringify(User),

          })

        return addUserRes.responseText
            

    }

    function findAllUsers() {

        var findAllUserRes =  $.ajax({
            async: false,
            type:"POST",
            url: "/api/onload",

        })

        return findAllUserRes.responseJSON

    }

    function updateUser(User) {


        var updateUserRes = $.ajax({
            async: false,
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url: "/api/updateuser",
            data: JSON.stringify(User),

        })

        return updateUserRes.responseText

    }

    function deleteUser(id) {

        var deleteUserRes = $.ajax({
            async: false,
            type:"POST",
            url: "/api/deluser",
            data: {
                'id': id
            },


        })

        var delResponse = deleteUserRes.responseText;
        return delResponse;

    }
    
}
