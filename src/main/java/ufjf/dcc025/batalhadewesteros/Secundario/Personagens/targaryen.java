package ufjf.dcc025.batalhadewesteros.Secundario.Personagens;
import java.util.List;
import java.util.Random;


public class targaryen implements personagem{
    
    private String nome;
    private final int VIDA_MAXIMA = 45; 
    private int vidaAtual;
    private final int alcanceMaximo = 3;
    private int time = 0;
    
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
        
        if(nome != null)
            this.nome = nome;
        this.nome = "targaryen";
        
        vidaAtual = VIDA_MAXIMA;
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
        return VIDA_MAXIMA;
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
