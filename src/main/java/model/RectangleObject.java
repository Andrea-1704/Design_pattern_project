package model;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public class RectangleObject extends AbstractGraphicObject {

	private Point2D position;
	private String id=null;
	private Dimension2D dim;
	private double scala=1.0;

	public RectangleObject(Point2D pos, double w, double h) {
		if (w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		dim = new Dimension();
		dim.setSize(w, h);
		position = new Point2D.Double(pos.getX(), pos.getY());

	}

	@Override
	public void setColor(Color c) {
		this.colore=c;
	}

	@Override
	public Color getColor() {
		return this.colore;
	}

	public RectangleObject(Point2D pos, double w, double h, String id) {
		this.id=id;
		if (w <= 0 || h <= 0)
			throw new IllegalArgumentException();
		dim = new Dimension();
		dim.setSize(w, h);
		position = new Point2D.Double(pos.getX(), pos.getY());

	}

	@Override
	public boolean contains(Point2D p) {
		double w = dim.getWidth() / 2;
		double h = dim.getHeight() / 2;
		double dx = Math.abs(p.getX() - position.getX());
		double dy = Math.abs(p.getY() - position.getY());
		return dx <= w && dy <= h;

	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Point2D getPosition() {
		return new Point2D.Double(position.getX(), position.getY());
	}


	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		dim.setSize(dim.getWidth() * factor, dim.getHeight() * factor);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension2D d = new Dimension();
		d.setSize(dim);
		return d;
	}

	@Override
	public RectangleObject clone() {
		RectangleObject cloned = (RectangleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		cloned.dim = (Dimension2D) dim.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Rectangle";
	}

	@Override
	public double getPerimetro() {
		return (dim.getWidth()*2)+(dim.getHeight()*2);
	}

	@Override
	public double getArea() {
		return dim.getWidth()*dim.getHeight();
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public void setID(String id) {
		this.id=id;
	}

	@Override
	public double getScala() {
		return scala;
	}
}
