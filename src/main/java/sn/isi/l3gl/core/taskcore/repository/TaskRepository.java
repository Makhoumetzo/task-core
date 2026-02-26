package sn.isi.l3gl.core.taskcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.l3gl.core.taskcore.entity.Task;
import sn.isi.l3gl.core.taskcore.enums.TaskStatus;

import java.util.List;

/**
 * Repository pour les opérations CRUD sur les tâches
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Trouve toutes les tâches ayant un statut donné
     * @param status Le statut recherché
     * @return Liste des tâches avec ce statut
     */
    List<Task> findByStatus(TaskStatus status);

    /**
     * Compte le nombre de tâches avec un statut donné
     * @param status Le statut à compter
     * @return Le nombre de tâches
     */
    long countByStatus(TaskStatus status);
}
