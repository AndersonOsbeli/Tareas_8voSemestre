package Alumno.demo.model;

public class Alumno {

    private String nombre;
    private Integer clave;
    private String seccion;
    private Double notaFinal;

    // Constructor vacío (necesario para la deserialización JSON)
    public Alumno() {
    }

    // Constructor con parámetros
    public Alumno(String nombre, Integer clave, String seccion, Double notaFinal) {
        this.nombre = nombre;
        this.clave = clave;
        this.seccion = seccion;
        this.notaFinal = notaFinal;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", clave=" + clave +
                ", seccion='" + seccion + '\'' +
                ", notaFinal=" + notaFinal +
                '}';
    }
}
