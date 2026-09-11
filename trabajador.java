// Trabajador.java
public class Trabajador {
    private Long id;
    private String nombre;
    private String categoria;
    private Double latitud;
    private Double longitud;
    private Double radioCobertura; // En km o metros para filtrar por distancia

    public Trabajador() {}

    public Trabajador(Long id, String nombre, String categoria, Double latitud, Double longitud, Double radioCobertura) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radioCobertura = radioCobertura;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public Double getRadioCobertura() { return radioCobertura; }
    public void setRadioCobertura(Double radioCobertura) { this.radioCobertura = radioCobertura; }
}
