package ufjf.dcc025.batalhadewesteros.Interface;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;
import ufjf.dcc025.batalhadewesteros.Secundario.bot;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.lannister;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.stark;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.targaryen;



public class menu {     
    public void menuPrincipal(){    
        String[] opcoes = {"Um jogador", "Dois jogadores", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "Selecione o modo de Jogo:", "Menu principal",  JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[2]);      //caixa de texto com as opçoes

        switch (escolha) {                           //realiza as ações
            case 0:
                menuUmJogador();
                break;
            case 1:
                menuDoisJogadores();
                break;
            default:
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do jogo?", "Confirmação", 
                                JOptionPane.YES_NO_OPTION);
                if(resposta == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "Encerrando...");
                else
                    menuPrincipal();
                break;
        }
    }
    
    private void menuUmJogador(){
        List<personagem> time = new ArrayList<>(); 
        String[] opcoes = {"Stark", "Lannister", "Targaryen", "voltar"};
        int escolha;
        bot oponente = new bot();
        

        for(int i = 1; i <= 3; i++){
            
            escolha = JOptionPane.showOptionDialog(null, "Escolha o " + i +"º personagem do seu time" , "Menu Um Jogador",  
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[3]); //caixa de texto com as opçoes


            if(escolha == 3 && i == 1){        //caso selecione voltar antes de escolher o primeiro personagem
                menuPrincipal();
                return;
            }     
                

            switch (escolha) {                           //realiza as escolhas de personagem e adiciona no time
            case 0:
                stark personagem1 = new stark(null);
                time.add(personagem1);
                oponente.escolherPersonagem();
                break;

            case 1:
                lannister personagem2 = new lannister(null);
                time.add(personagem2);
                oponente.escolherPersonagem();
                break;

            case 2:
                targaryen personagem3 = new targaryen(null);
                time.add(personagem3);
                oponente.escolherPersonagem();
                break;

            default:                                //volta
                i = i-2;                            //o for vai fazer i++ após isso
                time.removeLast();
                oponente.getTimeBot().removeLast();  
                break;
            }
            
            JOptionPane.showMessageDialog(null, "Sua equipe atual é: " + time);
        }
        JOptionPane.showMessageDialog(null, "Iniciando partida...");

        partida partida = new partida();
        partida.umJogador(time, oponente);
    }





    private void menuDoisJogadores(){
        List<personagem> time1 = new ArrayList<>(); 
        List<personagem> time2 = new ArrayList<>(); 
        String[] opcoes = {"Stark", "Lannister", "Targaryen", "voltar"};
        int escolha;
        int j;

        for(int i = 1; i <= 6; i++){
            
            if(i%2 == 1){                     //escolha do jogador 1
                j = i/2 + 1;
                escolha = JOptionPane.showOptionDialog(null, "Jogador 1 escolha o " + j +"º personagem do seu time" , "Menu Dois Jogadores",  
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[3]); //caixa de texto com as opçoes


                if(escolha == 3 && i == 1){        //caso selecione voltar antes de escolher o primeiro personagem
                    menuPrincipal();
                    return;
                }     
                

                switch (escolha) {                           //realiza as escolhas de personagem e adiciona no time
                case 0:
                    stark personagem1 = new stark(null);
                    time1.add(personagem1);
                    break;

                case 1:
                    lannister personagem2 = new lannister(null);
                    time1.add(personagem2);
                    break;

                case 2:
                    targaryen personagem3 = new targaryen(null);
                    time1.add(personagem3);
                    break;

                default:                                //volta
                    i = i-2;                            //o for vai fazer i++ após isso
                    time2.removeLast();
                    break;
                }
                JOptionPane.showMessageDialog(null, "A equipe atual do jogador 1 é " + time1);
            }


            else{                                             //escolha do jogador 2
                j = i/2;                                
                escolha = JOptionPane.showOptionDialog(null, "Jogador 2 escolha o " + j +"º personagem do seu time" , "Menu Dois Jogadores",  
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[3]); //caixa de texto com as opçoes

                switch (escolha) {                           //realiza as escolhas de personagem e adiciona no time
                case 0:
                    stark personagem1 = new stark(null);
                    time2.add(personagem1);
                    break;

                case 1:
                    lannister personagem2 = new lannister(null);
                    time2.add(personagem2);
                    break;

                case 2:                        
                    targaryen personagem3 = new targaryen(null);
                    time2.add(personagem3);
                    break;

                default:                                //volta
                    i = i-2;                            //o for vai fazer i++ após isso
                    time1.removeLast();
                    break;
                }
                JOptionPane.showMessageDialog(null, "A equipe atual do jogador 2 é: " + time2);
            }
        }
        partida partida = new partida();
        partida.doisJogadores(time1, time2);
    }





    public void menuFimPartida(replay partida){
        String[] opcao = {"Ver Replay", "Menu principal", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu Fim de partida ",  JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, opcao, opcao[1]); 
            
        switch (escolha) {
            case 0:
                partida.assistirReplay();
                break;

            case 1:
                menuPrincipal();
                break;

            case 2:
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do jogo?", "Confirmação", 
                JOptionPane.YES_NO_OPTION);
                
                if(resposta == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "Encerrando...");
                    return;
                }
                else
                    menuFimPartida(partida);
                break;

            default:
                break;
        }   
    }
}
