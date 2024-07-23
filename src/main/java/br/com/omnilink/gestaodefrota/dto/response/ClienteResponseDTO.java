package br.com.omnilink.gestaodefrota.dto.response;

import java.io.Serializable;
import java.util.List;

public class ClienteResponseDTO implements Serializable {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;

    private List<VeiculoResponseDTO> veiculos;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Integer id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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

    public List<VeiculoResponseDTO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<VeiculoResponseDTO> veiculos) {
        this.veiculos = veiculos;
    }
}
