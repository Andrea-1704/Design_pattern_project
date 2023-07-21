package concreteCommand;

import java.util.HashMap;
import java.util.LinkedList;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

import javax.swing.*;

public class ScaleCommand implements Command{
    private float scala;
    private HashMap<Integer, Double> vecchiaScala=new HashMap<>();;
    private LinkedList<GraphicObject> oggetti;
    private JTextArea visualizzazione;
    private String id;
    private PannelloGrafico panel;

    public ScaleCommand(PannelloGrafico panel, JTextArea visualizzazione, String id, float scala){
        this.scala=scala;
        this.visualizzazione=visualizzazione;
        this.id=id;
        this.panel=panel;
        //il pannello deve supporre ci sia un solo elemento con quell'id, altrimenti da il primo
        /*this.oggetti=panel.getOggetti(id);
        for(GraphicObject go: oggetti){
            vecchiaScala.put(go.getIdentifier(),(double)1/scala);
        }*/
    }

    @Override
    public boolean doIt() {
        this.oggetti=panel.getOggetti(id);
        for(GraphicObject go: oggetti){
            vecchiaScala.put(go.getIdentifier(),(double)1/scala);
        }
        if(oggetti.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return true;
        }
        for(GraphicObject go: oggetti){
            go.scale(scala);
        }
        if(oggetti.size()==0){
            visualizzazione.setText("Nessun oggetto con id "+this.id+" è stato trovato. Inserire ID valido.");
        }
        if(oggetti.size()==1){
            visualizzazione.setText("L'oggetto con id "+this.id+" è stato scalato correttamente.");
        }
        if(oggetti.size()>1){
            visualizzazione.setText("Gli oggetti con id "+this.id+" sono stati scalati correttamente.");
        }
        return true;
    }

    @Override
    public boolean undoIt() {
        if(oggetti.size()==0){

            return true;
        }
        for(GraphicObject go: oggetti){
            go.scale(vecchiaScala.get(go.getIdentifier()));
        }

        return true;
    }
}
