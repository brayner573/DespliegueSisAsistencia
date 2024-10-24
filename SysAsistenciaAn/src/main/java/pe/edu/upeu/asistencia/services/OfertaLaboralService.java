package pe.edu.upeu.asistencia.services;
import pe.edu.upeu.asistencia.models.OfertaLaboral;

import java.util.List;


public interface OfertaLaboralService {
    OfertaLaboral save(OfertaLaboral ofertaLaboral);
    List<OfertaLaboral> findAll();
    OfertaLaboral findById(Long id);
    OfertaLaboral update(OfertaLaboral ofertaLaboral, Long id);
    void delete(Long id);
}
