package crud.service;

import crud.model.Persona;
import crud.repository.FileRepository;
import java.util.List;

public class PersonaService {
    private final FileRepository<Persona> repo;

    public PersonaService() {
        repo = new FileRepository<>("personas.dat");
    }

    public void agregar(Persona persona) {
        repo.agregar(persona);
    }

    public List<Persona> listar() {
        return repo.listar();
    }

    public void eliminar(int id) {
        repo.eliminar(id);
    }

    public void actualizar(Persona persona) {
        repo.actualizar(persona);
    }
}
