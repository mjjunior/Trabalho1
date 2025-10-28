package ufjf.dcc025.batalhadewesteros.Interface;

import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;
import java.util.List;
import javax.swing.JOptionPane;

public class partida {
    
    public void umJogador(List<personagem> time){
        JOptionPane.showMessageDialog(null, "Partida um Jogador");
    }
    
    public void doisJogadores(List <personagem> time1, List <personagem> time2){
        JOptionPane.showMessageDialog(null, "Partida dois Jogadores");
    }
    
}
