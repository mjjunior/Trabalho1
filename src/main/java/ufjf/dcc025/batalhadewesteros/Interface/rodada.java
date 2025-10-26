package ufjf.dcc025.batalhadewesteros.Interface;

public class rodada {
    
    private final String nomePersonagem;
    private final String movimento;
    private final String acao; 
    private final String tipo;
    
    private final int novaLinha;
    private final int novaColuna;
    
    public rodada(String nome, String movimento, String acao, String tipo , int linha , int coluna){
        
        nomePersonagem = nome;
        this.movimento = movimento;
        this.acao = acao;
        this.tipo = tipo;
        novaLinha = linha;
        novaColuna = coluna;
    }
    
    public String getNomePersonagem() { return nomePersonagem; }
    public String getMovimento() { return movimento; }
    public String getAcao() { return acao; }
    public String getTipo() { return tipo; }
    public int getNovaLinha() { return novaLinha; }
    public int getNovaColuna() { return novaColuna; }
    
    
}
