package ufjf.dcc025.batalhadewesteros.Secundario;
import java.util.List;
import java.util.Random;
import  ufjf.dcc025.batalhadewesteros.personagem;
import static ufjf.dcc025.batalhadewesteros.personagem.posicao;


public class targaryen implements personagem{
    
    private final String nome;
    private final int vidaMaxima = 45;
    private int vidaAtual;
    private final int alcanceMaximo = 3;
    
     private void definePosicao(){
        Random gerador = new Random();
        int numAleatorio1, numAleatorio2;
        
        numAleatorio1= gerador.nextInt(10);
        numAleatorio2 = gerador.nextInt(10);
        
        posicao.add(numAleatorio1);
        posicao.add(numAleatorio2);
        
        //informar acao no menu
    }
    
    public targaryen(String nome){
        
        this.nome = nome;
        vidaAtual = vidaMaxima;
        definePosicao();
    }

    @Override
    public void atacar(personagem alvo) {
        alvo.recebeDano(ATAQUE_BASE+DEFESA_BASE);
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
    public List<Integer> getPosicao() {
        return posicao;
    }

    @Override
    public String getTipo() {
        return "TARGARYEN";
    }

    @Override
    public void setPosicao(int linha, int coluna) {
        posicao.add(linha);
        posicao.add(coluna);
        //informar acao no menu
    }
    
}
