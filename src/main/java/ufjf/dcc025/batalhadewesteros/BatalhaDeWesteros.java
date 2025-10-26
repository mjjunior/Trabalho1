package ufjf.dcc025.batalhadewesteros;
import ufjf.dcc025.batalhadewesteros.Interface.menu;

import javax.swing.JOptionPane;

public class batalhaDeWesteros {
    
    public static void main(String[] args) {           
        
        menu jogo = new menu();
        JOptionPane.showMessageDialog(null, "Bem vindo a Batalha Tática das Casas de  Westeros! \n" + 
                                    "Monte um time com 3 personagens e batalhe contra um amigo ou teste suas habilidades de maneira solo.");
        jogo.menuPrincipal();
    }   
}
