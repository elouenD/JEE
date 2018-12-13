<%-- 
    Document   : form_employe
    Created on : 29 nov. 2018, 08:52:22
    Author     : benjamin
--%>

<%@page import="fr.efrei.Employees"%>
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
           <h1 style="margin:.5rem;float:left;">Page Details</h1>
           <form method="post" action="Controleur">
            <button style="margin:1rem;float:right;" class="btn btn-outline-secondary" name="action" value="Deconnect" type="submit"><i class="fa fa-power-off"></i></button> 
           </form>
        </div>
        <% Employees employeeDetail = (Employees)session.getAttribute("kEmployees");
            System.out.println(employeeDetail.getEmpNom());
        %>
        <div class="container" style="padding-top: 5rem">
            <% if(request.getAttribute("kMessageMod") != null) {%>
                <div class="error" id="errorMessage" style="color: red;text-align: center;margin-top: 20px;"><%=request.getAttribute("kMessageMod")%></div>
            <%}%>
            <form>
            <!-- NOM -->
            <div class="form-group row">
              <label for="nom" class="col-sm-2 col-form-label">Nom</label>
              <div class="col-sm-10">
                  <input type="text" class="form-control" id="nom" name="nomModEmploye" value="<%out.print(employeeDetail.getEmpNom());%>" >
              </div>
            </div>
            
            <!-- PRENOM -->
            <div class="form-group row">
              <label for="prenom" class="col-sm-2 col-form-label">Prénom</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="prenom" name="prenomModEmploye" value="<%out.print(employeeDetail.getEmpPrenom());%>" >
              </div>
            </div>
            
            <!-- TEL DOM -->
            <div class="form-group row">
              <label for="telDom" class="col-sm-2 col-form-label">Tél Dom</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="telDom" name="telDomModEmploye" value="<%out.print(employeeDetail.getEmpTelDom());%>" >
              </div>
            </div>
            
            <!-- TEL MOB -->
            <div class="form-group row">
              <label for="telMob" class="col-sm-2 col-form-label">Tél Mob</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="TelMob" name="telMobModEmploye" value="<%out.print(employeeDetail.getEmpTelMob());%>" >
              </div>
            </div>
            
            <!-- TEL PRO -->
            <div class="form-group row">
              <label for="telPro" class="col-sm-2 col-form-label">Tél Pro</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="telPro" name="telProModEmploye" value="<%out.print(employeeDetail.getEmpTelPro());%>" >
              </div>
            </div>
            
            <div class="row">
                <div class="col">
                    
                    <!-- ADRESSE -->
                    <div class="form-group row">
                      <label for="adresse" class="col-sm-2 col-form-label">Adresse</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="adresse" name="adresseModEmploye" value="<%out.print(employeeDetail.getEmpAddress());%>" >
                      </div>
                    </div>
                </div>
                <div class="col">
                    
                    <!-- CODE POSTAL -->
                    <div class="form-group row">
                      <label for="codePostal" class="col-sm-2 col-form-label">Code Postal</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="codePostal" name="codePostalModEmploye" value="<%out.print(employeeDetail.getEmpCP());%>" >
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
                        <input type="text" class="form-control" id="ville" name="villeModEmploye" value="<%out.print(employeeDetail.getEmpVille());%>" >
                      </div>
                    </div>
                </div>
                <div class="col">
                    
                    <!-- EMAIL -->
                    <div class="form-group row">
                      <label for="email" class="col-sm-2 col-form-label">Email</label>
                      <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="emailModEmploye" value="<%out.print(employeeDetail.getEmpMail());%>" >
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
