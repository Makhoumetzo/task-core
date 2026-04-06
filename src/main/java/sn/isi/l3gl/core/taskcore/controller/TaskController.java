package sn.isi.l3gl.core.taskcore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.core.taskcore.entity.Task;
import sn.isi.l3gl.core.taskcore.enums.TaskStatus;
import sn.isi.l3gl.core.taskcore.service.TaskService;

import java.util.List;
import java.util.Map;

/**
 * Contrôleur REST pour la gestion des tâches
 * Expose les endpoints CRUD sur le port 8085
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * POST /api/tasks — Créer une nouvelle tâche
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }
        Task created = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/tasks — Lister toutes les tâches (avec filtre optionnel par statut)
     */
    @GetMapping
    public ResponseEntity<List<Task>> listTasks(
            @RequestParam(required = false) TaskStatus status) {
        List<Task> tasks;
        if (status != null) {
            tasks = taskService.listTasksByStatus(status);
        } else {
            tasks = taskService.listTasks();
        }
        return ResponseEntity.ok(tasks);
    }

    /**
     * PATCH /api/tasks/{id}/status — Mettre à jour le statut d'une tâche
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        TaskStatus newStatus = TaskStatus.valueOf(body.get("status"));
        Task updated = taskService.updateStatus(id, newStatus);
        return ResponseEntity.ok(updated);
    }

    /**
     * GET /api/tasks/count/completed — Compter les tâches terminées
     */
    @GetMapping("/count/completed")
    public ResponseEntity<Map<String, Long>> countCompleted() {
        long count = taskService.countCompletedTasks();
        return ResponseEntity.ok(Map.of("completedTasks", count));
    }
}
