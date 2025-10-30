package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.List;

import javax.swing.JOptionPane;

import ufjf.dcc025.batalhadewesteros.Secundario.bot;
import ufjf.dcc025.batalhadewesteros.Secundario.tabuleiro;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

public class partida {

    private String stringInteface(tabuleiro tabuleiro, List<personagem> time1, List<personagem> time2) { // transfora a interface em uma string
        StringBuilder sb = new StringBuilder();

        /// CABEÇALHO
        sb.append("    ");
        for (int j = 0; j < 10; j++) {
            sb.append(j + "  ");
        }
        sb.append("\n");

        // TABULEIRO
        for (int i = 0; i < 10; i++) {
            sb.append(i + "  ");
            for (int j = 0; j < 10; j++) {
                if (tabuleiro.verVazio(i, j)) {
                    sb.append(" _ ");
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

        int escolha = JOptionPane.showOptionDialog(null, stringInteface(tabuleiro, timePrincipal, timeSecundario) + "\n" + "\n" + Jogador
        + " selecione um personagem para mover:", "Turno " + turno, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        return escolha;
    }





    private void rodada(List<personagem> time1, List<personagem> time2, String jogador, int turno, tabuleiro tabuleiro,
                         List<personagem> timePrincipal, List<personagem> timeSecundario, replay replay) 
        {

        String[] direcao = { "Cima", "Baixo", "Direita", "Esquerda", "Voltar" };
        int escolhaDirecao = 0;
        personagem selecionado = null;
        String log;
        StringBuilder sb = new StringBuilder();
        int cont = turno;

        String[] opcoes = new String[time1.size()];
        for (int i = 0; i < time1.size(); i++) // inicializa as opções de movimento
            opcoes[i] = time1.get(i).getNome();

        
        /// O do While serve para poder ter a opção de voltar e escolher um outro personagem para mover
        do {
            int escolhaPersonagem = selecionaPersonagemMover(turno, tabuleiro, time1, time2, opcoes, jogador, timePrincipal, timeSecundario);
            
            if(cont == 1){
                replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                log = "TURNO 1";
                System.out.println(log);
                sb.append(log);
                replay.salvaLog(sb.toString());
            }
            

            // apenas verifica se a escolha foi valida
            if (escolhaPersonagem >= 0 && escolhaPersonagem < time1.size()) {
                selecionado = time1.get(escolhaPersonagem); 

                escolhaDirecao = JOptionPane.showOptionDialog(null, stringInteface(tabuleiro, timePrincipal, timeSecundario) + "\n" + "\n" + jogador +
                                " mova " + selecionado.getNome() + " para uma direção:", "Turno " + turno, JOptionPane.DEFAULT_OPTION, 
                                JOptionPane.QUESTION_MESSAGE, null, direcao, direcao[0]);

                if (escolhaDirecao < 4 && tabuleiro.moverPersonagem(selecionado, escolhaDirecao)) { // se entrou aqui é pq está tudo e certo e vai encerrar o loop
                    replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));

                    if(turno > 1){
                        log = "TURNO " + turno + "\n" + jogador +  " moveu " + selecionado.getNome() + " para posição " + selecionado.getPosicao();
                        System.out.println(log);
                        sb.append(log);
                        replay.salvaLog(sb.toString());
                    }
                    else{
                        log = jogador +  " moveu " + selecionado.getNome() + " para posição " + selecionado.getPosicao();
                        System.out.println(log);
                        sb.append(log);
                        replay.salvaLog(sb.toString());
                    }
                    

                    List<personagem> alvosDisponiveis = tabuleiro.verificaAreaAtaque(selecionado);

                    if (alvosDisponiveis.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não havia nenhum oponente na área do ataque de " + selecionado.getNome());
                        
                        replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                        log = "Não havia nenhum oponente na área do ataque de " + selecionado.getNome()  + "\n";
                        System.out.println(log);
                        sb.append(log);
                        replay.salvaLog(sb.toString());
                    }

                    else if (alvosDisponiveis.size() == 1) {
                        selecionado.atacar(alvosDisponiveis.getFirst());
                        JOptionPane.showMessageDialog(null, selecionado.getNome() + " atacou " + alvosDisponiveis.getFirst().getNome());
                        
                        replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                        log = selecionado.getNome() + " atacou " + alvosDisponiveis.getFirst().getNome() + "\n";
                        System.out.println(log);
                        sb.append(log);
                        replay.salvaLog(sb.toString());
                    }

                    else {
                        String[] alvo = new String[alvosDisponiveis.size()];
                        for (int i = 0; i < alvosDisponiveis.size(); i++)
                            opcoes[i] = time1.get(i).getNome();

                        int escolhaAlvo = JOptionPane.showOptionDialog(null, stringInteface(tabuleiro, time1, time2) + "\n" +
                        "Escolha um oponente para atacar", "Turno " + turno, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, alvo, alvo[0]);

                        selecionado.atacar(alvosDisponiveis.get(escolhaAlvo));

                        replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                        log = selecionado.getNome() + " atacou " + alvosDisponiveis.get(escolhaAlvo).getNome() + "\n";
                        System.out.println(log);
                        sb.append(log);
                        replay.salvaLog(sb.toString());
                    }
                }

                else if (escolhaDirecao < 4) // movimentação invalida
                    JOptionPane.showMessageDialog(null, "Direção invalida! Escolha um outro personagem" + " para se mover ou mova para uma posição valida.");

                cont++;    
            }
        } while (escolhaDirecao == 4 || !tabuleiro.moverPersonagem(selecionado, escolhaDirecao)); // caso selecione voltar ou movimente errado
        return;
    }





    public void umJogador(List<personagem> time, bot oponente) {
        int jogada = 1;
        tabuleiro tabuleiro = new tabuleiro(time, oponente.getTimeBot()); // inicialização de variaveis
        String jogador;
        replay replay = new replay();

        while (time.size() > 0 && oponente.getTimeBot().size() > 0) {

            // jogada do jogador 1
            if (jogada % 2 == 1) {
                jogador = "Jogador 1";
                rodada(time, oponente.getTimeBot(), jogador, jogada, tabuleiro, time, oponente.getTimeBot(), replay);
            }

            // jogada do jogador 2
            else {
                oponente.jogar(tabuleiro, time);
                
            }

            
            jogada++;
        }
    }






    public void doisJogadores(List<personagem> time1, List<personagem> time2) {
        int jogada = 1;
        tabuleiro tabuleiro = new tabuleiro(time1, time2); // inicialização de variaveis
        String jogador;
        replay replay = new replay();

        while (time1.size() > 0 && time2.size() > 0) {

            // jogada do jogador 1
            if (jogada % 2 == 1) {
                jogador = "Jogador 1";
                rodada(time1, time2, jogador, jogada, tabuleiro, time1, time2, replay);
            }

            // jogada do jogador 2
            else {
                jogador = "Jogador 2";
                rodada(time2, time1, jogador, jogada, tabuleiro, time1, time2, replay);
            }

            
            jogada++;
        }

        if(time1.size() == 0)
            JOptionPane.showMessageDialog(null, "Jogador 2 venceu!");
        else 
            JOptionPane.showMessageDialog(null, "Jogador 1 venceu!");
        
        menu jogo = new menu();
        jogo.menuFimPartida(replay);

        
    }

}
