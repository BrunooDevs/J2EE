package sistema.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;//objeto para generar respuestas en texto
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//importar las siguientes
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;//objeto para generar respuestas binarias, en este caso el reporte es binario
import javax.servlet.http.Part;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletResponse;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
//como vamos a trabajar con usuarios importamos las siguientes clases
import sistema.dao.AdminDao;
import sistema.modelAdmin.Doctor;
import sistema.dao.reporteDAOAdmin;
import sistema.util.Database;
//importar las clases de JasperReports
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JREmptyDataSource; 
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperExportManager; 
import net.sf.jasperreports.engine.JasperFillManager; 
import net.sf.jasperreports.engine.JasperPrint; 
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
//para almacenar logs
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet(name = "ReportsControllerAdmin", urlPatterns = {"/ReportsControllerAdmin"})
public class ReportsControllerAdmin extends HttpServlet {

    private static final Log logger = LogFactory.getLog(ReportsControllerAdmin.class);
    
    public ReportsControllerAdmin() {

        super();
        //dao = new reporteDAO();
        //udao = new AdminDao();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String action = request.getParameter("action");
            ServletContext context = this.getServletConfig().getServletContext();
            // get a database connection
            Connection conn = Database.getConnection();
            response.setContentType("application/pdf");
            if(action.equalsIgnoreCase("sinParametros"))
            {
                try{
                    //crear un mapa de parámetros para pasar al reporte .jasper
                     Map<String,Object> parametros = new HashMap<String,Object>();
                
                     File reportFile = new File(context.getRealPath("reportesAdmin/Doctores.jasper"));
                     //cargar JasperDesign desde XML y compilarlo en JasperReport
                     JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile(reportFile.getPath());
                     //  llena JasperPrint usando el método fillReport ()
                     JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);
                     
                     if(jasperPrint != null)
                     {
                         //para crear reporte en PDF
                        //response.setHeader("Content-Disposition","filename=abonos"+fechaInicio+"-"+fechaFin+".pdf;");
                        response.setHeader("Content-Disposition","inline; filename=reporte.pdf");
                        response.setContentType("application/pdf");
                        ServletOutputStream outputStream = response.getOutputStream();  
                        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);        
                        outputStream.close();
                     }
                
                }catch(Exception e){
                    e.printStackTrace();                
                }
            }else if(action.equalsIgnoreCase("compilarJrxml"))
            {
                try{
                    String jasperFilePath = context.getRealPath("reportesAdmin/Doctores.jasper"); //request.getSession().getServletContext().getRealPath("/reportesUsuarios/Usuarios.jrxml");
                    String jrxmlFilePath = context.getRealPath("reportesAdmin/Doctores.jrxml");
                    File reportFile = new File(jasperFilePath);
                    // create a map of parameters to pass to the report.
                    Map<String,Object> parametros = new HashMap<String,Object>();
                    //parametros.put("category_id", "1");
                    // If compiled file is not found, then compile XML template
                    if (!reportFile.exists()) 
                    {
                        // load JasperDesign from XML and compile it into JasperReport
                        JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFilePath);
                        // load JasperReport from .jasper file
                        JasperCompileManager.compileReportToFile(jasperDesign, jasperFilePath);
                    }
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
                    // fill JasperPrint using fillReport() method
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                    if(jasperPrint != null)
                    {
                        //response.setHeader("Content-Disposition","filename=abonos"+fechaInicio+"-"+fechaFin+".pdf;");
                        response.setHeader("Content-Disposition","inline; filename=reporte.pdf");
                        response.setContentType("application/pdf");
                        ServletOutputStream outputStream = response.getOutputStream();  
                        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);        
                        outputStream.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();                
                }
            }else if(action.equalsIgnoreCase("compilaConParametros"))
            {
                try{
                    int idcategoria = Integer.parseInt(request.getParameter("categoryid"));
                    String jasperFilePath = context.getRealPath("reportesUsuarios/InvoiceDOS.jasper");//reportesUsuarios/categories.jasper"); //request.getSession().getServletContext().getRealPath("/reportesUsuarios/Usuarios.jrxml");
                    String jrxmlFilePath = context.getRealPath("reportesUsuarios/InvoiceDOS.jrxml");//reportesUsuarios/categories.jrxml");
                    File reportFile = new File(jasperFilePath);
                    // create a map of parameters to pass to the report.
                    Map<String,Object> parametros = new HashMap<String,Object>();
                    parametros.put("ID_CAT", Integer.valueOf(idcategoria));
                    // If compiled file is not found, then compile XML template
                    if (!reportFile.exists()) 
                    {
                        // load JasperDesign from XML and compile it into JasperReport
                        JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFilePath);
                        // load JasperReport from .jasper file
                        JasperCompileManager.compileReportToFile(jasperDesign, jasperFilePath);
                    }
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
                    // fill JasperPrint using fillReport() method
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                    if(jasperPrint != null)
                    {
                        //response.setHeader("Content-Disposition","filename=abonos"+fechaInicio+"-"+fechaFin+".pdf;");
                        response.setHeader("Content-Disposition","inline; filename=reporte.pdf");
                        response.setContentType("application/pdf");
                        ServletOutputStream outputStream = response.getOutputStream();  
                        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);        
                        outputStream.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();                
                }
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
        String action = request.getParameter("action");
            ServletContext context = this.getServletConfig().getServletContext();
            Connection conn = Database.getConnection();
            response.setContentType("application/pdf");
            
            if(action.equalsIgnoreCase("reporteUsuarios"))
            {
                try{
                     Map<String,Object> parametros = new HashMap<String,Object>();            
                     File reportFile = new File(context.getRealPath("reportesUsuarios/Usuarios.jasper"));
                     JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile(reportFile.getPath());
                     JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);
                     if(jasperPrint != null)
                    {
                        //response.setHeader("Content-Disposition","filename=abonos"+fechaInicio+"-"+fechaFin+".pdf;");
                        response.setHeader("Content-Disposition","inline; filename=reporte.pdf");
                        response.setContentType("application/pdf");
                        ServletOutputStream outputStream = response.getOutputStream();  
                        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);        
                        outputStream.close();
                    }
                
                }catch(Exception e){
                    e.printStackTrace();                
                }
            }else if(action.equalsIgnoreCase("postConParametros"))
            {
                try{
                    int  idcategoria = Integer.parseInt(request.getParameter("ID_PERSONA"));
                    //if (idcategoria == 1)
                    //{
                    //    RequestDispatcher view = request.getRequestDispatcher("/uploads.jsp");
                    //    view.forward(request, response);
                    //}
                    String jasperFilePath = context.getRealPath("reportesAdmin/Constancia.jasper");//Invoice.jasper"); //request.getSession().getServletContext().getRealPath("/reportesUsuarios/Usuarios.jrxml");
                    String jrxmlFilePath = context.getRealPath("reportesAdmin/Constancia.jrxml");//Invoice.jrxml");
                    File reportFile = new File(jasperFilePath);
                    // create a map of parameters to pass to the report.
                    Map<String,Object> parametros = new HashMap<String,Object>();
                    parametros.put("ID_PERSONA", Integer.valueOf(idcategoria)); //new Integer(idcategoria));//Si el tipo de dato es String y lo queremos pasar con integer ("CAT_ID", ""+idcategoria);
                    // If compiled file is not found, then compile XML template
                    if (!reportFile.exists()) 
                    {
                        // load JasperDesign from XML and compile it into JasperReport
                        JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFilePath);
                        // load JasperReport from .jasper file
                        JasperCompileManager.compileReportToFile(jasperDesign, jasperFilePath);
                    }
                    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
                    // fill JasperPrint using fillReport() method
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
                    if(jasperPrint != null)
                    {
                        //persinaliza el nombre del PDF
                        response.setHeader("Content-Disposition","inline; filename=reporte"+idcategoria+".pdf");
                        response.setContentType("application/pdf");
                        ServletOutputStream outputStream = response.getOutputStream();  
                        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);        
                        outputStream.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();                
                }
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
