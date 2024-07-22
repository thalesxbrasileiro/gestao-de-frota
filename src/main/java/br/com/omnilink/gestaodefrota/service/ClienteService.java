package br.com.omnilink.gestaodefrota.service;

import br.com.omnilink.gestaodefrota.dto.request.ClienteRequestDTO;
import br.com.omnilink.gestaodefrota.dto.response.ClienteResponseDTO;
import br.com.omnilink.gestaodefrota.dto.response.VeiculoResponseDTO;
import br.com.omnilink.gestaodefrota.entity.ClienteEntity;
import br.com.omnilink.gestaodefrota.exceptions.ClienteNotFoundException;
import br.com.omnilink.gestaodefrota.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClienteService {

    private final ClienteRepository ClienteRepository;
    private final ObjectMapper objectMapper;

    public ClienteService(ClienteRepository ClienteRepository, ObjectMapper objectMapper) {
        this.ClienteRepository = ClienteRepository;
        this.objectMapper = objectMapper;
    }

    public ClienteResponseDTO criar(ClienteRequestDTO clienteRequestDTO) {
        ClienteEntity clienteEntity = objectMapper.convertValue(clienteRequestDTO, ClienteEntity.class);
        ClienteResponseDTO clienteResponseDTO = objectMapper.convertValue(ClienteRepository.save(clienteEntity), ClienteResponseDTO.class);
        log.info("Cliente <{}> adicionado com sucesso!", clienteResponseDTO.getNome());
        return clienteResponseDTO;
    }

	public List<ClienteResponseDTO> listarTodos() {
		log.info("Listando todos os clientes...");

		List<ClienteEntity> clientes = ClienteRepository.findAll();

		List<ClienteResponseDTO> clienteResponseDTOs = clientes.stream()
				.map(cliente -> {
			ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

			clienteResponseDTO.setId(cliente.getId());
			clienteResponseDTO.setNome(cliente.getNome());
			clienteResponseDTO.setEmail(cliente.getEmail());
			clienteResponseDTO.setTelefone(cliente.getTelefone());

			List<VeiculoResponseDTO> veiculosDTO = cliente.getVeiculos().stream()
					.map(veiculo -> {
				VeiculoResponseDTO veiculoDTO = new VeiculoResponseDTO();

				veiculoDTO.setId(veiculo.getId());
				veiculoDTO.setMarca(veiculo.getMarca());
				veiculoDTO.setModelo(veiculo.getModelo());
				veiculoDTO.setAno(veiculo.getAno());
				veiculoDTO.setCor(veiculo.getCor());
				veiculoDTO.setPlaca(veiculo.getPlaca());

				return veiculoDTO;
			}).collect(Collectors.toList());

			clienteResponseDTO.setVeiculos(veiculosDTO);
			return clienteResponseDTO;
		}).collect(Collectors.toList());

		log.info("Listando {} clientes", clienteResponseDTOs.size());

		return clienteResponseDTOs;
	}

	public ClienteResponseDTO buscarPorId(Integer id) {
		log.info("Buscando cliente por id: {}", id);
		if (id <= 0) {
			log.info("Erro: O ID não pode ser menor ou igual a zero.");
			throw new IllegalArgumentException("O ID não pode ser menor ou igual a zero.");
		}
		ClienteEntity clienteEntity = ClienteRepository.findById(id).orElseThrow(() -> {
			log.info("Erro: Cliente não encontrado com o ID: {}", id);
			return new ClienteNotFoundException("Cliente não encontrado com o ID: " + id);
		});
		log.info("Cliente encontrado: {}", clienteEntity.toString());
		return objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class);
	}

    public ClienteResponseDTO atualizar(Integer id, ClienteRequestDTO clienteRequestDTO) {
        log.info("Atualizando cliente por id: {}", id);
		if (id <= 0) {
			log.info("Erro: O ID não pode ser menor ou igual a zero.");
			throw new IllegalArgumentException("O ID não pode ser menor ou igual a zero.");
		}
        ClienteEntity clienteEntity = ClienteRepository.findById(id).orElseThrow(() -> {
				log.info("Erro: Cliente não encontrado com o ID: {}", id);
				return new ClienteNotFoundException("Cliente não encontrado com o ID: " + id);
		});

		clienteEntity.setNome(clienteRequestDTO.getNome());
		clienteEntity.setEmail(clienteRequestDTO.getEmail());
		clienteEntity.setTelefone(clienteRequestDTO.getTelefone());
		log.info("Cliente <{}> adicionado com sucesso!", clienteEntity.toString());

        return objectMapper.convertValue(ClienteRepository.save(clienteEntity), ClienteResponseDTO.class);
    }

	public void deletar(Integer id) {
		log.info("Deletando cliente por id: {}", id);

		if (id <= 0) {
			log.info("Erro: O ID não pode ser menor ou igual a zero.");
			throw new IllegalArgumentException("O ID não pode ser menor ou igual a zero. ");
		}
		if (!ClienteRepository.existsById(id)) {
			log.info("Erro: Cliente não encontrado com o ID: {}", id);
			throw new ClienteNotFoundException("Cliente não encontrado com o ID: " + id);
		}
		ClienteRepository.deleteById(id);
	}

}
