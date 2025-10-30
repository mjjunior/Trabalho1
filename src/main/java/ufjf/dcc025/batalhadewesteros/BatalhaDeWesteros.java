package ufjf.dcc025.batalhadewesteros;
import ufjf.dcc025.batalhadewesteros.Interface.menu;

import javax.swing.JOptionPane;

public class BatalhaDeWesteros {
    
    public static void main(String[] args) {           
        
        menu jogo = new menu();
        JOptionPane.showMessageDialog(null, "Bem vindo a Batalha Tática das Casas de  Westeros! \n" + 
                                    "Monte um time com 3 personagens e batalhe contra um amigo ou teste suas habilidades de maneira solo.");

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();      //limpa o console
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
        
        jogo.menuPrincipal();
    }   
}
