package concreteCommand;

import java.util.LinkedList;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

import javax.swing.*;

public class DeleteCommand implements Command{
    private PannelloGrafico panel;
	private String id;
    private LinkedList<GraphicObject> oggettiRimossi= new LinkedList<>();
    private JTextArea visualizzazione;

	public DeleteCommand(PannelloGrafico panel, JTextArea visualizzazione, String id) {
		this.panel = panel;
        this.visualizzazione=visualizzazione;
		this.id = id;
	}

    @Override
    public boolean doIt() {
        oggettiRimossi=panel.getByID(id);
        if(oggettiRimossi.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return true;
        }
        for(GraphicObject g: oggettiRimossi)panel.remove(g);
        if(oggettiRimossi.size()==0){
            visualizzazione.setText("Nessun oggetto con id "+this.id+" è stato trovato. Inserire ID valido.");
        }
        if(oggettiRimossi.size()==1){
            visualizzazione.setText("L'oggetto con id "+this.id+" è stato rimosso correttamente.");
        }
        if(oggettiRimossi.size()>1){
            visualizzazione.setText("Gli oggetti con id "+this.id+" sono stati rimossi correttamente.");
        }
        return true;
    }

    @Override
    public boolean undoIt() {
        for(GraphicObject go: oggettiRimossi){
            panel.add(go);
        }return true;
    }
}
