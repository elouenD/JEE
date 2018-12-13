<%-- 
    Document   : form_employe
    Created on : 29 nov. 2018, 08:52:22
    Author     : benjamin
--%>

<%@page import="fr.efrei.Employees"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>

        <div class="container">
            <form>  
            <!-- NOM -->
            <div class="form-group row">
              <label for="nom" class="col-sm-2 col-form-label">Nom</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="nom" name="nomAddEmploye" value="<c:out value="${kEmployees.empNom}"/>" >
              </div>
            </div>
            
            <!-- PRENOM -->
            <div class="form-group row">
              <label for="prenom" class="col-sm-2 col-form-label">Prénom</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="prenom" name="prenomAddEmploye" value="<c:out value="${kEmployees.empPrenom}"/>" >
              </div>
            </div>
            
            <!-- TEL DOM -->
            <div class="form-group row">
              <label for="telDom" class="col-sm-2 col-form-label">Tél Dom</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="telDom" name="telDomAddEmploye" value="<c:out value="${kEmployees.empTelDom}"/>" >
              </div>
            </div>
            
            <!-- TEL MOB -->
            <div class="form-group row">
              <label for="telMob" class="col-sm-2 col-form-label">Tél Mob</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="TelMob" name="telMobAddEmploye" value="<c:out value="${kEmployees.empTelMob}"/>" >
              </div>
            </div>
            
            <!-- TEL PRO -->
            <div class="form-group row">
              <label for="telPro" class="col-sm-2 col-form-label">Tél Pro</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="telPro" name="telProAddEmploye" value="<c:out value="${kEmployees.empTelPro}"/>" >
              </div>
            </div>
            
            <div class="row">
                <div class="col">
                    
                    <!-- ADRESSE -->
                    <div class="form-group row">
                      <label for="adresse" class="col-sm-2 col-form-label">Adresse</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="adresse" name="adresseAddEmploye" value="<c:out value="${kEmployees.empAddress}"/>" >
                      </div>
                    </div>
                </div>
                <div class="col">
                    
                    <!-- CODE POSTAL -->
                    <div class="form-group row">
                      <label for="codePostal" class="col-sm-2 col-form-label">Code Postal</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="codePostal" name="codePostalAddEmploye" value="<c:out value="${kEmployees.empCP}"/>" >
                      </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col">
                    
                    <!-- VILLE -->
                    <div class="form-group row">
                      <label for="ville" class="col-sm-2 col-form-label">Ville</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="ville" name="villeAddEmploye" value="<c:out value="${kEmployees.empVille}"/>" >
                      </div>
                    </div>
                </div>
                <div class="col">
                    
                    <!-- EMAIL -->
                    <div class="form-group row">
                      <label for="email" class="col-sm-2 col-form-label">Email</label>
                      <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="emailAddEmploye" value="<c:out value="${kEmployees.empMail}"/>" >
                      </div>
                    </div>
                </div>
            </div>
            
            <!-- SUBMIT -->
            <button class="btn btn-primary" name="action" value="Modify" type="submit">Modifier</button>
            <!-- LISTE -->
            <button class="btn btn-outline-secondary" name="action" value="BackToList" type="submit">Voir liste</button>
          </form>
        </div>
        
    </body>
</html>
