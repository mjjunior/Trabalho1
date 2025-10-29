package ufjf.dcc025.batalhadewesteros.Secundario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.*;

public class bot {       
    private List<personagem> timeBot;
    private Random rand;

    public bot(){
        timeBot = new ArrayList<>();
        
        rand = new Random();
        inicializarTimeBot();
    }

    //função para inicializar o time do bot com 3 personagens aleatorios
    private void inicializarTimeBot(){
        for(int i=0; i<3; i++){
            personagem p = escolherPersonagem();
            timeBot.add(p);
        }
    }

    //função para escolher um personagem aleatorio para o time do bot
    public personagem escolherPersonagem(){
        int escolha = rand.nextInt(3); //ja que temos 3 personagens, vou usar para o switch, se for 0 é um personagem, 1 outro, ...
        switch(escolha){
            case 0:
                return new stark("Stark");
            case 1:
                return new lannister("Lannister");
            default:
                return new targaryen("Targaryen");
        }
    }

    //função para adicionar um personagem ao time do bot - a gente pode usar no menu ou na partida eu acho, aí ficaria um pouco mais facil acho
    public void adicionarPersonagem(personagem p){
        timeBot.add(p);
    }

    //função para retornar o time do bot
    public List<personagem> getTimeBot(){
        return timeBot;
    }
    //acredito que pro bot ja esteja correto, agora é implmentar uma IA para ele jogar sozinho
    //vou fazer uma bem simples, ela basicamente faz o bot ignorar o personagem morto e proucura o personagem mais perto, aí se tiver do lado atacam e se n tiver anda 1 pra perto
    private personagem encontraMaisPerto(personagem p, List<personagem> inimigos){
        personagem alvo = null;
        int menorDistancia = Integer.MAX_VALUE;

        for(int i=0; i < inimigos.size(); i++){
            personagem inimigo = inimigos.get(i); //pego o inimigo na posicao i

            if(inimigo.getVidaAtual() > 0){ //ignoro os inimigos mortos
                int dist = Math.abs(p.getLinha() - inimigo.getLinha()) + Math.abs(p.getColuna() - inimigo.getColuna()); //distancia entre dois pontos
                    if(dist < menorDistancia){
                        menorDistancia = dist;
                        alvo = inimigo;
                    }
            }
        }

        return alvo;
    }
    
    //agora que sei o alvo mais perto, tem que fazer ele andar ou atacar
    public void jogar(tabuleiro t, List<personagem> inimigos){
        for(int i=0; i < timeBot.size(); i++){
            personagem p = timeBot.get(i);

            if(p.getVidaAtual() <= 0){
                continue; //ignorar personagens mortos
            }

            personagem alvo = encontraMaisPerto(p, inimigos);
            if(alvo == null){
                continue; //nenhum inimigo enontrado 
            }

            int linhaP = p.getLinha();
            int colunaP = p.getColuna();
            int linhaA = alvo.getLinha();
            int colunaA = alvo.getColuna();

            List<personagem> alvos = t.verificaAreaAtaque(p);
            if(!alvos.isEmpty()){
                personagem alvoAtaque = alvos.get(0); //para simplificar ataca o primeiro que encontrar na lista
                p.atacar(alvoAtaque);
                System.out.println(p.getNome() + " atacou " + alvoAtaque.getNome());
                return;

            }

            //se o inimigo ta do lado ele ataca
            // if (Math.abs(linhaP - linhaA) <= 1 && Math.abs(colunaP - colunaA) <= 1){
            //     p.atacar(alvo);
            //     System.out.println(p.getNome() + " atacou " + alvo.getNome());
            //     return;
            // }

            //se n entrou no if, quer dizer que ta mais longe, então anda 1 pra perto
            int dir = -1;
            if(linhaA < linhaP){
                dir =0; //vai pra cima
            }
            else if(linhaA > linhaP){
                dir = 1; // vai pra baixo
            } else if(colunaA > colunaP){
                dir = 2; //vai pra direita
            } else if(colunaA < colunaP) {
                dir = 3; //vai pra esquerda
            }

            t.moverPersonagem(p, dir);
            System.out.println(p.getNome() + " moveu para o seguinte alvo: "+ alvo.getNome() );
            continue;
        }
    }
}

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

