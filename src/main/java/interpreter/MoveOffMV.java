package interpreter;
import command.CommandHandler;
import concreteCommand.MoveOFFCommand;
import view.PannelloGrafico;

import javax.swing.*;

public class MoveOffMV extends Move{

    private String id;
    private Pos posizione;

    public MoveOffMV(String id, Pos posizione){
        this.id=id;
        this.posizione=posizione;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        Stato oggetto=posizione.interpret(panel, visualizzazione, invoker, stato);
        oggetto.setObjID(id);
        invoker.handle(new MoveOFFCommand(panel,visualizzazione,oggetto.getObjID(), oggetto.getPosition()));
        return oggetto;
    }
    
}
