package concreteCommand;

import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class CalcolaAreaTuttiCommand implements Command{
    private LinkedList<GraphicObject> oggetti;
    private JTextArea visualizzazione;

    public CalcolaAreaTuttiCommand(PannelloGrafico panel, JTextArea visualizzazione){
        this.visualizzazione=visualizzazione;
        this.oggetti=panel.getTutti();
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
        visualizzazione.setText("Area di tutti gli oggetti grafici :");
        visualizzazione.append(""+valore);
        return false;
    }

    @Override
    public boolean undoIt() {      
        return false;
    }
}
