package tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    List<Subtask> subtasks = new ArrayList<>();

    public Epic(int id, String title, String description) {
        super(id, title, description);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void updateState() {
        int count = 0;
        for (Subtask subtask : subtasks) {
            if (subtask.getState().equals(State.DONE)) {
                count++;
            }
        }
        if (count == subtasks.size()) {
            state = State.DONE;
        } else if (count > 0 && count < subtasks.size()) {
            state = State.IN_PROGRESS;
        } else if (count == 0) {
            state = State.NEW;
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }
}
