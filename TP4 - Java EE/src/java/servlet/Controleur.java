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
                        //Récupère et stock dans un bean, les infos du formulaire de index.jsp                
                String action =  request.getParameter(FORM_ACTION);
                
                if (action == null){
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
                }
                else{
                    switch (action){
                        case "Submit":                      
                            loginVerification(request, response);
                            break;

                        default :
                            this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
                            break;                   
                }
            }
        }
    }
    public void loginVerification(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Statement stmtLogin = DataAccess.DBConnect();
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

        // Vérification de la valeur des champs (si vide/s, message d'erreur)
        if (login.trim().isEmpty() || mdp.trim().isEmpty()) {
                message = "Vous n'avez pas rempli tous les champs !";
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp" )
                                .forward(request, response);
        }
        else {
            message = "";
            user.setLogin(login);
            user.setMdp(mdp);
            request.getSession().setAttribute("kUser", user);

        //boucle for à la place du if, si connBeans contient plusieurs lignes ?
            if ( (user.getLogin().equals(connBeans.getDbLogin())) && (user.getMdp().equals(connBeans.getDbMdp())) ){
                ArrayList<Employees> ListeEmployes = this.getEmployees();
                request.getSession().setAttribute("kEmployees", ListeEmployes);
                if (!ListeEmployes.isEmpty()){
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );
                }
                else {
                    System.out.println("La liste des employés est vide...");
                }
                this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );                              
            }
            else{
                message = "Erreur d'identifiant ou de mot de passe !";
                request.setAttribute("kMessage", message);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp" )
                                .forward(request, response);
            }
        }
        request.setAttribute("kMessage", message);
    }
    public ArrayList<Employees> getEmployees() throws SQLException{
            Statement stmt = DataAccess.DBConnect();
            ResultSet rs = stmt.executeQuery(DB_REQUEST_FROM_EMPLOYEES);
            
            ArrayList<Employees> ListeEmployes = new ArrayList();

            while(rs.next()){
                Employees emp = new Employees();
                emp.setEmpId(rs.getInt(EMP_ID_FROM_DB));
                emp.setEmpNom(rs.getString(EMP_NAME_FROM_DB));
                emp.setEmpPrenom(rs.getString(EMP_FIRSTNAME_FROM_DB));
                emp.setEmpTelDom(rs.getString(EMP_TELDOM_FROM_DB));
                emp.setEmpTelPro(rs.getString(EMP_TELPRO_FROM_DB));
                emp.setEmpAddress(rs.getString(EMP_ADDRESS_FROM_DB));
                emp.setEmpCP(rs.getString(EMP_CP_FROM_DB));
                emp.setEmpVille(rs.getString(EMP_VILLE_FROM_DB));
                emp.setEmpMail(rs.getString(EMP_MAIL_FROM_DB));
                ListeEmployes.add(emp);
            }

        return ListeEmployes;
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
