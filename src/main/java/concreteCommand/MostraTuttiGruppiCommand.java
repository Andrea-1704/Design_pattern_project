package concreteCommand;

import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import view.PannelloGrafico;

public class MostraTuttiGruppiCommand implements Command{
    private JTextArea visualizzazione;
    private LinkedList<String> lista;

    public MostraTuttiGruppiCommand(PannelloGrafico panel, JTextArea visualizzazione){
        
        this.visualizzazione=visualizzazione;
        this.lista=panel.mostraGruppi();
    }

    @Override
    public boolean doIt() {
        visualizzazione.setText("");
        if(lista.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return false;
        }
        //faccio tornare la scritta bianca
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
