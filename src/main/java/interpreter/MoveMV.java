package interpreter;

import command.CommandHandler;
import concreteCommand.MoveCommand;
import view.PannelloGrafico;

import javax.swing.*;

public class MoveMV extends Move{

    private String id;
    private Pos posizione;

    public MoveMV(String id, Pos posizione){
        this.id=id;
        this.posizione=posizione;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        Stato oggetto=posizione.interpret(panel, visualizzazione, invoker, stato);
        oggetto.setObjID(id);
        invoker.handle(new MoveCommand(panel,visualizzazione,oggetto.getObjID(), oggetto.getPosition()));
        //nota che il moveCommand non necessita del pannello: non sto aggiungendo qualcosa
        //o rimuovendo qualcosa, ma sto solamente modificando un graphicObject
        //e il pannello che il cliente imposta viene notificato

        //ho scelto di passare comunque il pannello perché lui mi deve dire la posizione precedente
        //per permettere l'undo
        return oggetto;
    }

    
}
