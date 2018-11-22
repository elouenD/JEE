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
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            ResultSet rs = DataAccess.DBConnect();

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
            System.out.println(ListeEmployes);
            request.getSession().setAttribute("kEmployees", ListeEmployes);
            if (!ListeEmployes.isEmpty()){
                //response.sendRedirect(BIENVENUE_PAGE);
                this.getServletContext().getRequestDispatcher( "/WEB-INF/bienvenue.jsp" ).forward( request, response );
            }
            else {
                System.out.println("La liste des employés est vide...");
            }
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
