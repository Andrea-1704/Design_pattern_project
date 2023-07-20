package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CalcolaPerimetroTuttiCommand;
import view.PannelloGrafico;

public class PerimetroTutto extends Perimetro{


    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new CalcolaPerimetroTuttiCommand(panel, visualizzazione));
        return null;
    }

   
}
