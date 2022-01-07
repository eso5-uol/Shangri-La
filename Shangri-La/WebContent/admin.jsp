<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ page import="net.test.dao.TestDAO" %>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix ="c" %>

<html>
<head>
<meta charset="utf-8">
<title>Admin Panel</title>
<script>
    function myFunction() {
    	  var x = document.getElementById("test_table");
    	  if (x.style.display === "none") {
    	    x.style.display = "block";
    	  } else {
    	    x.style.display = "none";
    	  }
    	}
    
    function myFunction2() {
  	  var x = document.getElementById("pos_table");
  	  if (x.style.display === "none") {
  	    x.style.display = "block";
  	  } else {
  	    x.style.display = "none";
  	  }
  	}
    function myFunction3() {
    	  var x = document.getElementById("neg_table");
    	  if (x.style.display === "none") {
    	    x.style.display = "block";
    	  } else {
    	    x.style.display = "none";
    	  }
    	}
   
    
    </script>
</head>
<body>
<%
String user =(String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
for( Cookie cookie: cookies){
	if(cookies !=null){
		if(cookie.getName().equals("user")){ userName = cookie.getValue();}
		if(cookie.getName().equals("JSESSIONID")){ sessionID= cookie.getValue();}
	}
	
}

TestDAO testDao = new TestDAO();

int pos = testDao.allTests("Positive").size();
int neg= testDao.allTests("Negative").size();
int total = testDao.selectAllTests().size();
String test = "this is a test";
pageContext.setAttribute("test", test);
%>

<h3>hi <%=userName %>, Login Sucessful. Your session ID=<% %></h3>
    <div class="panel" style="text-align: center">
        <h1>Admin Panel</h1>
        <br><br>
        <a href="logout" role="button">Logout</a>
        <br>
        <br>
        <a href="<%=request.getContextPath()%>/showtest" role="button" onclick="myFuntion()" >Tests</a> 
        <br><br>
        <p >Total number of test Submitted <%=total %></p>
        <br><br>
        <a href="<%=request.getContextPath()%>/showpos" role="button" onclick="myFuntion2()" >Positive Tests</a>
        <p >Total number of Positive tests Submitted <%=pos %></p>
        <br><br>
        <a href="<%=request.getContextPath()%>/showneg" role="button" onclick="myFuntion3()" >Negative Tests</a>
        <p >Total number of Negative tests submitted <%=neg %></p>
        
        <h3><% %></h3>
        
        <br><br>
   </div>
   
   
   
   <h3><%testDao.allTests("Inconlusive").size(); %></h3>
   
    <div id ="test_table" align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Tests</h2></caption>
            <tr>
                <th>Email</th>
                <th>FullName</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Address</th>
                <th>PostCode</th>
                <th>ttn</th>
                <th>TestResult</th>
              
            </tr>
            <c:forEach items="${listTest}" var="test" >
                <tr>
                    <td>${test.fullName}</td>
                    <td>${test.age}</td>
                    <td>${test.email}</td>
                    <td>${test.gender} </td>
                    <td>${test.address}</td>
                    <td>${test.postcode}</td>
                    <td>${test.ttn}</td>
                    <td>${test.testResult}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
    <div id ="pos_table" align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Positive tests</h2></caption>
            <tr>
                <th>Email</th>
                <th>FullName</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Address</th>
                <th>PostCode</th>
                <th>ttn</th>
                <th>TestResult</th>
              
            </tr>
            <c:forEach items="${listTestPos}" var="test" >
                <tr>
                    <td>${test.fullName}</td>
                    <td>${test.age}</td>
                    <td>${test.email}</td>
                    <td>${test.gender} </td>
                    <td>${test.address}</td>
                    <td>${test.postcode}</td>
                    <td>${test.ttn}</td>
                    <td>${test.testResult}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
    <div id ="neg_table" align="center">
        <table id="neg" border="1" cellpadding="4">
            <caption><h2>List of Negative tests</h2></caption>
            <tr>
                <th>Email</th>
                <th>FullName</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Address</th>
                <th>PostCode</th>
                <th>ttn</th>
                <th>TestResult</th>
              
            </tr>
            <c:forEach items="${listTestNeg}" var="test" >
                <tr>
                    <td>${test.fullName}</td>
                    <td>${test.age}</td>
                    <td>${test.email}</td>
                    <td>${test.gender} </td>
                    <td>${test.address}</td>
                    <td>${test.postcode}</td>
                    <td>${test.ttn}</td>
                    <td>${test.testResult}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
    
    <script>
    $(document).ready(function(){
    	var tabl = document.getElementById("neg")
    	var row = table.getElementByTagName("tr")
    	var t = 0;
    	for(var i=0; i<row.length; i++){
    		t++;
    	}
    	var message = "Total Row Count: " + t;
    }
    </script>
    
</body>
</html>