<%-- 
    Document   : affichage
    Created on : 25 oct. 2018, 11:18:15
    Author     : Namko
--%>

<%@page import="util.Constants"%>
<%@page import="fr.efrei.Connexion"%>
<%@page import="fr.efrei.Utilisateur"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>ID</td>
                <td>NOM</td>
                <td>PRENOM</td>
                <td>TEL DOM</td>
                <td>TEL PRO</td>
                <td>ADDRESSE</td>
                <td>CP</td>
                <td>VILLE</td>
                <td>MAIL</td>                 
            </tr>
            <c:forEach items="${kEmployees}" var="i"> 
                <tr>
                    <td><c:out value="${i.empId}"/></td>
                    <td><c:out value="${i.empNom}"/></td>
                    <td><c:out value="${i.empPrenom}"/></td>
                    <td><c:out value="${i.empTelDom}"/></td>
                    <td><c:out value="${i.empTelPro}"/></td>
                    <td><c:out value="${i.empAddress}"/></td>
                    <td><c:out value="${i.empCP}"/></td>
                    <td><c:out value="${i.empVille}"/></td>
                    <td><c:out value="${i.empMail}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
