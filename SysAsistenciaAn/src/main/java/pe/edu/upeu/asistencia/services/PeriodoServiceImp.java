package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Periodo;
import pe.edu.upeu.asistencia.repositories.PeriodoRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class PeriodoServiceImp implements PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepo;

    private static final String PERIOD_NOT_FOUND = "Periodo not exist with id :";

    @Override
    public Periodo save(Periodo periodo) {
        return periodoRepo.save(periodo);
    }

    @Override
    public List<Periodo> findAll() {
        return periodoRepo.findAll();
    }

    @Override
    public Periodo getPeriodoById(Long id) {
        return periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERIOD_NOT_FOUND + id));
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Periodo periodox = periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERIOD_NOT_FOUND + id));

        periodoRepo.delete(periodox);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Periodo update(Periodo periodo, Long id) {
        Periodo periodox = periodoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PERIOD_NOT_FOUND + id));
        periodox.setNombre(periodo.getNombre());
        periodox.setEstado(periodo.getEstado());

        return periodoRepo.save(periodox);
    }
}