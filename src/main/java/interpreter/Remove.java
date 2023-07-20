package interpreter;
import command.CommandHandler;
import concreteCommand.DeleteCommand;
import view.PannelloGrafico;

import javax.swing.*;

public class Remove extends Comando{

    private String ObjID;

    public Remove(String id){
        this.ObjID=id;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        stato.setObjID(ObjID);
        invoker.handle(new DeleteCommand(panel,visualizzazione,ObjID));
        return stato;
    }

    
    
}
