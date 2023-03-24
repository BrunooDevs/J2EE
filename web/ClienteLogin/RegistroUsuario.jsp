<%@page import="sistema.modelAdmin.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
x<%-- 
    Document   : RegistroUsuario
    Created on : 28 mar. 2021, 15:19:32
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
        <link rel="stylesheet" href="css/RegistroUsuarios.css">
    </head>
    
    
    <body>
           
<form method="POST" action="UsersController?action=alta" >

              
    
    
    
    
    
      
        <h1>Registro</h1>
        
        <fieldset>
          <legend><span class="number">1</span>INFORMACION PERSONAL</legend>
          <label for="name">Nombre:</label>
          <input type="text" id="user_name" name="user_name">
          
          <label for="apellidos">Apellidos:</label>
          <input type="text" id="user_apellidos" name="user_apellidos">
          
          <label for="fecha">Fecha de Nacimiento:</label>
          <input type="date"  id="user_fecha" name="user_fecha">
     
          <label for="telefono">Telefono:</label>
          <input type="text"  id="user_telefono" name="user_telefono">
          
          <label for="Correo">Correo:</label>
          <input type="email" id="user_email" name="user_email">
          
           <label for="Domicilio">Domicilio:</label>
          <input type="text"  id="user_domicilio" name="user_domicilio">
          

        </fieldset>
        
        <fieldset>
          <legend><span class="number">2</span>CREDENCIALES</legend>
          
          <label for="usuario">Usuario:</label>
          <input type="text" id="user_usuario" name="user_usuario">
          
          <label for="password">Contraseña:</label>
          <input type="password" id="user_password" name="user_password">
          
          <label for="password">Repetir Contraseña:</label>
          <input type="password" id="user_reppassword" name="user_reppassword">
          
        </fieldset>
        <fieldset>

        </fieldset>
        <button type="submit" value="Submit" >REGISTRAR</button>
       
      </form>
        

    
    <%-- end web service invocation --%><hr/>
    </body>
</html>
