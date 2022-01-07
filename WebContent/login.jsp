<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>


<html>
<head>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript"
    src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<meta charset="utf-8">
<title>Shangri La</title>

</head>

<style>
	#form-message{(255, 232, 232)
	border: 1px solid red;
	color: red;
	display: block;
	font-size: 12px;
	font-weight:bold;
	margin-bottom:10px;
	padding: 10px 25px;
	max-width: 250px;
	}
	
</style>

<body>
    <div style="text-align: center" class="form">
        <h1>Admin Login</h1>
 
        <form action="login" method="post" id="loginform">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" size="30" />
            <br><br>
            <label for="password">Password:</label>
            <input type="password" name="password" size="30" />
            <br>${message}
            <br><br>           
            <button type="submit">Login</button>
        </form>
    </div>
</body>
<script type="text/javascript">


    $(document).ready(function() {
        $("#loginform").validate({
            rules: {
                username: {
                    required: true,},
         
                password: "required",
            },
        
            messages: {
                username: {
                    required: "Please enter username",
                    username: "Please enter a valid username"
                },
                 
                password: "Please enter password"
            }
        });
    });
</script>
</html>