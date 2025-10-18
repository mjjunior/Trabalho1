package ufjf.dcc025.batalhadewesteros;

import javax.swing.JOptionPane;


public class BatalhaDeWesteros {

     public void start(){
         
        //JOptionPane.showMessageDialog(null, "Bem-vindo");
        
        String[] opcoes = {" Single-player "," Mutiplayer "," sair "};
     
      int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Opções", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
      
       if(escolha == 0){
           JOptionPane.showMessageDialog(null, "Iniciando jogo: " + opcoes[escolha]);
       }
       
     }
}
