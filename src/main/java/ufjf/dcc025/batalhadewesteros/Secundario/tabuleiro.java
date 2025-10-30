package ufjf.dcc025.batalhadewesteros.Secundario;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import ufjf.dcc025.batalhadewesteros.Secundario.Personagens.personagem;

public class tabuleiro {
    ///declaracao do mapa do tabuleiro que guarda os personagens em casa posicao, e o tamanho do tabuleiro
    private personagem[][] mapa;
    private final int tamanho = 10;


    ///construtor que recebe os times e os posiciona automaticamente no tabuleiro
    public tabuleiro(List<personagem> time1, List<personagem> time2){
        mapa = new personagem[tamanho][tamanho];

        //lado direito do tabuleiro
        posicionarTime(time1, true); 

        //lado esquerdo do tabuleiro
        posicionarTime(time2, false); 
    }

    //posiciona automaticamente os personagens no tabuleiro
    private void posicionarTime(List<personagem> time, boolean esquerda){
        Random r = new Random();

        for(personagem p : time){
            
            int linha, coluna;
            do{
                linha = r.nextInt(tamanho);

                //time do jogador, com as coluna de 0 a 3
                if(esquerda){
                    coluna = r.nextInt(4); 

                //time do bot, com as colunas de 6 a 9
                } else {
                    coluna = 6 + r.nextInt(4); 
                }

            } while(mapa[linha][coluna] != null);
            
            mapa[linha][coluna] = p;
            p.setPosicao(linha, coluna);
        }
    }

    ///verificar se a posicao do personagem está dentro do tamanho do tabuleiro
    public boolean dentroLimite(int linha, int coluna){

        if(linha>=0 && linha < tamanho && coluna>=0 && coluna < tamanho)
            return true;

        else
            return false;
    }

    ///pegar o personagem em uma certa posição do tabuleiro
    public personagem getPersonagem(int linha, int coluna){

        //verifico se a posicao em questão está dentro do tabuleiro
        if(!dentroLimite(linha, coluna)){
            return null; //ja que a posição é invalida, retorna null
        }

        //retorno o personagem que está na posição, porque não entrou no if
        else{
            return mapa[linha][coluna]; 
        }
    }

    ///verificar se a posicao está vazia
    public boolean verVazio(int linha, int coluna){
        if(dentroLimite(linha, coluna) == true && mapa[linha][coluna]==null)
            return true;
        else
            return false;
    }

    ///verificar quais personagens estão no alcance
    public List<personagem> verificaAreaAtaque(personagem p){
        List<personagem> alvos = new ArrayList<>();
        
        ///pega o alcance do personagem
        int alcance = p.getAlcance();
        int linhaP = p.getLinha();
        int colunaP = p.getColuna();

        //percorrendo o quadrado de alcance
        for(int i = linhaP - alcance; i <= linhaP + alcance; i++){
            for(int j=colunaP -alcance; j <= colunaP + alcance; j++){
                ///se tiver fora do tabuleiro ignora
                if(!dentroLimite(i,j)){
                    continue;
                }

                //também ignoro a posicao do personagem
                if(i == linhaP && j == colunaP){
                    continue;
                }

                //verifico se tem alguem na posicao
                personagem alvo = mapa[i][j];
                if(alvo != null && alvo.getTime()!= p.getTime()){
                    alvos.add(alvo);
                }
            }
        }

        return alvos;
    }

    ///mover o personagem no tabuleiro
    public boolean verificaMoverPersonagem(personagem p, int direcao){
        int novaLinha = p.getLinha();
        int novaColuna = p.getColuna();

        switch(direcao){
            case 0: //cima
                novaLinha--;
                break;
            case 1: //baixo
                novaLinha++;
                break;
            case 2: //direita
                novaColuna++;
                break;
            case 3: //esquerda
                novaColuna--;
                break; 
            default:
                return false;
        }

        if(!dentroLimite(novaLinha, novaColuna)){
            return false;
        }

        if(!verVazio(novaLinha, novaColuna)){
            return false;
        }

        
        

        return true;
    }

    public void movePersonagem(personagem p, int direcao){
        int novaLinha = p.getLinha();
        int novaColuna = p.getColuna();
        
        if(verificaMoverPersonagem(p, direcao)){
            switch(direcao){
                case 0: //cima
                    novaLinha--;
                    break;
                case 1: //baixo
                    novaLinha++;
                    break;
                case 2: //direita
                    novaColuna++;
                    break;
                case 3: //esquerda
                    novaColuna--;
                    break; 
            }
            mapa[p.getLinha()][p.getColuna()] = null;
            mapa[novaLinha][novaColuna] = p;
            p.setPosicao(novaLinha, novaColuna);
            return;
        }
        System.out.println("ERRO! MOVIMENTO INVALIDO!");

    }



