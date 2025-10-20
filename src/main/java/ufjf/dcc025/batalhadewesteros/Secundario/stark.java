package ufjf.dcc025.batalhadewesteros.Secundario;
import  ufjf.dcc025.batalhadewesteros.personagem;

public class stark implements personagem{

    
    private String nome;
    private int vidaMaxima = 60;
    private int vidaAtual;
    private int alcanceMaximo = 1; 
    
    public stark(String nome){
        this.nome = nome;
        vidaAtual = vidaMaxima;
    }
    
    
    @Override
    public void atacar(personagem alvo) {
        
        /// Chamar menu inrormando o ataque e exibindo as informações;
        alvo.recebeDano(ATAQUE_BASE);
    }

    @Override
    public void recebeDano(float dano) {
        
        // Opção 
        
        /* 
        float danoReal = dano - DEFESA_BASE;
        float modificadorDefensivo = dano * 0.2f;
        vidaAtual -= danoReal - modificadorDefensivo; 
        */
        vidaAtual -= (dano - DEFESA_BASE) * 0.8f;
        
        ///chamar aqui um menu passando a vida restante e o dano recebido
        
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVidaAtual() {
        return vidaAtual;
    }

    @Override
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    @Override
    public int getAlcance() {
        return alcanceMaximo;
    }

    @Override
    public String getTipo() {
        return "STARK";
    }
   
}
