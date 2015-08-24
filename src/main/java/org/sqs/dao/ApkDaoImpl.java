package org.sqs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sqs.domain.Apk;
import org.sqs.dao.ApkDao;

@Repository
@Component("apkDao")
public class ApkDaoImpl implements ApkDao {

	protected final Logger logger = Logger.getLogger(getClass());
	protected final String SQL_INSERT = "insert into apks (file, name, pack, creation_date, update_date) values (?, ?, ?, ?, ?)";
	protected final String SQL_UPDATE = "update apks set file = ?,  name = ?, pack = ?, creation_date = ?, update_date = ? where id = ?";
	protected final String SQL_DELETE = "delete from apks where id = ?";
			
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	protected EntityManager em = null;
	
    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	public Apk findOne(int id) {
		return em.find(Apk.class, id); 
	}

	public Apk findOne(String name) {
		return em.find(Apk.class, name); 
	}

	public List<Apk> findAll()  throws DataAccessException {
		TypedQuery<Apk> query = em.createNamedQuery("Apk.findAll",Apk.class);
        List<Apk> resultList = query.getResultList();
        return resultList;
	}
    
	@Transactional(readOnly = false)
	public Apk create(final Apk apk) {
		//OLD: em.persist(apk);
		KeyHolder holder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {           
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, apk.getFile());
	                ps.setString(2, apk.getName());
	                ps.setString(3, apk.getPack());
	                ps.setTimestamp(4,  apk.getCreation_date());
	                ps.setTimestamp(5, apk.getUpdate_date() );
	                return ps;
	            }
	        }, holder);
			int id = holder.getKey().intValue();
			apk.setId(id);
		} catch (DataAccessException dae) {
			logger.error(dae);	
		}
		return apk;
	}

	@Transactional(readOnly = false)
	public Apk update(Apk apk) {
		//OLD: em.merge(apk);
		try {
			jdbcTemplate.update(SQL_UPDATE,new Object[] {  apk.getFile(), apk.getName(), apk.getPack(), apk.getCreation_date(), apk.getUpdate_date(), apk.getId() });
			return apk;
		} catch (DataAccessException dae) {
			logger.error(dae);	
			return null;
		}
	}
	
	@Transactional(readOnly = false)
	public Apk delete(int id) {
		Apk apk = em.find(Apk.class, id);
		/* OLD
		em.getTransaction().begin();
		try {	
			if (apk!=null) 
				em.remove(apk);
		} catch (DataAccessException eee) {
			logger.error(eee);
			em.getTransaction().setRollbackOnly();
		} catch (IllegalArgumentException iae) {
			logger.error(iae);
			em.getTransaction().setRollbackOnly();
		} catch (TransactionRequiredException tre) {
			logger.error(tre);
			em.getTransaction().setRollbackOnly();
		} finally {
	        em.getTransaction().commit();
	    }
	    */
		try {
			jdbcTemplate.update(SQL_DELETE,new Object[] { apk.getId() });
			return apk;
		} catch (DataAccessException dae) {
			logger.error(dae);	
			return null;
		}
	}

}
