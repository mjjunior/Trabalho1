package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class replay {
    
   private final List<String> replayInterface;
   private final List<String> log;
   private int cont = 0;
    
    public replay(){
        replayInterface = new ArrayList<>();
        log = new ArrayList<>();
    }
    
    public void salvaReplay(String rodada){
        replayInterface.add(rodada);
    }   
    
    public void salvaLog(String loaAtual){
        log.add(loaAtual);
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
        
        if(i >= 0 && i <= replayInterface.size()){
                
            String jogada = replayInterface.get(i);
            JOptionPane.showMessageDialog(null, jogada);
            
            String logPartida = log.get(i);
            System.out.println(jogada);
            
            menuOpcoes();
        }
        else
            System.out.println("Opção invalida! \nEncerrando..."); 
   }

}
