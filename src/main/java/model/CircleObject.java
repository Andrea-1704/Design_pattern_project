package model;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public class CircleObject extends AbstractGraphicObject {

	private Point2D position;
	private double radius;
	private String id;
	private double scala=1.0;

	public CircleObject(String id){
		this.id=id;
	}

	public CircleObject(Point2D pos, double r) {
		if (r <= 0)
			throw new IllegalArgumentException();
		position = new Point2D.Double(pos.getX(), pos.getY());
		radius = r;
	}

	public CircleObject(Point2D pos, double r, String id) {
		this.id=id;
		if (r <= 0)
			throw new IllegalArgumentException();
		position = new Point2D.Double(pos.getX(), pos.getY());
		radius = r;
	}

	@Override
	public double getScala(){
		return this.scala;
	}

	@Override
	public void moveTo(Point2D p) {
		position.setLocation(p);
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public void setColor(Color c) {
		this.colore=c;
	}

	@Override
	public Color getColor() {
		return this.colore;
	}

	@Override
	public Point2D getPosition() {

		return new Point2D.Double(position.getX(), position.getY());
	}

	@Override
	public void scale(double factor) {
		if (factor <= 0)
			throw new IllegalArgumentException();
		radius *= factor;
		notifyListeners(new GraphicEvent(this));
	}

	@Override
	public Dimension2D getDimension() {
		Dimension d = new Dimension();
		d.setSize(2 * radius, 2 * radius);

		return d;
	}

	@Override
	public boolean contains(Point2D p) {
		return (position.distance(p) <= radius);

	}

	@Override
	public CircleObject clone() {
		CircleObject cloned = (CircleObject) super.clone();
		cloned.position = (Point2D) position.clone();
		return cloned;
	}

	@Override
	public String getType() {

		return "Circle";
	}

	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius){this.radius=radius;}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public void setID(String id){
		this.id=id;
	}

	@Override
	public double getPerimetro() {
		return radius*2*Math.PI;
	}

	@Override
	public double getArea() {
		return Math.PI*Math.pow(radius, 2);
	}


}
