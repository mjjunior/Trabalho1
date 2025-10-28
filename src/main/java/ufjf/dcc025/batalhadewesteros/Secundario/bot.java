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

    //função para inicializar o time do bot
    private void inicializarTimeBot(){
        personagem p = escolherPersonagem();
        timeBot.add(p);
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
}
