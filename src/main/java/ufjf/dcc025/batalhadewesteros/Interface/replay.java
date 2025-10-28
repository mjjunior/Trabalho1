package ufjf.dcc025.batalhadewesteros.Interface;

import java.util.ArrayList;
import java.util.List;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

public class replay {
    
   private final List<String> replayPartida;
    
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
    
    public void exibirReplay(List<personagem> p , List<personagem> p2){
        
         
    }
    
}
