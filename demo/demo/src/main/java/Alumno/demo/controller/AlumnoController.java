package Alumno.demo.controller;

import Alumno.demo.model.Alumno;
import Alumno.demo.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend (React, Angular, Postman, etc.)
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // Endpoint GET para obtener la lista de alumnos
    // http://localhost:8080/api/alumnos
    @GetMapping
    public ResponseEntity<List<Alumno>> obtenerAlumnos() {
        List<Alumno> alumnos = alumnoService.obtenerTodos();
        return ResponseEntity.ok(alumnos);
    }

    // Endpoint GET para obtener un alumno por su clave
    // http://localhost:8080/api/alumnos/1
    @GetMapping("/{clave}")
    public ResponseEntity<?> obtenerAlumnoPorClave(@PathVariable Integer clave) {
        Optional<Alumno> alumno = alumnoService.buscarPorClave(clave);
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "No se encontró ningún alumno con la clave: " + clave);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint POST para crear un nuevo alumno
    // Body JSON: { "nombre": "Ana Gomez", "clave": 4, "seccion": "C", "notaFinal": 92.5 }
    @PostMapping
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno alumno) {
        try {
            Alumno nuevoAlumno = alumnoService.guardar(alumno);
            return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Endpoint PUT para actualizar un alumno existente por clave
    // Body JSON: { "nombre": "Carlos Mendoza Editado", "seccion": "B", "notaFinal": 95.0 }
    @PutMapping("/{clave}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer clave, @RequestBody Alumno alumno) {
        Optional<Alumno> actualizado = alumnoService.actualizar(clave, alumno);
        if (actualizado.isPresent()) {
            return ResponseEntity.ok(actualizado.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "No se encontró ningún alumno con la clave: " + clave + " para actualizar");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint DELETE para eliminar un alumno por clave
    @DeleteMapping("/{clave}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer clave) {
        boolean eliminado = alumnoService.eliminar(clave);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "No se encontró ningún alumno con la clave: " + clave + " para eliminar");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Endpoint GET para consumir una API externa pasando su URL
    // http://localhost:8080/api/alumnos/consumir-externa?url=https://api.ejemplo.com/alumnos
    @GetMapping("/consumir-externa")
    public ResponseEntity<List<Alumno>> consumirApiExterna(@RequestParam String url) {
        List<Alumno> resultado = alumnoService.consumirApiExterna(url);
        return ResponseEntity.ok(resultado);
    }
}

