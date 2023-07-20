package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CreateCommand;
import view.PannelloGrafico;

public class Create extends Comando{
    
    private TypeConstr tipo;
    private Pos posizione;
    private static int cont=0;


    private String id;

    public Create(TypeConstr tipo, Pos posizione){
        this.posizione=posizione;
        this.tipo=tipo;
        this.id="ID"+cont++;
    }

    public String getID(){
        return this.id;
    }


    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        Stato oggetto=tipo.interpret(panel,visualizzazione, invoker, stato);
        oggetto=posizione.interpret(panel,visualizzazione, invoker, oggetto);
        oggetto.setObjID(this.id);
        //nota che graphicObject ha anche una stringa che indica l'id
        //per fare questo il graphicObject assegna degli id unici
        invoker.handle(new CreateCommand(panel, visualizzazione, oggetto));
        //passa il comand a HistoryCommandHandler
        return oggetto;
    }
    
}
