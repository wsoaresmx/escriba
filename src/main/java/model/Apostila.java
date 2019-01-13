package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Apostila extends ModelBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resumo;
	private String caminho;
	private boolean frenteEVerso;
	private boolean pretoEBranco;
	private QualidadeImpressaoEnum qualidadeImpressao;
	private List<Catedra> catedras;

	public Apostila() {
		super();
	}

	public Apostila(String titulo) {
		super(titulo);
	}

	@XmlElement
	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	@XmlElement
	public List<Catedra> getCatedras() {
		return catedras;
	}

	public void setCatedras(List<Catedra> catedras) {
		this.catedras = catedras;
	}

	@XmlElement
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	@XmlElement
	public boolean isFrenteEVerso() {
		return frenteEVerso;
	}

	public void setFrenteEVerso(boolean frenteEVerso) {
		this.frenteEVerso = frenteEVerso;
	}

	@XmlElement
	public boolean isPretoEBranco() {
		return pretoEBranco;
	}

	public void setPretoEBranco(boolean pretoEBranco) {
		this.pretoEBranco = pretoEBranco;
	}

	@XmlElement
	public QualidadeImpressaoEnum getQualidadeImpressao() {
		return qualidadeImpressao;
	}

	public void setQualidadeImpressao(QualidadeImpressaoEnum qualidadeImpressao) {
		this.qualidadeImpressao = qualidadeImpressao;
	}

	@Override
	public String toString() {
		return "Apostila [titulo=" + titulo + ", resumo=" + resumo + ", caminho=" + caminho + ", frenteEVerso="
				+ frenteEVerso + ", pretoEBranco=" + pretoEBranco + ", qualidadeImpressao=" + qualidadeImpressao
				+ ", catedras=" + catedras + "]";
	}

	@Override
	public Integer getTotalPaginas() {
		Integer retorno = 0;
		for (Catedra catedra : catedras) {
			retorno = retorno + catedra.getTotalPaginas();
		}
		return retorno;
	}

	@Override
	public Object[] getFilhos() {
		return catedras.toArray();
	}

	@Override
	public Object getPai() {
		return null;
	}
}
