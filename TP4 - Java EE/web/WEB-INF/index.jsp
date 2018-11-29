<%-- 
    Document   : Accueil
    Created on : 25 oct. 2018, 11:12:52
    Author     : Namko
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <% if(request.getAttribute("kMessage") != null) {%>
        <div class="error">Une erreur a été rencontrée: <%=request.getAttribute("kMessage")%></div>
        <%}%>
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">
                <div class="container col-offset-4 col-4" style="border: rgb(221,221,221) solid 1px; padding: 2px">
                    <div class=""></div>
                    <form class="col-12" method="post" action="Controleur">
                        <div class="form-group">
                            <input class="form-control" type="text" name="formLogin" placeholder="Login">
                        </div>
                        <div class="form-group">
                            <input class="form-control" type="password" name="formMdp" placeholder="Mot de passe">
                        </div>
                        <input type="submit" name="action" value="Submit" class="btn btn-primary">
                    </form>
                </div>
            </div>            
        </div>
    </body>
</html>
