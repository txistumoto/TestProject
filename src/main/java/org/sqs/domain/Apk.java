package org.sqs.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the apk database table.
 * 
 */
@Entity
@Table(name="apks") 
@NamedQueries({
	@NamedQuery(name="Apk.findAll", query = "SELECT u FROM Apk u"),
	@NamedQuery(name="Apk.delete", query = "DELETE FROM Apk a")
}) 
public class Apk implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String file;
	
	private String name;

	private String pack;

	private Timestamp creation_date;

	private Timestamp update_date;

	public Apk() {
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}
	
	public Timestamp getCreation_date() {
		return this.creation_date;
	}

	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}
	
	public Timestamp getUpdate_date() {
		return this.update_date;
	}

	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}

}