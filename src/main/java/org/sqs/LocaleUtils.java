package org.sqs;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

public class LocaleUtils {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	//Resource bundle message source name
	@Value("${message.source}")
	private String MESSAGES = "messages";
	
	private Locale locale = Locale.ENGLISH;
	private ResourceBundle resource = ResourceBundle.getBundle(MESSAGES);
	private ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	
	public LocaleUtils(Locale locale) {
		this.locale = locale;
		this.resource = ResourceBundle.getBundle(MESSAGES,this.locale);
		this.messageSource.setBasename(MESSAGES);
	}

		
	/*
	 *  Get the translation of input string for the locale	
	 */
	
	public String get(String sInput) {
		return resource.getString(sInput);
	}
	
	/* OLD 

	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("locale.xml");
	
	public String get(String sInput) {
		return get(sInput, new Object[]{});
	}
	 
	*/
	
	/*
	 *  Get the translation of input string for the locale
	 *  and replace '{#}' with the asParameters elements
	 */
 
	public String get(String sInput,  Object asParameters[]) {
		String sOutput = sInput;
		try {
			sOutput = messageSource.getMessage(sInput, asParameters, locale);
		} catch (NoSuchMessageException nsme) {
			logger.error("Resource bundle " + locale + " not found: " + sInput);
			try {
				sOutput = messageSource.getMessage(sInput, asParameters, Locale.ENGLISH);
			} catch (NoSuchMessageException nsmen) {
				logger.error("Resource bundle " + Locale.ENGLISH +" not found: " + sInput);
			}
		}
		return sOutput;
	}
	
	/* OLD:

	public String get(String sInput,  Object asParameters[]) {
		String sOutput = sInput;
	    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("locale.xml");
		try {
			sOutput = context.getMessage(sInput, asParameters, locale);
		} catch (NoSuchMessageException nsme) {
			logger.error("Resource bundle " + locale + " not found: " + sInput);
			try {
				sOutput = context.getMessage(sInput, asParameters, Locale.ENGLISH);
			} catch (NoSuchMessageException nsmen) {
				logger.error("Resource bundle " + Locale.ENGLISH +" not found: " + sInput);
			}
		}
		context.close();
		return sOutput;
	}	 
	 
	*/
}
