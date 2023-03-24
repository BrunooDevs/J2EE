package sistema.controller;

import sistema.dao.UploadDAOAdmin;
import sistema.dao.AdminDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//importar las siguientes clases
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Part;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import sistema.modelAdmin.User;
@WebServlet("/Upload1") //se pone /Upload por el campo value=Upload en el form(JSP)


@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB


public class UploadServlet extends HttpServlet {
    //almacenar el nombre del archivo 
    String fileNameUploaded = null; 
    private static final String SAVE_DIR = "images"; //carpeta donde se guardaran los archivos
    //crear un objeto DAO para ejecutar querys del UPLOAD
    private UploadDAOAdmin upObj;//crear un objetos de tipo DAO para realizar algunos querys
    private AdminDao userdao;
    public UploadServlet()
    {
        super();//invocar métodos de la clase padre
        upObj = new UploadDAOAdmin();//crear objetos de tipo AdminDAO
        userdao=new AdminDao();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String forward="";//almacena la ruta a la que debe redireccionar al usuario
        String action = request.getParameter("action");//obtiene el valor del campo 'action' del JSP
        response.setContentType("text/plain; charset=UTF-8");//establece el tipo de contenido de la respuesta 'text' en unicode UTF8
        PrintWriter out = response.getWriter();//devuelve un objeto PrintWriter para imprimir (enviar) texto en la página del navegador (cliente)
        
        if(action.equalsIgnoreCase("Upload"))//si el campo value de 'action' que viene del JSP es = login 
        {      
            
            //obtener la ruta absoluta de la AppWeb
            String appPath = request.getServletContext().getRealPath("");
            
            
            
            //construir la ruta donde se almacenara el archivo
            String savePath = appPath + File.separator + SAVE_DIR;               
       
            /****** crear el directorio images en caso de que no exista *******/
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
         
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
                part.write(savePath + File.separator + fileName);
                fileNameUploaded = fileName;
            }
            
           
        
            //fusionar el nombre de la carpeta + nombre-archivo
            String nombreArchivo = SAVE_DIR + "/" + fileNameUploaded;
            upObj.Deleteurl(User.getId_persona());
            upObj.addURLfromImageName(User.getId_persona(),nombreArchivo);
            doGet(request,  response);
            request.setAttribute("message", "Upload has been done successfully!  " + savePath + "/" +fileNameUploaded);
      
        //}else if (action.equalsIgnoreCase("buscarimg"))        
        //{
/*           int  imgID = Integer.parseInt(request.getParameter("imageid"));            
           String url = null;
           url = upObj.getImageById(imgID);
           request.getSession().setAttribute("mensaje", url);    
*/
        //}else{                
        //    forward = "index.jsp";
        //    request.getSession().setAttribute("mensaje", "Imagen no encontrada");
        //}
    }        
    }
    /*** metodo que extrae el nombre del archivo del header HTTP content-disposition ***/
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
int  imgID = User.getId_persona();
           String url = null;
           url = upObj.getImageById(imgID);
           
           request.getSession().setAttribute("urlimg", url);
           request.setAttribute("message", "Path imagen:  " + url);
           
            request.setAttribute("noticias",userdao.getnoticiass());
            getServletContext().getRequestDispatcher("/Admin/PanelAdmin.jsp").forward(
                request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet para subir archivos";
    }// </editor-fold>

}
