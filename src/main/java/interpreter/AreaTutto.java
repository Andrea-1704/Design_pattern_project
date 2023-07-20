package interpreter;

import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CalcolaAreaTuttiCommand;
import view.PannelloGrafico;

public class AreaTutto extends Area{

    private JTextArea visualizzazione;


    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new CalcolaAreaTuttiCommand(panel, visualizzazione));
        return null;
    }

    
}
