package interpreter;
import java.util.*;

import command.CommandHandler;
import view.PannelloGrafico;

import javax.swing.*;

public class ListID implements AbstractExpression{

    private LinkedList<String> listaObjID= new LinkedList<>();

    public ListID(String elem){
        listaObjID= new LinkedList<>();
        listaObjID.add(elem);
    }

    public ListID(LinkedList<String> lista){
        for(String s: lista){
            listaObjID.add(s);
        }
    }

    public void addObjID(String elem){
        listaObjID.add(elem);
    }

    public LinkedList<String> getElem(){
        LinkedList<String> l= new LinkedList<>();
        for(String s: listaObjID)l.add(s);
        return l;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        for(String elem:listaObjID)stato.setElementToList(elem);
        return stato;
    }
    
}
