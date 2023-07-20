package concreteCommand;

import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class MostraTuttiTipoCommand implements Command{
    private LinkedList<GraphicObject> oggetti;
    private JTextArea visualizzazione;
    private String tipo;

    public MostraTuttiTipoCommand(PannelloGrafico panel, JTextArea visualizzazione, String tipo){
        
        this.tipo=tipo;
        this.visualizzazione=visualizzazione;
        this.oggetti=panel.getOggettiTipo(tipo);
    }

    @Override
    public boolean doIt() {
        visualizzazione.setText("");
        if(oggetti.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return false;
        }
        visualizzazione.append("gli oggetti di tipo "+this.tipo+" sono:");
        visualizzazione.setText("");
        for(GraphicObject go: oggetti){
            visualizzazione.append(go.getID()+"");
            visualizzazione.append("\n");
        }
        return false; 
        //non è possibile fare un do: non avrebbe senso infatti farla in quanto la scritta 
        //è già apparsa a video magari!
    }

    @Override
    public boolean undoIt() {      
        return false;
        //non "punisco" l'utente
    }
}
