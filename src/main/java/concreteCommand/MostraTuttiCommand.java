package concreteCommand;

import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class MostraTuttiCommand implements Command{
    private LinkedList<GraphicObject> oggetti;
    private JTextArea visualizzazione;

    public MostraTuttiCommand(PannelloGrafico panel, JTextArea visualizzazione){
        
        this.oggetti=panel.getTutti();
        this.visualizzazione=visualizzazione;
    }

    @Override
    public boolean doIt()  {
        LinkedList<String> lista= new LinkedList<>();
        for(GraphicObject go: oggetti){
            if(!(lista.contains(go.getType())))lista.add(go.getType());
        }
        visualizzazione.setText("");
        //faccio tornare la scritta bianca
        visualizzazione.append("Sono presenti i seguenti tipi:");
        visualizzazione.append("\n");
        if(lista.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return false;
        }
        for(String s: lista){
            
            visualizzazione.append(s);
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
