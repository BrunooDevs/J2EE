/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;

import sistema.dao.DoctorDao;
import sistema.model.Permisos;
import sistema.model.Doctor;

/**
 *
 * @author Owner
 */
@WebServlet(name = "DoctorController", urlPatterns = {"/DoctorController"})

public class DoctorController extends HttpServlet {

    private DoctorDao MedicoDao;

    int id_pers;

    public DoctorController() {

        super();
        MedicoDao = new DoctorDao();

    }

    private static final long serialVersionUID = 1L;

    private static String Ingresar = "ClienteLogin/Ingresar.jsp";
    private static String MisDatos = "Medico/DatosMedico.jsp";
    private static String PorAtender = "Medico/CitasAtender.jsp";
    private static String PanelMedico = "Medico/PanelMedico.jsp";

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
        //processRequest(request, response);

        String forward = "";
        String action = request.getParameter("action");
        //equalsIgnoreCase ignora mayusculas, minusculas

        if (action.equalsIgnoreCase("Login")) {

            forward = Ingresar;

        } else if (action.equalsIgnoreCase("MisDatos")) {
            forward = MisDatos;

        } else if (action.equalsIgnoreCase("PanelMedico")) {

            forward = PanelMedico;
            request.setAttribute("noticias", MedicoDao.getnoticias());

        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

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
        //processRequest(request, response);

        //processRequest(request, response);
        String forward = "";
        String action = request.getParameter("action");

        //LA ACCION check NUNCA SE EJECUTARA YA QUE SE USA EL MISMO LOGIN PARA USUARIO Y MEDICO
        //SE TIENE QUE REALIZAR UN LOGIN ESPECIAL PARA EL MEDICO DIFERENTE AL DEL USUARIO PARA QUE ESTA
        //PARTE SE EJECUTE Y SE LE ASIGNE SU CORRESPONDIENTE ACCION
        if (action.equalsIgnoreCase("check")) {

            String usuario = request.getParameter("login");
            String contraseña = request.getParameter("password");
            boolean valor = MedicoDao.login(usuario, contraseña);

            if (valor) {

                //obtener datos del usuario
                int id_persona = MedicoDao.getUsuario(usuario);//obtener el ID_User de la tabla Usuario
                id_pers = id_persona;
                int id_rol = MedicoDao.getRolUsuario(id_persona);//obtener el ID_Rol de la tabla Usuario

                forward = MedicoDao.getURLMenu(id_persona);

                //asignar los siguientes atributos al HashMap de la sesion
                request.getSession().setAttribute("username", usuario);//agrega el unername al hashmap          
                request.getSession().setAttribute("id_persona", id_persona); //agrega el id de persona

                //Cargar permisos del usuario en un objeto de tipo Permisos
                Permisos pp = MedicoDao.obtenerPermisos(usuario);//obtener permisos del usuario 

                //asignar  los pernmisos del usuario como atributos de la sesion para el control de navegacion del usuairo en la Ap
                this.agregaPermiso(pp, request);

                //carga la informacion completa del usuario
                Doctor Datosusuario = MedicoDao.GetInformacionUsuario(id_persona);
                this.InformacionMedico(Datosusuario, request);

                //registrar la actividad de inicio de sesion del usuario en la bitacora
                MedicoDao.registroConexiones(id_persona, id_rol);

            }

        } else if (action.equalsIgnoreCase("Diagnostico")) {

            int folio = Integer.parseInt(request.getParameter("iddfolio"));
            int numerocama = Integer.parseInt(request.getParameter("numerocama"));
            String medico = request.getParameter("nombremedico");
            String diagnostico = request.getParameter("diag");
            String tratamiento = request.getParameter("tratamiento");

            MedicoDao.diagnosticos(folio, numerocama, medico, diagnostico, tratamiento);
            
            forward = PanelMedico;

        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("noticias", MedicoDao.getnoticias());
        view.forward(request, response);

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

    public void agregaPermiso(Permisos p, HttpServletRequest req) {
        //agrega permisos del modulo de Kardex a la sesion
        req.getSession().setAttribute("insertar_info", p.getInfoInsert());
        req.getSession().setAttribute("update_info", p.getinfoUpdate());
        req.getSession().setAttribute("select_info", p.getInfoSelect());
        req.getSession().setAttribute("delete_info", p.getInfoDelete());
        req.getSession().setAttribute("admin", p.getAdmin());
    }

    public void InformacionMedico(Doctor usuario, HttpServletRequest req) {
        //agrega permisos del modulo de Kardex a la sesion
        req.getSession().setAttribute("nombre", usuario.getNombre());
        req.getSession().setAttribute("apellidos", usuario.getApellidos());
        req.getSession().setAttribute("fecha_nac", usuario.getRegisteredon());
        req.getSession().setAttribute("telefono", usuario.getTelefono());
        req.getSession().setAttribute("correo", usuario.getCorreo());
        req.getSession().setAttribute("domicilio", usuario.getDomicilio());

    }

}
