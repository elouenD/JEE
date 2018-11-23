<%-- 
    Document   : Accueil
    Created on : 25 oct. 2018, 11:12:52
    Author     : Namko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="Controleur">
            Login:<br>
            <input type="text" name="formLogin">
            <br>
            Mot de passe:<br>
            <input type="text" name="formMdp">
            <br><br>
            <input type="submit" name="action" value="Submit">
        </form> 
            
    </body>
</html>
