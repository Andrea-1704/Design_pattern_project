package interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class AnalizzatoreLessicale {

	private StreamTokenizer input;
	private Simboli simbolo;
    private String pathRegex = "\"[\'.'*\'/'*a-zA-Z:/]*\""; // Espressione regolare per i percorsi path
	//private String pathRegex2 = "\"[\'.'*\'\\'*a-zA-Z:\\]*\"";
	private String pathID="[a-zA-Z]*[0-9]+";
	public AnalizzatoreLessicale(Reader in) {
		input = new StreamTokenizer(in);
		input.resetSyntax();
		input.eolIsSignificant(false);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
        input.wordChars('/', '/');
        input.wordChars(':', ':');
        input.wordChars('\"', '\"');
        input.wordChars('.', '.');
        
        
        input.whitespaceChars('\u0000', ' ');//riconosce lo spazio come elemento di spaziatura
        input.parseNumbers(); // Abilita il riconoscimento dei numeri
		input.ordinaryChar('(');
		input.ordinaryChar(')');
        input.ordinaryChar(',');
        //input.ordinaryChar('.'); // Tratta il punto come carattere normale
	}

	public AnalizzatoreLessicale(String s) {
		StringReader in = new StringReader(s);
		input = new StreamTokenizer(in);
		input.resetSyntax();
		input.eolIsSignificant(false);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('/', '/');

		input.wordChars(':', ':');
		input.wordChars('\"', '\"');
		input.wordChars('.', '.');


		input.whitespaceChars('\u0000', ' ');//riconosce lo spazio come elemento di spaziatura
		input.parseNumbers(); // Abilita il riconoscimento dei numeri
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.ordinaryChar(',');
		input.quoteChar('"');
		//input.ordinaryChar('.'); // Tratta il punto come carattere normale
	}

	public String getString() {
		return input.sval;
		
	}


	public float getNumber(){
		return (float)input.nval;
	}

	public Simboli prossimoSimbolo() {
		try {
			switch (input.nextToken()) {
			case StreamTokenizer.TT_EOF:
				simbolo = Simboli.EOF;
				break;
            case StreamTokenizer.TT_NUMBER:
                double numberValue = input.nval;
                if(numberValue<0){System.out.println("numero negativo");System.exit(-1);}
                simbolo=Simboli.posfloat;
                break;

			case StreamTokenizer.TT_WORD:
				// verifica prima se la parola e' riservata
                String wordValue = input.sval;
				String replacedString = wordValue.replace("\\", "/");
				System.out.println(replacedString);
                if (replacedString.matches(pathRegex)) {
                    simbolo=Simboli.PATH;break;
                }
				if(replacedString.matches(pathID)){
                    simbolo=Simboli.ObjID;break;
                }
				if (input.sval.equalsIgnoreCase("new"))
					simbolo = Simboli.NEW;
				else if (input.sval.equalsIgnoreCase("del"))
					simbolo = Simboli.del;
				else if (input.sval.equalsIgnoreCase("mv"))
					simbolo = Simboli.mv;
				else if (input.sval.equalsIgnoreCase("mvoff"))
					simbolo = Simboli.mvoff;
                else if (input.sval.equalsIgnoreCase("scale"))
					simbolo = Simboli.scale;
				else if (input.sval.equalsIgnoreCase("ls"))
					simbolo = Simboli.ls;
				else if (input.sval.equalsIgnoreCase("all"))
					simbolo = Simboli.all;
                else if (input.sval.equalsIgnoreCase("groups"))
					simbolo = Simboli.groups;
				else if (input.sval.equalsIgnoreCase("grp"))
					simbolo = Simboli.grp;
				else if (input.sval.equalsIgnoreCase("ungrp"))
					simbolo = Simboli.ungrp;
                else if (input.sval.equalsIgnoreCase("area"))
					simbolo = Simboli.area;
				else if (input.sval.equalsIgnoreCase("perimeter"))
					simbolo = Simboli.perimeter;
				else if (input.sval.equalsIgnoreCase("circle"))
					simbolo = Simboli.circle;
                else if (input.sval.equalsIgnoreCase("rectangle"))
					simbolo = Simboli.rectangle;
				else if (input.sval.equalsIgnoreCase("img"))
					simbolo = Simboli.img;
                else
					// parola normale - identificativo, PATH o parola con accanto il numero
					simbolo = Simboli.PAROLA;
				break;

			case '(':
				simbolo = Simboli.TONDA_APERTA;
				break;
			case ')':
				simbolo = Simboli.TONDA_CHIUSA;
				break;
			case ',':
				simbolo = Simboli.VIRGOLA;
				break;
			default:
				simbolo = Simboli.CHAR_INVALIDO;
			}
		} catch (IOException e) {
			simbolo = Simboli.EOF;
		}
		return simbolo;
	}// prossimoSimbolo
}// AnalizzatoreLessicale