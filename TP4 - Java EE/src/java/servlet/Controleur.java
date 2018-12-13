/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import fr.efrei.Connexion;
import fr.efrei.Employees;
import fr.efrei.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static util.Constants.*;

/**
 *
 * @author Namko
 */
public class Controleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                        //RÈcupËre et stock dans un bean, les infos du formulaire de index.jsp                
                String action =  request.getParameter(FORM_ACTION);
                String IdDetail;
                System.out.println("action="+action);
                
                if (action == null){
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
                }
                else{
                    switch (action){
                        case "Submit":                      
                            loginVerification(request, response);
                            break;
                        case "GoToAdd":
                            this.getServletContext().getRequestDispatcher( "/WEB-INF/form_employe.jsp" ).forward( request, response );
                            break;
                        case "BackToList":
                            getHomePage( request, response );
                            break;
                         
                        case "Add":
                            addEmploye( request, response );
                            break;
                            
                        case "Delete":
                            IdDetail = request.getParameter("employeId");
                            if(IdDetail != null){
                                deleteUser(request, response);
                            }else{
                                getHomePage( request, response );
                            }
                            break;
                            
                        case "Details":
                            IdDetail = request.getParameter("employeId");
                            if(IdDetail != null){
                                getDetailedEmployee(request, response); 
                            }else{
                                getHomePage( request, response );
                            }
                            break;
                          
                        default :
                            this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
                            break;                   
                }
            }
        }
    }
    
    /**
     * functions who sends the user to "bienvenue.jsp"
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void getHomePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        ArrayList<Employees> ListeEmployes = this.getEmployees();
        request.getSession().setAttribute("kEmployees", ListeEmployes);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );
    }
    
    /**
     * add one employee in the DBB
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void addEmploye(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        String message;
        
        if(checkFormIsFull(request)){
            try{
                try{
                    Connection dbConn = DataAccess.DBConnect();
                    PreparedStatement prepared;
                    prepared = dbConn.prepareStatement(DB_REQUEST_ADD_EMPLOYEE);
                    prepared.setString(1,request.getParameter(FORM_ADD_NAME));
                    prepared.setString(2,request.getParameter(FORM_ADD_FIRSTNAME));
                    prepared.setString(3,request.getParameter(FORM_ADD_TELHOME));
                    prepared.setString(4,request.getParameter(FORM_ADD_TELMOB));
                    prepared.setString(5,request.getParameter(FORM_ADD_TELPRO));
                    prepared.setString(6,request.getParameter(FORM_ADD_ADDRESS));
                    prepared.setString(7,request.getParameter(FORM_ADD_POSTAL_CODE));
                    prepared.setString(8,request.getParameter(FORM_ADD_CITY));
                    prepared.setString(9,request.getParameter(FORM_ADD_EMAIL));
                    prepared.execute();
                    message = "Employé Ajouté !";
                }
                catch(Exception e){
                    message = "Echec de l'ajout !";
                }
                ArrayList<Employees> ListeEmployes = this.getEmployees();
                request.getSession().setAttribute("kEmployees", ListeEmployes);
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );
            } catch(Exception e) {
                
            }
        }else{
            message = "Merci de remplir tous les champs avant d'ajouter un employé.";
            request.setAttribute("kMessageAdd", message);
            this.getServletContext().getRequestDispatcher("/WEB-INF/form_employe.jsp" )
                            .forward(request, response);
        }
    }
    
    /**
     * check if the form to add employee is full
     * @param request
     * @return true if all the fields are not empty
     */
    public Boolean checkFormIsFull(HttpServletRequest request){
        return  (!request.getParameter(FORM_ADD_NAME).isEmpty() 
                && !request.getParameter(FORM_ADD_FIRSTNAME).isEmpty()  
                && !request.getParameter(FORM_ADD_TELHOME).isEmpty()   
                && !request.getParameter(FORM_ADD_TELMOB).isEmpty()  
                && !request.getParameter(FORM_ADD_TELPRO).isEmpty()  
                && !request.getParameter(FORM_ADD_ADDRESS).isEmpty()  
                && !request.getParameter(FORM_ADD_POSTAL_CODE).isEmpty()  
                && !request.getParameter(FORM_ADD_CITY).isEmpty()  
                && !request.getParameter(FORM_ADD_EMAIL).isEmpty()  
                );
    }
    
    /**
     * return one employee details
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void getDetailedEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        String IdDetail = request.getParameter("employeId");
        Connection dbConn = DataAccess.DBConnect();
        Statement stmtDetail = dbConn.createStatement();
        ResultSet rsDetail = stmtDetail.executeQuery(DB_REQUEST_DETAILS_EMPLOYEE + IdDetail);
        
        Employees employeeDetail = new Employees();
        
        while(rsDetail.next()){
            employeeDetail.setEmpId(rsDetail.getInt("ID"));
            employeeDetail.setEmpNom(rsDetail.getString("NOM"));
            employeeDetail.setEmpPrenom(rsDetail.getString("PRENOM"));
            employeeDetail.setEmpTelDom(rsDetail.getString("TELDOM"));
            employeeDetail.setEmpTelMob(rsDetail.getString("TELPORT"));
            employeeDetail.setEmpTelPro(rsDetail.getString("TELPRO"));
            employeeDetail.setEmpAddress(rsDetail.getString("ADRESSE"));
            employeeDetail.setEmpCP(rsDetail.getString("CODEPOSTAL"));
            employeeDetail.setEmpVille(rsDetail.getString("VILLE"));
            employeeDetail.setEmpMail(rsDetail.getString("EMAIL"));
        }
        //request.setAttribute("kEmployees", employeeDetail);
        request.getSession().setAttribute("kEmployees", employeeDetail);
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/detailed_employee.jsp" ).forward( request, response );

    }
    
    /**
     * delete one user in the DB
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        String IdToDelete = request.getParameter("employeId");
        String message="";
        
        Connection dbConn = DataAccess.DBConnect();
        Statement stmtDelete = dbConn.createStatement();
        int result = stmtDelete.executeUpdate(DB_REQUEST_DELETE_EMPLOYEE + IdToDelete);
        if (result == 1) {
            message = "La suppression a réussi !";
            request.setAttribute("kMessage", message);
        }
        else {
            message = "La suppression a échoué !";
            request.setAttribute("kMessage", message);
        }
        
        ArrayList<Employees> ListeEmployes = this.getEmployees();
        request.getSession().setAttribute("kEmployees", ListeEmployes);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );

    }
    
    /**
     * check for the authenfication
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void loginVerification(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection dbConn = DataAccess.DBConnect();
        Statement stmtLogin = dbConn.createStatement();
        ResultSet rsLogin = stmtLogin.executeQuery(DB_REQUEST_FROM_DB_USERS);
        Connexion connBeans = new Connexion();
        
        String message;

        while(rsLogin.next()){
            connBeans.setDbLogin(rsLogin.getString(LOGIN_FROM_DB));
            connBeans.setDbMdp(rsLogin.getString(PW_FROM_DB));           
        }

        Utilisateur user = new Utilisateur();
        String login =  request.getParameter(FORM_LOGIN);
        String mdp = request.getParameter(FORM_MDP);

        // VÈrification de la valeur des champs (si vide/s, message d'erreur)
        if (login.trim().isEmpty() || mdp.trim().isEmpty()) {
                message = "Vous devez renseigner les deux champs";
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp" )
                                .forward(request, response);
        }
        else {
            message = "";
            user.setLogin(login);
            user.setMdp(mdp);
            request.getSession().setAttribute("kUser", user);

        //boucle for ‡ la place du if, si connBeans contient plusieurs lignes ?
            if ( (user.getLogin().equals(connBeans.getDbLogin())) && (user.getMdp().equals(connBeans.getDbMdp())) ){
                ArrayList<Employees> ListeEmployes = this.getEmployees();
                request.getSession().setAttribute("kEmployees", ListeEmployes);
                if (!ListeEmployes.isEmpty()){
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );
                }
                else {
                    System.out.println("La liste des employÈs est vide...");
                }
                this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );                              
            }
            else{
                message = "Echec de la connexion! Vérifiez votre login et/ou mot de passe et essayez à nouveau.";
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp" )
                                .forward(request, response);
            }
        }
        request.setAttribute("kMessage", message);
    }
    
    /**
     * 
     * @return employees array list
     * @throws SQLException 
     */
    public ArrayList<Employees> getEmployees() throws SQLException{
        Connection dbConn = DataAccess.DBConnect();
        Statement stmt = dbConn.createStatement();
        ResultSet rs = stmt.executeQuery(DB_REQUEST_FROM_EMPLOYEES);

        ArrayList<Employees> ListeEmployes = new ArrayList();

        while(rs.next()){
            Employees emp = new Employees();
            emp.setEmpId(rs.getInt(EMP_ID_FROM_DB));
            emp.setEmpNom(rs.getString(EMP_NAME_FROM_DB));
            emp.setEmpPrenom(rs.getString(EMP_FIRSTNAME_FROM_DB));
            emp.setEmpTelDom(rs.getString(EMP_TELDOM_FROM_DB));
            emp.setEmpTelMob(rs.getString(EMP_TELMOB_FROM_DB));
            emp.setEmpTelPro(rs.getString(EMP_TELPRO_FROM_DB));
            emp.setEmpAddress(rs.getString(EMP_ADDRESS_FROM_DB));
            emp.setEmpCP(rs.getString(EMP_CP_FROM_DB));
            emp.setEmpVille(rs.getString(EMP_VILLE_FROM_DB));
            emp.setEmpMail(rs.getString(EMP_MAIL_FROM_DB));
            ListeEmployes.add(emp);
        }

        return ListeEmployes;
    }
    
    /**
     * delete one employee in the DB
     * @param id
     * @return true if the delete is done
     */
    public boolean deleteEmployee(int id) {
        try{
            Connection dbConn = DataAccess.DBConnect();;
            PreparedStatement prepared = dbConn.prepareStatement(DB_REQUEST_REMOVE_EMPLOYEE, id);
            return prepared.execute();
        } catch(Exception e) {
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
