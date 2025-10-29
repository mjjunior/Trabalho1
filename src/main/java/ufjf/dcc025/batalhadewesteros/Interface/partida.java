package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.List;

import javax.swing.JOptionPane;

import ufjf.dcc025.batalhadewesteros.Secundario.tabuleiro;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

public class partida {

    private String imprimeInteface(tabuleiro tabuleiro, List<personagem> time1, List<personagem> time2) { // transfora a interface em uma string
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
                    if (p.getTime() == 2)
                        inicial = Character.toLowerCase(inicial);
                    sb.append(inicial + " ");
                }
            }
            sb.append("\n");
        }

        /// Estatisticas dos personagens;
        sb.append("\n===== STATUS DOS TIMES =====\n");
        sb.append("Time 1:\n");
        for (personagem p : time1) {
            sb.append(String.format(" - %s (%s): %d HP\n", p.getNome(), p.getTipo(), p.getVidaAtual()));
        }

        sb.append("\nTime 2:\n");
        for (personagem p : time2) {

            sb.append(String.format(" - %s (%s): %d HP\n", p.getNome(), p.getTipo(), p.getVidaAtual()));
        }
        // salva replay
        return sb.toString();
    }





    // Exibe a opção de escolha do personagem para mover
    private int selecionaPersonagemMover(int turno, tabuleiro tabuleiro, List<personagem> time1, List<personagem> time2,
        String[] opcoes, String Jogador,List<personagem> timePrincipal, List<personagem> timeSecundario) 
        {

        int escolha = JOptionPane.showOptionDialog(null,imprimeInteface(tabuleiro, timePrincipal, timeSecundario) + "/n" + "/n" + Jogador
        + " selecione um personagem para mover:", "Turno " + turno, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        return escolha;
    }





    private void rodada(List<personagem> time1, List<personagem> time2, String jogador, int turno, tabuleiro tabuleiro,
                         List<personagem> timePrincipal, List<personagem> timeSecundario) 
        {

        String[] direcao = { "Cima", "Baixo", "Direita", "Esquerda", "Voltar" };
        int escolhaDirecao = 0;
        personagem selecionado = null;

        String[] opcoes = new String[time1.size()];
        for (int i = 0; i < time1.size(); i++) // inicializa as opções de movimento
            opcoes[i] = time1.get(i).getNome();

        
        /// O do While serve para poder ter a opção de voltar e escolher um outro personagem para mover
        do {
            int escolhaPersonagem = selecionaPersonagemMover(turno, tabuleiro, time1, time2, opcoes, jogador, timePrincipal, timeSecundario);

            // apenas verifica se a escolha foi valida
            if (escolhaPersonagem >= 0 && escolhaPersonagem < time1.size()) {
                selecionado = time1.get(escolhaPersonagem); // elimina a necessidade de um swith

                escolhaDirecao = JOptionPane.showOptionDialog(null, imprimeInteface(tabuleiro, timePrincipal, timeSecundario) + "/n" + "/n" + "Mova para uma direção:",
                        "Turno " + turno, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, direcao, direcao[0]);

                if (escolhaDirecao < 4 && tabuleiro.moverPersonagem(selecionado, escolhaDirecao)) { // se entrou aqui é pq está tudo e certo e vai encerrar o loop

                    List<personagem> alvosDisponiveis = tabuleiro.verificaAreaAtaque(selecionado);

                    if (alvosDisponiveis.isEmpty()) {
                        break;
                    }

                    else if (alvosDisponiveis.size() == 1) {
                        selecionado.atacar(alvosDisponiveis.getFirst());
                    }

                    else {
                        String[] alvo = new String[alvosDisponiveis.size()];
                        for (int i = 0; i < alvosDisponiveis.size(); i++)
                            opcoes[i] = time1.get(i).getNome();

                        int escolhaAlvo = JOptionPane.showOptionDialog(null, imprimeInteface(tabuleiro, time1, time2) + "/n" + "/n" +
                        "Escolha um oponente para atacar", "Turno " + turno, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, alvo, alvo[0]);

                        selecionado.atacar(alvosDisponiveis.get(escolhaAlvo));
                    }
                }

                else if (escolhaDirecao < 4) // movimentação invalida
                    JOptionPane.showMessageDialog(null, "Direção invalida! Escolha um outro personagem" + " para se mover ou mova para uma posição valida.");

            }
        } while (escolhaDirecao == 4 || !tabuleiro.moverPersonagem(selecionado, escolhaDirecao)); // caso selecione voltar ou movimente errado
    }





    public void umJogador(List<personagem> time) {
        JOptionPane.showMessageDialog(null, "Partida um Jogador");
    }





    
    public void doisJogadores(List<personagem> time1, List<personagem> time2) {
        int jogada = 1;
        int turno = 1;
        tabuleiro tabuleiro = new tabuleiro(time1, time2); // inicialização de variaveis
        String jogador;

        while (time1.size() > 0 && time2.size() > 0) {

            // jogada do jogador 1
            if (jogada % 2 == 1) {
                jogador = "Jogador 1";
                rodada(time1, time2, jogador, turno, tabuleiro, time1, time2);
            }

            // jogada do jogador 2
            else {
                jogador = "Jogador 2";
                rodada(time2, time1, jogador, turno, tabuleiro, time1, time2);
            }

            turno = jogada / 2 + 1;
            jogada++;
        }
    }

}
