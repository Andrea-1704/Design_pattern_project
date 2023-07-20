package model;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public interface GraphicObject {

	void addGraphicObjectListener(GraphicObjectListener l);

	void removeGraphicObjectListener(GraphicObjectListener l);

	void moveTo(Point2D p);

	void setColor(Color c);

	Color getColor();

	void moveTo(double x, double y);

	Point2D getPosition();

	Dimension2D getDimension();

	double getPerimetro();

	double getArea();

	void scale(double factor);

	boolean contains(Point2D p);

	String getType();

	String getID();//nuovo

	void setID(String id);//nuovo

	int getIdentifier();//nuovo

	double getScala();//nuovo

	
}
