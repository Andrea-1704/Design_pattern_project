package concreteCommand;

import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class CalcolaPerimetroTuttiCommand implements Command{
    private LinkedList<GraphicObject> oggetti;
    private JTextArea visualizzazione;

    public CalcolaPerimetroTuttiCommand(PannelloGrafico panel, JTextArea visualizzazione){
        
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
            valore+=go.getPerimetro();
        }
        DecimalFormat df = new DecimalFormat("#." + "0".repeat(3));
        String numeroFormattato = df.format(valore);
        visualizzazione.setText("Perimetro di tutti gli oggetti:"+"\n");
        visualizzazione.append(""+numeroFormattato);
        return false; 
    }

    @Override
    public boolean undoIt() {      
        return false;
    }
}
