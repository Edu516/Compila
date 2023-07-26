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
        return "^[1-9]?[a-zA-Z]+[0-9]?$";
    }

    public static String getDefineConstantes() {
        return "^(0[1-9]|[1-9]\\d*)$";
    }

    public static String[] getDefineOperadores() {
        return new String[]{"+", "-", "*", "/", "%", "--", "++", "=","<", ">", "<=", ">=", "^", "!="};
    }

    public static String[] getDefineSeperadores() {
        return new String[]{"(", ")", "[", "]", "{", "}"};
    }

    public static String[] getDefineReservadas() {
        return new String[]{"IF", "ELSE", "FOR", "SWITCH", "WHILE", "DO", "PRINT", "READ", "FOREACH"};
    }

    private void compoeAnalise(String id, String lexema, String token) {
        this.analise = this.analise.concat("id : " + id + " Lexema : " + lexema + " Token : " + token + "\n");
    }

    public String analisa(String texto) {
        String[] sentenca = texto.split(" ");
        int tamanhoSentenca = sentenca.length;

        for (int i = 0; i < tamanhoSentenca; i++) {
            String palavra = sentenca[i].trim(); // Remove espaços em branco no início e no fim da palavra
            if (palavra.isEmpty()) {
                continue; // Ignora palavras vazias
            }
            
            String token = verificaReservadas(palavra);
            if (token.length() > 0) {
                compoeAnalise("" + i, palavra, token);
                continue;
            }

            token = verificaConstantes(palavra);
            if (token.length() > 0) {
                compoeAnalise("" + i, palavra, token);
                continue;
            }
            
            token = verificaIdentificadores(palavra);
            if (token.length() > 0) {
                compoeAnalise("" + i, palavra, token);
                continue;
            }

            token = verificaOperadores(palavra);
            if (token.length() > 0) {
                compoeAnalise("" + i, palavra, token);
                continue;
            }

            token = verificaSeparadores(palavra);
            if (token.length() > 0) {
                compoeAnalise("" + i, palavra, token);
                continue;
            }

           

            // Caso nenhum token seja encontrado, trata-se de um erro ou palavra desconhecida
            compoeAnalise("" + i, palavra, "ERRO/DESCONHECIDO");
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
        if (texto.matches(Lexico.getDefineConstantes())) {
            return "Constante";
        }
        return "";
    }

    private String verificaOperadores(String texto) {
        for (String operador : operadores) {
            if (texto.equals(operador)) {
                return "Operador";
            }
        }

        return "";
    }

    private String verificaSeparadores(String texto) {
        for (String separador : sepadores) {
            if (texto.equals(separador)) {
                return "Separador";
            }
        }

        return "";
    }

    private String verificaReservadas(String texto) {
        for (String reservada : reservadas) {
            if (texto.equals(reservada)) {
                return "Reservada";
            }
        }

        return "";
    }
}
