import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    int ganerateId();

    // Метод задач
    void addTask(Task task);

    void removeTask(int id);

    void removeAllTasks();

    Task getTask(int id);

    ArrayList<Task> getAllTasks();

    void updateTask(Task task);

    //методы подзадач
    void addSubtask(Subtask subtask);

    void removeSubtask(int id);

    void removeAllSubtasks();

    Subtask getSubtask(int id);

    ArrayList<Subtask> getAllSubtasks();

    void updateSubtask(Subtask subtask);

    ArrayList<Subtask> getEpicsSubtasks(int epicId);

    //методы эпиков
    void addEpic(Epic epic);

    void removeEpic(int id);

    void removeAllEpics();

    Epic getEpic(int id);

    ArrayList<Epic> getAllEpics();

    void updateEpic(Epic epic);

    List<Task> getHistory();
}
