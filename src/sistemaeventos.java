import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;

public class Sistemaeventos {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Evento> eventos;
    // Construtor
    public Sistemaeventos() {
        this.usuarios = new ArrayList<>();
        this.eventos = new ArrayList<>();
    }
    // Métodos para adicionar usuários e eventos
    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }
    public void adicionarEvento(Evento evento) {
        this.eventos.add(evento);
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public ArrayList<Evento> getEventos() {
        return eventos;
    }
    public void cadastrarEvento(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Evento ---");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Categoria (Festa, Show, Esportivo): ");
        String categoria = scanner.nextLine();

        System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();

        // Converte a string para um objeto LocalDateTime
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime horario = LocalDateTime.parse(dataHoraStr, formatter);

            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            // Cria o novo objeto Evento
            Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);

            // Adiciona o novo evento à lista de eventos do sistema
            this.eventos.add(novoEvento);

            System.out.println("Evento cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Formato de data e hora inválido. Por favor, use o formato dd/MM/yyyy HH:mm.");
        }
    }
    public void listarEventos() {
        System.out.println("\n--- Lista de Eventos Cadastrados ---");
        if (this.eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado ainda.");
        } else {
            // Percorre a lista de eventos
            for (Evento evento : this.eventos) {
                System.out.println("------------------------------------");
                System.out.println("Nome: " + evento.getNome());
                System.out.println("Endereço: " + evento.getEndereco());
                System.out.println("Categoria: " + evento.getCategoria());
                System.out.println("Horário: " + evento.getHorario());
                System.out.println("Descrição: " + evento.getDescricao());
            }
            System.out.println("------------------------------------");
        }
    }
    public void salvarEventos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("events.data"))) {
            for (Evento evento : eventos) {
                writer.println(evento.getNome() + ";" + evento.getEndereco() + ";" +
                        evento.getCategoria() + ";" + evento.getHorario() + ";" +
                        evento.getDescricao());
            }
            System.out.println("Eventos salvos com sucesso em events.data!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }
    public void carregarEventos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("events.data"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 5) { // Garante que a linha tem todos os atributos
                    String nome = partes[0];
                    String endereco = partes[1];
                    String categoria = partes[2];
                    LocalDateTime horario = LocalDateTime.parse(partes[3]);
                    String descricao = partes[4];

                    Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
                    eventos.add(evento);
                }
            }
            System.out.println("Eventos carregados com sucesso de events.data!");
        } catch (IOException e) {
            System.out.println("Arquivo de eventos não encontrado. Iniciando com lista vazia.");
        }
    }
    public void cadastrarUsuario(Scanner scanner) {
        System.out.println("\n--- Cadastro de Novo Usuário ---");
        // Lembre-se de implementar a lógica para um ID único no futuro.
        long novoId = usuarios.size() + 1;

        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario novoUsuario = new Usuario(novoId, nome, email);

        // Adiciona o novo usuário à lista do sistema
        this.usuarios.add(novoUsuario);

        System.out.println("Usuário " + nome + " cadastrado com sucesso!");
    }
    public void gerenciarParticipacao(Scanner scanner) {
        System.out.println("\n--- Gerenciar Participação em Eventos ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado. Cadastre um usuário primeiro.");
            return;
        }
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado. Cadastre um evento primeiro.");
            return;
        }

        // Exibe os usuários e eventos para o usuário escolher
        System.out.println("\nUsuários disponíveis:");
        usuarios.forEach(u -> System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome()));

        System.out.println("\nEventos disponíveis:");
        eventos.forEach(e -> System.out.println("Nome: " + e.getNome()));

        // Solicita o ID do usuário
        System.out.print("\nDigite o ID do usuário que irá participar/cancelar: ");
        long idUsuario = scanner.nextLong();
        scanner.nextLine();

        // Encontra o usuário na lista
        Optional<Usuario> usuarioOpt = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst();

        if (usuarioOpt.isEmpty()) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Usuario usuario = usuarioOpt.get();

        // Solicita o nome do evento
        System.out.print("Digite o nome do evento: ");
        String nomeEvento = scanner.nextLine();

        // Encontra o evento na lista
        Optional<Evento> eventoOpt = eventos.stream().filter(e -> e.getNome().equalsIgnoreCase(nomeEvento)).findFirst();

        if (eventoOpt.isEmpty()) {
            System.out.println("Evento não encontrado.");
            return;
        }

        Evento evento = eventoOpt.get();

        // Pergunta a ação
        System.out.print("Deseja participar (P) ou cancelar (C)? ");
        String acao = scanner.nextLine().toUpperCase();

        if (acao.equals("P")) {
            usuario.adicionarEvento(evento);
            System.out.println("Participação confirmada para o evento " + evento.getNome() + "!");
        } else if (acao.equals("C")) {
            usuario.removerEvento(evento);
            System.out.println("Participação cancelada para o evento " + evento.getNome() + ".");
        } else {
            System.out.println("Opção inválida. Nenhuma ação realizada.");
        }
    }
    public void listarMeusEventos(Scanner scanner) {
        System.out.println("\n--- Listar Meus Eventos ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\nUsuários disponíveis:");
        usuarios.forEach(u -> System.out.println("ID: " + u.getId() + ", Nome: " + u.getNome()));

        System.out.print("\nDigite o ID do usuário para listar seus eventos: ");
        long idUsuario = scanner.nextLong();
        scanner.nextLine();

        Optional<Usuario> usuarioOpt = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst();

        if (usuarioOpt.isEmpty()) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Usuario usuario = usuarioOpt.get();

        System.out.println("\nEventos confirmados para " + usuario.getNome() + ":");
        if (usuario.getEventosConfirmados().isEmpty()) {
            System.out.println("Nenhum evento confirmado.");
        } else {
            usuario.getEventosConfirmados().forEach(e -> System.out.println(" - " + e.getNome()));
        }
    }
}
