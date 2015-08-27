package org.sqs.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sqs.dao.ApkDao;
import org.sqs.domain.Apk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/applicationContext.xml","classpath*:**/app-config.xml"})
public class ApkDaoTest {	

	protected final Logger logger = Logger.getLogger(getClass());
	@PersistenceContext
	private EntityManager em;
	@Autowired
	ApkDao apkDao;

	public void setApkDao(ApkDao apkDao) {
		this.apkDao = apkDao;
	}

	@Test
	public void testDao() {
		
		int id = 999999;
		String name = "aaaaaa";
		String file = "aaaaaa";
		String pack = "aaaaaa";
		
		assertNotNull(apkDao);

		Apk apk = new Apk(name,file,pack);
		//apk.setId(id);
		//apk.setPack(pack);
		
		//
		assertEquals(apkDao.create(apk), apk);
		
		logger.info("Create: " + apk.toString());
		
		apk.setName(name+"2");
		
		assertEquals(apkDao.update(apk), apk);
		
		logger.info("Update: " + apk.toString());
		
		assertEquals(apkDao.findOne(apk.getId()), apk);
		
		//assertEquals(apkDao.findOne(name).getName(), apk.getName());
		
		assertNotNull((List<Apk>)apkDao.findAll());
		 
		assertTrue(apkDao.findAll().size()>0);
		
		assertNull(apkDao.delete(id));

	}
}
