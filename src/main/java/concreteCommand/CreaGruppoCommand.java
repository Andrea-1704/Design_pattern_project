package concreteCommand;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class CreaGruppoCommand implements Command{
    private LinkedList<String> oggetti=new LinkedList<>();
    private LinkedList<GraphicObject> tutti;
    private HashMap<Integer,String> oldID= new HashMap<>();
    private JTextArea visualizzazione;
    private PannelloGrafico panel;

    public CreaGruppoCommand(PannelloGrafico panel, JTextArea visualizzazione, LinkedList<String> oggetti){
        this.visualizzazione=visualizzazione;
        this.panel=panel;
        for(String s: oggetti){
            this.oggetti.add(s);
        }
        this.tutti=panel.getTutti();
    }

    @Override
    public boolean doIt() {
        visualizzazione.setText("E' stato creato il gruppo "+oggetti.get(0));
        visualizzazione.append("\n");
        visualizzazione.append(("questo gruppo contiene gli oggetti:"));
        visualizzazione.append("\n");
        Color colore=scegliColore();
        for(GraphicObject go: tutti){
            for(String id: oggetti){
                if(go.getID().equals(id)){
                    go.setColor(colore);
                    //panel.colora(panel.getGraphics(),go, Color.BLUE);
                    visualizzazione.append(go.getType()+" con ID: "+go.getID());
                    visualizzazione.append("\n");
                    oldID.put(go.getIdentifier(), id);
                    go.setID(oggetti.get(0));
                }
            }
        }

        return true; 
    }

    private Color scegliColore(){
        LinkedList<Color> colorList = new LinkedList<>();
        // Aggiungi tutti i colori disponibili alla LinkedList
        colorList.add(Color.BLACK);
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.MAGENTA);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PINK);
        colorList.add(Color.RED);
        colorList.add(Color.WHITE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.LIGHT_GRAY);
        colorList.add(Color.CYAN);
        colorList.add(Color.GRAY);
        colorList.add(Color.DARK_GRAY);
        LinkedList<Color>colori = new LinkedList<>();
        for(GraphicObject go: tutti){
            if(!(colori.contains(go.getColor())))colori.add(go.getColor());
        }
        for(Color colore: colorList){
            if(!(colori.contains(colore)))return colore;
        }
        return Color.blue;
    }

    

    @Override
    public boolean undoIt() {      
        for(int id: oldID.keySet()){
            for(GraphicObject o: tutti){
                if(o.getIdentifier()==id){
                    o.setColor(Color.black);
                    o.setID(oldID.get(id));
                }
            }
        }
        return true;
    }
}
