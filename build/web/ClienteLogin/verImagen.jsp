<%-- 
    Document   : verImagen
    Created on : Nov 23, 2020, 2:01:13 PM
    Author     : pmoreno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    
    
    
    <body>
        <h1>Ver imagen</h1><br/>
      <fieldset id="login">
        <legend>Ingresar</legend>
  
        <form method="GET" action="UploadController">  
             <input type="hidden" name="action" value="buscarimg">
             <label for="imgname">ID imagen</label>
             <input type="text" name="imageid"/>
             <br>
             <input type="submit" value="buscar" />               
        </form>
        <font color="red">
                    <% 
                     String msg = (String)session.getAttribute("message");
                     if( !(msg == null))
                     {
                        out.println(msg);                            
                        //session.setAttribute("mensaje", null);
                     }
                    %>                      
        </font>
      </fieldset> 

        
    </body>
</html>
