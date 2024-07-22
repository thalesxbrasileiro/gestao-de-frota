package br.com.omnilink.gestaodefrota.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class VeiculoRequestDTO {

    @NotBlank(message = "A marca não pode ser nula ou vazia")
    @Size(max = 100, message = "A marca deve ter no máximo 100 caracteres")
    private String marca;

    @NotBlank(message = "O modelo não pode ser nulo ou vazio")
    @Size(max = 50, message = "O modelo deve ter no máximo 50 caracteres")
    private String modelo;

	@NotBlank(message = "O ano não pode ser nulo ou vazio")
	@Pattern(regexp = "\\d{4}", message = "O ano deve seguir o seguinte formato: XXXX")
    private String ano;

    @NotBlank(message = "A cor não pode ser nula ou vazia")
    @Size(max = 30, message = "A cor deve ter no máximo 30 caracteres")
    private String cor;

    @NotBlank(message = "A palca não pode ser nula ou vazia")
    @Size(max = 7, message = "A placa deve ter no máximo 7 caracteres")
    private String placa;

    @JsonProperty("cliente_id")
    private Integer clienteId;

    public VeiculoRequestDTO() {
    }

    public VeiculoRequestDTO(String marca, String modelo, String ano, String cor, String placa, Integer clienteId) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.placa = placa;
        this.clienteId = clienteId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
