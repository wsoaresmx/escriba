package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class ModelBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String titulo;

	public ModelBase() {

	}

	public ModelBase(String titulo) {
		this.titulo = titulo;
	}

	@XmlAttribute
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInfoTitulo() {
		return getTitulo() + " [Total de Páginas " + getTotalPaginas() + "]";
	}

	public abstract Integer getTotalPaginas();

	public abstract Object[] getFilhos();

	public abstract Object getPai();
}
