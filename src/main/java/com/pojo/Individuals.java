package com.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="INDIVIDUALS")
public class Individuals {
	
	@XmlElement(name = "INDIVIDUAL")
	private List<Individual> individual;
	public List<Individual> getIndividual() {
		return individual;
	}
	public void setIndividual(List<Individual> individual) {
		this.individual = individual;
	}

	
	
	
}
