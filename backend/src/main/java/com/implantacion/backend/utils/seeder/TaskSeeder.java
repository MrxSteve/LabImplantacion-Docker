package com.implantacion.backend.utils.seeder;

import com.implantacion.backend.models.entities.TaskEntity;
import com.implantacion.backend.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskSeeder implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        if (taskRepository.count() == 0) {
            log.info("Starting to seed tasks...");
            seedTasks();
            log.info("Tasks seeded successfully!");
        } else {
            log.info("Tasks already exist, skipping seeding process");
        }
    }

    private void seedTasks() {
        TaskEntity task1 = TaskEntity.builder()
                .title("Complete project documentation")
                .completed(false)
                .build();

        TaskEntity task2 = TaskEntity.builder()
                .title("Review code changes")
                .completed(false)
                .build();

        TaskEntity task3 = TaskEntity.builder()
                .title("Setup development environment")
                .completed(false)
                .build();

        TaskEntity task4 = TaskEntity.builder()
                .title("Implement user authentication")
                .completed(false)
                .build();

        TaskEntity task5 = TaskEntity.builder()
                .title("Write unit tests")
                .completed(false)
                .build();

        TaskEntity task6 = TaskEntity.builder()
                .title("Deploy to staging server")
                .completed(false)
                .build();

        // Guardar todas las tareas
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        taskRepository.save(task6);

        log.info("Seeded {} tasks", taskRepository.count());
    }
}
