package crud.repository;

import crud.model.Identificable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileRepository<T extends Identificable> {
    private final File archivo;
    private List<T> datos;

    public FileRepository(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        this.datos = cargar();
    }

    @SuppressWarnings("unchecked")
    private List<T> cargar() {
        if (!archivo.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int siguienteId() {
        return datos.stream().mapToInt(Identificable::getId).max().orElse(0) + 1;
    }

    public void agregar(T entidad) {
        entidad.setId(siguienteId());
        datos.add(entidad);
        guardar();
    }

    public List<T> listar() { return new ArrayList<>(datos); }

    public void eliminar(int id) {
        datos.removeIf(e -> e.getId() == id);
        guardar();
    }

    public void actualizar(T entidad) {
        Optional<T> existente = datos.stream().filter(e -> e.getId() == entidad.getId()).findFirst();
        existente.ifPresent(e -> {
            datos.remove(e);
            datos.add(entidad);
            guardar();
        });
    }
}
