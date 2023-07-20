package interpreter;

import command.CommandHandler;
import view.PannelloGrafico;

import javax.swing.*;

public interface AbstractExpression {
    Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato);
}
