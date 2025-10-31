package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.List;

import javax.swing.JOptionPane;

import ufjf.dcc025.batalhadewesteros.Secundario.bot;
import ufjf.dcc025.batalhadewesteros.Secundario.tabuleiro;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

public class partida {

    public static String stringInteface(tabuleiro tabuleiro, List<personagem> time1, List<personagem> time2) { // transfora a interface em uma string
        StringBuilder sb = new StringBuilder();

        // CABEÇALHO
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
                    sb.append(" " + inicial + " ");
                }
            }
            sb.append("\n");
        }

        // ESTATÍSTICAS DOS PERSONAGENS
        sb.append("\n===== STATUS DOS TIMES =====\n");
        
        sb.append("Time 1:\n");
        for (personagem p : time1) {
            int linha = p.getLinha();
            int coluna = p.getColuna();
            sb.append(String.format(" - %s (%s): %d HP | Posição: (%d, %d)\n",
                    p.getNome(), p.getTipo(), p.getVidaAtual(), linha, coluna));
        }

        sb.append("\nTime 2:\n");
        for (personagem p : time2) {
            int linha = p.getLinha();
            int coluna = p.getColuna();
            sb.append(String.format(" - %s (%s): %d HP | Posição: (%d, %d)\n",
                    p.getNome(), p.getTipo(), p.getVidaAtual(), linha, coluna));
        }

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

        boolean jogadaValida = false;
        while (!jogadaValida) {
           int escolhaPersonagem = selecionaPersonagemMover(turno, tabuleiro, time1, time2, opcoes, jogador, timePrincipal, timeSecundario);
            
            if(cont == 1){
                replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                log = "TURNO 1";
                System.out.println(log);
                sb.append(log + "\n");
                replay.salvaLog(sb.toString());
            }
            

            // apenas verifica se a escolha foi valida
            if (escolhaPersonagem >= 0 && escolhaPersonagem < time1.size()) {
                selecionado = time1.get(escolhaPersonagem); 

                escolhaDirecao = JOptionPane.showOptionDialog(null, stringInteface(tabuleiro, timePrincipal, timeSecundario) + "\n" + "\n" + jogador +
                                " mova " + selecionado.getNome() + " para uma direção:", "Turno " + turno, JOptionPane.DEFAULT_OPTION, 
                                JOptionPane.QUESTION_MESSAGE, null, direcao, direcao[0]);

                // jogador quis voltar
                if (escolhaDirecao == 4) {
                    cont++;
                    continue;
                }
                    

                if (tabuleiro.verificaMoverPersonagem(selecionado, escolhaDirecao)) {
                    tabuleiro.movePersonagem(selecionado, escolhaDirecao);

                    replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));

                    if(turno > 1){
                        log = "TURNO " + turno + "\n" + jogador +  " moveu " + selecionado.getNome() + " para posição " + selecionado.getPosicao();
                        System.out.println(log);
                        sb.append(log + "\n");
                        replay.salvaLog(sb.toString());
                    }
                    else{
                        log = jogador +  " moveu " + selecionado.getNome() + " para posição " + selecionado.getPosicao();
                        System.out.println(log);
                        sb.append(log + "\n");
                        replay.salvaLog(sb.toString());
                    }
                    

                    List<personagem> alvosDisponiveis = tabuleiro.verificaAreaAtaque(selecionado);

                    if (alvosDisponiveis.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não havia nenhum oponente na área do ataque de " + selecionado.getNome());
                        
                        replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                        log = "Não havia nenhum oponente na área do ataque de " + selecionado.getNome()  + "\n";
                        System.out.println(log);
                        sb.append(log + "\n \n" );
                        replay.salvaLog(sb.toString());
                    }

                    else if (alvosDisponiveis.size() == 1) {
                        selecionado.atacar(alvosDisponiveis.getFirst());

                        if(alvosDisponiveis.getFirst().getVidaAtual() <= 0){
                            personagem morto = alvosDisponiveis.getFirst();
                            JOptionPane.showMessageDialog(null, selecionado.getNome() + " eliminou " + morto.getNome());

                            log = selecionado.getNome() + " eliminou " + morto.getNome() + "\n";
                            time2.remove(morto);
                            tabuleiro.removePersonagem(morto);
                    
                            replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                            System.out.println(log);
                            sb.append(log + "\n \n");
                            replay.salvaLog(sb.toString());
                        }
                        else{
                            JOptionPane.showMessageDialog(null, selecionado.getNome() + " atacou " + alvosDisponiveis.getFirst().getNome());
                            replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                            log = selecionado.getNome() + " atacou " + alvosDisponiveis.getFirst().getNome() + "\n";
                            System.out.println(log);
                            sb.append(log + "\n \n");
                            replay.salvaLog(sb.toString());
                        }
                    }

                    else {
                        String[] alvo = new String[alvosDisponiveis.size()];
                        for (int i = 0; i < alvosDisponiveis.size(); i++)
                            alvo[i] = alvosDisponiveis.get(i).getNome();

                        int escolhaAlvo = JOptionPane.showOptionDialog(null, stringInteface(tabuleiro, time1, time2) + "\n" +
                        "Escolha um oponente para atacar", "Turno " + turno, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, alvo, alvo[0]);

                        selecionado.atacar(alvosDisponiveis.get(escolhaAlvo));

                        //se o ataque elimnou
                        if(alvosDisponiveis.get(escolhaAlvo).getVidaAtual() <= 0){
                            personagem morto = alvosDisponiveis.get(escolhaAlvo);

                            log = selecionado.getNome() + " eliminou " + morto.getNome() + "\n";
                            time2.remove(morto);
                            tabuleiro.removePersonagem(morto);
                    
                            replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                            System.out.println(log);
                            sb.append(log + "\n \n");
                            replay.salvaLog(sb.toString());
                        }
                        else{
                            replay.salvaInterface(stringInteface(tabuleiro, timePrincipal, timeSecundario));
                            log = selecionado.getNome() + " atacou " + alvosDisponiveis.get(escolhaAlvo).getNome() + "\n";
                            System.out.println(log);
                            sb.append(log + "\n \n");
                            replay.salvaLog(sb.toString());
                        }
                    }
                    jogadaValida = true; 
                } else {
                    JOptionPane.showMessageDialog(null, "Direção inválida! Escolha um outro personagem ou posição válida.");
                }
                cont++;
            }
        }
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
                oponente.jogar(tabuleiro, time, replay, jogada);
                
            }   
            jogada++;
        }

        if(time.size() == 0)
            JOptionPane.showMessageDialog(null, "Oponente venceu!");
        else 
            JOptionPane.showMessageDialog(null, "Jogador venceu!");
        
        menu jogo = new menu();
        jogo.menuFimPartida(replay);
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
