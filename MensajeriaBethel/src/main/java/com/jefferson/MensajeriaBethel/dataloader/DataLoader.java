package com.jefferson.MensajeriaBethel.dataloader;

import com.jefferson.MensajeriaBethel.models.Trabajador;
import com.jefferson.MensajeriaBethel.models.Admin;
import com.jefferson.MensajeriaBethel.repositories.TrabajadorRepository;
import com.jefferson.MensajeriaBethel.repositories.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TrabajadorRepository trabajadorRepository;
    private final AdminRepository adminRepository;

    public DataLoader(TrabajadorRepository trabajadorRepository, AdminRepository adminRepository) {
        this.trabajadorRepository = trabajadorRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------------------------------------------------");
        System.out.println(">>> SEED DATA: Cargando el directorio completo de 15 personas...");

        // Trabajadores
        Trabajador[] trabajadores = {
                new Trabajador("Alvarado Toro Maricela", "maralexfive@hotmail.com", "3213716608", "Coordinadora de Convivencia", null, false),
                new Trabajador("Baron Gonzales Saira Janeth", "sajaba2688@hotmail.com", "3203426622", "Profesor", "Ética y Religión", true),
                new Trabajador("Jefferson Samper", "yeestahe@gmail.com", "3104102962", "Rector", null, false),
                new Trabajador("Delgadillo Pineda Michael Steven", "maiii.colombiaaa@gmail.com", "3226142016", "Profesor", "Inglés", true),
                new Trabajador("Garzon Espinosa Michel Valentina", "michelvalentina1021@outlook.com", "3212849281", "Profesor", "Química", true),
                new Trabajador("Manchego Vargas Luis Miguel", "miguel10-02@hotmail.com", "3155875036", "Profesor", "Educación Física", true),
                new Trabajador("Mendez Chaparro Eliana Andrea", "andreitamendez21@gmail.com", "3204979142", "Profesor", "Robótica", true),
                new Trabajador("Mendoza Rodriguez Edwin Alberto", "Edwinalbertmendoza@gmail.com", "3213851023", "Profesor", "Español", true),
                new Trabajador("Moreno Moreno Angie Sujey", "ansumore06@gmail.com", "3107739952", "Profesor", "Español", true),
                new Trabajador("Orozco Rincon Jehimy Andrea", "andreitas38@hotmail.com", "3114919012", "Profesor", "Matemáticas", true),
                new Trabajador("Pascuas Dussan Eunice", "eunicepascuas@gmail.com", "3005707384", "Coordinadora Académica", null, false),
                new Trabajador("Perez Carreño Yeimy Maricela", "yeimimaricelaperez@hotmail.com", "3214314129", "Coordinadora de Convivencia", null, false),
                new Trabajador("Perez Gutierrez Luz Nelba", "neka17349@hotmail.com", "3105592715", "Tesorería", null, false),
                new Trabajador("Rivas Camargo Cristian David", "cristianrivas876@gmail.com", "3007594711", "Profesor", "Música", true),
                new Trabajador("Robles Hernandez Geini Katerine", "geinykaterine@hotmail.com", "3227648875", "Profesor", "Física", true)
        };

        for (Trabajador t : trabajadores) {
            if (!trabajadorRepository.existsByCorreo(t.getCorreo())) {
                    trabajadorRepository.save(t);
            }
        }

        System.out.println(">>> SEED DATA: Trabajadores cargados (sin duplicados).");

        // Admins
        Admin[] admins = {
                new Admin("admin1", "12345678"),
                new Admin("admin2", "12345678"),
                new Admin("admin3", "12345678")
        };

        for (Admin a : admins) {
            if (!adminRepository.existsByUsuario(a.getUsuario())) {
                adminRepository.save(a);
            }
        }

        System.out.println(">>> SEED DATA: Admins cargados (sin duplicados).");
        System.out.println("-------------------------------------------------------");
    }
}
