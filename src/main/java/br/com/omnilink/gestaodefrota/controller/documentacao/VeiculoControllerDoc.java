package br.com.omnilink.gestaodefrota.controller.documentacao;

import br.com.omnilink.gestaodefrota.dto.request.VeiculoRequestDTO;
import br.com.omnilink.gestaodefrota.dto.response.VeiculoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VeiculoControllerDoc {

    @Operation(summary = "Cadastra veículo", description = "Cadastra um novo veículo no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Veículo cadastrado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
	ResponseEntity<VeiculoResponseDTO> criar(@Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO);

    @Operation(summary = "Listar todos os veículos", description = "Retorna uma lista de todos os veículos cadastrados no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de veículos retornados com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
	ResponseEntity<List<VeiculoResponseDTO>> listarTodos();

    @Operation(summary = "Buscar veículo por ID", description = "Retorna os detalhes de um veículo específico pelo seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Veículo encontrado e retornado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
	ResponseEntity<VeiculoResponseDTO> buscarPorId(@PathVariable("id") Integer id);

    @Operation(summary = "Atualizar veículo", description = "Atualiza os detalhes de um veículo existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
	ResponseEntity<VeiculoResponseDTO> atualizar(@PathVariable("id") Integer id,
												 @Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO);

    @Operation(summary = "Deletar veículo por ID", description = "Remove um veículo do banco de dados pelo seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Veículo deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
	ResponseEntity<Object> deletar(@PathVariable Integer id);
}