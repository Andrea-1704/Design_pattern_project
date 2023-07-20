package interpreter;
import java.util.StringTokenizer;

import javax.swing.*;

import command.CommandHandler;
import view.PannelloGrafico;

public class TypeConstrImage extends TypeConstr{

    private String path;

    public TypeConstrImage(String path){
        //System.out.println(path);
        StringTokenizer st= new StringTokenizer(path, "/");
        String path2="";
        while(st.hasMoreTokens()){
            path2+=st.nextToken();
            path2+="\\";
        }
        this.path=path2;

    }

    @Override
    public Stato interpret(PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker, Stato stato) {
        //ImageIcon image=new ImageIcon(path);
        stato.setTipo(OggettiGrafici.IMG);
        stato.setPath(path);
        return stato;
    }
    
}
