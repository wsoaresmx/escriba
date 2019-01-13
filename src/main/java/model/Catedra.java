package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Catedra extends ModelBase {

	private static final long serialVersionUID = 1L;

	private String resumo;
	private List<Tema> temas;
	private Apostila apostila;

	public Catedra() {
		super();
	}

	public Catedra(String titulo) {
		super(titulo);
	}

	@XmlElement
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@XmlElement
	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	@XmlElement
	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}

	public Apostila getApostila() {
		return apostila;
	}

	public void setApostila(Apostila apostila) {
		this.apostila = apostila;
	}

	@Override
	public String toString() {
		return "Catedra [titulo=" + titulo + ", resumo=" + resumo + ", temas=" + temas + "]";
	}

	@Override
	public Integer getTotalPaginas() {
		Integer retorno = 0;
		for (Tema tema : temas) {
			retorno = retorno + tema.getTotalPaginas();
		}
		return retorno;
	}

	@Override
	public Object[] getFilhos() {
		return temas.toArray();
	}

	@Override
	public Object getPai() {
		return apostila;
	}

}
