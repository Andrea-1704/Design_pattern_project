import interpreter.AnalizzatoreLessicale;
import interpreter.Simboli;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnalizzatoreLessicaleTest {
    @Test
    public void testSomma(){
        AnalizzatoreLessicale lexer= new AnalizzatoreLessicale("pippo");
        Simboli ris= lexer.prossimoSimbolo();
        AnalizzatoreLessicale lexer2= new AnalizzatoreLessicale("new");
        Simboli ris2= lexer2.prossimoSimbolo();
        AnalizzatoreLessicale lexer3= new AnalizzatoreLessicale("img");
        Simboli ris3= lexer3.prossimoSimbolo();
        //AnalizzatoreLessicale lexer4= new AnalizzatoreLessicale("./pippo.png");
        //Simboli ris4= lexer4.prossimoSimbolo();
        Simboli[] oggetti = {Simboli.NEW, Simboli.circle, Simboli.TONDA_APERTA, Simboli.posfloat, Simboli.TONDA_CHIUSA, Simboli.TONDA_APERTA, Simboli.posfloat, Simboli.VIRGOLA, Simboli.posfloat, Simboli.TONDA_CHIUSA};
        Simboli[] res= new Simboli[10];
        AnalizzatoreLessicale lexer5= new AnalizzatoreLessicale("new circle (500) (300,300)");

        for(int i=0;i<10;i++){
            res[i]=lexer5.prossimoSimbolo();
            assertEquals(oggetti[i], res[i]);
        }
        assertEquals(Simboli.PAROLA, ris);
        assertEquals(Simboli.NEW, ris2);
        assertEquals(Simboli.img, ris3);
        //assertEquals(Simboli.PATH, ris4);
    }
}
