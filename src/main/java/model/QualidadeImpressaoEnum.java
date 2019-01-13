package model;

public enum QualidadeImpressaoEnum {

	BAIXA_QUALIDADE(0), NORMAL(1), ALTA_QUALIDADE(2);

	private Integer qualidadeImpressao;

	private QualidadeImpressaoEnum(Integer qualidadeImpressao) {
		this.qualidadeImpressao = qualidadeImpressao;
	}

	public Integer getQualidadeImpressao() {
		return this.qualidadeImpressao;
	}

}
