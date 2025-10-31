package ufjf.dcc025.batalhadewesteros.Secundario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import ufjf.dcc025.batalhadewesteros.Interface.replay;
import ufjf.dcc025.batalhadewesteros.Interface.partida;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.*;

public class bot { 

    private List<personagem> timeBot;
    private Random rand;

    public bot(){
        timeBot = new ArrayList<>();
        rand = new Random();
        //inicializarTimeBot();
    }

    //função para escolher um personagem aleatorio para o time do bot quando for chamado pelo menu;
    public void escolherPersonagem(){
        int escolha = rand.nextInt(3); //ja que temos 3 personagens, vou usar para o switch, se for 0 é um personagem, 1 outro, ...
        personagem p;
        switch(escolha){
            case 0:
                p = new stark("Stark");
                break;
            case 1:
                p = new lannister("Lannister");
                break;
            default:
                p = new targaryen("Targaryen");
                break;
        }

        p.setTime(2); //o bot é sempre o time 2
        timeBot.add(p);
    }

    //função para retornar o time do bot
    public List<personagem> getTimeBot(){
        return timeBot;
    }
 

    
    private personagem encontraMaisPerto(personagem p, List<personagem> inimigos){
        personagem alvo = null;
        int menorDistancia = Integer.MAX_VALUE;

        for(int i=0; i < inimigos.size(); i++){
            personagem inimigo = inimigos.get(i); //pego o inimigo na posicao i
                int dist = Math.abs(p.getLinha() - inimigo.getLinha()) + Math.abs(p.getColuna() - inimigo.getColuna()); //distancia entre dois pontos
                    if(dist < menorDistancia){
                        menorDistancia = dist;
                        alvo = inimigo;
            }
        }

        return alvo;
    }
    
        
    private personagem escolhePersonagemAtivo(List<personagem> inimigos){
        if (timeBot.isEmpty()) return null;

        // 50% de chance de escolher aleatoriamente, 50% o mais próximo de um inimigo
        if (rand.nextBoolean()) {
            return timeBot.get(rand.nextInt(timeBot.size()));
        } else {
            personagem melhor = null;
            int menorDistancia = Integer.MAX_VALUE;

            for (personagem p : timeBot) {
                personagem alvo = encontraMaisPerto(p, inimigos);
                if (alvo != null) {
                    int dist = Math.abs(p.getLinha() - alvo.getLinha()) + Math.abs(p.getColuna() - alvo.getColuna());
                    if (dist < menorDistancia) {
                        menorDistancia = dist;
                        melhor = p;
                    }
                }
            }

            // se não encontrar nenhum alvo , escolhe aleatório mesmo
            return melhor != null ? melhor : timeBot.get(rand.nextInt(timeBot.size()));
        }
    }

   
    public void jogar(tabuleiro t, List<personagem> inimigos, replay replay, int turno){
        String log;
        StringBuilder sb = new StringBuilder();

        
        personagem p = escolhePersonagemAtivo(inimigos);
        if(p == null) return;

        personagem alvo = encontraMaisPerto(p, inimigos);
        if(alvo == null) return; // nenhum inimigo encontrado

        
        int linhaP = p.getLinha();
        int colunaP = p.getColuna();
        int linhaA = alvo.getLinha();
        int colunaA = alvo.getColuna();

        int dir = -1;
        if(linhaA < linhaP) dir = 0; // cima
        else if(linhaA > linhaP) dir = 1; // baixo
        else if(colunaA > colunaP) dir = 2; // direita
        else if(colunaA < colunaP) dir = 3; // esquerda

        if(t.verificaMoverPersonagem(p, dir)){
            t.movePersonagem(p, dir);

            JOptionPane.showMessageDialog(null, "Oponente moveu " + p.getNome() + " para posição " + p.getPosicao());
            replay.salvaInterface(partida.stringInteface(t, inimigos, getTimeBot()));
            log = "TURNO " + turno + "\nOponente moveu " + p.getNome() + " para posição " + p.getPosicao();
            System.out.println(log);
            sb.append(log + "\n");
            replay.salvaLog(sb.toString());
        }

        
        List<personagem> alvos = t.verificaAreaAtaque(p);
        if(alvos.isEmpty()){
            JOptionPane.showMessageDialog(null, "Não havia nenhum oponente na área do ataque de " + p.getNome());
                        
            replay.salvaInterface(partida.stringInteface(t, inimigos, getTimeBot()));
            log = "Não havia nenhum oponente na área do ataque de " + p.getNome()  + "\n";
            System.out.println(log + "\n \n" );
            sb.append(log);
            replay.salvaLog(sb.toString());
        }


        for(personagem possivel : alvos){
            if(possivel.getTime() != p.getTime()){
                p.atacar(possivel);

                if(possivel.getVidaAtual() <= 0){
                    personagem morto = possivel;
                    JOptionPane.showMessageDialog(null, p.getNome() + " eliminou " + morto.getNome());
                    log = p.getNome() + " eliminou " + morto.getNome() + "\n";
                    inimigos.remove(morto);
                    t.removePersonagem(morto);
                } else {
                    JOptionPane.showMessageDialog(null, p.getNome() + " atacou " + possivel.getNome());
                    log = p.getNome() + " atacou " + possivel.getNome() + "\n";
                }

                replay.salvaInterface(partida.stringInteface(t, inimigos, getTimeBot()));
                System.out.println(log);
                sb.append(log + "\n \n");
                replay.salvaLog(sb.toString());
                break; 
            }
        }
    }
}


///ideias antigas mantidas para referencia:
/// Crriei apenas para o teste e configuracao do replay
   //private static List<personagem> timeBot;
    //public bot(){
       // timeBot = new ArrayList<>();
        
        //stark p1 = new stark("BOT: stark");
        //linnister p2 = new linnister("BOT: linnister");
        //targaryen p3 = new targaryen("BOT: targaryen");
        
        //timeBot.add(p1);
        //timeBot.add(p2);
        //timeBot.add(p3);
    //}
    ///Seria interessante usarmos um metodo statico pois nao precisaremos instanciar
    ///um objeto do tipo bot para ultilizar seus metodos
    /// 
    //public static List<personagem> getPersonagem(){return timeBot;} 

        //função para inicializar o time do bot com 3 personagens aleatorios
    //private void inicializarTimeBot(){
        //for(int i=0; i<3; i++){
           // personagem p = escolherPersonagem();
            //timeBot.add(p);
        //}
    //}
     //função para adicionar um personagem ao time do bot - a gente pode usar no menu ou na partida eu acho, aí ficaria um pouco mais facil acho
    //public void adicionarPersonagem(personagem p){
       // timeBot.add(p);
    //}
// if(!alvos.isEmpty()){
//                 personagem alvoAtaque = alvos.get(0); //para simplificar ataca o primeiro que encontrar na lista
//                 p.atacar(alvoAtaque);
//                 //System.out.println(p.getNome() + " atacou " + alvoAtaque.getNome());
//                 return;
//}
//função para ser usada quando o jogador clica em voltar
    // public void removerUltimoPersonagem(){
    //     if(!timeBot.isEmpty()){
    //         timeBot.removeLast();
    //     }
    // }