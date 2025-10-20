package ufjf.dcc025.batalhadewesteros;

public interface personagem {
    
   public static final int ATAQUE_BASE = 20;
   public static final int DEFESA_BASE = 10;
   
   
    
    void atacar(personagem alvo);
    void recebeDano(int dano);
    
    String getNome();
    int getVidaAtual();
    int getVidaMaxima();
    int getAlcance();
    
    String getTipo();
    
}
