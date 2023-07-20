package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.MostraTuttiTipoCommand;
import view.PannelloGrafico;

public class ListaTipo extends Lista{

    private String tipo;
    private JTextArea visualizzazione;

    public ListaTipo(String tipo){
        this.tipo=tipo;
    }
    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new MostraTuttiTipoCommand(panel,visualizzazione,tipo));
        return null;
    }


    
}
