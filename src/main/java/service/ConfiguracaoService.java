package service;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Apostila;
import model.Catedra;
import model.Configuracao;
import model.Tema;

public class ConfiguracaoService {

	private static ConfiguracaoService instance;

	private Configuracao configuracao;

	private ConfiguracaoService() throws Exception {

		String currentPath = System.getProperty("user.dir");
		File file = new File(currentPath + "/resource/config.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Configuracao.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		configuracao = (Configuracao) jaxbUnmarshaller.unmarshal(file);

		List<Apostila> apostilas = configuracao.getApostilas();
		for (Apostila apostila : apostilas) {

			if (apostila.getCaminho() == null || apostila.getCaminho().isEmpty()) {
				throw new Exception("Não foi possível localizar o caminho da apostila " + apostila.getTitulo()
						+ ". Por favor, verifique o arquivo de configurações.");
			}

			for (Catedra catedra : apostila.getCatedras()) {

				catedra.setApostila(apostila);

				for (Tema tema : catedra.getTemas()) {
					tema.setCatedra(catedra);
				}
			}

		}

	}

	public static ConfiguracaoService getInstance() throws Exception {
		if (instance == null) {
			instance = new ConfiguracaoService();
		}
		return instance;
	}

	public Configuracao getContent() {
		return configuracao;
	}
}