    ///coisas/ideias antigas que podem ser uteis ainda
    /// mantido para ajustes futuros e referencia
    ///funcao para fazer a movimentacao do personagem(W,A,S,D,Q,E,Z,C) 
    // public void movimentacao(personagem p){
    //     boolean movimentoValido = false;

    //     while(!movimentoValido){
    //         imprimirTabuleiro(); //para mostrar o tabuleiro antes de cada movimento
    //         System.out.print("Mover " + p.getNome() + " :");
    //         String direcao = sc.nextLine().toUpperCase();

    //         int novaLinha = p.getLinha();
    //         int novaColuna = p.getColuna();

    //         ///movimentação na ortogonal e depois na diagonal
    //         switch(direcao){
    //             case "W":
    //                 novaLinha--;
    //                 break;
    //             case "S":
    //                 novaLinha++;
    //                 break;
    //             case "A":
    //                 novaColuna--;
    //                 break;
    //             case "D":
    //                 novaColuna++;
    //                 break;
    //             //case "Q":
    //                 //novaLinha--;
    //                 //novaColuna--;
    //                 //break;
    //             //case "E" :
    //                // novaLinha--;
    //                 //novaColuna++;
    //                 //break;
    //             //case "Z":
    //               //  novaLinha++;
    //                 //novaColuna--;
    //                 //break;
    //             //case "C":
    //               //  novaLinha++;
    //                 //novaColuna++;
    //                 //break;
    //             default:
    //                 System.out.println("Entrada invalida, use W,A,S ou D para se mover.");
    //                 continue;
    //         }

    //     if(!dentroLimite(novaLinha, novaColuna)){
    //         System.out.println("Movimento para fora dos limites do tabuleiro, tente novamente");
    //         continue;
    //     }

    //     if(!verVazio(novaLinha, novaColuna)){
    //         System.out.println("A posição está ocupada, tente novamente");
    //         continue;
    //     }

    //     ///se passar por tudo isso, quer dizer que o movimento é valido, então atualiza o tabuleiro
    //     mapa[p.getLinha()][p.getColuna()] = null;
    //     mapa[novaLinha][novaColuna] = p;
    //     p.setPosicao(novaLinha, novaColuna);

    //     movimentoValido = true;
    //     System.out.println(p.getNome() + " moveu para (" + novaLinha + ", " + novaColuna + " )");
    //     }
    // }

    ///funcao para imprimir a situacao do tabuleiro no console
    // public void imprimirTabuleiro(){
    //     System.out.println("TABULEIRO:");

    //     ///Pensando em um meio de diferenciar os times no tabuleiro
    //     System.out.println("Time 1 (Letras Maiusculas) vs Time 2 (Letras Minúsculas)");

    //     System.out.println();

    //     for(int i=0;i<tamanho;i++){
    //         ///imprimindo o numero das linhas, para facilitar ver o jogo
    //         System.out.print(i + " ");
    //         for(int j=0; j<tamanho; j++){
    //             ///verifica se a posicao está sem ninguem
    //             if(mapa[i][j] == null){
    //                 System.out.print(". ");
    //             }
    //             else{ 
    //                 ///verifico se é do time 1 ou do time 2 para imprimir cada um de maneira diferente
    //                 boolean verificaTime1 = time1.contains(mapa[i][j]);
    //                 boolean verificaTime2 = time2.contains(mapa[i][j]);

    //                 char simbolo = mapa[i][j].getNome().charAt(0);

    //                 if(verificaTime1){
    //                     System.out.print(Character.toUpperCase(simbolo) + " ");
    //                 }
    //                 else if(verificaTime2){
    //                     System.out.print(Character.toLowerCase(simbolo) + " ");
    //                 } else {
    //                     ///caso o personagem não esteja em nenhum dos dois times
    //                     System.out.print(simbolo + " ");
    //                 }

    //             }
    //         }
    //         System.out.println();
    //     }

    //     ///fazendo a mesma coisa que fiz com as linhas, agora para coluna pra facilitar ver o jogo
    //     System.out.print(" ");
    //     for(int j=0; j<tamanho; j++){
    //         System.out.print(j + " ");
    //     }

    //     System.out.println("\n");
    // }

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
}
