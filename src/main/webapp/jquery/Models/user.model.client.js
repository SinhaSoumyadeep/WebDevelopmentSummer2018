function User(id, user, password, first, last, role, email, dob, phone) {
    this.id = id;
  this.user= user;
  this.password = password;
  this.first = first;
  this.last = last;
  this.role = role;
  this.email = email;
  this.dob = dob;
  this.phone = phone;
 
    this.getId = getId;
    this.setId = setId;
  this.setUser = setUser;
  this.getUser = getUser;
  this.setPassword = setPassword;
  this.getPassword = getPassword;
  this.setFirst = setFirst;
  this.getFirst = getFirst;
  this.setLast = setLast;
  this.getLast = getLast;
  this.setRole = setRole;
  this.getRole = getRole;
    this.setEmail = setEmail;
    this.getEmail = getEmail;
    this.setDob = setDob;
    this.getDob = getDob;
    this.setPhone = setPhone;
    this.getPhone = getPhone;

    function setEmail(email) {
        this.email = email;

    }

    function getEmail() {
        return this.email;

    }

    function setDob(dob) {
        this.dob = dob;

    }

    function getDob() {
        return this.dob;

    }

    function setPhone(phone) {
        this.phone = phone;

    }

    function getPhone() {
        return this.phone;

    }



  function setId(id) {
      this.id = id;

  }

  function getId() {
      return this.id;

  }
  function setUser(user) {
    this.user = user;
  }
  
  function getUser() {
    return this.user;
  }
  
  function setPassword(password) {
	    this.password = password;
  }
  
  function getPassword() {
    return this.password;
  }
	  
  
  
  function setLast(last) {
	    this.lastName = last;
  }
  
  function getLast() {
    return this.last;
  }



	function setFirst(first) {
	    this.firstName = first;
	}
	
	function getFirst() {
	    return this.first;
	}
	
	function setRole(role) {
	  this.role = role;
	
	}
	
	function getRole() {
	  return this.role;
	}


	
	  
  
}
