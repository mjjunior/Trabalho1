package ufjf.dcc025.batalhadewesteros.Secundario;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

public class tabuleiro {
    ///declaracao do mapa do tabuleiro que guarda os personagens em casa posicao, e o tamanho do tabuleiro
    private personagem[][] mapa;
    private final int tamanho = 10;
    private Scanner sc;

    private List<personagem> time1; 
    private List<personagem> time2;

    ///construtor que recebe os times e os posiciona automaticamente no tabuleiro
    public tabuleiro(List<personagem> time1, List<personagem> time2){
        mapa = new personagem[tamanho][tamanho];
        sc = new Scanner(System.in);

        this.time1 = time1; //salvando o time 1
        this.time2 = time2; //salvando o time 2

        posicionarTime(time1, true); //lado direito do tabuleiro
        posicionarTime(time2, false); //lado esquerdo do tabuleiro
    }

    //função que posiciona automaticamente os personagens no tabuleiro
    private void posicionarTime(List<personagem> time, boolean esquerda){
        Random r = new Random();

        for(personagem p : time){
            
            int linha =0;
            int coluna =0;
        ///vai tentar ate achar uma posicao livre
        while(true){
            linha = r.nextInt(tamanho);
            if(esquerda){
                ///aqui to pensando que pro time do jogador vai ter as colunas de 0 a 3
                coluna = r.nextInt(4);
            }
            else {
                ///aqui entra pro bot, que vai ter as colunas de 6 a 9
                coluna = 6 + r.nextInt(4);
            }
            
            //se n entrar entar no if nem no else, a casa ta vazia então saio do while
            if(mapa[linha][coluna] == null){
                break;
                }
            }
            mapa[linha][coluna] = p;
            p.setPosicao(linha, coluna);
        }
    }


    ///função para verificar se a posicao do personagem está dentro do tamanho do tabuleiro
    public boolean dentroLimite(int linha, int coluna){
        if(linha>=0 && linha < tamanho && coluna>=0 && coluna < tamanho)
            return true;
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
                //case "Q":
                    //novaLinha--;
                    //novaColuna--;
                    //break;
                //case "E" :
                   // novaLinha--;
                    //novaColuna++;
                    //break;
                //case "Z":
                  //  novaLinha++;
                    //novaColuna--;
                    //break;
                //case "C":
                  //  novaLinha++;
                    //novaColuna++;
                    //break;
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
                    boolean verificaTime2 = time2.contains(mapa[i][j]);

                    char simbolo = mapa[i][j].getNome().charAt(0);

                    if(verificaTime1){
                        System.out.print(Character.toUpperCase(simbolo) + " ");
                    }
                    else if(verificaTime2){
                        System.out.print(Character.toLowerCase(simbolo) + " ");
                    } else {
                        ///caso o personagem não esteja em nenhum dos dois times
                        System.out.print(simbolo + " ");
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

    ///declaração e implementação de uma função para imprimir os status dos personagens depois da rodada, n tenho certeza se é no tabuleiro, mas ja adiantando.
    ///public void ImprimirStatusPersonagem(){
        ///System.out.println("Status dos personagens: ");
        ///for(int i=0; i<tamanho;i++){
            ///for(int j=0; j<tamanho; j++){
                ///if(mapa[i][j] == null){
                    ///personagem p = mapa[i][j];
                    ///System.out.println(p.getNome() + "Vida: " + p.getVida() + "Ataque: " + p.getAtaque() + "Defesa: " + p.getDefesa());
                //}
            //}
        //}
        //System.out.println();
    //}
   
    ///funcao para limpar o console antes de imprimir novamente(pensando se vai ser necessario ainda)
    ///public static void limparTabuleiro(){
       /// System.out.print("\033[H\033[2J");
        ///System.out.flush();
    ///}
}
