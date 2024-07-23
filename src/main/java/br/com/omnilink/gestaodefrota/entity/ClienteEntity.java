package br.com.omnilink.gestaodefrota.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENTE")
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
	private String email;
	private String telefone;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<VeiculoEntity> veiculos = new HashSet<>();

    public ClienteEntity() {
    }

    public ClienteEntity(Integer id, String nome, String email, String telefone, Set<VeiculoEntity> veiculos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.veiculos = veiculos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<VeiculoEntity> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Set<VeiculoEntity> veiculos) {
        this.veiculos = veiculos;
    }

	@Override
	public String toString() {
		return "\n\nCliente{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", telefone='" + telefone + '\'' +
				", veiculos=" + veiculos +
				'}';
	}
}
