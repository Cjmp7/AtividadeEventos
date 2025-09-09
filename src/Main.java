//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Sistemaeventos sistema = new Sistemaeventos();
        sistema.carregarEventos();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("Bem-vindo ao Sistema de Eventos!");

        // Loop principal do programa
        while (opcao != 0) {
            // Exibir o menu para o usuário
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar Evento");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Gerenciar Participação em Evento");
            System.out.println("5. Listar meus eventos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha


                switch (opcao) {
                    case 1:
                        sistema.cadastrarEvento(scanner);
                        break;
                    case 2:
                        sistema.listarEventos();
                        break;
                    case 3:
                        sistema.cadastrarUsuario(scanner);
                        break;
                    case 4:
                        sistema.gerenciarParticipacao(scanner);
                        break;
                    case 5:
                        sistema.listarMeusEventos(scanner);
                        break;
                    case 0:
                        sistema.salvarEventos();
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                opcao = -1;
            }
        }
        scanner.close();
    }
}