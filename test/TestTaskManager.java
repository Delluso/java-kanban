package test;

import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;


class TestTaskManager {
    @Test
    void sameIdTasksShouldBeEquals() {
        Task task = new Task(31, "title", "description");
        Task task1 = new Task(31, "title1", "description1");
        Assertions.assertEquals(task, task1);
    }

    @Test
    void sameIdEpicsShouldBeEquals() {
        Epic epic = new Epic(11, "title", "description");
        Epic epic1 = new Epic(11, "title1", "description1");
        Assertions.assertEquals(epic, epic1);
    }

    @Test
    void sameIdSubtasksShouldBeEquals() {
        Epic epic = new Epic(11, "title", "description");
        Subtask subtask = new Subtask(31, "title", "description", epic);
        Subtask subtask1 = new Subtask(31, "title1", "description1", epic);
    }

    @Test
    void managersShouldReturnExample() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        TaskManager manager = Managers.getDefault();
        Assertions.assertNotNull(historyManager);
        Assertions.assertNotNull(manager);
        Task task = manager.generateTask("task", "description");
        Assertions.assertNotNull(task);
        manager.getTaskById(1);
        Assertions.assertNotNull(historyManager.getHistory());
    }

    @Test
    void inMemoryTaskManagerShouldSaveAllTaskTypes() {
        TaskManager manager = Managers.getDefault();
        Task task = manager.generateTask("task", "description");
        Task epic = manager.generateEpicTask("epicTask", "description");
        Task subTask = manager.generateSub("subTask", "description", epic.getId());
        Assertions.assertNotNull(manager.getTaskById(task.getId()));
        System.out.println(manager.getTaskById(task.getId()));
        Assertions.assertNotNull(manager.getEpicById(epic.getId()));
        System.out.println(manager.getEpicById(epic.getId()));
        Assertions.assertNotNull(manager.getSubtaskById(subTask.getId()));
        System.out.println(manager.getSubtaskById(subTask.getId()));
    }

    @Test
    void taskShouldNotChangeAfterSave() {
        TaskManager manager = Managers.getDefault();
        Task task = manager.generateTask("task", "description");
        manager.saveTask(task);
        Task task1 = manager.getTaskById(task.getId());
        Assertions.assertEquals(task, task1);
        Assertions.assertEquals(task.getTitle(), task1.getTitle());
        Assertions.assertEquals(task.getDescription(), task1.getDescription());
    }

    @Test
    void taskShouldNotChangeAfterHistorySave() {
        TaskManager manager = Managers.getDefault();
        Task task = manager.generateTask("task", "description");
        manager.saveTask(task);
        manager.getTaskById(task.getId());
        Task task1 = manager.getHistoryManager().getHistory().getFirst();
        Assertions.assertEquals(task, task1);
        Assertions.assertEquals(task.getTitle(), task1.getTitle());
        Assertions.assertEquals(task.getDescription(), task1.getDescription());
    }

    @Test
    void taskShouldSaveInHistory() {
        TaskManager manager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = manager.generateTask("task", "description");
        Task task2 = manager.generateTask("task2", "description2");
        manager.saveTask(task);
        manager.saveTask(task2);
        manager.getTaskById(1);
        manager.getTaskById(2);
        Assertions.assertNotNull(historyManager.getHistory());
    }

    @Test
    void taskShouldSaveInHistoryOnlyOnes() {
        TaskManager manager = Managers.getDefault();
        Task task = manager.generateTask("title", "description");
        Epic epic = manager.generateEpicTask("epicTitle", "epicDescription");
        Subtask subtask = manager.generateSub("subTitle", "subDescription", epic.getId());

        manager.saveTask(task);
        manager.saveEpic(epic);
        manager.saveSubtask(subtask);

        manager.getTaskById(task.getId());
        manager.getEpicById(epic.getId());
        manager.getSubtaskById(subtask.getId());
        manager.getTaskById(task.getId());

        Assertions.assertNotEquals(task, manager.getHistory().getFirst());
        Assertions.assertEquals(task, manager.getHistory().getLast());
        Assertions.assertEquals(3, manager.getHistory().size());

    }
}