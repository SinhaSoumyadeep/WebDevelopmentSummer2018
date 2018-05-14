function User(id, user, password, first, last, role) {
    this.id = id;
  this.user= user;
  this.password = password;
  this.first = first;
  this.last = last;
  this.role = role;
 
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
