package concreteCommand;

import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class CalcolaAreaTipoCommand implements Command{
    private LinkedList<GraphicObject> oggetti;
    private String tipo;
    private JTextArea visualizzazione;

    public CalcolaAreaTipoCommand(PannelloGrafico panel, JTextArea visualizzazione, String tipo){
        this.tipo=tipo;
        this.visualizzazione=visualizzazione;
        this.oggetti=panel.getOggettiTipo(tipo);
    }

    @Override
    public boolean doIt() {
        if(oggetti.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return false;
        }
        double valore=0.0;
        for(GraphicObject go: oggetti){
            valore+=go.getArea();
        }
        visualizzazione.setText("Area di "+tipo+" :");
        visualizzazione.append(" "+valore);
        return false; 
    }

    @Override
    public boolean undoIt() {      
        return false;
    }
}
