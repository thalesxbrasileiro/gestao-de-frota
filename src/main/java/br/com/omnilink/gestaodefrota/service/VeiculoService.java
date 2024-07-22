package br.com.omnilink.gestaodefrota.service;

import br.com.omnilink.gestaodefrota.dto.request.VeiculoRequestDTO;
import br.com.omnilink.gestaodefrota.dto.response.VeiculoResponseDTO;
import br.com.omnilink.gestaodefrota.entity.ClienteEntity;
import br.com.omnilink.gestaodefrota.entity.VeiculoEntity;
import br.com.omnilink.gestaodefrota.exceptions.ClienteNotFoundException;
import br.com.omnilink.gestaodefrota.exceptions.VeiculoNotFoundException;
import br.com.omnilink.gestaodefrota.repository.ClienteRepository;
import br.com.omnilink.gestaodefrota.repository.VeiculoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VeiculoService {

    private final VeiculoRepository VeiculoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteRepository ClienteRepository;

    public VeiculoService(VeiculoRepository VeiculoRepository, ObjectMapper objectMapper, ClienteRepository ClienteRepository) {
        this.VeiculoRepository = VeiculoRepository;
        this.objectMapper = objectMapper;
        this.ClienteRepository = ClienteRepository;
    }

    public VeiculoResponseDTO criar(VeiculoRequestDTO veiculoRequestDTO) {
        ClienteEntity clienteEntity = ClienteRepository.findById(veiculoRequestDTO.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + veiculoRequestDTO.getClienteId()));

        VeiculoEntity veiculoEntity = objectMapper.convertValue(veiculoRequestDTO, VeiculoEntity.class);
        veiculoEntity.setCliente(clienteEntity); // Associando o cliente ao veículo

        VeiculoResponseDTO veiculoResponseDTO = objectMapper.convertValue(VeiculoRepository.save(veiculoEntity), VeiculoResponseDTO.class);
        log.info("Veículo <{}> adicionado com sucesso!", veiculoResponseDTO.getPlaca());
        return veiculoResponseDTO;
    }

    public List<VeiculoResponseDTO> listarTodos() {
        log.info("Listando todos os veículos...");
        List<VeiculoEntity> veiculoEntity = VeiculoRepository.findAll();

        List<VeiculoResponseDTO> veiculoResponseDTO = veiculoEntity.stream()
                .map(veiculos -> objectMapper.convertValue(veiculos, VeiculoResponseDTO.class))
                .collect(Collectors.toList());
        log.info("Listando {} veículos", veiculoResponseDTO.size());
        return veiculoResponseDTO;
    }

	public VeiculoResponseDTO buscarPorId(Integer id) {
		log.info("Buscando veículo por id: {}", id);
		VeiculoEntity veiculoEntity = VeiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("Veículo não encontrado com o ID: " + id));
		VeiculoResponseDTO veiculoResponseDTO = objectMapper.convertValue(veiculoEntity, VeiculoResponseDTO.class);
		log.info("Veículo encontrado com a placa: {}", veiculoResponseDTO.getPlaca());
		return veiculoResponseDTO;
	}

    public VeiculoResponseDTO atualizar(Integer id, VeiculoRequestDTO veiculoRequestDTO) {
        log.info("Atualizando veículo por id: {}", id);
        VeiculoEntity veiculo = VeiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("Veículo não encontrado com o ID: " + id));

		ClienteEntity cliente = ClienteRepository.findById(veiculoRequestDTO.getClienteId())
				.orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com ID: " + veiculoRequestDTO.getClienteId()));

		veiculo.setMarca(veiculoRequestDTO.getMarca());
        veiculo.setModelo(veiculoRequestDTO.getModelo());
        veiculo.setAno(veiculoRequestDTO.getAno());
        veiculo.setCor(veiculoRequestDTO.getCor());
        veiculo.setPlaca(veiculoRequestDTO.getPlaca());
		veiculo.setCliente(cliente);
		log.info("Veículo de placa {} atualizado com sucesso!", veiculo.getPlaca());

        return objectMapper.convertValue(VeiculoRepository.save(veiculo), VeiculoResponseDTO.class);
    }

	public void deletar(Integer id) {
		log.info("Deletando veículo por id: {}", id);

		boolean exists = VeiculoRepository.existsById(id);
		if (id <= 0) {
			log.info("Erro: O ID não pode ser menor ou igual a zero.");
			throw new IllegalArgumentException("O ID não pode ser menor ou igual a zero. ");
		}
		if (!exists) {
			log.info("Erro: Veículo não encontrado com o ID: {}", id);
			throw new VeiculoNotFoundException("Veículo não encontrado com o ID: " + id);
		}
		VeiculoRepository.deleteById(id);
	}

}
