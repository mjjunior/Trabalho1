package ufjf.dcc025.batalhadewesteros.Secundario;

import java.util.ArrayList;
import java.util.List;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.lannister;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.stark;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.targaryen;

public class bot {       
    
    /// Crriei apenas para o teste e configuracao do replay
   private static List<personagem> timeBot;
    
    public bot(){
        
        timeBot = new ArrayList<>();
        
        stark p1 = new stark("BOT: stark");
        lannister p2 = new lannister("BOT: linnister");
        targaryen p3 = new targaryen("BOT: targaryen");
        
        timeBot.add(p1);
        timeBot.add(p2);
        timeBot.add(p3);
    }
    
    
    ///
    ///Seria interessante usarmos um metodo statico pois nao precisaremos instanciar
    ///um objeto do tipo bot para ultilizar seus metodos
    /// 
    public static List<personagem> getPersonagem(){return timeBot;} 

    ///Mateus:
    ///gente pensei em um possivel  metodo
    ///a gente pode sortear uma casa aleatoria usando random e criar 3 personagens dessa casa, depois posiciona o time do bot automatico e faz ele se mover e atacar sozinho
    /// tipo assim:
    /// imoport java.util.Random;
    /// public class bot{
    ///     private ArrayList<personagem> timeDoBot;
    ///     private String casaDoBot;
    /// }
    /// public bot(){
    ///     this.timeDoBot = new ArrayList<>();
    ///     this.casaDoBot = casaAleatoria();
    ///     inicializaTimeDoBot();
    /// }
    /// ///sorteando a casa do bot
    ///  private String casaAleatoria(){
    ///     Random rand = new Random();
    ///     int escolher = rand.nextInt(3);
    ///     switch(escolha){
    ///          case 0: 
    ///                return "Stark";
    ///                break;
    ///          case 1:
    ///                return "Linnister";
    ///                break;
    ///          default:
    ///               return "Targaryen";
    ///     }
    /// }
    /// aí pensa numa função para inicializar o time do bot, que ainda n pensei kkkk
    /// aqui alguns getters para acessar fora da classe
    /// public ArrayList<personagem> getTimeDoBot(){
    ///     return timeDoBot;
    /// }
    /// public String getCasaDoBot(){
    ///     return casaDoBot;
    /// }
    /// e ate tentar fazer em algo para mostrar as informação do time
}
