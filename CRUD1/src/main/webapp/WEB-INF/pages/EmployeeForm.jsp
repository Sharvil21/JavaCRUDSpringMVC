<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<style type="text/css">
.error{
color: red;
}

</style>

</head>
<body>

<div align="left"><a href="/hb.smvc">Back</a></div>

    <div align="center">
        <h1>New/Edit Employee</h1>
        <form:form action="saveEmployee" method="post" modelAttribute="employee">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" required="required" id="func" /></td>
                <td id="resp" style="color:red;"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" required="required" /></td>
                <td><form:errors path="email" cssClass="error"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address" required="required" id="addr" /></td>
            </tr>
            <tr>
                <td>Telephone:</td>
                <td><form:input path="telephone" required="required" /></td>
                <td><form:errors path="telephone" cssClass="error"/></td>
                
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save" ></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>

<script type="text/javascript">
$( document ).ready(function() {

$("#func").blur(function(){

	
	
	$.ajax({
		
		url: 'Controller/GetServlet',
		type: 'GET',
		data: {
			name: $('#func').val()
		},
		
		success: function(responseText){
			$('#resp').text(responseText)
			console.log("Success")
		}
	});
	console.log("Lost focus");	
});
});


</script>
</html>