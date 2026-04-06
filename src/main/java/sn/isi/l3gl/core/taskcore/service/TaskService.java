package sn.isi.l3gl.core.taskcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.isi.l3gl.core.taskcore.entity.Task;
import sn.isi.l3gl.core.taskcore.enums.TaskStatus;
import sn.isi.l3gl.core.taskcore.repository.TaskRepository;

import java.util.List;

/**
 * Service métier pour la gestion des tâches
 * Version 0.3.0 : toutes les méthodes implémentées
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Crée une nouvelle tâche
     * 
     * @param task La tâche à créer
     * @return La tâche créée avec son ID
     * @since 0.0.1-SNAPSHOT
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Retourne toutes les tâches enregistrées
     * 
     * @return Liste de toutes les tâches
     * @since 0.1.0-SNAPSHOT
     */
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retourne les tâches filtrées par statut
     * 
     * @param status Le statut à filtrer (TODO, IN_PROGRESS, DONE)
     * @return Liste des tâches ayant ce statut
     * @since 0.1.0-SNAPSHOT
     */
    public List<Task> listTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    /**
     * Met à jour le statut d'une tâche existante
     * 
     * @param id     L'identifiant de la tâche
     * @param status Le nouveau statut
     * @return La tâche mise à jour
     * @throws RuntimeException si la tâche n'est pas trouvée
     * @since 0.2.0-SNAPSHOT
     */
    public Task updateStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable avec l'id : " + id));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    /**
     * Compte le nombre de tâches avec le statut DONE
     * 
     * @return Le nombre de tâches terminées
     * @since 0.3.0-SNAPSHOT
     */
    public long countCompletedTasks() {
        return taskRepository.countByStatus(TaskStatus.DONE);
    }
}
