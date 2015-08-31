package org.sqs.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.sqs.TimestampUtil;

import java.sql.Timestamp;

/**
 * The persistent class for the apk database table.
 * 
 */
@Entity
@Table(name="apks") 
@NamedQueries({
	@NamedQuery(name="Apk.findName", query = "SELECT a FROM Apk a WHERE a.name LIKE :name"),
	@NamedQuery(name="Apk.findAll", query = "SELECT a FROM Apk a"),
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

	public Apk(String file, String name, String pack) {
		this.file = file;
		this.name = name;
		this.pack = pack;
		this.creation_date = TimestampUtil.getCurrentTimestamp();
		this.update_date = TimestampUtil.getCurrentTimestamp();
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
	
	@Override
	public String toString() {
		return "Apk {" + 
				"\"id\":" + id + 
				", \"file\": " + (file==null?"null":"\"" + file + "\"") + 
				", \"name\":\"" + (name==null?"null":"\"" +name + "\"") + 
				", \"pack\":\"" + (pack==null?"null":"\"" +pack + "\"") + 
		        ", \"creation_date\": " + (creation_date==null?"null":"\"" + creation_date.toString().replaceFirst(" ", "T") + "\"") +
		        ", \"update_date\": " + (update_date==null?"null":"\"" + update_date.toString().replaceFirst(" ", "T") + "\"") + 
				"}";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pack == null) ? 0 : pack.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apk other = (Apk) obj;
		if (id == 0) {
			if (other.id != 0)
				return false;
		} else if (id!=other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (pack == null) {
			if (other.pack != null)
				return false;
		} else if (!pack.equals(other.pack))
			return false;
		return true;
	}
}