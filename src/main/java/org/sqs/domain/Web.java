package org.sqs.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="webs") 
public class Web implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String url;
    private Double usability;
    private Double accessibility;
    private Double security;
    private Double functionality;
    private Double architecture_and_design;
    private Double html_standard;
    private Double other_requirements;
    private Double capacity;
    private Double availability;
    private Double credibility;
    private Double total;

    
	public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    } 
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getUsability() {
		return usability;
	}

	public void setUsability(Double usability) {
		this.usability = usability;
	}

	public Double getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(Double accessibility) {
		this.accessibility = accessibility;
	}

	public Double getSecurity() {
		return security;
	}

	public void setSecurity(Double security) {
		this.security = security;
	}

	public Double getFunctionality() {
		return functionality;
	}

	public void setFunctionality(Double functionality) {
		this.functionality = functionality;
	}

	public Double getArchitecture_and_design() {
		return architecture_and_design;
	}

	public void setArchitecture_and_design(Double architecture_and_design) {
		this.architecture_and_design = architecture_and_design;
	}

	public Double getHtml_standard() {
		return html_standard;
	}

	public void setHtml_standard(Double html_standard) {
		this.html_standard = html_standard;
	}

	public Double getOther_requirements() {
		return other_requirements;
	}

	public void setOther_requirements(Double other_requirements) {
		this.other_requirements = other_requirements;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public Double getAvailability() {
		return availability;
	}

	public void setAvailability(Double availability) {
		this.availability = availability;
	}

	public Double getCredibility() {
		return credibility;
	}

	public void setCredibility(Double credibility) {
		this.credibility = credibility;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("URL: " + url + ";");
        buffer.append("Usability: " + usability);
        buffer.append("Accessibility: " + accessibility + ";");
        buffer.append("Security: " + security);
        buffer.append("Functionality: " + functionality + ";");
        buffer.append("Architecture & design: " + architecture_and_design);
        buffer.append("HTML standard: " + html_standard + ";");
        buffer.append("Other requirements: " + other_requirements);
        buffer.append("Capacity: " + capacity);
        buffer.append("Availability: " + availability);
        buffer.append("Credibility: " + credibility);
        buffer.append("Total: " + total);
        
        return buffer.toString();
    }
}