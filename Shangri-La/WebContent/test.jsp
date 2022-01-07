<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test Submission</title>
<style>
form{
font: 1em sans-serif;
max-width: 320px;
}

p > label{
display: block;
}

input[type="text"],
input[type="email"],
input[type="number"],
textarea,
fieldset{
	width: 100%
	border:1px solid #333
	box-sizing: border-box;
}

input:invalid{
	box-shadow:0 0 5px 1px red;
}
	
input:focus:invalid {
	box-shadow:none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>

<div align="center">
  <h1>Test Submission form</h1>
  
  <form action="<%= request.getContextPath() %>/test" method="post" >
  <p>
  <label for="t1"> What is your Email Address</label>
  	<input type="email" name="email" />
  </p>

  <p>
  	<label for="t2"> What is your full Name</label>
    <input type="text" name="fullName" />
  </p>
     
  <p>
    <label for="t3">How old are you?</label>
    <input type="number" name="age"  value="1" min="1" step="1" pattern="\d"/>
 </p>
 
 <p>
 <label for="t4">Please Enter your Gender</label>
     <select name = "gender">
     	<option value="Male">Male</option>
     	<option value="Memale">Female</option>
     	<option value="Other">Other</option>
     </select>
 </p>
 
 <p>
    <label for="t5">Please Enter your Address?</label>
    <input  type="text" name="address" />
</p>
     <p>
     <label for="t6">Please Enter your Postcode</label>
     <input  type="text" name="postcode" />
     </p>
     <p>
     
     <label for="ttn">Please Enter your TTN code</label>
     <input id="ttn" type="text" name="ttn" autocomplete="off" />
     
     </p>
     <P>
     <label for="t8">Please Enter your Test Result</label>
     <select  name = "testresult">
     	<option value="Positive">Positive</option>
     	<option value="Negative">Negative</option>
     	<option value="Inconclusive">Inconclusive</option>
     </select>
     </p>
     <input type="submit" value="Submit" id="register" />
  </form>
  
 </div>
<div id="name"></div>




<script type="text/javascript">
$document.ready(function() {
    $('form > input').keyup(function() {

        var empty = false;
        $('form > input').each(function() {
            if ($(this).val() == '') {
                empty = true;
            }
        });
        if (empty) {
            $('#register').attr('disabled', 'disabled'); 
        } else {
            $('#register').removeAttr('disabled');
        }
    });
})
	$(document).ready(function() {
        $("test_form").validate({
            rules: {
                email: {
                    required: true,
                    },
                fullname: {
                	required:true,
                }    
                age: {
                	required:true,
                },
                address: {
                	required:true,
                },
                postcode: {
                	required:true,
                },
                ttn: {
                	required:true,
                },
                
                    
            },
             
            messages: {
                email: {
                    required: "Please enter email",
                    email: "Please enter a valid email address"
                },
                 
       
            }
        });
    });
 

   </script>
   </body>
</html>