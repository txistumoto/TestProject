package org.sqs.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.sqs.domain.Apk;

@Repository
@Component("apkDao")
public interface ApkDao {

	public Apk findOne(int id);
	
	public Apk findOne(String name);
	
	public List<Apk> findAll();
	
	public Apk create(Apk apk);

	public Apk update(Apk apk);

	public Apk delete(int id);
	
}
