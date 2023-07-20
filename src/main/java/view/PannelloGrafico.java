package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import model.GraphicEvent;
import model.GraphicObject;
import model.GraphicObjectListener;

public class PannelloGrafico extends JComponent implements GraphicObjectListener {

	private static final long serialVersionUID = 8993548105090978185L;
	private FlyweightFactory fabbrica;
	private List<GraphicObject> objects = new LinkedList<>();


	public PannelloGrafico(FlyweightFactory fabbrica) {
		this.fabbrica=fabbrica;setBackground(Color.WHITE);
	}

	@Override
	public void graphicChanged(GraphicEvent e) {
		repaint();
		revalidate();
	}

	public LinkedList<String> mostraGruppi(){
		//restituisce gli id di tutti i gruppi
		LinkedList<String> id= new LinkedList<>();
		for(GraphicObject go:objects)if(!(id.contains(go.getID())))id.add(go.getID());
		return id;
	}//vedi MostraTuttiGruppiCommand

	@Override
	public Dimension getPreferredSize() {
		Dimension ps = super.getPreferredSize();
		double x = ps.getWidth();
		double y = ps.getHeight();
		for (GraphicObject go : objects) {
			double nx = go.getPosition().getX() + go.getDimension().getWidth() / 2;
			double ny = go.getPosition().getY() + go.getDimension().getHeight() / 2;
			if (nx > x)
				x = nx;
			if (ny > y)
				y = ny;
		}
		return new Dimension((int) x, (int) y);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (GraphicObject go : objects) {
			GraphicObjectView view=fabbrica.createView(go);
			view.drawGraphicObject(go, g2);
		}
	}

	protected void paintComponent(Graphics g, GraphicObject go, Color c) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		GraphicObjectView view=fabbrica.createView(go);
		view.drawGraphicObject(go, g2, c);


	}

	public void add(GraphicObject go) {
		objects.add(go);
		go.addGraphicObjectListener(this);
		repaint();
	}

	public LinkedList<GraphicObject> getByID(String id){
		LinkedList<GraphicObject> oggetti= new LinkedList<>();
		for(GraphicObject go: objects){
			if(go.getID().equals(id))oggetti.add(go);
		}return oggetti;
	}

	public LinkedList<GraphicObject> getTutti(){
		LinkedList<GraphicObject> oggetti= new LinkedList<>();
		for(GraphicObject go: objects){
			oggetti.add(go);
		}return oggetti;
	}

	public LinkedList<GraphicObject> getOggettiTipo(String tipo){
		LinkedList<GraphicObject> oggetti= new LinkedList<>();
		for(GraphicObject go: objects){
			if(go.getType().equalsIgnoreCase(tipo))oggetti.add(go);
		}return oggetti;
	}

	public HashMap<Integer, Point2D> getPosizione(String id){//id, posizione
		HashMap<Integer, Point2D> mappa= new HashMap<>();
		for(GraphicObject go: objects){
			if(go.getID().equals(id))mappa.put(go.getIdentifier(), go.getPosition());
		}return mappa;
	}

	public HashMap<Integer, Double> getScala(String id){
		HashMap<Integer, Double> mappa= new HashMap<>();
		for(GraphicObject go: objects){
			if(go.getID().equals(id))mappa.put(go.getIdentifier(), go.getScala());
		}return mappa;
	}

	public LinkedList<GraphicObject> getOggetti(String id){
		LinkedList<GraphicObject> oggetti=new LinkedList<>();
		for(GraphicObject go: objects){
			if(go.getID().equals(id))oggetti.add(go);
		}return oggetti;
	}

	public void colora(Graphics g,GraphicObject go, Color c){
		paintComponent(g,go,c);
	}

	public void remove(GraphicObject go) {
		if (objects.remove(go)) {
			repaint();
			go.removeGraphicObjectListener(this);
		}

	}
}
