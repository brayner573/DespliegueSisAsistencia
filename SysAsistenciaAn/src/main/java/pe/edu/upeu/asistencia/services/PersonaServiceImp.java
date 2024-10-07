package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Persona;
import pe.edu.upeu.asistencia.repositories.PersonaRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository entidadRepo;

    private static final String PERSON_NOT_FOUND = "Persona not exist with id :";

    @Override
    public Persona save(Persona entidad) {
        return entidadRepo.save(entidad);
    }

    @Override
    public List<Persona> findAll() {
        return entidadRepo.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Persona entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERSON_NOT_FOUND + id));
        entidadRepo.delete(entidadx);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Persona geEntidadById(Long id) {
        return entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERSON_NOT_FOUND + id));
    }

    @Override
    public Persona update(Persona entidad, Long id) {
        Persona entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERSON_NOT_FOUND + id));
        entidadx.setCelular(entidad.getCelular());
        entidadx.setTipo(entidad.getTipo());
        entidadx.setEstado(entidad.getEstado());
        return entidadRepo.save(entidadx);
    }

}
