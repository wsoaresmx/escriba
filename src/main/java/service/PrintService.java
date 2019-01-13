package service;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPageable;

import model.Tema;

public class PrintService {

	private List<Tema> temas;
	private Integer qtdeCopias;

	public PrintService(List<Tema> temas, Integer qtdeCopias) {
		this.temas = temas;
		this.qtdeCopias = qtdeCopias;
	}

	public void imprimir(boolean exibirCaixaDialogo) throws Exception {
		try {
			for (Tema tema : this.temas) {
				imprimir(tema, exibirCaixaDialogo);
			}
		} catch (Exception e) {
			throw new Exception("Error ao imprimir o tema." + e.getMessage());
		}
	}

	private void imprimir(Tema tema, boolean exibirCaixaDialogo)
			throws InvalidPasswordException, IOException, PrinterException {

		String caminhoApostila = tema.getCatedra().getApostila().getCaminho();

		PDDocument document = PDDocument.load(new File(caminhoApostila));

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(new PDFPageable(document));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String jobName = tema.getCatedra().getApostila().getTitulo() + "_" + tema.getCatedra().getTitulo() + "_"
				+ tema.getTitulo() + "_" + sdf.format(new Date());
		job.setJobName(jobName);

		PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
		attr.add(new PageRanges(tema.getPaginaInicial(), tema.getPaginaFinal()));
		attr.add(Chromaticity.MONOCHROME);
		attr.add(PrintQuality.NORMAL);
		attr.add(Sides.TWO_SIDED_SHORT_EDGE);
		attr.add(new Copies(this.qtdeCopias));

		if (exibirCaixaDialogo) {
			if (job.printDialog(attr)) {
				job.print(attr);
			}
		} else {
			job.print(attr);
		}

		document.close();
	}

}
