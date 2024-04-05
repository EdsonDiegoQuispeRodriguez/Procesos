/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package komatsu.corp.procesos.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import komatsu.corp.procesos.model.dao.OrdenServicioDAO;
import komatsu.corp.procesos.model.dao.ProcesoDAO;
import komatsu.corp.procesos.model.dto.ProcesoDTO;

/**
 *
 * @author edson
 */
@WebServlet(name = "IniciarToDoServlet", urlPatterns = {"/IniciarToDoServlet"})
public class IniciarToDoServlet extends HttpServlet {
    OrdenServicioDAO dao = new OrdenServicioDAO();
    ProcesoDAO daoProc = new ProcesoDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IniciarToDoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IniciarToDoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
            String opcionSeleccionada = request.getParameter("opcionSeleccionada"); //Desarme - Armado
            String nombreProceso = "";
            String tipo = "IN PROCESS";
            String motivo ="";
            String estado = "activo";
            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Formatear la fecha en el formato 'YYYY-MM-DD'
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fecha = fechaActual.format(formatter);
            
            
            if (opcionSeleccionada != null && !opcionSeleccionada.isEmpty()) {  
                System.out.println("Opcion seleccionada: " + opcionSeleccionada);
                
                if (opcionSeleccionada.equals("1")) 
                    nombreProceso = "ARMADO";
                else if(opcionSeleccionada.equals("2"))
                    nombreProceso = "DESARMADO";
            } else {
                System.out.println("No se seleccionó ninguna opción.");
            }
            
            //OBTENCIÓN DE CHECKLIST
            String[] checkboxes = request.getParameterValues("checkboxName1");
            int numero = 0;
            
            ProcesoDTO proceso = new ProcesoDTO();
            proceso.setNombreProceso(nombreProceso);
            proceso.setEstado(estado);
            proceso.setFecha(fecha);
            proceso.setMotivo(motivo);
            proceso.setTipo(tipo);
            
            if (checkboxes != null) {
                for (String checkbox : checkboxes) {
                    numero = Integer.parseInt(checkbox);
                    proceso.setIdOS(numero);
                    daoProc.registrarProceso(proceso);
                    dao.editarOrdenServicioEmpezar(numero);
                    System.out.println("Checkbox marcado: " + checkbox);
                }
            } else {
                System.out.println("No se seleccionaron checkboxes.");
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("./pantallaPrincipal.jsp"); // Reemplaza "/miVista.jsp" con la ruta real de tu JSP.
            dispatcher.forward(request, response);   

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
