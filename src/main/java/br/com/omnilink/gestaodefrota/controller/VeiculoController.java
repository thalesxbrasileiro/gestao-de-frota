package br.com.omnilink.gestaodefrota.controller;

import br.com.omnilink.gestaodefrota.controller.documentacao.VeiculoControllerDoc;
import br.com.omnilink.gestaodefrota.dto.request.VeiculoRequestDTO;
import br.com.omnilink.gestaodefrota.dto.response.VeiculoResponseDTO;
import br.com.omnilink.gestaodefrota.service.VeiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
@Tag(name = "Veículo")
public class VeiculoController implements VeiculoControllerDoc {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> criar(@Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO) {
        VeiculoResponseDTO veiculo = veiculoService.criar(veiculoRequestDTO);
        return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorId(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(@PathVariable("id") Integer id,
                                                                                          @Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoService.atualizar(id, veiculoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Integer id) {
        veiculoService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Veículo deletado com sucesso");
    }

}
