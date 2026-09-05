package Alumno.demo.controller;

import Alumno.demo.model.Alumno;
import Alumno.demo.service.AlumnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AlumnoControllerTest {

    private MockMvc mockMvc;
    private AlumnoService alumnoService;

    @BeforeEach
    void setUp() {
        alumnoService = new AlumnoService();
        AlumnoController controller = new AlumnoController(alumnoService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testObtenerTodosLosAlumnos() throws Exception {
        mockMvc.perform(get("/api/alumnos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testObtenerAlumnoPorClaveExistente() throws Exception {
        mockMvc.perform(get("/api/alumnos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clave").value(1))
                .andExpect(jsonPath("$.nombre").value("Carlos Mendoza"));
    }

    @Test
    void testObtenerAlumnoPorClaveInexistente() throws Exception {
        mockMvc.perform(get("/api/alumnos/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    void testCrearAlumnoExitoso() throws Exception {
        String json = "{\"nombre\":\"Sofia Ramirez\",\"clave\":10,\"seccion\":\"B\",\"notaFinal\":91.0}";

        mockMvc.perform(post("/api/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clave").value(10))
                .andExpect(jsonPath("$.nombre").value("Sofia Ramirez"))
                .andExpect(jsonPath("$.seccion").value("B"))
                .andExpect(jsonPath("$.notaFinal").value(91.0));
    }

    @Test
    void testCrearAlumnoClaveDuplicada() throws Exception {
        String json = "{\"nombre\":\"Carlos Clon\",\"clave\":1,\"seccion\":\"A\",\"notaFinal\":50.0}";

        mockMvc.perform(post("/api/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje", containsString("Ya existe un alumno con la clave")));
    }

    @Test
    void testActualizarAlumno() throws Exception {
        String json = "{\"nombre\":\"Carlos Mendoza Actualizado\",\"seccion\":\"C\",\"notaFinal\":96.5}";

        mockMvc.perform(put("/api/alumnos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clave").value(1))
                .andExpect(jsonPath("$.nombre").value("Carlos Mendoza Actualizado"))
                .andExpect(jsonPath("$.seccion").value("C"))
                .andExpect(jsonPath("$.notaFinal").value(96.5));
    }

    @Test
    void testEliminarAlumno() throws Exception {
        // Eliminamos el alumno con clave 2 existente
        mockMvc.perform(delete("/api/alumnos/2"))
                .andExpect(status().isNoContent());

        // Verificamos que ya no existe (404)
        mockMvc.perform(get("/api/alumnos/2"))
                .andExpect(status().isNotFound());
    }
}
