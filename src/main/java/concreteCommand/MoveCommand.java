package concreteCommand;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedList;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

import javax.swing.*;

public class MoveCommand implements Command{
    
    private Point2D nuovaPosizione;
    private HashMap<Integer, Point2D> vecchiaPosizione;//identifier (univoco), posizione old
    private LinkedList<GraphicObject> oggetti;
    private  JTextArea visualizzazione;
    private String id;

    public MoveCommand(PannelloGrafico panel, JTextArea visualizzazione, String id, Point2D nuovaPosizione){
        this.nuovaPosizione=nuovaPosizione;
        this.id=id;
        this.visualizzazione=visualizzazione;
        this.vecchiaPosizione=panel.getPosizione(id);
        System.out.println(vecchiaPosizione);
        //il pannello deve supporre ci sia un solo elemento con quell'id, altrimenti da il primo
        this.oggetti=panel.getOggetti(id);
    }

    @Override
    public boolean doIt() {
        for(GraphicObject go: oggetti){
            go.moveTo(nuovaPosizione);
        }
        if(oggetti.size()==0){
            visualizzazione.setText("Nessun oggetto con id "+this.id+" è stato trovato. Inserire ID valido.");
        }
        if(oggetti.size()==1){
            visualizzazione.setText("L'oggetto con id "+this.id+" è stato spostato correttamente.");
        }
        if(oggetti.size()>1){
            visualizzazione.setText("Gli oggetti con id "+this.id+" sono stati spostati correttamente.");
        }
        return true;
    }

    @Override
    public boolean undoIt() {
        for(GraphicObject go: oggetti){
            Point2D old=vecchiaPosizione.get(go.getIdentifier());
            go.moveTo(old);
        }        

        return true;
    }
    
}
