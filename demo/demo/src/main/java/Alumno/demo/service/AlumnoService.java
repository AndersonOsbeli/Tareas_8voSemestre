package Alumno.demo.service;

import Alumno.demo.model.Alumno;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class AlumnoService {

    private final List<Alumno> listaAlumnos = new CopyOnWriteArrayList<>();
    private final RestTemplate restTemplate;

    public AlumnoService() {
        this.restTemplate = new RestTemplate();
        // Datos de prueba iniciales con campo 'clave'
        listaAlumnos.add(new Alumno("Carlos Mendoza", 1, "A", 88.5));
        listaAlumnos.add(new Alumno("María López", 2, "B", 94.0));
        listaAlumnos.add(new Alumno("Juan Pérez", 3, "A", 75.2));
    }

    // Obtener todos los alumnos almacenados localmente
    public List<Alumno> obtenerTodos() {
        return new ArrayList<>(listaAlumnos);
    }

    // Buscar alumno por clave
    public Optional<Alumno> buscarPorClave(Integer clave) {
        if (clave == null) return Optional.empty();
        return listaAlumnos.stream()
                .filter(a -> clave.equals(a.getClave()))
                .findFirst();
    }

    // Guardar un nuevo alumno (retorna null si ya existe la clave)
    public Alumno guardar(Alumno alumno) {
        if (alumno == null || alumno.getClave() == null) {
            throw new IllegalArgumentException("El alumno y su clave no pueden ser nulos");
        }
        if (buscarPorClave(alumno.getClave()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un alumno con la clave: " + alumno.getClave());
        }
        listaAlumnos.add(alumno);
        return alumno;
    }

    // Actualizar un alumno existente
    public Optional<Alumno> actualizar(Integer clave, Alumno alumnoActualizado) {
        if (clave == null || alumnoActualizado == null) return Optional.empty();

        return buscarPorClave(clave).map(existente -> {
            if (alumnoActualizado.getNombre() != null) {
                existente.setNombre(alumnoActualizado.getNombre());
            }
            if (alumnoActualizado.getSeccion() != null) {
                existente.setSeccion(alumnoActualizado.getSeccion());
            }
            if (alumnoActualizado.getNotaFinal() != null) {
                existente.setNotaFinal(alumnoActualizado.getNotaFinal());
            }
            return existente;
        });
    }

    // Eliminar un alumno por clave
    public boolean eliminar(Integer clave) {
        if (clave == null) return false;
        return listaAlumnos.removeIf(a -> clave.equals(a.getClave()));
    }

    /**
     * Método para consumir una API externa que devuelve una lista de Alumnos en JSON.
     * Ejemplo de uso: consumirApiExterna("https://api.ejemplo.com/alumnos")
     */
    public List<Alumno> consumirApiExterna(String apiUrl) {
        try {
            Alumno[] respuesta = restTemplate.getForObject(apiUrl, Alumno[].class);
            if (respuesta != null) {
                return Arrays.asList(respuesta);
            }
        } catch (Exception e) {
            System.err.println("Error al consumir la API externa: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Método para consumir un único Alumno desde una API externa por ID/URL.
     */
    public Alumno consumirAlumnoIndividual(String apiUrl) {
        try {
            return restTemplate.getForObject(apiUrl, Alumno.class);
        } catch (Exception e) {
            System.err.println("Error al consumir el alumno individual: " + e.getMessage());
            return null;
        }
    }
}

