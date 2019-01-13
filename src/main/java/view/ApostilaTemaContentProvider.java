package view;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import model.Configuracao;
import model.ModelBase;

public class ApostilaTemaContentProvider implements ITreeContentProvider {

	private Configuracao config;

	public ApostilaTemaContentProvider(Configuracao config) {
		this.config = config;
	}

	@Override
	public Object[] getElements(Object arg0) {
		return config.getApostilas().toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object[] getChildren(Object arg0) {
		return ((ModelBase) arg0).getFilhos();
	}

	@Override
	public Object getParent(Object arg0) {
		return ((ModelBase) arg0).getPai();
	}

	@Override
	public boolean hasChildren(Object arg0) {
		Object[] obj = getChildren(arg0);
		return obj == null ? false : obj.length > 0;
	}

}
