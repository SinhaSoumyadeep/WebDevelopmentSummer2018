function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findByUsername = findByUsername;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.deleteUser = deleteUser;
    this.register = register;
    this.login = login;
    this.profile = profile;

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

    function register(User) {

        var regResponse = $.ajax({
            async: false,
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url: "/api/register",
            data: JSON.stringify(User),

        })

        return regResponse.responseText

    }

    function findByUsername(user) {
        var findUsernameRes = $.ajax({
            async: false,
            type:"POST",
            url: "/api/finduser",
            data: {
                'user': user
            },

        })


        return findUsernameRes.responseText;

    }

    function login(User) {

        var loginRes = $.ajax({
            async: false,
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url: "/api/login",
            data: JSON.stringify(User),

        })

        return loginRes.responseJSON

    }

    function findUserById(User) {

        var retUser = $.ajax({
            async: false,
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url: "/api/retrieveProfile",
            data: JSON.stringify(User),

        })

        return retUser.responseJSON

    }

    function profile(User) {

        var retUser = $.ajax({
            async: false,
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url: "/api/profile",
            data: JSON.stringify(User),

        })

        return retUser.responseJSON

    }



}
