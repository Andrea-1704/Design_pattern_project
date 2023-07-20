package interpreter;

import javax.swing.*;

import command.CommandHandler;
import view.PannelloGrafico;

public class Type implements AbstractExpression{

    private String tipo;

    public Type(String tipo){
        this.tipo=tipo;
        if(!(tipo.equals("circle"))&&!(tipo.equals("rectangle"))&&!(tipo.equals("img"))){
            throw new ExceptionInInitializerError("passa un oggetto riconosciuto");
        }
    }

    public String getTipo(){
        return tipo;
    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        
        if(tipo.equals("circle")){
            Stato res= new Stato();
            res.setTipo(OggettiGrafici.CIRCLE);
            return res;
        }
        if(tipo.equals("rectangle")){
            Stato res= new Stato();
            res.setTipo(OggettiGrafici.RECTANGLE);
            return res;
        }
        else{
            Stato res= new Stato();
            res.setTipo(OggettiGrafici.IMG);
            return res;
        }
        
    }
    
}
