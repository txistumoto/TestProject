package org.sqs.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.sqs.domain.Apk;

@Service
@Component("apkService")
public interface ApkService {

	public Apk findOne(int id);
	
	public List<Apk> findAll();
	
	public Apk create(Apk apk);
	
	public Apk update(Apk apk);

	public Apk delete(int id);

}
