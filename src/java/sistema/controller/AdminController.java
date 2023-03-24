package sistema.controller;

import sistema.modelAdmin.Noticias;
import sistema.modelAdmin.Permisos;
import sistema.modelAdmin.Plantas;
import sistema.modelAdmin.User;
import sistema.modelAdmin.Doctor;
import sistema.modelAdmin.Camas;
import sistema.modelAdmin.Diagnosticos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.text.ParseException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import javax.servlet.RequestDispatcher;

import sistema.dao.AdminDao;

@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})

public class AdminController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private AdminDao dao;

    public AdminController() {

        super();
        dao = new AdminDao();
    }
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
        
        //---------------------------------Plantas Hospital--------------------------------------------------
        if (action.equalsIgnoreCase("plantas")) {
            forward = "Admin/Plantas.jsp";
            request.setAttribute("plantas", dao.getAllplantas());
        } else if (action.equalsIgnoreCase("editplanta")) {
            forward = "Admin/Plantasedit.jsp";
            String id_planta=request.getParameter("id_planta");
            int id=Integer.parseInt(id_planta);
            Plantas planta=dao.getPlantaById(id);
            request.setAttribute("planta", planta);
        } else if (action.equalsIgnoreCase("deleteplanta")) {
            forward = "Admin/Plantasedit.jsp";
            String id_planta=request.getParameter("id_planta");
            dao.deleteplanta(Integer.parseInt(id_planta));
            forward = "Admin/Plantas.jsp";
            request.setAttribute("plantas", dao.getAllplantas());
        } else if (action.equalsIgnoreCase("insertplanta")) {
            forward="Admin/Plantasedit.jsp";
        }
        //---------------------------------Plantas Hospital--------------------------------------------------
        
        //---------------------------------Camas Hospital----------------------------------------------------
        else if (action.equalsIgnoreCase("camas")) {
            forward = "Admin/Camas.jsp";
            request.setAttribute("camas", dao.getAllcamas());
        } else if (action.equalsIgnoreCase("editcama")) {
            forward = "Admin/Camasedit.jsp";
            String numero_cama=request.getParameter("numero_cama");
            int id=Integer.parseInt(numero_cama);
            Camas cama=dao.getCamaById(id);
            request.setAttribute("planta", dao.getAllIDPlantas());
            request.setAttribute("cama", cama);
        } else if (action.equalsIgnoreCase("deletecama")) {
            forward = "Admin/Camasedit.jsp";
            String numero_cama=request.getParameter("numero_cama");
            dao.deletecama(Integer.parseInt(numero_cama));
            forward = "Admin/Camas.jsp";
            request.setAttribute("camas", dao.getAllcamas());
        } else if (action.equalsIgnoreCase("insertcama")) {
            request.setAttribute("planta", dao.getAllIDPlantas());
            forward="Admin/Camasedit.jsp";
        }
        //---------------------------------Camas Hospital----------------------------------------------------
        //---------------------------------Noticias Hospital------------------------------------------------- 
        else if (action.equalsIgnoreCase("noticias")) {
            forward = "Admin/Noticias.jsp";
            request.setAttribute("noticias", dao.getnoticiass());
        } else if (action.equalsIgnoreCase("editnoticia")) {
            forward = "Admin/AgregarNoticia.jsp";
            String id_noticia=request.getParameter("id_noticia");
            int id=Integer.parseInt(id_noticia);
            Noticias noticia=dao.getNoticiaById(id);
            request.setAttribute("noticia", noticia);
        } else if (action.equalsIgnoreCase("deletenoticia")) {
            forward = "Admin/AgregarNoticia.jsp";
            String id_noticia=request.getParameter("id_noticia");
            dao.deletenoticia(Integer.parseInt(id_noticia));
            forward = "Admin/Noticias.jsp";
            request.setAttribute("noticias", dao.getnoticiass());
        } else if (action.equalsIgnoreCase("insertnoticia")) {
            forward="Admin/AgregarNoticia.jsp";
        }
        //---------------------------------Noticias Hospital------------------------------------------------- 
        //---------------------------------Doctores Hospital------------------------------------------------- 
        else if (action.equalsIgnoreCase("doctores")) {
            forward = "Admin/Doctores.jsp";
            request.setAttribute("doctores", dao.getAllDoctores());
        } else if (action.equalsIgnoreCase("editdoctor")) {
            forward = "Admin/Doctoresedit.jsp";
            String id_persona=request.getParameter("id_persona");
            int id=Integer.parseInt(id_persona);
            Doctor doctor=dao.getDoctorById(id);
            request.setAttribute("doctor", doctor);
        } else if (action.equalsIgnoreCase("deletedoctor")) {
            forward = "Admin/Doctoresedit.jsp";
            String id_persona=request.getParameter("id_persona");
            dao.deletedoctor(Integer.parseInt(id_persona));
            forward = "Admin/Doctores.jsp";
            request.setAttribute("doctores", dao.getAllDoctores());
        } else if (action.equalsIgnoreCase("insertdoctor")) {
            forward="Admin/Doctoresedit.jsp";
        }
        //---------------------------------Doctores Hospital-------------------------------------------------
        //---------------------------------Diagnosticos Hospital---------------------------------------------
        if (action.equalsIgnoreCase("diagnosticos")) {
            forward = "Admin/Diagnosticos.jsp";
            request.setAttribute("diagnosticos", dao.getAlldiagnosticos());
        } else if (action.equalsIgnoreCase("editdiagnostico")) {
            forward = "Admin/Diagnosticosedit.jsp";
            String folio=request.getParameter("folio");
            int id=Integer.parseInt(folio);
            Diagnosticos diagnostico=dao.getdiagnosticoById(id);
            request.setAttribute("camas", dao.getAlllistcamas());
            request.setAttribute("diagnostico", diagnostico);
        } else if (action.equalsIgnoreCase("deletediagnostico")) {
            forward = "Admin/Diagnosticos.jsp";
            String folio=request.getParameter("folio");
            dao.deletediagnostico(Integer.parseInt(folio));
            forward = "Admin/Diagnosticos.jsp";
            request.setAttribute("diagnosticos", dao.getAlldiagnosticos());
        } else if (action.equalsIgnoreCase("insertdiagnostico")) {
            request.setAttribute("camas", dao.getAlllistcamas());
            request.setAttribute("folios",dao.getAllfolios());
            forward="Admin/Diagnosticosedit.jsp";
        }
        //--------------------------------Diagnosticos Hospital-----------------------------------------------
        
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
    //------------------------------------------------------------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String action = request.getParameter("action");
        //---------------------------------Plantas Hospital--------------------------------------------------
        if (action.equalsIgnoreCase("formplanta")){
            Plantas planta=new Plantas();
            planta.setId_planta(Integer.parseInt(request.getParameter("id_planta")));
            planta.setNombre(request.getParameter("nombre"));
            planta.setNumerocamas(Integer.parseInt(request.getParameter("numerocamas")));
            int idplanta = Integer.parseInt(request.getParameter("id_planta"));
            if(idplanta==-1){
                dao.addplanta(planta);
            }else{
                planta.setId_planta(idplanta);
                dao.checkplanta(planta);
            }
            RequestDispatcher view = request.getRequestDispatcher("Admin/Plantas.jsp");
            request.setAttribute("plantas", dao.getAllplantas());
            view.forward(request, response);
            }
        //---------------------------------Plantas Hospital--------------------------------------------------
        //---------------------------------Camas Hospital----------------------------------------------------
        else if (action.equalsIgnoreCase("formcama")){
            Camas cama=new Camas();
            cama.setNumero_cama(Integer.parseInt(request.getParameter("numero_cama")));
            cama.setId_planta(Integer.parseInt(request.getParameter("id_planta")));
            cama.setCaracteristicas(request.getParameter("caracteristicas"));
            cama.setOxigeno(Integer.parseInt(request.getParameter("oxigeno")));
            cama.setTermometro(Integer.parseInt(request.getParameter("termometro")));
            int idcama = Integer.parseInt(request.getParameter("numero_cama"));
            if(idcama==-1){
                dao.addcama(cama);
            }else{
                cama.setNumero_cama(idcama);
                dao.checkcama(cama);
            }
            RequestDispatcher view = request.getRequestDispatcher("Admin/Camas.jsp");
            request.setAttribute("camas", dao.getAllcamas());
            view.forward(request, response);
            }
        //---------------------------------Camas Hospital----------------------------------------------------
        //---------------------------------Noticias Hospital--------------------------------------------------
           else if (action.equalsIgnoreCase("formnoticia")){
            Noticias noticia =new Noticias();
            noticia.setId_noticia(Integer.parseInt(request.getParameter("id_noticia")));
            noticia.setTitulo(request.getParameter("titulo"));
            noticia.setDescripcion(request.getParameter("descripcion"));
            int idnoticia = Integer.parseInt(request.getParameter("id_noticia"));
            if(idnoticia==-1){
                dao.addnoticia(noticia);
            }else{
                noticia.setId_noticia(idnoticia);
                dao.checknoticia(noticia);
            }
            
            RequestDispatcher view = request.getRequestDispatcher("Admin/Noticias.jsp");
            request.setAttribute("noticias", dao.getnoticiass());
            view.forward(request, response);
            }
        //---------------------------------Noticias Hospital--------------------------------------------------
        //---------------------------------Doctores Hospital--------------------------------------------------
            else if (action.equalsIgnoreCase("formdoctor")){
            Doctor doctor=new Doctor();
            doctor.setId_persona(Integer.parseInt(request.getParameter("id_persona")));
            doctor.setNombre(request.getParameter("nombre"));
            doctor.setApellidos(request.getParameter("apellidos"));
            doctor.setEspecialidad(request.getParameter("especialidad"));
            doctor.setFecha_nac(request.getParameter("fecha_nac"));
            doctor.setTelefono(request.getParameter("telefono"));
            doctor.setCorreo(request.getParameter("correo"));
            doctor.setDomicilio(request.getParameter("domicilio"));
            doctor.setUser(request.getParameter("user"));
            doctor.setPassword(request.getParameter("password"));
            
            int idpersona = Integer.parseInt(request.getParameter("id_persona"));
            if(idpersona==0){
                dao.adddoctor(doctor);
            }else{
                doctor.setId_persona(idpersona);
                dao.checkdoctor(doctor);
            }
            
            RequestDispatcher view = request.getRequestDispatcher("Admin/Doctores.jsp");
            request.setAttribute("doctores", dao.getAllDoctores());
            view.forward(request, response);
            }
        //---------------------------------Doctores Hospital--------------------------------------------------
        //---------------------------------Diagnostico Hospital-----------------------------------------------
           else if (action.equalsIgnoreCase("formDiagnostico")){
            Diagnosticos diagnostico=new Diagnosticos();
            diagnostico.setFolio(Integer.parseInt(request.getParameter("folio")));
            diagnostico.setNumero_cama(Integer.parseInt(request.getParameter("numero_cama")));
            diagnostico.setMedico(request.getParameter("medico"));
            diagnostico.setDiagnostico(request.getParameter("diagnostico"));
            diagnostico.setTratamiento(request.getParameter("tratamiento"));
            
            int folio = Integer.parseInt(request.getParameter("folio"));
            if(folio==-1){
                dao.adddiagnostico(diagnostico);
            }else{
                diagnostico.setFolio(folio);
                dao.checkdiagnostico(diagnostico);
            }
            
            RequestDispatcher view = request.getRequestDispatcher("Admin/Diagnosticos.jsp");
            request.setAttribute("diagnosticos", dao.getAlldiagnosticos());
            view.forward(request, response);
            }
        //---------------------------------Diagnostico Hospital------------------------------------------------
    }

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

    public void InformacionUsuario(User usuario, HttpServletRequest req) {
        //agrega permisos del modulo de Kardex a la sesion
        req.getSession().setAttribute("nombre", usuario.getNombre());
        req.getSession().setAttribute("apellidos", usuario.getApellidos());
        req.getSession().setAttribute("fecha_nac", usuario.getRegisteredon());
        req.getSession().setAttribute("telefono", usuario.getTelefono());
        req.getSession().setAttribute("correo", usuario.getCorreo());
        req.getSession().setAttribute("domicilio", usuario.getDomicilio());
        
    }
    
}
