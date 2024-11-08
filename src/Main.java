import tasks.Epic;
import tasks.State;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskManager;

public class Main {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        Task task1 = manager.generateTask("Сделать кофе", "пойти на кухню и сделать");
        Task task2 = manager.generateTask("Продать гараж","продать дорого");
        Epic epic1 = manager.generateEpicTask("Убраться в квартире", "Сделать сегодня");
        Epic epic2 = manager.generateEpicTask("Выйти на улицу", "Говорят надо");
        Subtask subtask1 = manager.generateSub("Помыть пол","Помыть тщательно", 3);
        Subtask subtask2 = manager.generateSub("Вытереть пыль","Сделать это везде", 3);
        Subtask subtask3 = manager.generateSub("Одеться","На улице холодно",4);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        System.out.println("==================================================================================");

        task1 = manager.getTaskById(1);
        task1.setState(State.DONE);
        manager.updateTask(task1);
        System.out.println(task1);

        subtask1 = manager.getSubtaskById(5);
        subtask1.setState(State.DONE);
        System.out.println(subtask1);

        System.out.println("==================================================================================");

        manager.removeTaskById(1);
        System.out.println(manager.getTaskById(1));
        manager.removeEpicById(3);
        System.out.println(manager.getEpicById(3));
        System.out.println(manager.getSubtaskById(5));
        System.out.println(manager.getSubtaskById(6));

        System.out.println(manager.getSubtasks(3));

    }
}
