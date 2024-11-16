package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private final Map<Integer, Task> taskById = new HashMap<>();
    private final Map<Integer, Epic> epicById = new HashMap<>();
    private final Map<Integer, Subtask> subtaskById = new HashMap<>();
    HistoryManager historyManager = Managers.getDefaultHistory();

    static int idCounter;

    public static int generateId() {
        idCounter += 1;
        return idCounter;
    }

    public InMemoryHistoryManager getHistoryManager() {
        return (InMemoryHistoryManager) historyManager;
    }

    @Override
    public Task generateTask(String title, String description) {
        Task task = new Task(generateId(), title, description);
        saveTask(task);
        return task;
    }

    @Override
    public Epic generateEpicTask(String title, String description) {
        Epic epic = new Epic(generateId(), title, description);
        saveEpic(epic);
        return epic;
    }

    private Epic getEpic(int id) {
        return epicById.get(id);
    }

    @Override
    public Subtask generateSub(String title, String description, int id) {
        if (!epicById.containsKey(id)) {
            return null;
        }
        Epic epic = getEpic(id);
        Subtask subtask = new Subtask(generateId(), title, description, epic);
        saveSubtask(subtask);
        epic.addSubtask(subtask);
        return subtask;
    }

    @Override
    public void saveTask(Task task) {
        taskById.put(task.getId(), task);
    }

    @Override
    public void saveEpic(Epic epic) {
        epicById.put(epic.getId(), epic);
    }

    @Override
    public void saveSubtask(Subtask subtask) {
        subtaskById.put(subtask.getId(), subtask);
    }

    @Override
    public void updateTask(Task task) {
        taskById.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epicById.put(epic.getId(), epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtaskById.put(subtask.getId(), subtask);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskById.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epicById.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtaskById.values());
    }

    @Override
    public void clearAllTasks() {
        taskById.clear();
    }

    @Override
    public void clearAllEpics() {
        epicById.clear();
    }

    @Override
    public void clearAllSubtasks() {
        subtaskById.clear();
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(taskById.get(id));
        return taskById.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epicById.get(id));
        return epicById.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.add(subtaskById.get(id));
        return subtaskById.get(id);
    }

    @Override
    public void removeTaskById(int id) {
        taskById.remove(id);
    }

    @Override
    public void removeEpicById(int id) {
        Epic epic = epicById.get(id);
        for (Subtask subtask : epic.getSubtasks()) {
            int subtaskId = subtask.getId();
            removeSubtaskById(subtaskId);
        }
        epicById.remove(id);
    }

    @Override
    public void removeSubtaskById(int id) {
        subtaskById.remove(id);
    }

    @Override
    public List<Subtask> getSubtasks(int id) {
        if (!epicById.containsKey(id)) {
            return null;
        }
        Epic epic = getEpicById(id);
        return epic.getSubtasks();
    }
}
