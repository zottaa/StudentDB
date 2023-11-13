package UserAction;

public class ShowOptions {
    private final boolean showParents;
    private final boolean showPerformances;
    private final boolean showStudents;

    ShowOptions() {
        this.showParents = false;
        this.showPerformances = false;
        this.showStudents = false;
    }

    public ShowOptions(boolean showParents, boolean showPerformances) {
        this.showParents = showParents;
        this.showPerformances = showPerformances;
        this.showStudents = false;
    }

    public ShowOptions(boolean showStudents) {
        this.showStudents = showStudents;
        this.showPerformances = false;
        this.showParents = false;
    }

    public boolean isShowParents() {
        return showParents;
    }

    public boolean isShowPerformances() {
        return showPerformances;
    }

    public boolean isShowStudents() {
        return showStudents;
    }
}
