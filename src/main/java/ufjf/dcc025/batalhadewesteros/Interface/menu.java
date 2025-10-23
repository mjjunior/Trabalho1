package ufjf.dcc025.batalhadewesteros.Interface;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ufjf.dcc025.batalhadewesteros.personagem;
import ufjf.dcc025.batalhadewesteros.Secundario.linnister;
import ufjf.dcc025.batalhadewesteros.Secundario.stark;
import ufjf.dcc025.batalhadewesteros.Secundario.targaryen;

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

        for(int i = 1; i <= 3; i++){
            String[] opcoes = {"Stark", "Lannister", "Targaryen", "voltar"};
            int escolha = JOptionPane.showOptionDialog(null, "Escolha o " + i +"º personagem do seu time" , "Menu Um Jogador",  
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[3]); //caixa de texto com as opçoes


            if(escolha == 3 && i == 1){        //caso selecione voltar antes de escolher o primeiro personagem
                menuPrincipal();
                return;
            }     
                

            switch (escolha) {                           //realiza as escolhas de personagem e adiciona no time
            case 0:
                stark personagem1 = new stark(null);
                time.add(personagem1);
                break;

            case 1:
                linnister personagem2 = new linnister(null);
                time.add(personagem2);
                break;

            case 2:
                targaryen personagem3 = new targaryen(null);
                time.add(personagem3);
                break;

            default:                                //volta
                i = i-2;                            //o for vai fazer i++ após isso
                time.removeLast();
                break;
            }
            JOptionPane.showMessageDialog(null, "Sua equipe atual é" + time);
        }

        partida partida = new partida();
        partida.umJogador(time);
         
    }


    private void menuDoisJogadores(){
        JOptionPane.showMessageDialog(null, "Menu dois Jogador");
    }
}
