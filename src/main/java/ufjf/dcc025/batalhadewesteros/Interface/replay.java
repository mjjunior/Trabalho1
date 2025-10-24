package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.ArrayList;
import java.util.List;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;
import ufjf.dcc025.batalhadewesteros.Secundario.bot;

public class replay {
    
   private List<personagem> playerUm, playerDois;
   private List<rodada> replayPlayerUm, replayPlayerDois;
    
    public replay(){
        
        playerUm = new ArrayList<>();
        playerDois = new ArrayList<>();
        
        replayPlayerUm = new ArrayList<>();
        replayPlayerDois = new ArrayList<>();
    }
    
    ///
    /// OBS:
    /// talvez aqui possamos simplificar para nao ter 2 modos diferentes
    /// podemos ter apenas um modo jogo , onde ele recebe os parametros
    /// como se fosse no modo multiplayer (lista pq, lista p2) e tratamos
    /// essas listas na chamada , tipo , se o modo escolhido foi o single-player
    /// o segundo parametro passado será a lista de personagens do bot
    /// caso multiplayer enão seguimos normal
    ///
    public void jogoSinglePlayer(List<personagem> p){
        
        /// neste caso o Bbot é inicializado como se fosse o player 2  
       playerUm   = new ArrayList<>(p);
       playerDois = new ArrayList<>(bot.getPersonagem());
    }
    
    public void jogoMultiPlayer(List<personagem> p , List<personagem> p2){
         
       playerUm   = new ArrayList<>(p);
       playerDois = new ArrayList<>(p2);
    }
    
    private void salvarRodadaPlayer1(personagem p , String movimento, String acao, int linha , int coluna){
        
        /// Salvando as ações do player 1 
        rodada r = new rodada(p.getNome(), movimento, acao, p.getTipo(), linha, coluna);
        replayPlayerUm.add(r);           
    }
    
    private void salvarRodadaPlayer2(personagem p , String movimento, String acao, int linha , int coluna){
        
        /// Salvando as ações do player 12
        rodada r = new rodada(p.getNome(), movimento, acao, p.getTipo(), linha, coluna);
        replayPlayerDois.add(r);           
    }
    
}
