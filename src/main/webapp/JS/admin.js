
$(document).ready(function(){

	onload();
	function desc(msg)
	{
	   var x = document.getElementById("info")
	    x.className = "show";
	   if(!msg.startsWith("SUCCESSFULLY")){
	   	x.style.backgroundColor = "rgb(217, 56, 26)";
	   }
	    x.innerHTML=msg;
	    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 2600);
	}
	function onload()
	{
    $.ajax({
    	type:"POST", 
    	url: "api/onload",
    	
    	
    	success: function(response)
    	{
    		

    		var list = response;
    		if(list.length==0)
			{
    			alert("NO DATA FOUND..")
			}
    		else{
		    		list.forEach(function(l){
		    			
		    			
		    			var $row = $('<tr class="wbdv-template wbdv-user wbdv-hidden" id="trow['+l.id+']">'+
		  				      '<td id="user['+l.id+']">'+l.user+'</td>'+
		  				      '<td id="pass['+l.id+']">'+l.password+'</td>'+
		  				      '<td id="first['+l.id+']">'+l.first+'</td>'+
		  				      '<td id="last['+l.id+']">'+l.last+'</td>'+
		  				      '<td id="role['+l.id+']">'+l.role+'</td>'+
		  				      '<td style="padding-left: 123px;" ><button type="button" class="btn btn-danger" id="del">Delete</button>  <button type="button" style=" padding-left: 23px; padding-right: 23px;" class="btn btn-warning" id="edit">Edit</button></td>'+
		  				      '</tr>'); 
		    			
		    			$('table> tbody:last').append($row);
		    			
		    			
		    			
		    			
		    		});
    		
    	}
    		
    	}
    })
    $("#usernameFld").removeAttr("readonly");
	}
	
	
	
	
	$("#search").click(function(){
		
		
		
		var searchKey= $("#searchFld").val();	
		
        
        $.ajax({
        	type:"POST", 
        	url: "api/searchuser",
        	data: {
        		
        		'search': searchKey,
          		
        	},
        	
        	success: function(response)
        	{
        		
        		var list = response;
        		if(list.length==0)
    			{
        			/*var $row = $('<tr class="wbdv-template wbdv-user wbdv-hidden" id="terror">'+
	      				      '<td id="err"> NO SEARCH RESULT...</td>'+
	      				      '</tr>'); 
        			$('table> tbody:last').append($row);*/
        			desc("NO SEARCH RESULT");
        			
    			}
        		else{
        			$("tbody").empty();
	        		list.forEach(function(l){
	        			
	        			
	        			var $row = $('<tr class="wbdv-template wbdv-user wbdv-hidden" id="trow['+l.id+']">'+
	      				      '<td id="user['+l.id+']">'+l.user+'</td>'+
	      				      '<td id="pass['+l.id+']">'+l.password+'</td>'+
	      				      '<td id="first['+l.id+']">'+l.first+'</td>'+
	      				      '<td id="last['+l.id+']">'+l.last+'</td>'+
	      				      '<td id="role['+l.id+']">'+l.role+'</td>'+
	      				      '<td style="padding-left: 123px;" ><button type="button" class="btn btn-danger" id="del">Delete</button>  <button type="button" style=" padding-left: 23px; padding-right: 23px;" class="btn btn-warning" id="edit">Edit</button></td>'+
	      				      '</tr>'); 
	        			
	        			$('table> tbody:last').append($row);
	        			
	        			
	        		});
        		}
        		$(':input').val('');
        		$("#usernameFld").removeAttr("readonly");
        		
        		
        	}
          })
        
        
	})
	
	
	
	$("#add").click(function(){
		var user= $("#usernameFld").val();	
        var pass= $("#passwordFld").val();
        var first= $("#firstNameFld").val();
        var last= $("#lastNameFld").val();
        var role= $("#roleFld").val();
		$.ajax({
        	type:"POST", 
        	url: "api/adduser",
        	data: {
        		'user': user,
          		'pass': pass,
          		'first':first,
          		'last':last,
          		'role':role
        	},
        	
        	success: function(response)
        	{
        		$(':input').val('');
        		if(response!='success')
    			{
        			//$("tbody").empty();
        			/*var $row = $('<tr class="wbdv-template wbdv-user wbdv-hidden" id="terror">'+
	      				      '<td id="err"> '+response+' </td>'+
	      				      '</tr>'); 
        			$('table> tbody:last').append($row);*/
        			desc(response);
    			}
        		else{
        			
	        		$("tbody").empty();
	        		onload();
	        		desc("SUCCESSFULLY ADDED");
	        		
        		}
        	}
          })
          $("#usernameFld").removeAttr("readonly");
	})

    $(document).on('click', '#del', function(){
    	
    	var $id = $(this).closest('tr').attr('id');
    	
    	$.ajax({
        	type:"POST", 
        	url: "api/deluser",
        	data: {
        		'id': $id
        	},
        	
        	success: function(response)
        	{
        		$(':input').val('');
        		$("tbody").empty();
        		onload();
        		desc("SUCCESSFULLY DELETED");
        	}
          })
          $("#usernameFld").removeAttr("readonly");
        
    });
	
	
	 $(document).on('click', '#edit', function(){
		 
		 
		 var $id = $(this).closest('tr').attr('id');
		 var res = $id.match(/[\d]/g);
		 var ress="[";
		    for (i = 0; i < res.length; i++) { 
		    		ress=ress+res[i];
			}
		    ress=ress.concat("]");
		  

		$(this).closest('tr').find('td').each(function(){
			 if($(this).attr("id")=="user".concat(ress))
			 {
			 	$("#usernameFld").val($(this).html());
			 }
			 
			 if($(this).attr("id")=="pass".concat(ress))
			 {
			 	$("#passwordFld").val($(this).html());
			 }
			 
			 if($(this).attr("id")=="first".concat(ress))
			 {
			 	$("#firstNameFld").val($(this).html());
			 }
			 
			 if($(this).attr("id")=="last".concat(ress))
			 {
			 	$("#lastNameFld").val($(this).html());
			 }
			 if($(this).attr("id")=="role".concat(ress))
			 {
			 	$("#roleFld").val($(this).html());
			 }
			 $("#usernameFld").attr('readonly','true');
				 	
		 })
		 
		 
		 
	 });
	 
	 $("#update").click(function(){
		 
			var user= $("#usernameFld").val();	
	        var pass= $("#passwordFld").val();
	        var first= $("#firstNameFld").val();
	        var last= $("#lastNameFld").val();
	        var role= $("#roleFld").val();
			$.ajax({
	        	type:"POST", 
	        	url: "api/updateuser",
	        	data: {
	        		'user': user,
	          		'pass': pass,
	          		'first':first,
	          		'last':last,
	          		'role':role
	        	},
	        	
	        	success: function(response)
	        	{
	        		$(':input').val('');
	        		if(response!='success')
	    			{
	        			//$("tbody").empty();
	        			/*var $row = $('<tr class="wbdv-template wbdv-user wbdv-hidden" id="terror">'+
		      				      '<td id="err"> CANNOT BE UPDATED... </td>'+
		      				      '</tr>'); 
	        			$('table> tbody:last').append($row);*/
	        			desc("CANNOT BE UPDATED");
	    			}
	        		else{
		        		
		        		$("tbody").empty();
		        		onload();
		        		desc("SUCCESSFULLY UPDATED");
	        		}
	        	}
	          })
	          $("#usernameFld").removeAttr("readonly");
			
		})
    
    
});




