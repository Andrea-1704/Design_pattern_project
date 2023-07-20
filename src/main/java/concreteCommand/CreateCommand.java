package concreteCommand;

import javax.swing.*;

import command.Command;
import interpreter.OggettiGrafici;
import interpreter.Stato;
import model.CircleObject;
import model.GraphicObject;
import model.ImageObject;
import model.RectangleObject;
import view.PannelloGrafico;

public class CreateCommand implements Command{

    private PannelloGrafico panel;
	private GraphicObject go;
	private JTextArea visualizzazione;
	private Stato stato;

	public CreateCommand(PannelloGrafico panel, JTextArea visualizzazione, Stato stato) {
		this.stato=stato;
		OggettiGrafici tipo=stato.getGraphicObject();
		if(tipo==OggettiGrafici.CIRCLE){
			this.go=new CircleObject(stato.getPosition(), stato.getRadiusCircle(), stato.getObjID());
		}
		if(tipo==OggettiGrafici.RECTANGLE){
			this.go=new RectangleObject(stato.getPosition(), stato.getDimensionRectangle().getWidth(), stato.getDimensionRectangle().getHeight(),stato.getObjID());
		}
		if(tipo==OggettiGrafici.IMG){
			this.go=new ImageObject(new ImageIcon(stato.getPath()), stato.getPosition(), stato.getObjID());
		}
		go.moveTo(stato.getPosition().getX(), stato.getPosition().getY());
		this.panel = panel;
		this.visualizzazione=visualizzazione;
	}

	@Override
	public boolean doIt() {
		double x = go.getPosition().getX();
		double y =  go.getPosition().getY();
		go.moveTo(x, y);
		panel.add(go);
		visualizzazione.setText("Nuovo oggetto di tipo "+stato.getGraphicObject()+" creato con id: "+go.getID()+" e identificativo unico: "+go.getIdentifier());
        //il pannello sa coma disegnare go in base alle sue proprietà
        //in realtà il pannello delega questo compito ad un altro oggetto
        //(GraphicObjectView)
		return true;
	}

	@Override
	public boolean undoIt() {
		visualizzazione.setText("Oggetto con id "+go.getID()+" rimosso.");
		panel.remove(go);
		return true;
	}

    
}
