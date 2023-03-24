<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link rel="stylesheet" href="css/InicioSesion.css">
    </head>
    <body>

       <div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->
    <h2 class="active"> Iniciar Sesion </h2>
    

    <div class="fadeIn first">
     
    </div>

    <!-- Login Form -->
    <form method="post" action="UsersController?action=check" >
      <input type="text" id="login" class="fadeIn second" name="login" placeholder="Usuario">
      
      <input type="text" id="password" class="fadeIn third" name="password" placeholder="Contraseña">
   
      <input type="submit" class="fadeIn fourth" value="Ingresar">
    </form>

  </div>
</div>
        
        
    </body>
</html>
