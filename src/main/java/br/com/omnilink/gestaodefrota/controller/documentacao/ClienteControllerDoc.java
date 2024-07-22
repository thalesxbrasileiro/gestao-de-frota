package br.com.omnilink.gestaodefrota.controller.documentacao;

import br.com.omnilink.gestaodefrota.dto.request.ClienteRequestDTO;
import br.com.omnilink.gestaodefrota.dto.response.ClienteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ClienteControllerDoc {

    @Operation(summary = "Criar cliente", description = "Cria um novo cliente no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Acesso negado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO);

    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<List<ClienteResponseDTO>> listarTodos();

    @Operation(summary = "Buscar cliente por ID", description = "Retorna os detalhes de um cliente específico pelo seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado e retornado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable("id") Integer id);

    @Operation(summary = "Atualizar cliente", description = "Atualiza os detalhes de um cliente existente")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable("id") Integer id, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO);

    @Operation(summary = "Deletar cliente por ID", description = "Remove um cliente do banco de dados pelo seu ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Falha inesperada no servidor")
            }
    )
    ResponseEntity<Object> deletar(@PathVariable Integer id);
}