package tasks;

public class Subtask extends Task {
    private final Epic epic;

    public Subtask(int id, String title, String description, Epic epic) {
        super(id, title, description);
        this.epic = epic;
    }

    public void setState(State state) {
        super.setState(state);
        epic.updateState();
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epic=" + epic +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", state=" + getState() +
                '}';
    }
}
