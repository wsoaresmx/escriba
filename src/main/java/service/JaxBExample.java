package service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Apostila;
import model.Catedra;
import model.Configuracao;
import model.QualidadeImpressaoEnum;
import model.Tema;

public class JaxBExample {

	public static void main1(String[] args) {

		try {

			File file = new File("/home/stp/Documentos/wsoares/me/workspace/Escriba/resource/config.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuracao.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Configuracao configuracao = (Configuracao) jaxbUnmarshaller.unmarshal(file);
			System.out.println(configuracao);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		List<Tema> temas = new ArrayList<Tema>();

		Integer paginaInicial = 1;
		Integer paginaFinal = paginaInicial + 9;

		for (int i = 1; i <= 10; i++) {

			String temaAsString = "Tema " + (i);

			Tema tema = new Tema(temaAsString);

			tema.setPaginaInicial(paginaInicial);
			tema.setPaginaFinal(paginaFinal);
			
			paginaInicial = paginaFinal + 1;
			paginaFinal = paginaFinal + 10;

			temas.add(tema);

		}

		Apostila nivel1 = new Apostila("Nível 1");
		nivel1.setCatedras(new ArrayList<Catedra>());
		nivel1.getCatedras().add(new Catedra("Cátedra 1"));
		nivel1.getCatedras().get(0).setTemas(temas);
		nivel1.setFrenteEVerso(true);
		nivel1.setPretoEBranco(true);
		nivel1.setQualidadeImpressao(QualidadeImpressaoEnum.NORMAL);
		nivel1.setCaminho("/home/stp/Documentos/wsoares/me/na/GUERRA DA ARTE_STEVEN PRESSFIELD.pdf");


		Apostila nivel2 = new Apostila("Nível 2");
		nivel2.setCatedras(new ArrayList<Catedra>());
		nivel2.getCatedras().add(new Catedra("Cátedra 1"));
		nivel2.getCatedras().get(0).setTemas(temas);
		nivel2.setFrenteEVerso(true);
		nivel2.setPretoEBranco(true);
		nivel2.setQualidadeImpressao(QualidadeImpressaoEnum.NORMAL);
		nivel2.setCaminho("/home/stp/Documentos/wsoares/me/na/GUERRA DA ARTE_STEVEN PRESSFIELD.pdf");
		
		Configuracao config = new Configuracao();
		config.setApostilas(new ArrayList<Apostila>());
		config.getApostilas().add(nivel1);
		config.getApostilas().add(nivel2);
		

		try {

			File file = new File("/home/stp/Documentos/wsoares/me/workspace/Escriba/resource/config.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuracao.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(config, file);
			jaxbMarshaller.marshal(config, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
