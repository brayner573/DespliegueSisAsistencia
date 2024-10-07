package pe.edu.upeu.asistencia.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.edu.upeu.asistencia.controllers.PeriodoController;
import pe.edu.upeu.asistencia.dtos.PeriodoDto;
import pe.edu.upeu.asistencia.models.Periodo;
import pe.edu.upeu.asistencia.services.PeriodoServiceImp;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class PeriodoControllerTest {

    @Mock
    private PeriodoServiceImp periodoService;

    @InjectMocks
    private PeriodoController periodoController;

    Periodo periodo;
    List<Periodo> periodos;
    PeriodoDto.PeriodoCrearDto periodoCrearDto;

    @BeforeEach
    public void setUp() {
        periodo = Periodo.builder()
                .id(1L)
                .nombre("2024-1")
                .estado("Activo")
                .build();
        periodos = List.of(periodo,
                Periodo.builder()
                        .id(2L)
                        .nombre("2024-2")
                        .estado("Desactivo")
                        .build()
        );
    }

    @Test
    public void listarPeriodo() {
        // given
        BDDMockito.given(periodoService.findAll()).willReturn(periodos);
        // when
        ResponseEntity<List<Periodo>> lp = periodoController.listPeriodo();
        // then
        Assertions.assertEquals(lp.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(periodos, lp.getBody());
    }

//    @Test
//    public void crearPeriodo() {
//        // given
//        periodoCrearDto = new PeriodoDto.PeriodoCrearDto("2024-1", "Activo"); // Sin el ID
//        BDDMockito.given(periodoService.save(BDDMockito.any(Periodo.class))).willReturn(periodo);
//        // when
//        ResponseEntity<Periodo> response = periodoController.createPeriodo(periodoCrearDto);
//        // then
//        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        Assertions.assertEquals(periodo, response.getBody());
//    }

    @Test
    public void actualizarPeriodo() {
        // given
        periodo.setEstado("Desactivo");
        BDDMockito.given(periodoService.update(BDDMockito.any(Periodo.class), BDDMockito.eq(1L))).willReturn(periodo);
        // when
        ResponseEntity<Periodo> response = periodoController.updatePeriodo(1L, periodo);
        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(periodo.getEstado(), "Desactivo");
    }

    @Test
    public void eliminarPeriodo() {
        // given
        Map<String, Boolean> result = Map.of("deleted", true);
        BDDMockito.given(periodoService.delete(1L)).willReturn(result);
        // when
        ResponseEntity<Map<String, Boolean>> response = periodoController.deletePeriodo(1L);
        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(response.getBody().get("deleted"));
    }
}