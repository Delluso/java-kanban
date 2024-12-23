package managers;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private final HandMadeLinkedList<Task> historyList = new HandMadeLinkedList<>();
    private final Map<Integer, Node<Task>> historyMap = new HashMap<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            if (historyMap.get(task.getId()) != null) {
                remove(task.getId());
            }
            historyList.linkLast(task);
            historyMap.put(task.getId(), historyList.tail);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyList.getTasks();
    }

    @Override
    public void remove(int id) {
        historyList.removeNode(historyMap.get(id));
        historyMap.remove(id);
    }


    public class HandMadeLinkedList<T> {

        private Node<T> head;
        private Node<T> tail;


        public void linkLast(T task) {
            final Node<T> oldTail = tail;
            final Node<T> newNode = new Node<>(tail, task, null);
            tail = newNode;
            if (oldTail == null)
                head = newNode;
            else
                oldTail.next = newNode;
        }

        public List<T> getTasks() {
            List<T> tasks = new ArrayList<>();
            Node<T> current = head;
            while (current != null) {
                tasks.add(current.getData());
                current = current.getNext();
            }
            return tasks;
        }

        public void removeNode(Node<T> node) {
            if (node == head) {
                head = node.getNext();
            } else if (node == tail) {
                tail = node.getPrev();
            } else {
                node.getPrev().setNext(node.getNext());
                node.getNext().setPrev(node.getPrev());
            }
        }
    }
}
