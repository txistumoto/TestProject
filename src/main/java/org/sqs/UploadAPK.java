package org.sqs;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
//@WebServlet("/UploadServlet")
public class UploadAPK extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath = "C:\\Workspace\\PhoneApp\\src\\";
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadAPK() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	      // Get the file location where it would be stored.
	      //TODO: filePath = getServletContext().getInitParameter("file-path"); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
	         out.println("<html>");
	         out.println("<head>");
		     out.println("<title>Upload APK</title>");  
		     out.println("<link rel='stylesheet' href='css/style.css' media='screen' type='text/css' />");  
		     out.println("</head>");
		     out.println("<body>");
		     out.println("<h1>Run APK</h1>");
		     out.println("<ul>");
		     out.println("<li><a href='index.html'>Run APK</a>");
		     out.println("<li><a href='upload.html'>Upload APK</a>");
		     out.println("</ul>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File(filePath));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );

	      try{ 
		      // Parse the request to get file items.
		      List fileItems = upload.parseRequest(request);
			
		      // Process the uploaded file items
		      Iterator i = fileItems.iterator();
	
		      out.println("<html>");
		      out.println("<head>");
		      out.println("<title>Upload APK</title>");  
		      out.println("<link rel='stylesheet' href='css/style.css' media='screen' type='text/css' />");  
		      out.println("</head>");
		      out.println("<body>");
		      out.println("<h1>Run APK</h1>");
		      out.println("<ul>");
		      out.println("<li><a href='index.html'>Run APK</a>");
		      out.println("<li><a href='upload.html'>Upload APK</a>");
		      out.println("</ul>");
			
		      while ( i.hasNext () ) 
		      {
		         FileItem fi = (FileItem)i.next();
		         if ( !fi.isFormField () )	
		         {
		            // Get the uploaded file parameters
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            String contentType = fi.getContentType();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            // Write the file
		            if( fileName.lastIndexOf("\\") >= 0 ){
		               file = new File( filePath + 
		               fileName.substring(fileName.lastIndexOf("\\"))) ;
		            }else{
		               file = new File( filePath + 
		               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;
		            out.println("<b>Uploaded Filename:</b>\n<br>\n" + fileName + "\n<br>\n");
		         }
		      }
		      out.println("</body>");
		      out.println("</html>");
		  }catch(Exception e) {
			  out.println("<b>Exception:</b>\n<br>\n");
			  out.println(e+"\n<br>\n");
		  }
	}

}
