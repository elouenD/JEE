/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Namko
 */
public class Constants {

    // DB
    public static final String DB_REQUEST_FROM_EMPLOYEES = "SELECT * FROM EMPLOYES";
    public static final String DB_REQUEST_FROM_DB_USERS = "SELECT * FROM IDENTIFIANTS";
    public static final String DB_REQUEST_ADD_EMPLOYEE = "INSERT INTO EMPLOYES (NOM,PRENOM,TELDOM,TELPORT,TELPRO,ADRESSE,CODEPOSTAL,VILLE,EMAIL) VALUES ";
    public static final String DB_REQUEST_DELETE_EMPLOYEE = "DELETE FROM EMPLOYES WHERE ID =";
    
    // JSP PAGE
    public static final String INDEX_PAGE = "index.jsp";
    public static final String BIENVENUE_PAGE = "bienvenue.jsp";
    
    // JSP PAGE NAME
        // LOGIN FORM
    public static final String FORM_LOGIN = "formLogin";
    public static final String FORM_MDP = "formMdp";
    public static final String FORM_ACTION = "action";
        // ADD FORM
    public static final String FORM_ADD_NAME = "nomAddEmploye";
    public static final String FORM_ADD_FIRSTNAME = "prenomAddEmploye";
    public static final String FORM_ADD_TELHOME = "telDomAddEmploye";
    public static final String FORM_ADD_TELMOB = "telMobAddEmploye";
    public static final String FORM_ADD_TELPRO = "telProAddEmploye";
    public static final String FORM_ADD_ADDRESS = "adresseAddEmploye";
    public static final String FORM_ADD_POSTAL_CODE = "codePostalAddEmploye";
    public static final String FORM_ADD_CITY = "villeAddEmploye";
    public static final String FORM_ADD_EMAIL = "emailAddEmploye";
    public static final String FORM_ADD_ACTION = "add";
    
    public static final String RETURN_TO_LIST_ACTION = "returnToList";
    
    public static final String GO_TO_DETAILS = "goToDetail";
    public static final String DELETE = "delete";
    public static final String GO_TO_ADD = "goToAdd";
    
    
    
    
    
    
    
    public static final String LOGIN_FROM_DB = "LOGIN";
    public static final String PW_FROM_DB = "MDP";
    
    public static final String EMP_ID_FROM_DB = "ID";
    public static final String EMP_NAME_FROM_DB = "NOM";
    public static final String EMP_FIRSTNAME_FROM_DB = "PRENOM";
    public static final String EMP_TELDOM_FROM_DB = "TELDOM";
    public static final String EMP_TELMOB_FROM_DB = "TELPORT";
    public static final String EMP_TELPRO_FROM_DB = "TELPRO";
    public static final String EMP_ADDRESS_FROM_DB = "ADRESSE";
    public static final String EMP_CP_FROM_DB = "CODEPOSTAL";
    public static final String EMP_VILLE_FROM_DB = "VILLE";
    public static final String EMP_MAIL_FROM_DB = "EMAIL";
            
}
