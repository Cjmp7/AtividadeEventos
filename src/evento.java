import java.time.LocalDateTime;
public class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;
    // Construtor
    public Evento(String nome, String endereço, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }
    // Métodos (getters) para acessar os atributos
    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getCategoria() {
        return categoria;
    }
    public LocalDateTime getHorario() {
        return horario;
    }
    public String getDescricao() {
        return descricao;
    }
}