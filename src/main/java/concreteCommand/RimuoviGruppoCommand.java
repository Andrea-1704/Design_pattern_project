package concreteCommand;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

import javax.swing.*;

public class RimuoviGruppoCommand implements Command{
    private String id;
    private LinkedList<GraphicObject> oggetti= new LinkedList<>();
    private JTextArea visualizzazione;
    private PannelloGrafico panel;
    private Color coloreVecchio;

    public RimuoviGruppoCommand(PannelloGrafico panel, JTextArea visualizzazione, String id){
        //suppongo che questo id sia un od di gruppo ed elimino da tutti i graphicObject
        //questo id
        this.visualizzazione=visualizzazione;
        this.panel=panel;
        this.id=id;
        this.oggetti=panel.getOggetti(id);
    }

    @Override
    public boolean doIt() {
        if(oggetti.size()==0){
            visualizzazione.setText("Identificativo inesistente.");
            return true;
        }
        visualizzazione.setText("ID di gruppo "+id+" eliminato.");
        visualizzazione.append("\n");
        visualizzazione.append("Ecco i nuovi id per gli oggetti:\n");
        for(GraphicObject go: oggetti){
            String nuovo="ID"+go.getIdentifier();
            //panel.colora(panel.getGraphics(),go, Color.GREEN);
            //visualizzazione.setText("ID di gruppo "+id+" eliminato.");
            visualizzazione.append("\n");
            //visualizzazione.append("Ecco i nuovi id per gli oggetti:\n");
            visualizzazione.append("Il "+go.getType()+" con identificativo unico "+go.getIdentifier()+" presenta un nuovo identificatore "+nuovo);
            go.setID(nuovo);
            coloreVecchio=go.getColor();
            go.setColor(Color.black);
        }


        return true;
    }
    /*@Override
    public boolean doIt() {
        if(oggetti.size()==0){
            visualizzazione.setText("Identificativo inesistente.");
            return true;
        }
        visualizzazione.setText("ID di gruppo "+id+" eliminato.");
        for(GraphicObject go: oggetti){
            String nuovo="ID"+go.getIdentifier();
            go.setID(nuovo);
            try {
                TimeUnit.SECONDS.sleep(3);

            } catch (InterruptedException e) {
                visualizzazione.append("Errore!");
                throw new RuntimeException(e);
            }
        }


        return true;
    }*/

    @Override
    public boolean undoIt() {
        if(oggetti.size()==0){
            return true;
        }
        for(GraphicObject go: oggetti){
            go.setID(id);
            go.setColor(coloreVecchio);
        }
        visualizzazione.setText("Gruppo con ID "+id+" ristabilito.");

        return true;
    }
}
