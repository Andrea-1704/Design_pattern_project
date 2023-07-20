package view;

import java.awt.*;

import model.GraphicObject;

public interface GraphicObjectView {
	void drawGraphicObject(GraphicObject go, Graphics2D g);
	void drawGraphicObject(GraphicObject go, Graphics2D g, Color c);
}
