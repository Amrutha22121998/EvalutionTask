package com.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CONSOLIDATED_LIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsolidatedList {
	
	@XmlElement(name = "INDIVIDUALS")
	private Individuals individuals;

	public Individuals getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individuals individuals) {
		this.individuals = individuals;
	}

	

	

}
