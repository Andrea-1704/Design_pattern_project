package interpreter;

import command.CommandHandler;
import view.PannelloGrafico;

import javax.swing.*;

public class TypeConstrCircle extends TypeConstr{

    private float posFloat;

    public TypeConstrCircle(float posFloat){
        this.posFloat=posFloat;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        stato.setTipo(OggettiGrafici.CIRCLE);
        stato.setRadiusCircle(posFloat);
        return stato;
    }
    
}
