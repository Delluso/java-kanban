package tasks;

public class Subtask extends Task {
    Epic epic;

    public Subtask(int id, String title, String description, Epic epic) {
        super(id, title, description);
        this.epic = epic;
    }

    public void setState(State state) {
        this.state = state;
        epic.updateState();
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epic=" + epic +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }
}
