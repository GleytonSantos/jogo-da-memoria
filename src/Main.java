
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    public static final String RESET = "\033[0m";
    public static final String VERMELHO = "\033[91m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha uma opção de 1 a 4: ");
        System.out.println("1. iniciar");
        System.out.println("2. Pontuação participantes");
        System.out.println("3. Regras do jogo");
        System.out.println("4. Sair");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        if(opcao == 1){
            System.out.print("Qual tamanho de tabuleiro?\n1)4 x 4\n2)6 x 6\n3)8 x 8\n4)10 x 10\n");
            int opcao2 = scanner.nextInt();
            scanner.nextLine();
            int tamanho = switch (opcao2){
                case 1 -> 4;
                case 2 -> 6;
                case 3 -> 8;
                case 4 -> 10;
                default -> throw new IllegalArgumentException("Opção inválida");
            };
            System.out.print("Digite o nome do primeiro jogador: ");
            String jogador1 = scanner.nextLine();
            System.out.print("Digite o nome do segundo jogador: ");
            String jogador2 = scanner.nextLine();
            System.out.println(jogador1);
            System.out.println(jogador2);
            String [][] tabela  =  gerarTabela(tamanho);
            jogar(tabela,scanner,jogador1,jogador2);

        }
        else if (opcao == 2) {
            System.out.print("");
        }else if (opcao == 3) {
            System.out.println("1. No início do jogo, a(o) participante deve escolher o tamanho do tabuleiro: 4x4,\r\n" + //
                    "6x6, 8x8 ou 10x10.\r\n" + //
                    "2. Todos os pares de figuras possuem uma cor de fundo: vermelho, azul, amarelo ou\r\n" + //
                    "preto.\r\n" + //
                    "3. Em todo jogo deve existir uma figura de fundo preto.\r\n" + //
                    "4. Em todo jogo metade das figuras devem ter fundo azul e vermelho.\r\n" + //
                    "5. As demais figuras que sobram no jogo devem ter fundo amarelo.\r\n" + //
                    "Vamos exemplificar essas regras iniciais para vocês. Um exemplo sempre é uma boa forma\r\n" + //
                    "de aprender. Imagine que as(os) participantes escolheram o tamanho de tabuleiro 4x4. Nós\r\n" + //
                    "temos o total de 16 cartas, ou seja, 8 pares (ou figuras). Dessas 8 figuras, vamos ter a\r\n" + //
                    "seguinte distribuição:\r\n" + //
                    "1. 4 (quatro) figuras vermelhas e azuis (2 vermelhas e 2 azuis).\r\n" + //
                    "2. 1 (uma) figura preta.\r\n" + //
                    "3. 3 (três) figuras amarelas.\r\n" + //
                    "Bom, vamos para as outras regras? Vamos, né?! Acho que essa parte já deu para entender.\r\n" + //
                    "Lá vamos nós:\r\n" + //
                    "1. Cada participante deve ter atribuído a si uma cor (vermelho ou azul) no início do\r\n" + //
                    "jogo.\r\n" + //
                    "2. Todo participante deve ter um nome registrado. Senão, o nome padrão\r\n" + //
                    "“PARTICIPANTE01” e “PARTICIPANTE02” devem ser atribuídos a cada um das(os)\r\n" + //
                    "participantes.\r\n" + //
                    "3. Cada participante possui uma pontuação atrelada a si.\r\n" + //
                    "4. Se a(o) participante encontrar um par de cartas com fundo amarelo, fatura 1\r\n" + //
                    "ponto.\r\n" + //
                    "5. Se a(o) participante encontrar um par de cartas com o fundo da sua cor, fatura 5\r\n" + //
                    "pontos.\r\n" + //
                    "6. Se a(o) participante encontrar um par de cartas com o fundo da cor de seu\r\n" + //
                    "adversário e errar, perde 2 pontos. Porém, se acertar, ganha apenas 1 ponto.\r\n" + //
                    "7. A(o) participante não pode ter pontuação negativa. Se ela(ele) perder mais\r\n" + //
                    "pontos do que possui, ficará com a pontuação zerada.\r\n" + //
                    "8. Se a(o) participante encontrar uma carta com o fundo preto e errar o seu par,\r\n" + //
                    "perde o jogo, mesmo que tenha a pontuação superior à da(o) outra(o)\r\n" + //
                    "participante. Mas se acertar, ganha o jogo.");
        }

        scanner.close();
    }
    public static String[][] gerarTabela(int tamanho) {
        String[][] tabela = new String[tamanho][tamanho];
        ArrayList<String> pares = new ArrayList<>();

        int numPares = (tamanho * tamanho) / 2;

        int numPretas = 1;
        int numAzuisVermelhas = (numPares) / 4;
        int numAmarelas = (numPares) - numPretas - (numAzuisVermelhas*2);


        for (int i = 1; i <= numPretas; i++) {
            pares.add("\033[30m"+"K" + (i + 1));
        }

        for (int i = 1; i <= numAzuisVermelhas; i++) {
            pares.add("\033[91m" + "R" + (i + 1));
            pares.add("\033[34m"+ "B" + (i + 1));


        }

        for (int i = 1; i <= numAmarelas; i++) {
            pares.add("\033[33m"+"Y" + (i + 1));

        }


        if (pares.size() != numPares) {
            System.out.println("Erro: a lista de pares tem " + pares.size() + " elementos, mas a tabela precisa de " + numPares + " pares.");
        }

        ArrayList<String> cartas = new ArrayList<>();
        for (String carta : pares) {
            cartas.add(carta);
            cartas.add(carta);
        }

        Collections.shuffle(cartas);

        int index = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabela[i][j] = cartas.get(index++);
            }
        }

        return tabela;
    }
    public static String colorirCarta(String carta) {
        String corPadrao = "\u001B[37m";
        String corEscolhida = "\u001B[32m";
        return corEscolhida + carta + corPadrao ;
    }

    public static void exibirTabela(String[][] tabela, boolean[][] revelado) {
        int tamanho = tabela.length;
        System.out.print("      ");
        for (int j = 0; j < tamanho; j++) {
            System.out.print(" [" + (j + 1) + "]  ");
        }
        System.out.println();
        for (int i = 0; i < tamanho; i++) {
            System.out.print(" [" + (i + 1) + "] ");
            for (int j = 0; j < tamanho; j++) {
                if (revelado[i][j]) {
                    System.out.print(" [" + colorirCarta( tabela[i][j])+ "] ");
               } else {
                  System.out.print(" \u001B[32m[??]\u001B[37m ");
               }
            }
            System.out.println();
        }
    }

    public static void jogar(String[][] tabela, Scanner scanner, String jogador1, String jogador2) {
        System.out.println("Jogadores: " + jogador1 + " vs " + jogador2);
        boolean[][] revelado = new boolean[tabela.length][tabela[0].length];
        int pontosJ1 = 0, pontosJ2 = 0;
        boolean turnoJ1 = true;

        while (true) {
            System.out.println("Jogadores: "+ jogador1 + " (" + pontosJ1 + ") " + jogador2+ " (" + pontosJ2 + ") ");
            System.out.println("Vez de: " + (turnoJ1 ? jogador1 : jogador2));
            exibirTabela(tabela,revelado);
            int linha1;
            int coluna1;

            System.out.print("Escolha a primeira carta (linha e coluna, separadas por espaço): ");
            do {
                linha1 = scanner.nextInt() - 1;
                coluna1  = scanner.nextInt() - 1;

                if((linha1 >= tabela.length || linha1 <0) || (coluna1 >= tabela.length || coluna1 <0)) {
                    System.out.println("Digite números entre 1 e " + (tabela.length));
                }
        } while ((linha1 >= tabela.length || linha1 <0) || (coluna1 >= tabela.length || coluna1 <0));



            String[][] tabelaComDestaque = new String[tabela.length][tabela[0].length];
            for (int i = 0; i < tabela.length; i++) {
                for (int j = 0; j < tabela[i].length; j++) {
                    tabelaComDestaque[i][j] = tabela[i][j];
                }
            }
            tabelaComDestaque[linha1][coluna1] = VERMELHO + tabela[linha1][coluna1] + RESET;
            revelado[linha1][coluna1] = true;
            exibirTabela(tabelaComDestaque,revelado);

            System.out.print("Escolha a segunda carta (linha e coluna, separadas por espaço): ");
            int linha2 ;
            int coluna2 ;

            do {
                linha2 = scanner.nextInt() - 1;
                coluna2  = scanner.nextInt() - 1;

                if((linha2 >= tabela.length || linha2 <0) || (coluna2 >= tabela.length || coluna2 <0)) {
                    System.out.println("Digite números entre 1 e " + (tabela.length));
                }
            } while ((linha2 >= tabela.length || linha2 <0) || (coluna2 >= tabela.length || coluna2 <0));


            tabelaComDestaque[linha2][coluna2] = VERMELHO + tabela[linha2][coluna2] + RESET;
            revelado[linha2][coluna2] = true;
            exibirTabela(tabelaComDestaque,revelado);

            if (tabela[linha1][coluna1] != null && tabela[linha2][coluna2] != null &&
                    tabela[linha1][coluna1].equals(tabela[linha2][coluna2])) {
                System.out.println("Par encontrado! Removendo as cartas...");
                tabela[linha1][coluna1] = "  ";
                tabela[linha2][coluna2] = "  ";
                System.out.println("Par encontrado! " + (turnoJ1 ? jogador1 : jogador2) + " ganha 1 ponto.");
                if (turnoJ1){
                    pontosJ1+= 5;
                }
                else{
                    pontosJ2+= 5;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e ) {
                    System.out.println(e);
                };
            } else {
                System.out.println("As cartas não são iguais. Tente novamente.");
                revelado[linha1][coluna1] = false;
                revelado[linha2][coluna2] = false;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e ){
                    System.out.println(e);
                }
                ;
            }
            if (jogoConcluido(tabela)) {
                System.out.println("Parabéns! Você concluiu o jogo!");
                System.out.println(jogador1 + " pontuação final: " + pontosJ1);
                System.out.println(jogador2 + " pontuação final: " + pontosJ2);
                break;
            }
            turnoJ1 = !turnoJ1;
        }
    }

    public static boolean jogoConcluido(String[][] tabela) {
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela[i].length; j++) {
                if (!tabela[i][j].equals("   ")) {
                    return false;
                }
            }
        }
        return true;
    }
}