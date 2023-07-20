package view;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import model.GraphicObject;

public class RectangleObjectView implements GraphicObjectView {
	@Override
	public void drawGraphicObject(GraphicObject go, Graphics2D g) {
		Point2D position = go.getPosition();
		Dimension2D dim = go.getDimension();
		double x = position.getX() - dim.getWidth() / 2.0;
		double y = position.getY() - dim.getHeight() / 2.0;
		g.setColor(go.getColor());
		g.draw(new Rectangle2D.Double(x, y, dim.getWidth(), dim.getHeight()));
	}

	@Override
	public void drawGraphicObject(GraphicObject go, Graphics2D g, Color c) {
		Point2D position = go.getPosition();
		Dimension2D dim = go.getDimension();
		double x = position.getX() - dim.getWidth() / 2.0;
		double y = position.getY() - dim.getHeight() / 2.0;
		g.setColor(c);
		g.draw(new Rectangle2D.Double(x, y, dim.getWidth(), dim.getHeight()));
	}
}

