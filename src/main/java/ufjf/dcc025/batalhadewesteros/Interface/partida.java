package ufjf.dcc025.batalhadewesteros.Interface;

import ufjf.dcc025.batalhadewesteros.Secundario.tabuleiro;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

import java.util.EnumSet;
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

            sb.append(String.format(" - %s (%s): %d HP\n", p.getNome(), p.getTipo(), p.getVidaAtual()));
        }

        return sb.toString();
    }

    
    private int selecionaPersonagem(int turno, tabuleiro tabuleiro, List <personagem> time1, List <personagem> time2, String[] opcoes, String Jogador){
        int escolha = JOptionPane.showOptionDialog(null, imprimeInteface(tabuleiro, time1, time2) + "/n" + "/n" + Jogador +" selecione um personagem para mover:", 
                            "Turno " + turno,  JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]); 
        return escolha;
    }


    public void umJogador(List<personagem> time){
        JOptionPane.showMessageDialog(null, "Partida um Jogador");
    }
    


    public void doisJogadores(List <personagem> time1, List <personagem> time2){
        int jogada = 1;
        int turno = 1;
        tabuleiro tabuleiro = new tabuleiro(time1, time2);
        String[] direcao = {"Cima" , "Baixo", "Direita", "Esquerda", "Voltar"};

        while (time1.size() > 0 && time2.size() > 0) {

            //jogada do jogador 1
            if(jogada % 2 == 1){
                String[] opcoes = new String[time1.size()];
                for (int i = 0; i < time1.size(); i++)                //inicializa as opções de movimento
                    opcoes[i] = time1.get(i).getNome();
        
                
                int escolhaDirecao = 0;
                personagem selecionado = null;
                /// O do While serve para poder ter a opção de voltar e escolher um outro personagem para mover
                do{                                
                    int escolhaPersonagem = selecionaPersonagem(turno, tabuleiro, time1, time2, opcoes, "Jogador 1");

                    if (escolhaPersonagem >= 0 && escolhaPersonagem < time1.size()) {
                        selecionado = time1.get(escolhaPersonagem);            //elimina a necessidade de um swith

                        escolhaDirecao = JOptionPane.showOptionDialog(null, imprimeInteface(tabuleiro, time1, time2) + "/n" + "/n" + "Mova para uma direção:", 
                                "Turno " + turno,  JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, direcao, direcao[0]);
                                
                                
                        if(escolhaDirecao < 4 && tabuleiro.moverPersonagem(selecionado, escolhaDirecao)){      //se entrou aqui é pq não vai ter loop
                            
                        }
                        else if(escolhaDirecao < 4)                                                            //movimentação invalida
                            JOptionPane.showMessageDialog(null, "Direção invalida! Escolha um outro personagem" +
                                                          " para se mover ou mova para uma posição valida.");
                            
                    }
                } while(escolhaDirecao == 4 || !tabuleiro.moverPersonagem(selecionado, escolhaDirecao));       //caso selecione voltar ou movimente errado
            }

            //jogada do jogador 2
            else{

            }
            
            turno = jogada/2 + 1;
            jogada++;
        }
    }
    
}
