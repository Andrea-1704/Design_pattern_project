package interpreter;

import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CalcolaAreaTipoCommand;
import view.PannelloGrafico;

public class AreaType extends Area{

    private String tipo;

    public AreaType(String tipo){
        this.tipo=tipo;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new CalcolaAreaTipoCommand(panel, visualizzazione,tipo));
        return null;
    }

    
    
}
