/**
 * 
 */




function dateCheck() {
	 
	if (document.form1.name.value.length < 3)
    {
		document.getElementById("name_").innerHTML = "Name is not valid!";
		return false;
    }
	else{
		document.getElementById("name_").innerHTML = "";
	}
	
	
	if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form1.email.value)))
	  {	    
		document.getElementById("email_").innerHTML = "Email is not valid!";
		return (false)
	  }
	else{
		document.getElementById("email_").innerHTML = "";
	}
	
	if (document.form1.text.value.length < 1)
    {
		document.getElementById("text_").innerHTML = "Comment must not be empty!!";
		return false;
    }
	else{
		document.getElementById("text_").innerHTML = "";
	}
	
	
	  var x = document.getElementById("success");

	  if (x.style.display === "none") {
	    x.style.display = "block";
	  }

}




