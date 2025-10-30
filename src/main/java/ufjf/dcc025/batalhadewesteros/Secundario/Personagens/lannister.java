package ufjf.dcc025.batalhadewesteros.Secundario.Personagens;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class lannister implements personagem {
    
    private List<Integer> posicao = new ArrayList<>();
    private String nome;
    private final int VIDA_MAXIMA = 50;
    private int vidaAtual;
    private final int alcanceMaximo = 2;
    private int time = 0;
    
    public lannister(String nome){
        
        if(nome != null)
            this.nome = nome;
        this.nome = "linnister";
        
        vidaAtual = VIDA_MAXIMA;
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
        return VIDA_MAXIMA;
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
    
    @Override
     public int getLinha() {
       return posicao.getFirst();
    }

    @Override
    public int getColuna() {
        return posicao.getLast();
    }
    
    @Override
    public String toString(){
        return " Nome: " + nome;
    }

    @Override
    public void setTime(int time) {
        if(time > 0 && time <= 2)
            this.time = time;
    }

    @Override
    public int getTime() {
        return time;
    }
    
}
