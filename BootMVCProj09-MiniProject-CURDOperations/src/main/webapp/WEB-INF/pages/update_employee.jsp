<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm"%>

<h1 style="color:red;text-align:center">Update Employee</h1>

<frm:form modelAttribute="emp">

 <table align="center" bgcolor="cyan">
   <tr>
    <td>Employee Number::</td>
    <td><frm:input type="text" path="empno" readonly="true"/></td> 
   </tr>
   
   <tr>
    <td>Employee Name::</td>
    <td><frm:input type="text" path="ename"/></td> 
   </tr>
   
   <tr>
    <td>Employee Desg::</td>
    <td><frm:input type="text" path="job"/></td> 
   </tr>
   <tr>
    <td>Employee salary::</td>
    <td><frm:input type="text" path="sal"/></td> 
   </tr> 
   
   <tr>
    <td><input type="submit" value="Update Employee"></td>
    <td><input type="reset" value="cancel"></td>
   </tr>
 </table>
</frm:form>