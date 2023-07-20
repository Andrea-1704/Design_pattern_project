package interpreter;

import command.CommandHandler;
import view.PannelloGrafico;

import javax.swing.*;

public class Cmd extends Comando{
    private Comando comando;

    public Cmd(Comando comando){
        this.comando=comando;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        Stato oggetto =comando.interpret(panel,visualizzazione,invoker,stato);
        return oggetto;
    }

    
}
