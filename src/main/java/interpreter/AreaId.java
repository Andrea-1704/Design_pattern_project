package interpreter;

import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CalcolaAreaIdCommand;
import view.PannelloGrafico;

public class AreaId extends Area{

    private String idOggetto;
    private JTextArea visualizzazione;

    public AreaId(String oggetto){

        this.idOggetto=oggetto;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new CalcolaAreaIdCommand(panel,visualizzazione,idOggetto));
        return null;
    }

    
}
