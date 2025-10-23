package ufjf.dcc025.batalhadewesteros.Secundario;
import java.util.List;
import java.util.Random;
import  ufjf.dcc025.batalhadewesteros.personagem;

public class stark implements personagem{

    
    private final String nome;
    private final int vidaMaxima = 60;
    private int vidaAtual;
    private final int alcanceMaximo = 1; 
    
    private void definePosicao(){
        Random gerador = new Random();
        int numAleatorio1, numAleatorio2;
        
        numAleatorio1= gerador.nextInt(10);
        numAleatorio2 = gerador.nextInt(10);
        
        posicao.add(numAleatorio1);
        posicao.add(numAleatorio2);
    }
    
    public stark(String nome){
        this.nome = nome;
        vidaAtual = vidaMaxima;
        definePosicao();
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

    @Override
    public List<Integer> getPosicao() {
       return posicao;
    }

    @Override
    public void setPosicao(int linha, int coluna) {
        posicao.add(linha);
        posicao.add(coluna);
        //informar acao no menu
    }
   
}
