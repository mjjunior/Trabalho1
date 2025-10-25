package ufjf.dcc025.batalhadewesteros.Secundario;
import java.util.Scanner;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

public class tabuleiro {
    ///declaracao do mapa do tabuleiro que guarda os personagens em casa posicao, e o tamanho do tabuleiro
    private personagem[][] mapa;
    private final int tamanho = 10;
    private Scanner sc;

    public tabuleiro(){
        mapa = new personagem[tamanho][tamanho];
        sc = new Scanner(System.in);
    }

    ///função para verificar se a posicao do personagem está dentro do tamanho do tabuleiro
    public boolean dentroLimite(int linha, int coluna){
        if(linha>=0 && linha < tamanho && coluna>=0 && coluna < tamanho)
            return true;
        else
            return false;
    }
    
    ///função para colocar o personagem na posicao, caso ela esteja livre
    public boolean posicionar(personagem p, int linha, int coluna){
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

    ///funcao para fazer a movimentacao do personagem(W,A,S,D,Q,E,Z,C) 
    public void movimentacao(personagem p){
        boolean movimentoValido = false;

        while(!movimentoValido){
            imprimirTabuleiro(); //para mostrar o tabuleiro antes de cada movimento
            System.out.print("Mover " + p.getNome() + " :");
            String direcao = sc.nextLine().toUpperCase();

            int novaLinha = p.getLinha();
            int novaColuna = p.getColuna();

            ///movimentação na ortogonal e depois na diagonal
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
                case "Q":
                    novaLinha--;
                    novaColuna--;
                    break;
                case "E" :
                    novaLinha--;
                    novaColuna++;
                    break;
                case "Z":
                    novaLinha++;
                    novaColuna--;
                    break;
                case "C":
                    novaLinha++;
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
        ///Pensando em um meio de diferenciar os times no tabuleiro
        System.out.println("Time 1 (Letras Maiusculas) vs Time 2 (Letras Minúsculas)");
        System.out.println();

        for(int i=0;i<tamanho;i++){
            ///imprimindo o numero das linhas, para facilitar ver o jogo
            System.out.print(i + " ");
            for(int j=0; j<tamanho; j++){
                ///verifica se a posicao está sem ninguem
                if(mapa[i][j] == null){
                    System.out.print(". ");
                }
                else{ 
                    ///verifico se é do time 1 ou do time 2 para imprimir cada um de maneira diferente
                    boolean verificaTime1 = time1.contains(mapa[i][j]);
                    char simbolo = mapa[i][j].getNome().charAt(0);
                    if(verificaTime1){
                        System.out.print(Character.toUpperCase(simbolo) + " ");
                    }
                    else{
                        System.out.print(Character.toLowerCase(simbolo) + " ");
                    }
                }
            }
            System.out.println();
        }

        ///fazendo a mesma coisa que fiz com as linhas, agora para coluna pra facilitar ver o jogo
        System.out.print(" ");
        for(int j=0; j<tamanho; j++){
            System.out.print(j + " ");
        }

        System.out.println("\n");
    }
   
    ///funcao para limpar o console antes de imprimir novamente(pensando se vai ser necessario ainda)
    ///public static void limparTabuleiro(){
       /// System.out.print("\033[H\033[2J");
        ///System.out.flush();
    ///}
}
