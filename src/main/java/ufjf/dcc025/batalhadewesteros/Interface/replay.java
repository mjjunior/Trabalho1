package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class replay {
    
   private final List<String> replayPartida;
   private int cont = 0;
    
    public replay(){
        replayPartida = new ArrayList<>();
    }
    
    ///
    /// OBS:
    /// talvez aqui possamos simplificar para nao ter 2 modos diferentes
    /// podemos ter apenas um modo jogo , onde ele recebe os parametros
    /// como se fosse no modo multiplayer (lista pq, lista p2) e tratamos
    /// essas listas na chamada , tipo , se o modo escolhido foi o single-player
    /// o segundo parametro passado será a lista de personagens do bot
    /// caso multiplayer enão seguimos normal
 
    /// @param rodada
    /// salva a jogada da rodada
    ///
    public void salvar(String rodada){
        replayPartida.add(rodada);
    }
    
    public void exibirReplay(){
             
        String[] opcao = {"Ver Replay", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, "Replay: ", "Menu Replay",  JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, null, opcao, opcao[1]); 
            
           
            if(escolha != 1)
                menuOpcoes();
            else{
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do replay ?", "Confirmação", 
                JOptionPane.YES_NO_OPTION);
                
                if(resposta == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "Encerrando...");
            }
         
    }
    
    private void menuOpcoes(){
        
        String[] opcoes = {"Próximo", "Anterior", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "Selecione uma opção: ", "Menu Replay",  JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[2]); 

        switch (escolha) {
            case 0:
                imprime(cont++);
                break;
            case 1:
                imprime(cont--);
                break;
            default:
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do replay ? ", "Confirmação", 
                                JOptionPane.YES_NO_OPTION);
                if(resposta == JOptionPane.YES_OPTION)
                    JOptionPane.showMessageDialog(null, "Encerrando...");
                break;
        }
        
    }
    
    private void imprime(int i){
        
        if(i >= 0 && i <= replayPartida.size()){
                
            String jogada = replayPartida.get(i);
            JOptionPane.showMessageDialog(null, jogada);
            
            menuOpcoes();
        }
    }

}
