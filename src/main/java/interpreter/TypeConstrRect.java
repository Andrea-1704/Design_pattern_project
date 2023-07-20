package interpreter;
import command.CommandHandler;
import view.PannelloGrafico;

import javax.swing.*;

public class TypeConstrRect extends TypeConstr{

    private Pos posizione;

    public TypeConstrRect(Pos posizione){
        this.posizione=posizione;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        Stato oggetto=posizione.interpret(panel, visualizzazione, invoker, stato);
        oggetto.setTipo(OggettiGrafici.RECTANGLE);
        oggetto.setDimensionRectangle(new DoubleDimension(oggetto.getPosition().getX(), oggetto.getPosition().getY()));
        return oggetto;
    }
    
}
