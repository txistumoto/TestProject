package org.sqs.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.sqs.domain.Apk;
import org.sqs.service.ApkService;
import org.sqs.LocaleUtils;

@Controller
public class WebController {

    protected final Log logger = LogFactory.getLog(getClass());
    
	//Path of the file to be downloaded, relative to application's directory
	@Value("${path.files}")
	private String PATH_FILES = "C:\\Workspace\\PhoneApp\\files\\";	
	
    @Autowired
    public WebController(ApkService apkService) {
        this.apkService = apkService;
    }
 
    
	/**
	  * SERVICES
	  */
	
	@Autowired
	private ApkService apkService;
	
	@Autowired
	public void setApkService(ApkService apkService) {
		this.apkService = apkService;
	}
	
	/**
	  * Index page
	  */
    
    @RequestMapping(value="/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("index is executed!");
        List<Apk> apkList = apkService.findAll();
        return new ModelAndView("index", "apkList", apkList);
    }

	
	/**
	  * Upload page
	  */
    
	@RequestMapping(value="/upload.html")
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return new ModelAndView("upload");
    }
    
	@RequestMapping(value="/upload.html",method=RequestMethod.POST)
	public ModelAndView processUpload(@RequestParam("name") String name, @RequestParam("pack") String pack, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		logger.debug("fileUpload '" + request.getRemoteAddr() + "' " + ServletFileUpload.isMultipartContent(request) + " uploaded successfully");
		Locale locale = request.getLocale(); 
		LocaleUtils localeUtils = new LocaleUtils(locale);
		String message = "";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
        		String file_name = file.getOriginalFilename();
        		String file_path = PATH_FILES + file_name; 
        		String file_extension = file.getOriginalFilename().split("\\.")[1];
    	        if (!file_extension.isEmpty() && file_extension.toLowerCase().equals("apk")) {
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(file_path)));
	                stream.write(bytes);
	                stream.close();
	                
	        		Apk apk = new Apk();
	        		if (!name.isEmpty()) 
	        			apk.setName(name);
	        		if (!pack.isEmpty()) 
	        			apk.setPack(pack);
	        		if (!file_name.isEmpty()) 
	        			apk.setFile(file_name);
	        		apk = apkService.create(apk);
	                message = localeUtils.get("msg.1", new Object[] {file_path});
    	        } else {
    	        	message = localeUtils.get("msg.2", new Object[] {file_extension});
    	        }
            } catch (Exception e) {
            	logger.error(e);
            	message = localeUtils.get("msg.3", new Object[] {name,e.getMessage()}); 
            }
        } else {
        	message = localeUtils.get("msg.4", new Object[] {name}); 
        }
		
        ModelAndView mav = new ModelAndView("index", "apkList", apkService.findAll());
        mav.addObject("message", message);
		return mav;
		
	}    

	/**
	  * Run page
	  */
	
	@RequestMapping("/run.html")
	public ModelAndView run(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("run is executed!");
		ModelAndView mav = new ModelAndView("/run");
		//Load apk list
		mav.addObject( "apkList", apkService.findAll());
		//Load command catalog
		mav.addObject("cmdList", getCommandMap());

		return mav;	
	}
	
	@RequestMapping(value="/run.html",method=RequestMethod.POST) 
	public ModelAndView processRun(@RequestParam int id, @RequestParam int cmd, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("run apk(" + id + ") and cmd(" + cmd + ")  executed!");
		Locale locale = request.getLocale(); 
		LocaleUtils localeUtils = new LocaleUtils(locale);
		String message = "";
		
		//Load apk
		Apk apk = apkService.findOne(id);
		if (apk!=null) {
			String sAppPath = PATH_FILES + "adb.exe";
			String sApkPath = PATH_FILES + apk.getFile();
			Stack<String> asCommands =new Stack<String>();
			
			//Load command stack
			switch (cmd) {
			case 1:
				asCommands.push(sAppPath + " install " + sApkPath);
	            break;
	        case 2:
	        	asCommands.push(sAppPath + " shell am start -n " + apk.getName() + "/." + apk.getPack());
	            break;
	        case 3:
	        	asCommands.push(sAppPath + " shell pm uninstall " + apk.getName());
	            break;
	        case 4:
	        	asCommands.push(sAppPath + " shell pm uninstall " + apk.getName());
	        	asCommands.push(sAppPath + " shell am start -n " + apk.getName() +  "/." + apk.getPack());
	        	asCommands.push(sAppPath + " install " + sApkPath);
	            break;
	        default: 
	        	asCommands.push(sAppPath);
	            break;
			}
			
			//Run command stack
	        while (!asCommands.empty() && apk!=null) {  
	        	
	        	String sCommandActual = asCommands.pop();
	        	
		        try {   
		            message += localeUtils.get("msg.5", new Object[] {sCommandActual}); 
		        	
		            Process p = Runtime.getRuntime().exec(sCommandActual);
		             
		            BufferedReader stdInput = new BufferedReader(new
		                 InputStreamReader(p.getInputStream()));
		 
		            BufferedReader stdError = new BufferedReader(new
		                 InputStreamReader(p.getErrorStream()));
		 
		            // read the output from the command
		            String sResponse = null;
		            String sOutput = "";
		            while ((sResponse = stdInput.readLine()) != null) 
		            	sOutput += sResponse + "\n";
		            if (!sOutput.isEmpty())
		            	message += localeUtils.get("msg.6", new Object[] {sOutput}); 
		            
		            // read any errors from the attempted command
		            String sError = "";
		            while ((sResponse = stdError.readLine()) != null) 
		            	sError += sResponse;
		            if (!sError.isEmpty())
		            	message += localeUtils.get("msg.7", new Object[] {sError});  
		        }
		        catch (IOException e) {
		        	logger.error(e);
		        	message += localeUtils.get("msg.7", new Object[] {e.getMessage()});   
		        }
	        }
		} else {
			message = localeUtils.get("msg.8");
		}

		ModelAndView mav = new ModelAndView("/run");
		//Load apk list
		mav.addObject( "apkList", apkService.findAll());
		//Load command catalog
		mav.addObject("cmdList", getCommandMap());
		//Load message
		mav.addObject("message", message);
		
		logger.info(message);
		
		return mav;
	}
	
	
	/**
	  * Add page
	  */
  
	@RequestMapping("/add.html")
	public ModelAndView add(@ModelAttribute Apk apk, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("add (" + apk.getId() + ") is executed!");
		return new ModelAndView("add");
	}	
	
	@RequestMapping(value="/add.html",method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute Apk apk, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("create (" + apk.getId() + ") is executed!");
		if (apkService.create(apk)==null) {
			ModelAndView mav = new ModelAndView("add", "apk", apk);
			Locale locale = request.getLocale(); 
			LocaleUtils localeUtils = new LocaleUtils(locale);
			mav.addObject("message", localeUtils.get("msg.13"));
			return mav;
		} else {
			return new ModelAndView("index", "apkList", apkService.findAll());
		}
	}
	
	/**
	  * Edit page
	  */
	
	@RequestMapping("/edit.html")
	public ModelAndView edit(@RequestParam int id, @ModelAttribute Apk apk, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("edit (" + id + "/" + apk.getId() + "/" + apk.getFile() + ") is executed!");		
		apk = apkService.findOne(id);
		return new ModelAndView("edit", "apk", apk);

	}

	@RequestMapping(value="/edit.html",method=RequestMethod.POST)
	public ModelAndView processEdit(@ModelAttribute Apk apk, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("update (" + apk.getId() + ") is executed!");	
		if (apkService.update(apk)==null) {
			ModelAndView mav = new ModelAndView("edit", "apk", apk);
			Locale locale = request.getLocale(); 
			LocaleUtils localeUtils = new LocaleUtils(locale);
			mav.addObject("message", localeUtils.get("msg.10"));
			return mav;
		} else {
			return new ModelAndView("index", "apkList", apkService.findAll());
		}
		
	}
	
	/**
	  * Edit page
	  */

	@RequestMapping("/delete.html")
	public ModelAndView delete(@RequestParam int id, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.debug("delete (" + id + ") is executed!");			
		if (apkService.delete(id)==null) {
			ModelAndView mav = new ModelAndView("index", "apkList", apkService.findAll());
			Locale locale = request.getLocale(); 
			LocaleUtils localeUtils = new LocaleUtils(locale);
			mav.addObject("message", localeUtils.get("msg.11"));
			return mav;
		} else {
			return new ModelAndView("index", "apkList", apkService.findAll());
		}
	}

	//Load command catalog
	private List<Integer> getCommandMap() {
		List<Integer> cmdList = new ArrayList<Integer>();
		for (int i = 1; i<=4; i++)
			cmdList.add(i);
		return cmdList;
	}

	/* DEPRECATED
	//Load aplication root path
	private String getPath() throws UnsupportedEncodingException {	    
		String PATH_SEPARATOR = System.getProperty("file.separator"); //File.separatorChar
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/PhoneApp/WEB-INF/classes/");
		System.out.println(fullPath);
		System.out.println(pathArr[0]);
		fullPath = pathArr[0];
		// to read a file from webcontent
		String reponsePath = new File(fullPath).getPath() + PATH_SEPARATOR + "adb.exe";
		return reponsePath;
	}
	*/

}