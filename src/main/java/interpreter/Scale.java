package interpreter;
import command.CommandHandler;
import concreteCommand.ScaleCommand;
import view.PannelloGrafico;

import javax.swing.*;

public class Scale extends Comando{
    private String objId;
    private float valore;

    public Scale(String objID, float tipo){
        this.objId=objID;
        this.valore=tipo;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new ScaleCommand(panel,visualizzazione,objId, valore));
        return stato;
    }

    
    
}
