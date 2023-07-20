package concreteCommand;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.JTextArea;

import command.Command;
import model.GraphicObject;
import view.PannelloGrafico;

public class CalcolaPerimetroTipoCommand implements Command{
    private LinkedList<GraphicObject> oggetti;
    private String tipo;
    private JTextArea visualizzazione;

    public CalcolaPerimetroTipoCommand(PannelloGrafico panel, JTextArea visualizzazione, String tipo){
        this.tipo=tipo;
        this.visualizzazione=visualizzazione;
        this.oggetti=panel.getOggettiTipo(tipo);
    }

    @Override
    public boolean doIt() {
        if(oggetti.size()==0){
            visualizzazione.setText("Oggetto non presente.");
            return false;
        }
        double valore=0.0;
        for(GraphicObject go: oggetti){
            valore+=go.getPerimetro();
        }
        DecimalFormat df = new DecimalFormat("#." + "0".repeat(3));
        String numeroFormattato = df.format(valore);
        visualizzazione.setText("Perimetro del tipo "+tipo+" :"+"\n");
        visualizzazione.append(""+numeroFormattato);
        return false;
    }

    @Override
    public boolean undoIt() {      
        return false;
    }
}
