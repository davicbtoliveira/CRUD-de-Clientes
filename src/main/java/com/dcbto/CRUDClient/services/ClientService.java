package com.dcbto.CRUDClient.services;

import com.dcbto.CRUDClient.dtos.ClientDTO;
import com.dcbto.CRUDClient.entities.Client;
import com.dcbto.CRUDClient.repositories.ClientRepository;
import com.dcbto.CRUDClient.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado")
        );
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repo.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();

        copyDtoToEntity(dto, entity);

        entity = repo.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repo.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repo.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!repo.existsById(id)) {
          throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        repo.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
