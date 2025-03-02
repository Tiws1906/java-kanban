import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    // Метод задач
    void addTask(Task task);

    void removeTask(int id);

    void removeAllTasks();

    Task getTask(int id);

    List<Task> getAllTasks();

    void updateTask(Task task);

    //методы подзадач
    void addSubtask(Subtask subtask);

    void removeSubtask(int id);

    void removeAllSubtasks();

    Subtask getSubtask(int id);

    List<Subtask> getAllSubtasks();

    void updateSubtask(Subtask subtask);

    List<Subtask> getEpicsSubtasks(int epicId);

    //методы эпиков
    void addEpic(Epic epic);

    void removeEpic(int id);

    void removeAllEpics();

    Epic getEpic(int id);

    List<Epic> getAllEpics();

    void updateEpic(Epic epic);

    List<Task> getHistory();
}
