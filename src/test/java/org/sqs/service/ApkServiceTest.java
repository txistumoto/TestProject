package org.sqs.service;

import java.util.Locale;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.sqs.LocaleUtils;
import org.sqs.domain.Apk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/applicationContext.xml","classpath*:**/app-config.xml"})
@WebAppConfiguration
public class ApkServiceTest {
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
    private ApkService apkService;

    public void setApkService(ApkService apkService) {
        this.apkService = apkService;
    }
    
    @Test
    public void testLocale() {
    	
    	LocaleUtils localeUtils = new LocaleUtils(new Locale("en"));
    	
    	String message1 = localeUtils.get("title");
    	
    	assertEquals(message1, "TestProject");
    	
    	String message2 = localeUtils.get("msg.1", new Object[] {"parametro.1"});
    	
    	assertEquals(message2, "You successfully uploaded parametro.1!");

    }

    @Test
    public void testGetApks() {
    	
    	int cont = apkService.findAll().size();
    	
    	Apk apk1 = new Apk("aaa","aaa","aaa");
    	apk1 = apkService.create(apk1);
        
        Apk apk2 = new Apk("bbb","bbb","bbb");
        apk2 = apkService.create(apk2);

        assertEquals(cont+2, apkService.findAll().size());
        
        apkService.delete(apk1.getId());
        apkService.delete(apk2.getId());
        
        assertEquals(cont, apkService.findAll().size());
    }
}
