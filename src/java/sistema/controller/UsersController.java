package sistema.controller;

import java.sql.*;

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import sistema.dao.UserDao;
import sistema.model.Noticias;
import sistema.model.Permisos;
import sistema.model.User;
import sistema.modelAdmin.Doctor;

@WebServlet(name = "UsersController", urlPatterns = {"/UsersController"})

public class UsersController extends HttpServlet {

    private static final long serialVersionUID = 1L;
  
    
    private static String RegistroUsuario = "ClienteLogin/RegistroUsuario.jsp";
    private static String index = "/index.jsp";
    private static String Ingresar = "ClienteLogin/Ingresar.jsp";
    private static String PanelUusario = "ClienteLogin/PanelUsuario.jsp";
    private static String MisCitas = "ClienteLogin/CitasMedicas.jsp";
    private static String MisDatos = "ClienteLogin/DatosPaciente.jsp";
    private static String MiHistorial = "ClienteLogin/HistorialCitas.jsp";
    private static String AgendarCita = "ClienteLogin/AgendarCita.jsp";
    
    //esta deve de ir en el controlador de admin
     private static String AgregarNoticia = "/Admin/AgregarNoticia.jsp";
    
    
    //esta ruta deberia de ir el controlador de DoctorController
    private static String PorAtender = "Medico/CitasAtender.jsp";

    int id_pers;
    int idrol;

    private UserDao dao;

    public UsersController() {

        super();
        dao = new UserDao();
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
        
        if (action.equalsIgnoreCase("Login")) {

            forward = Ingresar;

        } else if (action.equalsIgnoreCase("MisCitas")) {

            forward = MisCitas;
            request.setAttribute("users", dao.getAllUsersCitas(id_pers)); 
            request.setAttribute("listCategory", dao.getAllUsersCitas(id_pers)); 
            
     

        } else if (action.equalsIgnoreCase("MisDatos")) {

            forward = MisDatos;

        } else if (action.equalsIgnoreCase("MiHistorial")) {

            forward = MiHistorial;
            request.setAttribute("MiDiagnostico", dao.getDiagnosticos(id_pers));
    

        } else if (action.equalsIgnoreCase("AgendarCita")) {
            forward = AgendarCita;
            request.setAttribute("especialidades", dao.getAllEspecialidades());

        } else if (action.equalsIgnoreCase("listUser")) {
             

        } else if (action.equalsIgnoreCase("CitasAtender")) {

            forward = PorAtender;
            request.setAttribute("CitasPorAtender", dao.getCitasAtender(id_pers));
            request.setAttribute("getMiAgenda", dao.getMiAgenda(id_pers))
            ;
            request.setAttribute("getCmas", dao.getCamas());
        }else if (action.equalsIgnoreCase("Panel")) {

            forward = PanelUusario;
            request.setAttribute("noticias", dao.getnoticias());
            
         
        
            
 
        }else if (action.equalsIgnoreCase("AgregarNoticia")) {
         //esta va adentro del controller admin
            forward = AgregarNoticia;

        }
        
        else {
           forward = RegistroUsuario;
           
        
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
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("alta")) {

            User user = new User();

            user.setNombre(request.getParameter("user_name"));
            user.setApellidos(request.getParameter("user_apellidos"));
            user.setRegisteredon(request.getParameter("user_fecha"));

            user.setTelefono(request.getParameter("user_telefono"));
            user.setEmail(request.getParameter("user_email"));
            user.setDomicilio(request.getParameter("user_domicilio"));

            user.setUsuario(request.getParameter("user_usuario"));
            user.setPassword(request.getParameter("user_password"));
            user.setRepcontraseña(request.getParameter("user_reppassword"));
          
            String userid = request.getParameter("usuario");
            // if(userid == null || userid.isEmpty())
            //{
            dao.addUser(user);
            // }else{
            //user.setUsuario(userid);
            //dao.checkUser(user);
            //}

    } else if (action.equalsIgnoreCase("check")) {

            User user = new User();
            user.setUsuario((request.getParameter("login")));
            user.setPassword((request.getParameter("password")));
            boolean valor = dao.checkUser(user);

            if (valor) {

                //obtener datos del usuario
                int id_persona = dao.getUsuario(user.getUsuario());//obtener el ID_User de la tabla Usuario
                id_pers = id_persona;
                
                //carga la imagen del usuario 
                User.setId_persona(id_persona);
                
                int  imgID = User.getId_persona();
                String url = null;
                url = dao.getImageById(imgID);
                request.getSession().setAttribute("urlimg", url);
               
               
                
                int id_rol = dao.getRolUsuario(id_persona);//obtener el ID_Rol de la tabla Usuario
          
                forward = dao.getURLMenu(id_persona);
                

                //asignar los siguientes atributos al HashMap de la sesion
                request.getSession().setAttribute("username", user.getUsuario());//agrega el unername al hashmap          
                request.getSession().setAttribute("id_persona", id_persona);

                //Cargar permisos del usuario en un objeto de tipo Permisos
                Permisos pp = dao.obtenerPermisos(user.getUsuario());//obtener permisos del usuario 

                //asignar  los pernmisos del usuario como atributos de la sesion para el control de navegacion del usuairo en la Ap
                this.agregaPermiso(pp, request);

                //carga la informacion completa del usuario
                User Datosusuario = dao.GetInformacionUsuario(id_persona);
                
          
                this.InformacionUsuario(Datosusuario, request);

                //registrar la actividad de inicio de sesion del usuario en la bitacora
                dao.registroConexiones(id_persona, id_rol);
            }

        } else if (action.equalsIgnoreCase("AltaCita")) {

            String nombre = request.getParameter("firstname");
            String apelldos = request.getParameter("lastname");
            String numeross = request.getParameter("numero_Seguro");
            String ine = request.getParameter("INE");
            String fecha = request.getParameter("fecha_cita");
            String hora = request.getParameter("Hora_cita");
            String especialidad = request.getParameter("Especialidad");
            String sintomas = request.getParameter("sintomas");
            int id_medico =  dao.getIdMedico(especialidad);

            dao.AltaCita(id_pers,id_medico, nombre, apelldos, numeross, ine, fecha, hora, especialidad, sintomas);
            forward = PanelUusario;
            
            //ESTA TIENE QUE IR EN EL ADMINCONTROLLER YA QUE EL DA DE ALTA UNA OTICIA;
        } else if(action.equalsIgnoreCase("AgregarNoticia")){
            
            
            String titulo = request.getParameter("nombrenoticia");
            String Descricpion = request.getParameter("descripcion");
            
            dao.addnoticia(titulo, Descricpion);
            forward = PanelUusario;

        }
        

    
        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("users", dao.getAllUsersCitas(id_pers));
        request.setAttribute("especialidades", dao.getAllEspecialidades());
        request.setAttribute("CitasPorAtender", dao.getCitasAtender(id_pers));
        request.setAttribute("noticias", dao.getnoticias());
        request.setAttribute("listCategory", dao.getAllUsersCitas(id_pers)); 
        request.setAttribute("getMiAgenda", dao.getMiAgenda(id_pers)); 
        request.setAttribute("getCmas", dao.getCamas());
        request.setAttribute("MiDiagnostico", dao.getDiagnosticos(id_pers));
        

       
        view.forward(request, response);
       
        
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
