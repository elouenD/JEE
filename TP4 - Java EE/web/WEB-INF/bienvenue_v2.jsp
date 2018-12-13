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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Page d'accueil</h1>
        <c:choose >
            <c:when test="${kEmployees == null}">
                <div class="error"><h4>Nous devons recruter !</h4></div>
            </c:when>
            <c:otherwise >
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
                            <c:forEach items="${kEmployees}" var="i"> 
                                <tr>
                                    <td>
                                        <input type='radio' name='employeId' value="${i.empId}">
                                    </td>
                                    <td><c:out value="${i.empNom}"/></td>
                                    <td><c:out value="${i.empPrenom}"/></td>
                                    <td><c:out value="${i.empTelDom}"/></td>
                                    <td><c:out value="${i.empTelMob}"/></td>
                                    <td><c:out value="${i.empTelPro}"/></td>
                                    <td><c:out value="${i.empAddress}"/></td>
                                    <td><c:out value="${i.empCP}"/></td>
                                    <td><c:out value="${i.empVille}"/></td>
                                    <td><c:out value="${i.empMail}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button class="btn btn-primary" name="action" value="Delete" type="submit">Supprimer</button>
                    <button class="btn btn-primary" name="action" value="Details" type="submit">Details</button>
                    <button class="btn btn-outline-secondary" name="action" value="GoToAdd" type="submit">Ajouter</button>

                    <c:if test="${kMessage != null}">
                        <div class="error"><c:out value="${kMessage}"/></div>
                    </c:if>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
