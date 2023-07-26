package Base;

/**
 *
 * @author eduardo
 */
public class Lexico {

    private String identificadores;
    private String constante;
    private String[] operadores;
    private String[] sepadores;
    private String[] reservadas;
    private String analise;

    public Lexico() {
        this.identificadores = getDefineIdentificadores();
        this.constante = getDefineConstantes();
        this.operadores = getDefineOperadores();
        this.sepadores = getDefineSeperadores();
        this.reservadas = getDefineReservadas();
        this.analise = "";
    }

    public static String getDefineIdentificadores() {
        return "^[a-zA-Z][a-zA-Z0-9]*$";
    }

    public static String getDefineConstantes() {
        return "^\\d+$";
    }

    public static String[] getDefineOperadores() {
        return new String[]{"+", "-", "*", "/", "%", "--", "++", "<", ">", "<=", ">=", "^", "!="};
    }

    public static String[] getDefineSeperadores() {
        return new String[]{"(", ")", "[", "]", "{", "}"};
    }

    public static String[] getDefineReservadas() {
        return new String[]{"IF", "ELSE", "FOR", "SWITCH", "WHILE", "DO", "PRINT", "READ", "FOREACH"};
    }

    private void compoeAnalise(String id, String lexema, String token) {
        this.analise = this.analise.concat("id : " + id + " Lexema : " + lexema + " Token :" + token + "\n");
    }

    public String analisa(String texto) {
        String[] sentenca = texto.split(" ");
        int tamanhoSentenca = sentenca.length;

        for (int i = 0; i < tamanhoSentenca; i++) {
            String token = verificaIdentificadores(sentenca[i]);
            if (token.length() > 0) {
                compoeAnalise("" + i, sentenca[i], token);
                continue;
            }

            token = verificaConstantes(sentenca[i]);
            if (token.length() > 0) {
                compoeAnalise("" + i, sentenca[i], token);
                continue;
            }

            token = verificaOperadores(sentenca[i]);
            if (token.length() > 0) {
                compoeAnalise("" + i, sentenca[i], token);
                continue;
            }

            token = verificaSeparadores(sentenca[i]);
            if (token.length() > 0) {
                compoeAnalise("" + i, sentenca[i], token);
                continue;
            }

            token = verificaReservadas(sentenca[i]);
            if (token.length() > 0) {
                compoeAnalise("" + i, sentenca[i], token);
                continue;
            }

            // Caso nenhum token seja encontrado, trata-se de um erro ou palavra desconhecida
            compoeAnalise("\n" + i, sentenca[i], "ERRO/DESCONHECIDO");
        }

        return this.analise;
    }

    private String verificaIdentificadores(String texto) {
        if (texto.matches(identificadores)) {
            return "Palavra";
        }
        return "";
    }

    private String verificaConstantes(String texto) {
        if (texto.matches(constante)) {
            return "Palavra";
        }
        return "";
    }

    private String verificaOperadores(String texto) {
        for (String operador : operadores) {
            if (texto.equals(operador)) {
                return operador;
            }
        }

        return "";
    }

    private String verificaSeparadores(String texto) {
        for (String separador : sepadores) {
            if (texto.equals(separador)) {
                return separador;
            }
        }

        return "";
    }

    private String verificaReservadas(String texto) {
        for (String reservada : reservadas) {
            if (texto.equals(reservada)) {
                return reservada;
            }
        }

        return "";
    }
}
