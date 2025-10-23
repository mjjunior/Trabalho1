package ufjf.dcc025.batalhadewesteros.Secundario;
import java.util.List;
import java.util.Random;
import  ufjf.dcc025.batalhadewesteros.personagem;

public class linnister implements personagem {
    
    String nome;
    private final int vidaMaxima = 50;
    private int vidaAtual;
    private final int alcanceMaximo = 2;
    
     private void definePosicao(){
        Random gerador = new Random();
        int numAleatorio1, numAleatorio2;
        
        numAleatorio1= gerador.nextInt(10);
        numAleatorio2 = gerador.nextInt(10);
        
        posicao.add(numAleatorio1);
        posicao.add(numAleatorio2);
    }
    
    public linnister(String nome){
        this.nome = nome;
        vidaAtual = vidaMaxima;
        definePosicao();
    }

    @Override
    public void atacar(personagem alvo) {  
        
        float modificadorOfensivo = ATAQUE_BASE * 0.5f;
        alvo.recebeDano(ATAQUE_BASE+modificadorOfensivo);
    }

    @Override
    public void recebeDano(float dano) {
        vidaAtual -= dano - DEFESA_BASE;
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
        return "LANNISTER";
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
