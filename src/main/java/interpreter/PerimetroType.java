package interpreter;
import javax.swing.JTextArea;

import command.CommandHandler;
import concreteCommand.CalcolaPerimetroTipoCommand;
import view.PannelloGrafico;

public class PerimetroType extends Perimetro{
    private String tipo;

    public PerimetroType(String tipo){
        this.tipo=tipo;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        invoker.handle(new CalcolaPerimetroTipoCommand(panel,visualizzazione, tipo));
        return null;
    }

    
}
