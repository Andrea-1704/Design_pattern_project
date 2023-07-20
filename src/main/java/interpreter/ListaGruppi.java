package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.MostraTuttiGruppiCommand;
import view.PannelloGrafico;

public class ListaGruppi extends Lista{
    private JTextArea visualizzazione;


    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new MostraTuttiGruppiCommand(panel, visualizzazione));
        
        return null;
    }

    
}
