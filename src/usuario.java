import java.util.ArrayList;
public class Usuario {
    private long id;
    private String nome;
    private String email;
    private ArrayList<Evento> eventosConfirmados;

    //construtor
    public Usuario(long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.eventosConfirmados = new ArrayList<>();
    }

    //Métodos (getters) para acessar os atributos
    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public ArrayList<Evento> getEventosConfirmados() {
        return eventosConfirmados;
    }

    // Métodos para gerenciar a lista de eventos
     public void adicionarEvento(Evento evento) {
        this.eventosConfirmados.add(evento);
     }
     public void removerEvento(Evento evento) {
        this.eventosConfirmados.remove(evento);
     }
}