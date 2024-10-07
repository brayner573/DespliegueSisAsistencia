package pe.edu.upeu.asistencia.controllers;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/asis")
public class MessagesController {

    private static final Logger logger = LoggerFactory.getLogger(MessagesController.class);

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages(HttpSession session) {
        String sesionx = (String) session.getAttribute("USER_SESSION");
        logger.info("Session data: {}", sesionx);  // Reemplazo de System.out por logger
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }
}
