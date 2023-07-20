package interpreter;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Stato {

    private OggettiGrafici tipoGraphicObject;
    private float radiusCircle=-1;
    private String path=null;
    private Dimension2D rectangleDimendion=null;
    private Point2D position=null;
    private String lsAreaPerimeterSubject=null; //all, circle, rectangle,..., ID (Stringa generica).
    private LinkedList<String>listId=new LinkedList<>();//grp listId;
    private String objID=null;



    public void setTipo(OggettiGrafici tipo){this.tipoGraphicObject=tipo;}
    public void setRadiusCircle(float radius){this.radiusCircle=radius;}
    public void setDimensionRectangle(Dimension2D dimension){
        rectangleDimendion= new DoubleDimension(dimension.getWidth(), dimension.getHeight());
    }
    public void setPath(String path){this.path=path;}
    public void setElementToList(String id){this.listId.add(id);}
    public void setObjID(String objID){this.objID=objID;}
    public void setPosition(Point2D position){
        Point2D posit=new Point2D.Double(position.getX(), position.getY());
        this.position=posit;
    }
    public void setLsAreaPerimeterSubject(String value){this.lsAreaPerimeterSubject=value;}

    public OggettiGrafici getGraphicObject(){return this.tipoGraphicObject;}

    public String getPath(){
        return this.path;
    }

    public float getRadiusCircle(){
        return this.radiusCircle;
    }

    public Dimension2D getDimensionRectangle(){
        return this.rectangleDimendion;
    }

    public Point2D getPosition(){
        return this.position;
    }

    public String getObjID(){
        return this.objID;
    }

    public LinkedList<String> getListaId(){
        return this.listId;
    }

    public String getLsAreaPerimeter(){
        return lsAreaPerimeterSubject;
    }
}
