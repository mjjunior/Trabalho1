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
    
    //public void removeInterface(){
    //    interfac.removeLast();
    //}

    public void salvaLog(String logAtual){
        log.add(logAtual);
    }

    //public void removeLog(){
     //   log.removeLast();
    //}
    
    
        public void assistirReplay(){

            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();      //limpa o console
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } catch (Exception e) {
                for (int i = 0; i < 50; i++) System.out.println();
            }


            System.out.println(log.get(cont));
            String[] opcoes = {"Ir para o inicio", "Próximo", "Anterior", "Pular para o final", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, interfac.get(cont) + "\n" + "\n" + "Selecione uma opção: ", "Menu Replay",  JOptionPane.DEFAULT_OPTION, 
                            JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[4]);

            

            switch (escolha) {
                case 0:
                    cont = 0;
                    assistirReplay();
                    break;

                case 1:
                    cont++;
                    assistirReplay();
                    break;
                
                case 2:
                    cont--;
                    assistirReplay();
                    break;
                
                case 3:
                    cont = interfac.size() - 1;
                    assistirReplay();
                    break;

                default:
                    menu menu = new menu();
                    menu.menuFimPartida(this);
                    break;
            }
            
        }
}
    
