
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    public static final String RESET = "\033[0m";  // Reseta a cor
    public static final String VERMELHO = "\033[91m";  // Cor branca
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
        for (int i = 0; i < numPares; i++) {
            String letra = String.valueOf((char) ('A' + i));
            pares.add(letra + "1");
            pares.add(letra + "2");
        }

        Collections.shuffle(pares);

        int index = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabela[i][j] = pares.get(index++);
            }
        }

        return tabela;
    }
    public static String colorirCarta(String carta) {
        String corPadrao = "\u001B[37m"; // Cor padrão (branco)
        String corEscolhida = "\u001B[32m"; // Verde para a carta escolhida (pode mudar para qualquer cor desejada)

        return corEscolhida + carta + corPadrao;
    }

    public static void exibirTabela(String[][] tabela) {
        int tamanho = tabela.length;
        System.out.print("   ");
        for (int j = 0; j < tamanho; j++) {
            System.out.print(" " + (j + 1) + "  ");
        }
        System.out.println();
        for (int i = 0; i < tamanho; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tamanho; j++) {
                System.out.print(" " + colorirCarta(tabela[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public static void jogar(String[][] tabela, Scanner scanner, String jogador1, String jogador2) {
        System.out.println("Jogadores: " + jogador1 + " vs " + jogador2);
        while (true) {
            exibirTabela(tabela);

            System.out.print("Escolha a primeira carta (linha e coluna, separadas por espaço): ");
            int linha1 = scanner.nextInt() - 1;
            int coluna1 = scanner.nextInt() - 1;

            String[][] tabelaComDestaque = new String[tabela.length][tabela[0].length];
            for (int i = 0; i < tabela.length; i++) {
                for (int j = 0; j < tabela[i].length; j++) {
                    tabelaComDestaque[i][j] = tabela[i][j];  // Copia os valores da tabela original
                }
            }
            tabelaComDestaque[linha1][coluna1] = VERMELHO + tabela[linha1][coluna1] + RESET;  // Destaca a carta escolhida
            exibirTabela(tabelaComDestaque);

            System.out.print("Escolha a segunda carta (linha e coluna, separadas por espaço): ");
            int linha2 = scanner.nextInt() - 1;
            int coluna2 = scanner.nextInt() - 1;

            tabelaComDestaque[linha2][coluna2] = VERMELHO + tabela[linha2][coluna2] + RESET;  // Destaca a carta escolhida
            exibirTabela(tabelaComDestaque);

            if (tabela[linha1][coluna1] != null && tabela[linha2][coluna2] != null &&
                    tabela[linha1][coluna1].equals(tabela[linha2][coluna2])) {
                System.out.println("Par encontrado! Removendo as cartas...");
                tabela[linha1][coluna1] = "   ";
                tabela[linha2][coluna2] = "   ";
            } else {
                System.out.println("As cartas não são iguais. Tente novamente.");
            }

            if (jogoConcluido(tabela)) {
                System.out.println("Parabéns! Você concluiu o jogo!");
                break;
            }
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