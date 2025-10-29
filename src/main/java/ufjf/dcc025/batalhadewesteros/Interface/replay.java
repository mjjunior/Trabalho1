package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class replay {
    
   private final List<String> interfac;
   private final List<String> log;
   private int cont;
                                                                 //IMPLEMENTAR PULAR PARA O COMEÇO E PRO FINAL
                                                                 //IMPRIMIR AO CHAMAR A FUNÇÃO ASSISTIR REPLAY
    public replay(){
        interfac = new ArrayList<>();
        log = new ArrayList<>();
        cont = 0;
    }
    
    public void salvaInterface(String interfaceAtual){
        interfac.add(interfaceAtual);
    }   
    
    public void salvaLog(String logAtual){
        log.add(logAtual);
    }
    
    
    public void assistirReplay(){
        
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
        
        if(i >= 0 && i <= interfac.size()){
                
            String jogada = interfac.get(i);
            JOptionPane.showMessageDialog(null, jogada);
            
            String logPartida = log.get(i);
            System.out.println(jogada);
            
            assistirReplay();
        }
        else
            System.out.println("Opção invalida! \nEncerrando..."); 
   }

}
