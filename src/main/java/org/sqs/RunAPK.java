package org.sqs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Stack;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Run
 */
//@WebServlet("/Run")
public class RunAPK extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 	
	private String sAppPath = "C:\\Workspace\\PhoneApp\\src\\adb.exe"; //this.getClass().getClassLoader().getResource("").getPath() + "adb.exe";
	//private String sApkPath = "C:\\Workspace\\PhoneApp\\src\\PreReqAgile.apk"; //this.getClass().getClassLoader().getResource("").getPath() + "PreReqAgile.apk";
	private String sApkPath = "C:\\Workspace\\PhoneApp\\src\\HiloMusical1.0.8.apk";
	
	private String sApkName = "sqs.prereqagile";
	private String sApkPackage = "PreReqAgileActivity";
	
	private String sCommand = "";
	private Stack<String> asCommands =new Stack<String>();
	  
    /**
     * @throws UnsupportedEncodingException 
     * @see HttpServlet#HttpServlet()
     */
    public RunAPK() {    	
    	super(); 
    	
	   	
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//TODO: sAppPath = getServletContext().getInitParameter("app-path") + "adb.exe";
	
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		sCommand = request.getParameter("Cmd");
		
		String reponsePath = getPath();
		
		switch (Integer.parseInt(sCommand)) {
		case 1:
			asCommands.push(reponsePath + " install " + sApkPath);
            break;
        case 2:
        	asCommands.push(sAppPath + " shell am start -n " + sApkName + "/." + sApkPackage);
            break;
        case 3:
        	asCommands.push(sAppPath + " shell pm uninstall " + sApkName);
            break;
        case 4:
        	asCommands.push(sAppPath + " shell pm uninstall " + sApkName);
        	asCommands.push(sAppPath + " shell am start -n " + sApkName +  "/." + sApkPackage);
        	asCommands.push(sAppPath + " install " + sApkPath);
            break;
        default: 
        	asCommands.push(sAppPath);
            break;
		}
		
	    response.setContentType("text/html");
	    java.io.PrintWriter out = response.getWriter( );
	    
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Run APK</title>");  
		out.println("<link rel='stylesheet' href='css/style.css' media='screen' type='text/css' />");  
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Run APK</h1>");
		out.println("<ul>");
		out.println("<li><a href='index.html'>Run APK</a>");
		out.println("<li><a href='upload.html'>Upload APK</a>");
		out.println("</ul>");

        while (!asCommands.empty()) {  
        	
        	String sCommandActual = asCommands.pop();
        	
	        try {
	        	out.println("<br>\n");      
	        	out.println("<b>Command:</b>\n<br>\n" + sCommandActual + "\n<br>\n");
	        	
	            Process p = Runtime.getRuntime().exec(sCommandActual);
	             
	            BufferedReader stdInput = new BufferedReader(new
	                 InputStreamReader(p.getInputStream()));
	 
	            BufferedReader stdError = new BufferedReader(new
	                 InputStreamReader(p.getErrorStream()));
	 
	            // read the output from the command
	            out.println("<b>Output:</b>\n<br>\n");
	            String sResponse = null;
	            while ((sResponse = stdInput.readLine()) != null) 
	                out.println(sResponse);
	            out.println("<br>\n");    
	            // read any errors from the attempted command
	            String sError = null;
	            out.println("<b>Error:</b>\n<br>\n");
	            while ((sResponse = stdError.readLine()) != null) 
	                out.println(sResponse);
	            out.println("<br>\n");    
	        }
	        catch (IOException e) {
	            out.println("<b>Exception:</b>\n<br>\n");
	            out.println(e+"\n<br>\n");
	        }
        }
        out.println("</body>");
        out.println("</html>");
	}
	
	public String getPath() throws UnsupportedEncodingException {	    
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/PhoneApp/WEB-INF/classes/");
		System.out.println(fullPath);
		System.out.println(pathArr[0]);
		fullPath = pathArr[0];
		// to read a file from webcontent
		String reponsePath = new File(fullPath).getPath() + File.separatorChar + "adb.exe";
		return reponsePath;
		}
}
