package br.com.omnilink.gestaodefrota.controller;

import br.com.omnilink.gestaodefrota.controller.documentacao.ClienteControllerDoc;
import br.com.omnilink.gestaodefrota.dto.request.ClienteRequestDTO;
import br.com.omnilink.gestaodefrota.dto.response.ClienteResponseDTO;
import br.com.omnilink.gestaodefrota.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Validated
@Tag(name = "Cliente")
public class ClienteController implements ClienteControllerDoc {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO cliente = clienteService.criar(clienteRequestDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable("id") Integer id,
														@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(id, clienteRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Integer id) {
        clienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
    }

}
