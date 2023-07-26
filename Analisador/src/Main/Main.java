package Main;

import Base.Lexico;

public class Main {
    public static void main(String[] args) {
        String codigoFonte = "int x = 10 + 20;";

        Lexico lexico = new Lexico();
        String resultadoAnalise = lexico.analisa(codigoFonte);

        System.out.println("Resultado da análise léxica:");
        System.out.println(resultadoAnalise);
    }
}
