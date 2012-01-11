package org.agetac.common;

import java.util.Collection;

public class Aptitude {
	private String grade;

	public Aptitude(String grade) {
		super();
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String toString() {
		 StringBuffer sb = new StringBuffer();
		 sb.append("grade:");
		 sb.append(this.grade);
		 return sb.toString();
		}

	public Collection toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}
