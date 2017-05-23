package com.repair.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.repair.tools.FileUploadUtil;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@RequestMapping(value="/uploadImg", method=RequestMethod.POST)  
    public void upload(@RequestParam("studentPhoto") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {  
        
System.out.println(file.getSize());

		String filePath = FileUploadUtil.uploadFile(file, request);
        
        response.setContentType("text/html;charset=utf8");  
        response.getWriter().write("<img src='"+filePath+"'/>");  
    }
	
	 @RequestMapping(value="/download", method=RequestMethod.POST)  
	    public void download(String fileName, HttpServletResponse response) throws IOException {  
	        OutputStream os = response.getOutputStream();  
	        try {  
	            response.reset();  
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
	            response.setContentType("image/jpeg; charset=utf-8");  
	            os.write(FileUtils.readFileToByteArray(FileUploadUtil.getFile(fileName)));  
	            os.flush();  
	        } finally {  
	            if (os != null) {  
	                os.close();  
	            }  
	        }  
	    } 
}
