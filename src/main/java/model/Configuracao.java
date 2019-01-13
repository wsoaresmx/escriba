package model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuracao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Apostila> apostilas;

	@XmlElement
	public List<Apostila> getApostilas() {
		return apostilas;
	}

	public void setApostilas(List<Apostila> apostilas) {
		this.apostilas = apostilas;
	}

	@Override
	public String toString() {
		return "Configuracao [apostilas=" + apostilas + "]";
	}

}
