package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tema extends ModelBase {

	private static final long serialVersionUID = 1L;

	private Integer indice;
	private Integer paginaInicial;
	private Integer paginaFinal;
	private String resumo;
	private Catedra catedra;

	public Tema() {
		super();
	}

	public Tema(String titulo) {
		super(titulo);
	}

	@XmlElement
	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	@XmlElement
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@XmlElement
	public Integer getPaginaInicial() {
		return paginaInicial;
	}

	public void setPaginaInicial(Integer paginaInicial) {
		this.paginaInicial = paginaInicial;
	}

	@XmlElement
	public Integer getPaginaFinal() {
		return paginaFinal;
	}

	public void setPaginaFinal(Integer paginaFinal) {
		this.paginaFinal = paginaFinal;
	}

	@XmlElement
	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Catedra getCatedra() {
		return catedra;
	}

	public void setCatedra(Catedra catedra) {
		this.catedra = catedra;
	}

	@Override
	public String toString() {
		return "Tema [indice=" + indice + ", titulo=" + titulo + ", paginaInicial=" + paginaInicial + ", paginaFinal="
				+ paginaFinal + ", resumo=" + resumo + "]";
	}

	public static List<Tema> toTemaList(Object[] objects) {
		List<Tema> retorno = new ArrayList<Tema>();
		for (Object object : objects) {
			if (object instanceof Tema) {
				retorno.add((Tema) object);
			}
		}
		return retorno;
	}

	@Override
	public Integer getTotalPaginas() {
		return (paginaFinal - paginaInicial) + 1;
	}

	@Override
	public String getInfoTitulo() {
		return getTitulo() + " [Página " + getPaginaInicial() + " à " + getPaginaFinal() + "] Total "
				+ getTotalPaginas() + " páginas";
	}

	@Override
	public Object[] getFilhos() {
		return null;
	}

	@Override
	public Object getPai() {
		return catedra;
	}

}
