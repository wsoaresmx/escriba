package view;

import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import model.Configuracao;
import model.Tema;
import service.ConfiguracaoService;
import service.PrintService;

/**
 * This class demonstrates the CheckboxTreeViewer
 */
public class Escriba extends ApplicationWindow {

	public Escriba() {
		super(null);
	}

	public void run() {
		// Don't return from open() until window closes
		setBlockOnOpen(true);

		// Open the main window
		open();

		// Dispose the display
		Display.getCurrent().dispose();
	}

	public static void main(String[] args) {
		new Escriba().run();
	}

	/**
	 * Configures the shell
	 * 
	 * @param shell
	 *            the shell
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);

		// Set the title bar text and the size
		shell.setText("Escriba");
		shell.setMaximized(true);
	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param parent
	 *            the main window
	 * @return Control
	 */
	protected Control createContents(Composite parent) {

		final Composite composite = new Composite(parent, SWT.NONE);

		Configuracao configuracao = null;
		try {
			configuracao = ConfiguracaoService.getInstance().getContent();
		} catch (Exception e) {
			errorMessage(composite, e.getMessage());
			System.exit(0);
		}

		composite.setLayout(new GridLayout(1, false));

		Label label = new Label(composite, SWT.BORDER);
		label.setText("Quantidade de Cópias");

		final Spinner qtdeCopias = new Spinner(composite, SWT.BORDER);
		qtdeCopias.setMinimum(1);
		qtdeCopias.setMaximum(30);
		qtdeCopias.setSelection(1);
		qtdeCopias.setIncrement(1);

		// Create the tree viewer
		final CheckboxTreeViewer tv = new CheckboxTreeViewer(composite);
		tv.setAutoExpandLevel(OK);
		tv.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		tv.setContentProvider(new ApostilaTemaContentProvider(configuracao));
		tv.setLabelProvider(new ApostilaTemaLabelProvider());
		tv.setInput("root"); // pass a non-null that will be ignored
		tv.expandAll();

		Button imprimir = new Button(composite, SWT.BUTTON1);
		imprimir.setText("Imprimir");
		imprimir.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				int totalPaginas = 0;
				for (Object element : tv.getCheckedElements()) {
					if (element instanceof Tema) {
						Tema tema = (Tema) element;
						totalPaginas = totalPaginas + tema.getTotalPaginas();
					}
				}

				int infoDialog = 0;
				if (totalPaginas > 0) {
					infoDialog = SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL;
				} else {
					infoDialog = SWT.ICON_ERROR | SWT.CANCEL;
				}

				Integer qtdeCopiasAsInteger = new Integer(qtdeCopias.getText());

				MessageBox dialog = new MessageBox(composite.getShell(), infoDialog);
				dialog.setText("Pronto para imprimir?");
				if (totalPaginas > 0) {
					dialog.setMessage("Foram selecionadas " + totalPaginas + " páginas. E serão feitas "
							+ qtdeCopiasAsInteger + " cópias. Totalizando " + (totalPaginas * qtdeCopiasAsInteger)
							+ " páginas a imprimir. Deseja continuar?");
				} else {
					dialog.setMessage("O número de páginas deve ser maior que 0 (zero).");
				}

				if (dialog.open() == SWT.OK) {

					try {

						List<Tema> temas = Tema.toTemaList(tv.getCheckedElements());
						PrintService service = new PrintService(temas, new Integer(qtdeCopias.getText()));
						service.imprimir(false);

					} catch (Exception e) {
						e.printStackTrace();
						errorMessage(composite, e.getMessage());
					}
				}
			}
		});

		// When user checks a checkbox in the tree, check all its children
		tv.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				tv.setSubtreeChecked(event.getElement(), event.getChecked());
			}
		});

		return composite;
	}

	private void errorMessage(Composite composite, String errorMessage) {
		if (errorMessage == null) {
			errorMessage = "Erro";
		}
		MessageBox error = new MessageBox(composite.getShell(), SWT.ICON_ERROR | SWT.OK);
		error.setText("Erro");
		error.setMessage(errorMessage);
		error.open();
	}

}