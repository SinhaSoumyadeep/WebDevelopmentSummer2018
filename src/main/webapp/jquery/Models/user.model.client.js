function User(user, password, first, last, role) {
  this.user= user;
  this.password = password;
  this.first = first;
  this.last = last;
  this.role = role;
 

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
