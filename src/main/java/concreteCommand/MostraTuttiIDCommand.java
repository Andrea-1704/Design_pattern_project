package concreteCommand;

import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class MostraTuttiIDCommand implements Command{
    private LinkedList<GraphicObject> oggetti;
    private JTextArea visualizzazione;
    private String id;

    public MostraTuttiIDCommand(PannelloGrafico panel, JTextArea visualizzazione, String id){
        this.oggetti=panel.getByID(id);
        this.visualizzazione=visualizzazione;
        this.id=id;
    }

    @Override
    public boolean doIt() {
        visualizzazione.setText("");
        if(oggetti.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return false;
        }
        visualizzazione.append("caratteristiche di "+this.id+ " sono:");
        visualizzazione.append("\n");
        for(GraphicObject go: oggetti){
            visualizzazione.append("identificatore unico: "+go.getIdentifier());
            visualizzazione.append("\n");
            visualizzazione.append("scala: "+go.getScala());
            visualizzazione.append("\n");
            visualizzazione.append("tipo: "+go.getType());
            visualizzazione.append("\n");
            visualizzazione.append("dimensione: "+"{"+go.getDimension().getHeight()+","+go.getDimension().getWidth()+"}");
            visualizzazione.append("\n");
            visualizzazione.append("posizione: "+"{"+go.getPosition().getX()+","+go.getPosition().getY()+"}");
            visualizzazione.append("\n");
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
