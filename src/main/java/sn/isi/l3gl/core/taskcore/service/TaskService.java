package sn.isi.l3gl.core.taskcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.isi.l3gl.core.taskcore.entity.Task;
import sn.isi.l3gl.core.taskcore.repository.TaskRepository;

/**
 * Service métier pour la gestion des tâches
 * Version 0.0.1-SNAPSHOT : contient uniquement createTask()
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Crée une nouvelle tâche
     * @param task La tâche à créer
     * @return La tâche créée avec son ID
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Les méthodes suivantes seront ajoutées dans les versions ultérieures :
    // - listTasks()         → version 0.1.0-SNAPSHOT
    // - updateStatus()      → version 0.2.0-SNAPSHOT
    // - countCompletedTasks() → version 0.3.0-SNAPSHOT
}
