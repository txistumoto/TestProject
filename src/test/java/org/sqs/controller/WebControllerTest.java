package org.sqs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.sqs.TestUtil;
import org.sqs.domain.Apk;
import org.sqs.service.ApkService;

import java.util.Arrays;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
/*
 * Ref: 
 * http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration
 * http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/applicationContext.xml","classpath*:**/app-config.xml"})
@WebAppConfiguration
public class WebControllerTest {
	 
    private MockMvc mockMvc;
 
    @Autowired
    private ApkService apkService;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new WebController(apkService))
                //.setHandlerExceptionResolvers(handlerExceptionResolver())
                .setValidator(validator())
                .setViewResolvers(viewResolver())
                .build();
    }
    
    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/index.html"))
          .andExpect(status().isOk());
    }
    
    @Test
    public void upload() throws Exception {
        this.mockMvc.perform(get("/upload.html"))
          .andExpect(status().isOk());
    }
    
    @Test
    public void run() throws Exception {
        this.mockMvc.perform(get("/run.html"))
          .andExpect(status().isOk());
    }
    
    @Test
    public void add() throws Exception {
        this.mockMvc.perform(get("/add.html"))
          .andExpect(status().isOk());
    }
    
    @Test
    public void edit() throws Exception {
        this.mockMvc.perform(get("/edit.html?id=1"))
          .andExpect(status().isOk());
    }
    
    @Test
    public void delete() throws Exception {
        this.mockMvc.perform(get("/delete.html?id=1"))
          .andExpect(status().isOk());
    }
    
/* TODO

    @Test
    public void findAll_ShouldAddApkEntriesToModelAndRenderTodoListView() throws Exception {
        Apk first = new Apk("Foo file","Foo name","Foo pack");

        Apk second = new Apk("Bar file","Bar name","Bar pack");
 
        when(apkService.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("todo/list"))
                .andExpect(forwardedUrl("/WEB-INF/view/list.jsp"))
                .andExpect(model().attribute("todos", hasSize(2)))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                        		hasProperty("file", is("Foo file")),
                                hasProperty("name", is("Foo name")),
                                hasProperty("pack", is("Foo pack"))
                        )
                )))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("file", is("Bar file")),
                                hasProperty("name", is("Bar name")),
                                hasProperty("pack", is("Bar pack"))
                        )
                )));
 
        verify(apkService, times(1)).findAll();
        verifyNoMoreInteractions(apkService);
    }
    
    @Test
    public void add_DescriptionAndTitleAreTooLong_ShouldRenderFormViewAndReturnValidationErrorsForTitleAndDescription() throws Exception {
        String file = TestUtil.createStringWithLength(51);
        String name = TestUtil.createStringWithLength(501);
        String pack = TestUtil.createStringWithLength(51);
 
        mockMvc.perform(post("/add.html")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("file", file)
                .param("name", name)
                .param("pack", pack)
                .sessionAttr("apk", new Apk())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("todo/add"))
                .andExpect(forwardedUrl("/WEB-INF/view/add.jsp"))
                .andExpect(model().attributeHasFieldErrors("apk", "file"))
                .andExpect(model().attributeHasFieldErrors("apk", "name"))
                .andExpect(model().attributeHasFieldErrors("apk", "pack"))
                .andExpect(model().attribute("apk", hasProperty("id", nullValue())))
                .andExpect(model().attribute("apk", hasProperty("file", is(file))))
                .andExpect(model().attribute("apk", hasProperty("name", is(name))))
                .andExpect(model().attribute("apk", hasProperty("pack", is(pack))));
 
        verifyZeroInteractions(apkService);
    }
 
*/    
    
    private HandlerExceptionResolver handlerExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
 
        Properties exceptionMappings = new Properties();
 
        exceptionMappings.put("net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException", "error/404");
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");
 
        exceptionResolver.setExceptionMappings(exceptionMappings);
 
        Properties statusCodes = new Properties();
 
        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");
 
        exceptionResolver.setStatusCodes(statusCodes);
 
        return exceptionResolver;
    }
    
    private MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
 
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
 
        return messageSource;
    }
    
    private LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
 
    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
 
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
}
