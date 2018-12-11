<%-- 
    Document   : form_employe
    Created on : 29 nov. 2018, 08:52:22
    Author     : benjamin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <% if(request.getAttribute("kMessageAdd") != null) {%>
                <div class="error" id="errorMessage" style="color: red;text-align: center;margin-top: 20px;"><%=request.getAttribute("kMessageAdd")%></div>
            <%}%>
            <form>
            <!-- NOM -->
            <div class="form-group row">
              <label for="nom" class="col-sm-2 col-form-label">Nom</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="nom" name="nomAddEmploye" placeholder="Ex: DURANT" >
              </div>
            </div>
            
            <!-- PRENOM -->
            <div class="form-group row">
              <label for="prenom" class="col-sm-2 col-form-label">Prénom</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="prenom" name="prenomAddEmploye" placeholder="Ex: Gilles" >
              </div>
            </div>
            
            <!-- TEL DOM -->
            <div class="form-group row">
              <label for="telDom" class="col-sm-2 col-form-label">Tél Dom</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="telDom" name="telDomAddEmploye" placeholder="Ex: 0156565656" >
              </div>
            </div>
            
            <!-- TEL MOB -->
            <div class="form-group row">
              <label for="telMob" class="col-sm-2 col-form-label">Tél Mob</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="TelMob" name="telMobAddEmploye" placeholder="Ex: 0645454545" >
              </div>
            </div>
            
            <!-- TEL PRO -->
            <div class="form-group row">
              <label for="telPro" class="col-sm-2 col-form-label">Tél Pro</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" id="telPro" name="telProAddEmploye" placeholder="Ex: 0612121212" >
              </div>
            </div>
            
            <div class="row">
                <div class="col">
                    
                    <!-- ADRESSE -->
                    <div class="form-group row">
                      <label for="adresse" class="col-sm-2 col-form-label">Adresse</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="adresse" name="adresseAddEmploye" placeholder="123 boulevard..." >
                      </div>
                    </div>
                </div>
                <div class="col">
                    
                    <!-- CODE POSTAL -->
                    <div class="form-group row">
                      <label for="codePostal" class="col-sm-2 col-form-label">Code Postal</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="codePostal" name="codePostalAddEmploye" placeholder="Ex: 75001" >
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
                        <input type="text" class="form-control" id="ville" name="villeAddEmploye" placeholder="Ex: Paris" >
                      </div>
                    </div>
                </div>
                <div class="col">
                    
                    <!-- EMAIL -->
                    <div class="form-group row">
                      <label for="email" class="col-sm-2 col-form-label">Email</label>
                      <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="emailAddEmploye" placeholder="Ex: gilles@gmail.com" >
                      </div>
                    </div>
                </div>
            </div>
            
            <!-- SUBMIT -->
            <button class="btn btn-primary" name="action" value="Add" type="submit">Valider</button>
            <!-- LISTE -->
            <button class="btn btn-outline-secondary" name="action" value="BackToList" type="submit">Voir list</button>
          </form>
        </div>
        
    </body>
</html>
