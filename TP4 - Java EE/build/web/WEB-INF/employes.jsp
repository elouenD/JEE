<%-- 
    Document   : employes
    Created on : 23 nov. 2018, 11:12:37
    Author     : ArnoldChappedelaine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employes</title>
    </head>
    <body>
        <h1>Liste des employés</h1>
        <form>
            <table class="table table-striped">
                <t>
                    <th>Sél</th>
                    <th>NOM</th>
                    <th>PRENOM</th>
                    <th>TEL DOM</th>
                    <th>TEL PRO</th>
                    <th>ADDRESSE</th>
                    <th>CP</th>
                    <th>VILLE</th>
                    <th>MAIL</th>                 
                </tr>
                <c:forEach items="${kEmployees}" var="i">

                        <tr>
                            <td><input type="radio" value="${i.empNom}" name="EmpId"></td>
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
            <input type="submit" name="supprimer" value="Supprimer" class="btn btn-primary">
            <input type="submit" name="details" value="Details" class="btn btn-primary">
            <input type="submit" name="ajouter" value="Ajouter" class="btn btn-default">
        </form>
    </body>
</html>
