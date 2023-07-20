package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CalcolaPerimetroIdCommand;
import view.PannelloGrafico;

public class PerimetroId extends Perimetro{
    private String idOggetto;

    public PerimetroId(String oggetto){

        this.idOggetto=oggetto;

    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new CalcolaPerimetroIdCommand(panel,visualizzazione, idOggetto));
        return null;
    }

    
}
