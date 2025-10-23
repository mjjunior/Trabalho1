package ufjf.dcc025.batalhadewesteros.Secundario.Personagens;
import java.util.ArrayList;
import java.util.List;

public interface personagem {
    
   public static final int ATAQUE_BASE = 20;
   public static final int DEFESA_BASE = 10;
   
    List<Integer> posicao = new ArrayList<>(); 
    void atacar(personagem alvo);
    void recebeDano(float dano);
    
    String getNome();
    int getVidaAtual();
    int getVidaMaxima();
    int getAlcance();
    List<Integer> getPosicao();

    /**
     * Definne a posição do personagem no tabuleiro 
     * @param linha
     * @param coluna
     * 
     */
    void setPosicao(int linha, int coluna);
    
    String getTipo();
    
}
