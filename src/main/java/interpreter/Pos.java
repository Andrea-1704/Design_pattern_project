package interpreter;
import java.awt.geom.Point2D;

import command.CommandHandler;
import view.PannelloGrafico;

import javax.swing.*;

public class Pos implements AbstractExpression{
    private float x;
    private float y;
    
    public Pos(float x, float y){
        this.x=x;
        this.y=y;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        Point2D punto= new Point2D.Float(x,y);
        System.out.println(punto);
        stato.setPosition(punto);
        return stato;
    }

    
}
