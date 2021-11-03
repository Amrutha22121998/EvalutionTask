package com.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "INDIVIDUAL")
@XmlAccessorType(XmlAccessType.FIELD)
public class Individual {
	@XmlElement(name = "FIRST_NAME")
	private String firstName;
	@XmlElement(name = "SECOND_NAME")
	private String secondName;
	@XmlElement(name = "THIRD_NAME")
	private String thirdName;
	@XmlElement(name = "FOURTH_NAME")
	private String fourthName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getFourthName() {
		return fourthName;
	}

	public void setFourthName(String fourthName) {
		this.fourthName = fourthName;
	}

}
