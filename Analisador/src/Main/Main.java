package Main;

import Base.Lexico;
import Grafico.analisadorPanel;

public class Main {
    public static void main(String[] args) {
//       String codigoFonte = "i if IF int x = 10 + 20; 01";
//        String codigoFonte = "20;";
//        String codigoFonte = "2 eu sou a lenda IF if E else ";
//
//        Lexico lexico = new Lexico();
//        String resultadoAnalise = lexico.analisa(codigoFonte);
//
//        System.out.println("Resultado da análise léxica:");
//        System.out.println(resultadoAnalise);
          // Criação e exibição da interface gráfica
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                analisadorPanel panel = new analisadorPanel();
                panel.setVisible(true);
            }
        });

    }
}
