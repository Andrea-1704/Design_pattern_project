import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.StringReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

import command.HistoryCommandHandler;
import interpreter.AnalizzatoreSintattico;
import interpreter.Comando;
import interpreter.Stato;
import model.CircleObject;
import model.ImageObject;
import model.RectangleObject;
import view.*;

public class Interprete {
    public static void main(String[] args){
        JFrame frame = new JFrame("INTERPRETE DI COMANDI CAD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlyweightFactory fabbrica= new FlyweightFactory();
        fabbrica.installView(RectangleObject.class, new RectangleObjectView());
        fabbrica.installView(CircleObject.class, new CircleObjectView());
        fabbrica.installView(ImageObject.class, new ImageObjectView());//in questo modo

        PannelloGrafico panel= new PannelloGrafico(fabbrica);
        panel.setPreferredSize(new Dimension(935,590));
        //panel.setBackground(Color.WHITE);

        //JScrollPane scrollPane = new JScrollPane(panel);
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //frame.getContentPane().add(scrollPane);

        //frame.add(panel);
        JButton indietro= new JButton("indietro");
        indietro.setPreferredSize(new Dimension(200, 20));
        HistoryCommandHandler invoker= new HistoryCommandHandler(100);
        JButton avanti= new JButton("avanti");
        avanti.setPreferredSize(new Dimension(200, 20));
        JPanel pannello = new JPanel(new FlowLayout());
        pannello.add(indietro);
        pannello.add(avanti);
        indietro.addActionListener(evt -> invoker.handle(HistoryCommandHandler.NonExecutableCommands.UNDO));
        avanti.addActionListener(evt->invoker.handle(HistoryCommandHandler.NonExecutableCommands.REDO));
        frame.add(pannello,BorderLayout.NORTH);
        frame.pack(); // Adatta la dimensione del frame in base ai componenti contenuti
        frame.setVisible(true);
        
		//si accorge di cambiamenti e disegna tutto

        
        JTextArea visualizzazione = new JTextArea();
        JScrollPane visualizzazione2 = new JScrollPane(visualizzazione);
        visualizzazione.setBackground(Color.getHSBColor(219,61,97));
        visualizzazione.setForeground(Color.black);
        //visualizzazione.setLineWrap(true); // Abilita il wrapping del testo a livello di riga
        //visualizzazione.setWrapStyleWord(true); // Abilita il wrapping a livello di parola
        //JScrollPane scrollPaneTesto = new JScrollPane(visualizzazione);

        JTextField scrittura= new JTextField("digita qui");
        scrittura.setBackground(Color.getHSBColor(219,61,97));
        //pannello.setBackground(Color.darkGray);
        scrittura.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField textField = (JTextField) e.getComponent();
                textField.setText("");
            }
        });
        
        scrittura.setForeground(Color.black);
        scrittura.setFont(new Font("calibri", 0, 19));
        visualizzazione.add(scrittura, BorderLayout.SOUTH);
        scrittura.setPreferredSize(new Dimension(40, 40));
        Font font = new Font(visualizzazione.getFont().getName(), Font.ITALIC, 15); // Dimensione 16

        visualizzazione.setFont(font);

        JScrollPane scrollPanel = new JScrollPane(visualizzazione);
        // Aggiungi il JScrollPane alla finestra
        frame.getContentPane().add(scrollPanel);

        scrittura.addActionListener(e -> {
                String comando=scrittura.getText();
                StringReader sr = new StringReader(comando);
                AnalizzatoreSintattico p = new AnalizzatoreSintattico(sr, panel, visualizzazione, invoker);
                Comando ris=p.getComando();
                ris.interpret(panel, visualizzazione, invoker, new Stato());
        });
        
        frame.add(scrittura, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(20000, 20000));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setScrollMode(JViewport.BLIT_SCROLL_MODE);
        frame.getContentPane().add(scrollPane);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, scrollPanel);
        //splitPane.setDividerLocation(0.8);  // Imposta la posizione iniziale del divisore

        frame.getContentPane().add(splitPane);
        frame.setSize(1650,650);

        

    }
}
