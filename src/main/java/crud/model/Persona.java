package crud.model;

public class Persona implements Identificable {
    private int id;
    private String nombre;
    private int edad;
    private Genero genero;

    public Persona(String nombre, int edad, Genero genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public Genero getGenero() { return genero; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setGenero(Genero genero) { this.genero = genero; }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + edad + " años, " + genero + ")";
    }
}
