package org.sqs.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;

import javax.servlet.http.*;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;
import org.sqs.RunAPK;
import org.sqs.UploadAPK;

public class ServletTest extends Mockito {
	
    @Test
    public void testRunAPKServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    
/*TODO:
        when(request.getParameter("Cmd")).thenReturn("me");
        when(request.getParameter("password")).thenReturn("secret");
        PrintWriter writer = new PrintWriter("somefile.txt");
        when(response.getWriter()).thenReturn(writer);

        new RunAPK().doPost(request, response);

        verify(request, atLeast(1)).getParameter("Cmd"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(FileUtils.readFileToString(new File("somefile.txt"), "UTF-8")
                   .contains("My Expected String"));
*/                   
    }
    
    @Test
    public void testUploadAPKServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);    
/*TODO:
        when(request.getParameter("Cmd")).thenReturn("me");
        when(request.getParameter("password")).thenReturn("secret");
        PrintWriter writer = new PrintWriter("somefile.txt");
        when(response.getWriter()).thenReturn(writer);

        new UploadAPK().doPost(request, response);

        verify(request, atLeast(1)).getParameter("Cmd"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(FileUtils.readFileToString(new File("somefile.txt"), "UTF-8")
                   .contains("My Expected String"));
*/                   
    }

}
