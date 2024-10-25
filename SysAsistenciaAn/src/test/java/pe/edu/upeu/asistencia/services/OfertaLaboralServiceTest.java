package pe.edu.upeu.asistencia.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import pe.edu.upeu.asistencia.models.OfertaLaboral;
import pe.edu.upeu.asistencia.repositories.OfertaLaboralRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class OfertaLaboralServiceTest {

    @InjectMocks
    private OfertaLaboralServiceImpl ofertaLaboralService;

    @Mock
    private OfertaLaboralRepository ofertaLaboralRepository;

    private OfertaLaboral ofertaLaboral;

    @BeforeEach
    public void setUp() {
        ofertaLaboral = new OfertaLaboral();
        ofertaLaboral.setId(1L);
        ofertaLaboral.setTitulo("Desarrollador Java");
        ofertaLaboral.setDescripcion("Con 3 años de experiencia.");
        ofertaLaboral.setEmpresa("Empresa XYZ");
        ofertaLaboral.setSalario(5000.0);
    }

    @Test
    public void testGetOfertaById() {
       
        given(ofertaLaboralRepository.findById(1L)).willReturn(Optional.of(ofertaLaboral));

        OfertaLaboral result = ofertaLaboralService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Desarrollador Java", result.getTitulo());
    }

    @Test
    public void testCreateOferta() {
        given(ofertaLaboralRepository.save(Mockito.any(OfertaLaboral.class))).willReturn(ofertaLaboral);

        OfertaLaboral result = ofertaLaboralService.save(ofertaLaboral);

        assertNotNull(result);
        assertEquals("Desarrollador Java", result.getTitulo());
    }
}