package tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    Map<Integer, Task> taskById = new HashMap<>();
    Map<Integer, Epic> epicById = new HashMap<>();
    Map<Integer, Subtask> subtaskById = new HashMap<>();

    static int idCounter;

    public static int generateId() {
        idCounter += 1;
        return idCounter;
    }

    public Task generateTask(String title, String description) {
        Task task = new Task(generateId(), title, description);
        saveTask(task);
        return task;
    }

    public Epic generateEpicTask(String title, String description) {
        Epic epic = new Epic(generateId(), title, description);
        saveEpic(epic);
        return epic;
    }

    public Subtask generateSub(String title, String description, int id) {
        Epic epic = getEpicById(id);
        Subtask subtask = new Subtask(generateId(), title, description, epic);
        saveSubtask(subtask);
        epic.addSubtask(subtask);
        return subtask;
    }

    public void saveTask(Task task) {
        taskById.put(task.getId(), task);
    }

    public void saveEpic(Epic epic) {
        epicById.put(epic.getId(), epic);
    }

    public void saveSubtask(Subtask subtask) {
        subtaskById.put(subtask.getId(), subtask);
    }


    public void updateTask(Task task) {
        taskById.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epicById.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtaskById.put(subtask.getId(), subtask);

    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskById.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epicById.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtaskById.values());
    }

    public void clearAllTasks() {
        taskById.clear();
    }

    public void clearAllEpics() {
        epicById.clear();
    }

    public void clearAllSubtasks() {
        subtaskById.clear();
    }

    public Task getTaskById(int id) {
        return taskById.get(id);
    }

    public Epic getEpicById(int id) {
        return epicById.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtaskById.get(id);

    }

    public void removeTaskById(int id) {
        taskById.remove(id);
    }

    public void removeEpicById(int id) {
        epicById.remove(id);
    }

    public void removeSubtaskById(int id) {
        subtaskById.remove(id);
    }

    public List<Subtask> getSubtasks(int id) {
        Epic epic = getEpicById(id);
        return epic.getSubtasks();
    }

    public void setStateTask(int id, State state) {
        Task task = getTaskById(id);
        task.setState(state);
    }

    public void setStateSubtask(int id, State state) {
        Subtask subtask = getSubtaskById(id);
        subtask.setState(state);
    }
}
