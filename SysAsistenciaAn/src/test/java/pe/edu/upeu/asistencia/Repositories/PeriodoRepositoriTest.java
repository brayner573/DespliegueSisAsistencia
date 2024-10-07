package pe.edu.upeu.asistencia.Repositories;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pe.edu.upeu.asistencia.models.Periodo;
import pe.edu.upeu.asistencia.repositories.PeriodoRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PeriodoRepositoriTest {

 @Autowired
 private PeriodoRepository periodoRepository;

 @Autowired
 private TestEntityManager entityManager;

 @BeforeEach
 public void setUp() {
  Periodo periodo = new Periodo();
  periodo.setNombre("2024-1");
  periodo.setEstado("Activo");
  periodoRepository.save(periodo);
  entityManager.persist(periodo);
 }

 @Order(1)
 @Test
 public void testFindById() {
  Long id = periodoRepository.maxID().get();
  Periodo periodo = periodoRepository.findById(id).get();
  Assertions.assertEquals("2024-1", periodo.getNombre());
 }

 @Order(2)
 @Test
 public void testByName() {
  Periodo periodo = periodoRepository.findByNombre("2024-1").get();
  Assertions.assertEquals("2024-1", periodo.getNombre());
 }

 @Order(3)
 @Test
 public void createPeriodo() {
  Periodo periodo = new Periodo();
  periodo.setNombre("2024-2");
  periodo.setEstado("Activo");
  periodoRepository.save(periodo);
  entityManager.persist(periodo);
  Assertions.assertEquals("2024-2", periodo.getNombre());
 }

 @Order(4)
 @Test
 public void listaPeriodo() {
  List<Periodo> periodos = periodoRepository.findAll();
  Assertions.assertEquals(periodos.size(), 1);
 }

 @Order(5)
 @Test
 public void testUpdatePeriodo() {
  Long id = periodoRepository.maxID().get();
  Periodo periodo = periodoRepository.findById(id).get();
  periodo.setEstado("Desactivo");
  periodoRepository.save(periodo);
  Periodo updated = entityManager.persist(periodo);
  Assertions.assertEquals("Desactivo", updated.getEstado());
 }

 @Order(6)
 @Test
 public void testDeletePeriodo() {
  Long id = periodoRepository.maxID().get();
  periodoRepository.deleteById(id);
  Periodo periodo = periodoRepository.findById(id).orElse(null);
  Assertions.assertNull(periodo);
 }
}