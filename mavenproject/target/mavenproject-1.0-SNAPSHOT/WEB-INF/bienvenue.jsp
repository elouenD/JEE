<%-- 
    Document   : affichage
    Created on : 25 oct. 2018, 11:18:15
    Author     : Namko
--%>

<%@page import="fr.efrei.Employees"%>
<%@page import="java.util.List"%>
<%@page import="util.Constants"%>
<%@page import="fr.efrei.Connexion"%>
<%@page import="fr.efrei.Utilisateur"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
           <h1 style="margin:.5rem;float:left;">Page d'accueil</h1>
           <form method="post" action="Controleur">
            <button style="margin:1rem;float:right;" class="btn btn-outline-secondary" name="action" value="Deconnect" type="submit"><i class="fa fa-power-off"></i></button> 
           </form>
        </div>
        
        <%
            List<Employees> userList = (List<Employees>)session.getAttribute("kEmployees");
            if(userList.isEmpty()){ %>
                <div class="error"> 
                    <h4>Nous devons recruter !</h4>
                </div>
            <%
                //out.print("<td>" + session.getAttribute("empty_list_message") + "</td>");
            }
            else { %>
        <form method="post" action="Controleur">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Sél</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">TelDom</th>
                    <th scope="col">TelPor</th>
                    <th scope="col">TelPro</th>
                    <th scope="col">Adresse</th>
                    <th scope="col">CP</th>
                    <th scope="col">Ville</th>
                    <th scope="col">Email</th>
                </tr>
                </thead>
                <tbody>
                    <%
                    for(Employees employe : userList){
                        out.print("<tr><td>" + "<input type='radio' name='employeId' value='"+ employe.getEmpId() +"'>" + "</td>");
                        out.print("<td>" + employe.getEmpNom() + "</td>");
                        out.print("<td>" + employe.getEmpPrenom() + "</td>");
                        out.print("<td>" + employe.getEmpTelDom() + "</td>");
                        out.print("<td>" + employe.getEmpTelMob()+ "</td>");
                        out.print("<td>" + employe.getEmpTelPro() + "</td>");
                        out.print("<td>" + employe.getEmpAddress() + "</td>");
                        out.print("<td>" + employe.getEmpCP() + "</td>");
                        out.print("<td>" + employe.getEmpVille() + "</td>");
                        out.print("<td>" + employe.getEmpMail() + "</td></tr>");
                    }
                %>
                </tbody>
            </table>
            <button class="btn btn-primary" name="action" value="Delete" type="submit">Supprimer</button>
            <button class="btn btn-primary" name="action" value="Details" type="submit">Details</button>
            <button class="btn btn-outline-secondary" name="action" value="GoToAdd" type="submit">Ajouter</button>
            <% if(request.getAttribute("kMessage") != null) {%>
                <div class="error"><%=request.getAttribute("kMessage")%></div>
            <%}%>
        </form>
        <% } %>
    </body>
</html>
