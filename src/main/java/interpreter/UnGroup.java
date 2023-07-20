package interpreter;

import command.CommandHandler;
import concreteCommand.RimuoviGruppoCommand;
import view.PannelloGrafico;

import javax.swing.*;

public class UnGroup extends Comando{

    private String idGruppo;

    public UnGroup(String idGruppo){
        this.idGruppo=idGruppo;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        //visualizzazione.setText("ciao, sono io");
        invoker.handle(new RimuoviGruppoCommand(panel, visualizzazione,idGruppo));
        return null;
    }

    
    
}
