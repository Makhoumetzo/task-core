package sn.isi.l3gl.core.taskcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.isi.l3gl.core.taskcore.entity.Task;
import sn.isi.l3gl.core.taskcore.enums.TaskStatus;
import sn.isi.l3gl.core.taskcore.repository.TaskRepository;

import java.util.List;

/**
 * Service métier pour la gestion des tâches
 * Version 0.1.0-SNAPSHOT : ajout de listTasks()
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Crée une nouvelle tâche
     * @param task La tâche à créer
     * @return La tâche créée avec son ID
     * @since 0.0.1-SNAPSHOT
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Retourne toutes les tâches enregistrées
     * @return Liste de toutes les tâches
     * @since 0.1.0-SNAPSHOT
     */
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retourne les tâches filtrées par statut
     * @param status Le statut à filtrer (TODO, IN_PROGRESS, DONE)
     * @return Liste des tâches ayant ce statut
     * @since 0.1.0-SNAPSHOT
     */
    public List<Task> listTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    // Les méthodes suivantes seront ajoutées dans les versions ultérieures :
    // - updateStatus()          → version 0.2.0-SNAPSHOT
    // - countCompletedTasks()   → version 0.3.0-SNAPSHOT
}
