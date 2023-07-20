package view;

import model.GraphicObject;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private final Map<Class<? extends GraphicObject>, GraphicObjectView> viste = new HashMap<>();
    GraphicObjectView createView( GraphicObject go) {
        return viste.get(go.getClass());
    }
    public void installView(Class<? extends GraphicObject> clazz, GraphicObjectView view) {
        viste.put(clazz, view);
    }
}