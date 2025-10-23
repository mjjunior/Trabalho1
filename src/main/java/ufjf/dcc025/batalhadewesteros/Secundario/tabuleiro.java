package ufjf.dcc025.batalhadewesteros.Secundario;
import java.util.Scanner;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

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
        Scanner sc = new Scanner(System.in);
        boolean movimentoValido = false;

        while(!movimentoValido){
            System.out.print("Mover " + p.getNome() + " :");
            String direcao = sc.nextLine().toUpperCase();

            int novaLinha = p.getLinha();
            int novaColuna = p.getColuna();

            switch(direcao){
                case "W":
                    novaLinha--;
                    break;
                case "S":
                    novaLinha++;
                    break;
                case "A":
                    novaColuna--;
                    break;
                case "D":
                    novaColuna++;
                    break;
                default:
                    System.out.println("Entrada invalida, use W,A,S ou D para se mover.");
                    continue;
            }

        if(!dentroLimite(novaLinha, novaColuna)){
            System.out.println("Movimento para fora dos limites do tabuleiro, tente novamente");
            continue;
        }

        if(!verVazio(novaLinha, novaColuna)){
            System.out.println("A posição está ocupada, tente novamente");
            continue;
        }

        ///se passar por tudo isso, quer dizer que o movimento é valido, então atualiza o tabuleiro
        mapa[p.getLinha()][p.getColuna()] = null;
        mapa[novaLinha][novaColuna] = p;
        p.setPosicao(novaLinha, novaColuna);

        movimentoValido = true;
        System.out.println(p.getNome() + " moveu para (" + novaLinha + ", " + novaColuna + " )");
        }
    }

    ///funcao para imprimir a situacao do tabuleiro no console
    public void imprimirTabuleiro(){
        System.out.println("TABULEIRO:");
        for(int i=0;i<tamanho;i++){
            for(int j=0; j<tamanho; j++){
                if(mapa[i][j] == null){
                    System.out.print(". ");
                }
                else{ //precisampos ajustatr aqui, implementei o personagem com uma lista Posicao - junior;
                    System.out.print(mapa[i][j].getPosicao()+ " ");
                }
            }
            System.out.println();
        }
    }

    ///funcao para limpar o console antes de imprimir novamente(pensando se vai ser necessario ainda)
    //public static void limparTabuleiro(){

    //}
}
