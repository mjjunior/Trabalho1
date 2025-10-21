package ufjf.dcc025.batalhadewesteros.Secundario;

//import ufjf.dcc025.batalhadewesteros.Personagens.personagem;
import java.util.Scanner;

public class tabuleiro {
    ///declaracao do mapa do tabuleiro que guarda os personagens em casa posicao, e o tamanho do tabuleiro
    private personagem[][] mapa;
    private final int tamanho = 10;

    public tabuleiro(){
        mapa = new personagem[tamanho][tamanho];
    }

    ///função para verificar se a posicao do personagem está dentro do tamanho do tabuleiro
    public boolean dentroLimite(int linha, int coluna){
        if(linha>=0 && linha < tamanho && coluna>=0 && coluna < tamanho)
            return true;
        else
            return false;
    }
    
    ///função para colocar o personagem na posicao, caso ela esteja livre
    public boolean posicicao(personagem p, int linha, int coluna){
        if(dentroLimite(linha, coluna) == true && mapa[linha][coluna] == null){
            mapa[linha][coluna] = p;
            p.setPosicao(linha, coluna);
            return true;
        }
        else
            return false;
    }

    ///funcao para verificar se a posicao está vazia
    public boolean verVazio(int linha, int coluna){
        if(dentroLimite(linha, coluna) == true && mapa[linha][coluna]==null)
            return true;
        else
            return false;
    }

    ///funcao para fazer a movimentacao do personagem(W,A,S,D) 
    public void movimentacao(personagem p){

    }

    ///funcao para imprimir a situacao do tabuleiro no console
    public void imprimirTabuleiro(){
        System.out.println("TABULEIRO:");
        for(int i=0;i<tamanho;i++){
            for(int j=0; j<tamanho; j++){
                if(mapa[i][j] == null){
                    System.out.print(". ");
                }
                else{
                    System.out.print(mapa[i][j].getSimbolo() + " ");
                }
            }
            System.out.println();
        }
    }

    ///funcao para limpar o console antes de imprimir novamente(pensando se vai ser necessario ainda)
    //public static void limparTabuleiro(){

    //}
}
