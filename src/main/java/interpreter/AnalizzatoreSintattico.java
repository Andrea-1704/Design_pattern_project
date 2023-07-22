package interpreter;

import java.awt.*;
import java.io.Reader;
import java.util.LinkedList;

import javax.swing.JTextArea;

import command.CommandHandler;
import view.PannelloGrafico;


public class AnalizzatoreSintattico {

    private AnalizzatoreLessicale lexer;
    private Comando root;
    private Simboli simbolo;
    private PannelloGrafico panel;
    private CommandHandler invoker;
    private JTextArea visualizzazione;

    public AnalizzatoreSintattico(Reader in, PannelloGrafico panel, JTextArea visualizzazione, CommandHandler invoker){
        lexer= new AnalizzatoreLessicale(in);
        this.panel=panel;
        this.invoker=invoker;
        this.visualizzazione=visualizzazione;
        visualizzazione.setForeground(Color.black);
        root=comando();
        //atteso(Simboli.EOF);
    }

    private Comando comando(){
        visualizzazione.setForeground(Color.black);
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.NEW)return create(panel);
        if(simbolo==Simboli.del)return delete(panel);
        if(simbolo==Simboli.mv || simbolo==Simboli.mvoff)return move(panel);
        if(simbolo==Simboli.scale)return scale(panel);
        if(simbolo==Simboli.ls)return list(panel);
        if(simbolo==Simboli.grp)return group(panel);
        if(simbolo==Simboli.ungrp)return unGroup(panel);
        if(simbolo==Simboli.area)return area(panel);
        if(simbolo==Simboli.perimeter)return perimetro(panel);
        visualizzazione.setForeground(Color.RED);
        visualizzazione.setText("Hai inserito un comando non riconosciuto, per favore riprova!");
        throw new SyntaxException("atteso un comando valido");
    }

    private Create create(PannelloGrafico panel){
        TypeConstr typeConstr=typeConstr();
        Pos posizione=pos();
        return new Create(typeConstr, posizione);
    }

    private Remove delete(PannelloGrafico panel){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.ObjID){
            return new Remove(lexer.getString());
        }
        else{
            visualizzazione.setForeground(Color.RED);
            visualizzazione.setText("Per rimuovere un oggetto bisogna specificarne l'ID. Si prega di riprovare.");
            throw new SyntaxException("atteso un comando valido");
        }
    }

    private Move move(PannelloGrafico panel){
        if(lexer.getString().equals("mv")){
            simbolo=lexer.prossimoSimbolo();
            if(simbolo==Simboli.ObjID){
                String id=lexer.getString();
                Pos posizione= pos();
               
                return new MoveMV(id, posizione);
            }else{
                visualizzazione.setForeground(Color.RED);
                visualizzazione.setText("Per spostare un oggetto bisogna specificarne l'ID. Si prega di riprovare.");
                throw new SyntaxException("atteso un identificativo");
            }
        }else{//mvoff
            simbolo=lexer.prossimoSimbolo();
            if(simbolo==Simboli.ObjID){
                String id=lexer.getString();
                Pos posizione= pos();
                return new MoveOffMV(id, posizione);
            }else{
                visualizzazione.setForeground(Color.RED);
                visualizzazione.setText("Per spostare un oggetto bisogna specificarne l'ID. Si prega di riprovare.");
                throw new SyntaxException("atteso un identificativo");
            }
        }
    }

    private Scale scale(PannelloGrafico panel){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.ObjID){
            String id=lexer.getString();
            simbolo=lexer.prossimoSimbolo();
            if(simbolo==Simboli.posfloat){
                return new Scale(id, lexer.getNumber());
            }
            else{
                visualizzazione.setForeground(Color.RED);
                visualizzazione.setText("Atteso un valore di tipo float. Si prega di riprovare.");
                throw new SyntaxException("atteso un float");
            }
        }else{
            visualizzazione.setForeground(Color.RED);
            visualizzazione.setText("Per scalare un oggetto di dimensione bisogna specificarne l'ID. Si prega di riprovare.");
            throw new SyntaxException("atteso un identificativo");
        }
    }

    private Lista list(PannelloGrafico panel){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.ObjID){
            return new ListaOggetto(lexer.getString());
        }if(simbolo==Simboli.all){
            return new ListaTutti();
        }
        if(simbolo==Simboli.groups){
            return new ListaGruppi();
        }
        else{
            Type tipo=type();
            return new ListaTipo(tipo.getTipo());
        }
    }

    private Group group(PannelloGrafico panel){
        ListID list= listID();
        return new Group(list);
    }

    private UnGroup unGroup(PannelloGrafico panel){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.ObjID){
            return new UnGroup(lexer.getString());
        }
        else{
            visualizzazione.setForeground(Color.RED);
            visualizzazione.setText("Per rimuovere un gruppo bisogna specificarne l'ID. Si prega di riprovare.");
            throw new SyntaxException("atteso un identificativo");
        }
    }

    private Area area(PannelloGrafico panel){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.ObjID){
            return new AreaId(lexer.getString());
        }if(simbolo==Simboli.all){
            return new AreaTutto();
        }else{
            Type tipo=type();
            return new AreaType(tipo.getTipo());
        }
    }

    private Perimetro perimetro(PannelloGrafico panel){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.ObjID){
            return new PerimetroId(lexer.getString());
        }if(simbolo==Simboli.all){
            return new PerimetroTutto();
        }else{
            Type tipo=type();
            return new PerimetroType(tipo.getTipo());
        }
    }

    private TypeConstr typeConstr(){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.circle){
            simbolo=lexer.prossimoSimbolo();
            if(simbolo==Simboli.TONDA_APERTA){
                simbolo=lexer.prossimoSimbolo();
                if(simbolo==Simboli.posfloat){
                    float valore=lexer.getNumber();
                    simbolo=lexer.prossimoSimbolo();
                    if(simbolo==Simboli.TONDA_CHIUSA){
                        return new TypeConstrCircle(valore);
                    }else{
                        visualizzazione.setForeground(Color.RED);
                        visualizzazione.setText("Parentesi non chiusa. Si prega di riprovare.");
                        throw new SyntaxException("attesa chiusura parentesi tonda");
                    }
                }else{
                    visualizzazione.setForeground(Color.RED);
                    visualizzazione.setText("Atteso valore di tipo float. Si prega di riprovare.");
                    throw new SyntaxException("atteso un float");
                }
                
            }else{
                visualizzazione.setForeground(Color.RED);
                visualizzazione.setText("Parentesi non presente. Si prega di riprovare.");
                throw new SyntaxException("attesa parentesi tonda aperta");
            } 
        }
        if(simbolo==Simboli.rectangle){
            Pos posizione=pos();
            return new TypeConstrRect(posizione);
        }if(simbolo==Simboli.img){
            simbolo=lexer.prossimoSimbolo();
            if(simbolo==Simboli.TONDA_APERTA){
                simbolo=lexer.prossimoSimbolo();
                if(simbolo==Simboli.PATH){
                    String valore=lexer.getString();
                    String percorso=valore.substring(1, valore.length()-1);
                    simbolo=lexer.prossimoSimbolo();
                    if(simbolo==Simboli.TONDA_CHIUSA){
                        return new TypeConstrImage(percorso);
                    }else{
                        visualizzazione.setForeground(Color.RED);
                        visualizzazione.setText("Parentesi non chiusa. Si prega di riprovare.");
                        throw new SyntaxException("attesa chiusura parentesi tonda");
                    }
                }else{
                    visualizzazione.setForeground(Color.RED);
                    visualizzazione.setText("Percorso immagine non valido. Si prega di riprovare.");
                    throw new SyntaxException("atteso un percorso valido");
                }
                
            }else{
                visualizzazione.setForeground(Color.RED);
                visualizzazione.setText("Parentesi tonda non aperta. Si prega di riprovare.");
                throw new SyntaxException("attesa parentesi tonda aperta");
            } 
        }else{
            visualizzazione.setForeground(Color.RED);
            visualizzazione.setText("Specifica uno tra i seguenti graphicObject:");
            visualizzazione.append("\n");
            visualizzazione.append("circle;");
            visualizzazione.append("\n");
            visualizzazione.append("rectangle;");
            visualizzazione.append("\n");
            visualizzazione.append("img.");
            throw new SyntaxException("atteso uno tra: circle, rectangle, img");
        }
    }

    private Pos pos(){
        simbolo=lexer.prossimoSimbolo();
        if(simbolo==Simboli.TONDA_APERTA){
            simbolo=lexer.prossimoSimbolo();
            if(simbolo==Simboli.posfloat){
                float valore=lexer.getNumber();
                simbolo=lexer.prossimoSimbolo();
                if(simbolo==Simboli.VIRGOLA){
                    simbolo=lexer.prossimoSimbolo();
                    if(simbolo==Simboli.posfloat){
                        float valore2=lexer.getNumber();
                        simbolo=lexer.prossimoSimbolo();
                        if(simbolo==Simboli.TONDA_CHIUSA){
                            return new Pos(valore, valore2);
                        }
                        else{
                            visualizzazione.setForeground(Color.RED);
                            visualizzazione.setText("Parentesi non chiusa. Si prega di riprovare.");

                            throw new SyntaxException("attesa una parentesi tonda chiusa");
                        }
                    }else{
                        visualizzazione.setForeground(Color.RED);
                        visualizzazione.setText("Atteso valore di tipo float. Si prega di riprovare.");

                        throw new SyntaxException("atteso unfloat");
                    }
                }else{
                    visualizzazione.setForeground(Color.RED);
                    visualizzazione.setText("Attesa una virgola. Si prega di riprovare.");

                    throw new SyntaxException("atteso una virgola");
                }
            }else{
                visualizzazione.setForeground(Color.RED);
                visualizzazione.setText("Atteso valore di tipo float. Si prega di riprovare.");

                throw new SyntaxException("atteso un float");
            }
        }else{
            visualizzazione.setForeground(Color.RED);
            visualizzazione.setText("Parentesi non chiusa. Si prega di riprovare.");

            throw new SyntaxException("attesa una parentesi tonda chiusa");
        }
    }

    private Type type(){
        if(simbolo==Simboli.circle){
            return new Type(lexer.getString());
        }
        if(simbolo==Simboli.rectangle){
            return new Type(lexer.getString());
        }
        if(simbolo==Simboli.img){
            return new Type(lexer.getString());
        }
        else{
            visualizzazione.setForeground(Color.RED);
            visualizzazione.setText("Specifica uno tra i seguenti graphicObject:");
            visualizzazione.append("\n");
            visualizzazione.append("circle;");
            visualizzazione.append("\n");
            visualizzazione.append("rectangle;");
            visualizzazione.append("\n");
            visualizzazione.append("img.");

            throw new SyntaxException("attesa un graphic object");
        }
    }

    private ListID listID(){
        simbolo=lexer.prossimoSimbolo();
        LinkedList<String> id= new LinkedList<>();
        int iterazione=0;
        while(true){
            if(simbolo==Simboli.ObjID){
                id.add(lexer.getString());
                iterazione++;
                simbolo=lexer.prossimoSimbolo();
            }
            else{
                if(simbolo==Simboli.VIRGOLA&&iterazione%2!=0){
                    simbolo=lexer.prossimoSimbolo();
                    iterazione++;
                }
                else{
                    break;
                }
            }

        }
        return new ListID(id);
    }

    /*private void atteso(Simboli s){
        if(simbolo!=s){
            throw new SyntaxException("flusso non terminato");
        }
    }*/

    public Comando getComando(){
        return root;
    }

   
}
