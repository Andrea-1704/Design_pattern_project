package concreteCommand;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedList;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

import javax.swing.*;

public class MoveOFFCommand implements Command{
    private HashMap<Integer, Point2D> nuovaPosizione= new HashMap<>();
    private Point2D offset;
    private HashMap<Integer, Point2D> vecchiaPosizione;//identifier (univoco), posizione old
    private LinkedList<GraphicObject> oggetti;
    private JTextArea visualizzazione;
    private String id;

    public MoveOFFCommand(PannelloGrafico panel, JTextArea visualizzazione, String id, Point2D nuovaPosizione){
        this.offset=nuovaPosizione;
        this.id=id;
        this.visualizzazione=visualizzazione;
        this.vecchiaPosizione=panel.getPosizione(id);
        //il pannello deve supporre ci sia un solo elemento con quell'id, altrimenti da il primo
        this.oggetti=panel.getOggetti(id);
    }

    @Override
    public boolean doIt() {
        for(GraphicObject go: oggetti){
            double newX=go.getPosition().getX()+offset.getX();
            double newY=go.getPosition().getY()+offset.getY();
            nuovaPosizione.put(go.getIdentifier(), new Point2D.Float((float)newX, (float)newY));
            go.moveTo(nuovaPosizione.get(go.getIdentifier()));
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
            go.moveTo(vecchiaPosizione.get(go.getIdentifier()));
        }

        return true;
    }
}
