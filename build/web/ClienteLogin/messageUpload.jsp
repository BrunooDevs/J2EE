<%-- 
    Document   : messageUpload
    Created on : Nov 23, 2020, 11:52:03 AM
    Author     : pmoreno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <title>Upload Success</title>
    </head>
    <body>
        <h2>${requestScope.message}</h2>
        <br />
       
        <a href="ClienteLogin/verImagen.jsp">buscar imagen</a> 
        <br/>
        <br/>
        <h1>Ver imagen</h1><br/>
        
        <fieldset id="login">
            <legend>Mensaje</legend>
                <font color="red">
                    <% 
                     String url = (String)session.getAttribute("urlimg");
                     if( !(url == null))
                     {
                        out.println(url);                            
                        //session.setAttribute("mensaje", null);
                     }
                    %> 
                </font>
                <!--<img src="images/genetica.jpg" width="50" height="50"> -->
                <img src=${sessionScope.urlimg} alt=""  width="200" height="200"/>
        </fieldset> 
    </body>
</html>
