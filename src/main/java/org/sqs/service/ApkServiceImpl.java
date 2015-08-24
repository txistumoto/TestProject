package org.sqs.service;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.sqs.dao.ApkDao;
import org.sqs.domain.Apk;

@Service
@Component("apkService")
public class ApkServiceImpl implements ApkService {

	protected final Logger logger = Logger.getLogger(getClass());

	@Autowired
	ApkDao apkDao;

	public void setApkDao(ApkDao apkDao) {
		this.apkDao = apkDao;
	}

	public Apk findOne(int id) {
		return apkDao.findOne(id);
	}

	public List<Apk> findAll() {
		return apkDao.findAll();
	}
	
	public Apk create(Apk apk) {
		return apkDao.create(apk);
	}

	public Apk update(Apk apk) {
		return apkDao.update(apk);
	}

	public Apk delete(int id) {
		return apkDao.delete(id);
	}


}
