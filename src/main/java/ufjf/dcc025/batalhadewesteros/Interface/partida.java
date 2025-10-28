package ufjf.dcc025.batalhadewesteros.Interface;

import ufjf.dcc025.batalhadewesteros.Secundario.tabuleiro;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;
import java.util.List;
import javax.swing.JOptionPane;

public class partida {
    
    private String imprimeInteface(tabuleiro tabuleiro, List <personagem> time1, List <personagem> time2){     //transfora a interface em uma String
        StringBuilder sb = new StringBuilder();

        /// CABEÇALHO
        sb.append("   ");
        for (int j = 0; j < 10; j++) {
            sb.append(j + " ");
        }
        sb.append("\n");

        // TABULEIRO
        for (int i = 0; i < 10; i++) {
            sb.append(i + "  ");
            for (int j = 0; j < 10; j++) {
                if (tabuleiro.verVazio(i, j)) {
                    sb.append(". ");
                } else {
                    personagem p = tabuleiro.getPersonagem(i, j);
                    char inicial = p.getTipo().charAt(0);
                    if (p.getTime() == 2) inicial = Character.toLowerCase(inicial);
                    sb.append(inicial + " ");
                }
            }
            sb.append("\n");
        }

        ///Estatisticas dos personagens;
        sb.append("\n===== STATUS DOS TIMES =====\n");
        sb.append("Time 1:\n");
        for (personagem p : time1) {
            sb.append(String.format(" - %s (%s): %d HP\n", p.getNome(), p.getTipo(), p.getVidaAtual()));
        }

        sb.append("\nTime 2:\n");
        for (personagem p : time2) {
          //  sb.append(String.format(" - %s (%s): %d HP\n", p.getNome(), p.getCasa(), p.getVidaAtual()));
        }

        return sb.toString();
    }





    public void umJogador(List<personagem> time){
        JOptionPane.showMessageDialog(null, "Partida um Jogador");
    }
    


    public void doisJogadores(List <personagem> time1, List <personagem> time2){
        int jogada = 1;
        int turno = 1;
       // tabuleiro tabuleiro = new tabuleiro();

        while (time1.size() > 0 && time2.size() > 0) {
            if(jogada % 2 == 1){

            }
            else{

            }
            
            turno = jogada/2 + 1;
            jogada++;
        }
    }
    
}
