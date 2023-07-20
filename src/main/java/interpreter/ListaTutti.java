package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.MostraTuttiCommand;
import view.PannelloGrafico;

public class ListaTutti extends Lista{
    private JTextArea visualizzazione;

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new MostraTuttiCommand(panel, visualizzazione));
        return null;
    }

    
}
