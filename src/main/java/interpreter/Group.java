package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CreaGruppoCommand;
import view.PannelloGrafico;

import java.util.concurrent.TimeUnit;

public class Group extends Comando{

    private ListID listaID;
    private JTextArea visualizzazione;

    public Group(ListID listaID){
        this.listaID=listaID;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        this.visualizzazione=visualizzazione;
        visualizzazione.setText("ciao");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Stato oggetto=listaID.interpret(panel, visualizzazione, invoker, stato);
        invoker.handle(new CreaGruppoCommand(panel,visualizzazione,listaID.getElem()));
        //SCELGO COME ID DEL GRUPPO: listaID.getElem().get(0)
        return oggetto;
    }

    
    
}
