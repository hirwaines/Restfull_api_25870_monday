package auca.ac.rw.question5_TaskManagement.controller;
import org.springframework.web.bind.annotation.*;
import auca.ac.rw.question5_TaskManagement.modal.Task;
import java.util.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId() == taskId) return t;
        }
        return null;
    }

    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted() == completed) result.add(t);
        }
        return result;
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (priority.equals(t.getPriority())) result.add(t);
        }
        return result;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setTaskId(nextId++);
        tasks.add(task);
        return task;
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                task.setTaskId(taskId);
                tasks.set(i, task);
                return task;
            }
        }
        return null;
    }

    @PatchMapping("/{taskId}/complete")
    public Task markComplete(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId() == taskId) {
                t.setCompleted(true);
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        tasks.removeIf(t -> t.getTaskId() == taskId);
    }
}
