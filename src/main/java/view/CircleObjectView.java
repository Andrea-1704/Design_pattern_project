package view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import model.CircleObject;
import model.GraphicObject;

public class CircleObjectView implements GraphicObjectView {
	@Override
	public void drawGraphicObject(GraphicObject go, Graphics2D g) {
		CircleObject co = (CircleObject) go;
		Point2D position = co.getPosition();
		double r = co.getRadius();
		double x = position.getX() - r;
		double y = position.getY() - r;
		g.setColor(go.getColor());
		g.draw(new Ellipse2D.Double(x, y, r * 2.0, r * 2.0));

	}
	@Override
	public void drawGraphicObject(GraphicObject go, Graphics2D g, Color c){
		CircleObject co = (CircleObject) go;
		Point2D position = co.getPosition();
		double r = co.getRadius();
		double x = position.getX() - r;
		double y = position.getY() - r;
		g.setColor(c);
		g.draw(new Ellipse2D.Double(x, y, r * 2.0, r * 2.0));
	}
}