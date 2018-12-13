/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.Employes;
import fr.efrei.Connexion;
import fr.efrei.Employees;
import fr.efrei.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.service.EmployesFacadeREST;
import services.service.IdentifiantsFacadeREST;
import static util.Constants.*;

/**
 *
 * @author Namko
 */
public class Controleur extends HttpServlet {
    
    @EJB
    private EmployesFacadeREST employeService;
    private IdentifiantsFacadeREST identifiantService;
    

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                        //RÈcupËre et stock dans un bean, les infos du formulaire de index.jsp                
                String action =  request.getParameter(FORM_ACTION);
                String IdDetail;
                System.out.println("action="+action);
                
                if (action == null){
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/index_v2.jsp" ).forward( request, response );
                }
                else{
                    switch (action){
                        case "Submit":                      
                            loginVerification(request, response);
                            break;
                        case "GoToAdd":
                            this.getServletContext().getRequestDispatcher( "/WEB-INF/form_employe_v2.jsp" ).forward( request, response );
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
                            
                        case "Modify":
                            IdDetail = (String) request.getSession().getAttribute("employeId");
                            if(IdDetail != null){
                                modEmploye( request, response , IdDetail);
                            }else{
                                getHomePage( request, response );
                            }
                            break;
                            
                        case "Deconnect":
                            request.getSession().setAttribute("kConnect", false);
                            this.getServletContext().getRequestDispatcher( "/WEB-INF/index_v2.jsp" ).forward( request, response );
                            break;
                          
                        default :
                            this.getServletContext().getRequestDispatcher( "/WEB-INF/index_v2.jsp" ).forward( request, response );
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
    public void getHomePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException{
        ArrayList<Employees> ListeEmployes = this.getEmployees();
        request.getSession().setAttribute("kEmployees", ListeEmployes);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue_v2.jsp" ).forward( request, response );
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
        
        if(checkFormAddIsFull(request)){
            try{
                try{
                    entities.Employes e = new Employes(request.getParameter(FORM_ADD_NAME),request.getParameter(FORM_ADD_FIRSTNAME), request.getParameter(FORM_ADD_TELHOME), request.getParameter(FORM_ADD_TELMOB), request.getParameter(FORM_ADD_TELPRO), request.getParameter(FORM_ADD_ADDRESS), parseInt(request.getParameter(FORM_ADD_POSTAL_CODE)), request.getParameter(FORM_ADD_CITY), request.getParameter(FORM_ADD_EMAIL));
                    employeService.create(e);
                    message = "Employé Ajouté !";
                }
                catch(Exception e){
                    message = "Echec de l'ajout !";
                }
                ArrayList<Employees> ListeEmployes = this.getEmployees();
                request.getSession().setAttribute("kEmployees", ListeEmployes);
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue_v2.jsp" ).forward( request, response );
            } catch(Exception e) {
                
            }
        }else{
            message = "Merci de remplir tous les champs avant d'ajouter un employé.";
            request.setAttribute("kMessageAdd", message);
            this.getServletContext().getRequestDispatcher("/WEB-INF/form_employe_v2.jsp" )
                            .forward(request, response);
        }
    }
    

    public void modEmploye(HttpServletRequest request, HttpServletResponse response, String idEmploye) throws SQLException, ServletException, IOException{
        String message;
        
        if(checkFormModIsFull(request)){
            try{
                try{
                    entities.Employes em = new Employes(request.getParameter(FORM_ADD_NAME),request.getParameter(FORM_ADD_FIRSTNAME), request.getParameter(FORM_ADD_TELHOME), request.getParameter(FORM_ADD_TELMOB), request.getParameter(FORM_ADD_TELPRO), request.getParameter(FORM_ADD_ADDRESS), parseInt(request.getParameter(FORM_ADD_POSTAL_CODE)), request.getParameter(FORM_ADD_CITY), request.getParameter(FORM_ADD_EMAIL));
                    employeService.edit(parseInt(idEmploye), em);
                    message = "Employé Modifié !";
                }
                catch(Exception e){
                    message = "Echec de la modification !";
                }
                ArrayList<Employees> ListeEmployes = this.getEmployees();
                request.getSession().setAttribute("kEmployees", ListeEmployes);
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue_v2.jsp" ).forward( request, response );
            } catch(Exception e) {
                
            }
        }else{
            message = "Merci de remplir tous les champs avant de modifier un employé.";
            request.setAttribute("kMessageMod", message);
            this.getServletContext().getRequestDispatcher("/WEB-INF/detailed_employee_v2.jsp" )
                            .forward(request, response);
        }
    }
    
    public Boolean checkFormAddIsFull(HttpServletRequest request){
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
    public Boolean checkFormModIsFull(HttpServletRequest request){
        return  (!request.getParameter(FORM_MOD_NAME).isEmpty() 
                && !request.getParameter(FORM_MOD_FIRSTNAME).isEmpty()  
                && !request.getParameter(FORM_MOD_TELHOME).isEmpty()   
                && !request.getParameter(FORM_MOD_TELMOB).isEmpty()  
                && !request.getParameter(FORM_MOD_TELPRO).isEmpty()  
                && !request.getParameter(FORM_MOD_ADDRESS).isEmpty()  
                && !request.getParameter(FORM_MOD_POSTAL_CODE).isEmpty()  
                && !request.getParameter(FORM_MOD_CITY).isEmpty()  
                && !request.getParameter(FORM_MOD_EMAIL).isEmpty()  
                );
    }
    
    public void getDetailedEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException{
        String IdDetail = request.getParameter("employeId");
        entities.Employes e = employeService.find(parseInt(IdDetail));
       
        
        Employees employeeDetail = new Employees();
        employeeDetail.setEmpId(e.getId());
        employeeDetail.setEmpNom(e.getNom());
        employeeDetail.setEmpPrenom(e.getPrenom());
        employeeDetail.setEmpTelDom(e.getTeldom());
        employeeDetail.setEmpTelMob(e.getTelport());
        employeeDetail.setEmpTelPro(e.getTelpro());
        employeeDetail.setEmpAddress(e.getAdresse());
        employeeDetail.setEmpCP(String.valueOf(e.getCodepostal()));
        employeeDetail.setEmpVille(e.getVille());
        employeeDetail.setEmpMail(e.getEmail());
        
        request.getSession().setAttribute("kEmployees", employeeDetail);
        request.getSession().setAttribute("employeId", IdDetail);
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/detailed_employee_v2.jsp" ).forward( request, response );

    }
    
    /**
     * delete one user in the DB
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException{
        String IdToDelete = request.getParameter("employeId");
        String message="";
        
        
        try{
           employeService.remove(parseInt(IdToDelete)); 
           message = "La suppression a réussi !";
        }catch(Exception e){
            message = "La suppression a échoué !";
        }
        request.setAttribute("kMessage", message);
        
        
        ArrayList<Employees> ListeEmployes = this.getEmployees();
        request.getSession().setAttribute("kEmployees", ListeEmployes);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue_v2.jsp" ).forward( request, response );

    }
    
    /**
     * check for the authenfication
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    public void loginVerification(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
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
                this.getServletContext().getRequestDispatcher("/WEB-INF/index_v2.jsp" )
                                .forward(request, response);
        }
        else {
            message = "";
            user.setLogin(login);
            user.setMdp(mdp);
            request.getSession().setAttribute("kUser", user);

        //boucle for ‡ la place du if, si connBeans contient plusieurs lignes ?
            if ( (user.getLogin().equals(connBeans.getDbLogin())) && (user.getMdp().equals(connBeans.getDbMdp())) ){
                request.getSession().setAttribute("kConnect", true);
                ArrayList<Employees> ListeEmployes = this.getEmployees();
                request.getSession().setAttribute("kEmployees", ListeEmployes);
                if (!ListeEmployes.isEmpty()){
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue_v2.jsp" ).forward( request, response );
                }
                else {
                    System.out.println("La liste des employÈs est vide...");
                }
                this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue_v2.jsp" ).forward( request, response );                              
            }
            else{
                message = "Echec de la connexion! Vérifiez votre login et/ou mot de passe et essayez à nouveau.";
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index_v2.jsp" )
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
    public ArrayList<Employees> getEmployees() throws SQLException, ClassNotFoundException{
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
            Connection dbConn = DataAccess.DBConnect();
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
        } catch (ClassNotFoundException ex) {
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
        } catch (ClassNotFoundException ex) {
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
