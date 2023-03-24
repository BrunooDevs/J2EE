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
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
//como vamos a trabajar con usuarios importamos las siguientes clases
import sistema.dao.UserDao;
import sistema.model.User;

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
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
//para almacenar logs
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sistema.dao.UserDao;

@WebServlet(name = "ReportsController", urlPatterns = {"/ReportsController"})
public class ReportsController extends HttpServlet {

    private static final Log logger = LogFactory.getLog(ReportsController.class);

    //reporteDAO dao;    
    UserDao udao;
    public ReportsController() {

        super();
        //dao = new reporteDAO();
        udao = new UserDao();
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
                
                     File reportFile = new File(context.getRealPath("/reportesUsuarios/MisUsuarios.jasper"));
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
                    String jasperFilePath = context.getRealPath("../reportesUsuarios/MisUsuarios.jasper"); //request.getSession().getServletContext().getRealPath("/reportesUsuarios/Usuarios.jrxml");
                    String jrxmlFilePath = context.getRealPath("../reportesUsuarios/MisUsuarios.jrxml");
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
                    //int idcategoria = Integer.parseInt(request.getParameter("categoryid"));
                    
                    String jasperFilePath = context.getRealPath("../reportesUsuarios/MisUsuarios.jasper");//reportesUsuarios/categories.jasper"); //request.getSession().getServletContext().getRealPath("/reportesUsuarios/Usuarios.jrxml");
                    String jrxmlFilePath = context.getRealPath("../reportesUsuarios/MisUsuarios.jrxml");//reportesUsuarios/categories.jrxml");
                    File reportFile = new File(jasperFilePath);
                    // create a map of parameters to pass to the report.
                    Map<String,Object> parametros = new HashMap<String,Object>();
                    parametros.put("id_folio", Integer.valueOf(3));
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        ServletContext context = this.getServletConfig().getServletContext();
        Connection conn = Database.getConnection();
        response.setContentType("application/pdf");

        if (action.equalsIgnoreCase("reporteUsuarios")) {
            try {
                Map<String, Object> parametros = new HashMap<String, Object>();
                File reportFile = new File(context.getRealPath("../reportesUsuarios/MisUsuarios.jasper"));
                JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conn);

                //response.setHeader("Content-Disposition","filename=abonos"+fechaInicio+"-"+fechaFin+".pdf;");
                response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");
                response.setContentType("application/pdf");
                ServletOutputStream outputStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("postConParametros")) {
            

//https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v60/using-parameters-queries#jss-user-parameters_221620694_1025678                
            try {

               int  idcategoria = Integer.parseInt(request.getParameter("categoryid"));
             
                //if (idcategoria == 1)
                //{
                 //   RequestDispatcher view = request.getRequestDispatcher("/CitasMedicas.jsp");
                //    view.forward(request, response);
                //}
                 //int prueba = 1;
                
                String jasperFilePath = context.getRealPath("reportesUsuarios/Comprobante.jasper");//Invoice.jasper"); //request.getSession().getServletContext().getRealPath("/reportesUsuarios/Usuarios.jrxml");
                String jrxmlFilePath = context.getRealPath("reportesUsuarios/Comprobante.jrxml");//Invoice.jrxml");
                File reportFile = new File(jasperFilePath);
                // create a map of parameters to pass to the report.
               Map<String, Object> parametros = new HashMap<String, Object>();
      
               parametros.put("idfolio", Integer.valueOf(idcategoria)); //new Integer(idcategoria));//Si el tipo de dato es String y lo queremos pasar con integer ("CAT_ID", ""+idcategoria);
                
               // parametros.put("idfolio", 1);
               
                // If compiled file is not found, then compile XML template
                if (!reportFile.exists()) {
                    // load JasperDesign from XML and compile it into JasperReport
                    JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFilePath);
                    // load JasperReport from .jasper file
                    JasperCompileManager.compileReportToFile(jasperDesign, jasperFilePath);
                }
                JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
                // fill JasperPrint using fillReport() method
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, Database.getConnection());
                if (jasperPrint != null) {
                    //persinaliza el nombre del PDF
                    response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");
                    response.setContentType("application/pdf");
                    ServletOutputStream outputStream = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                    outputStream.close();
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
               
       
        }
         
                
    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
/* Referencias
* http://aspalliance.com/1140_Creating_Report_Using_JasperReports.8
* https://community.jaspersoft.com/documentation/tibco-jaspersoft-studio-user-guide/v60/getting-started-jaspersoft-studio
* https://stackoverflow.com/questions/33966181/how-to-pass-parameters-to-jasperreport-with-java-to-use-later-in-sql-query
* https://stackoverflow.com/questions/12172711/how-to-pass-date-as-parameter-to-jasper-report
* http://jasperreports.sourceforge.net/api/net/sf/jasperreports/engine/query/package-summary.html
 */
