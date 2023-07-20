package interpreter;

import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.MostraTuttiIDCommand;
import view.PannelloGrafico;

public class ListaOggetto extends Lista{
    private String id;
    private JTextArea visualizzazione;

    public ListaOggetto(String id){
        this.id=id;
    }
    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new MostraTuttiIDCommand(panel, visualizzazione,id));
        return null; 
    }

    
    
}
