package pe.edu.upeu.asistencia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.edu.upeu.asistencia.models.Inscrito;
import pe.edu.upeu.asistencia.services.InscritoService;

@RestController
@RequestMapping("/asis/inscrito")
public class InscritoController {

    @Autowired
    private InscritoService inscritoService;

    private static final Logger logger = LoggerFactory.getLogger(InscritoController.class);

    @GetMapping("/list")
    public ResponseEntity<List<Inscrito>> listActividad() {
        List<Inscrito> actDto = inscritoService.findAll();

        logger.info("Ver Aqui: {}", actDto.get(0).getTipoCui());
        logger.info("Ver Aquix: {}", actDto.get(0).getActividadId().getNombreActividad());

        return ResponseEntity.ok().body(actDto);
    }
}
