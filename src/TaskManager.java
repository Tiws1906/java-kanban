import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId = 1;


    public int ganerateId() {
        return nextId++;
    }

    // Метод задач
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(int id) {
        tasks.remove(id);
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasksList = new ArrayList<>();
        for (Task task : tasks.values()) {
            tasksList.add(task);
        }
        return tasksList;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    //методы подзадач
    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic parentEpic = epics.get(subtask.getEpicId());
        if (parentEpic != null) {
            parentEpic.addSubtaskId(subtask.getId());
            updateEpic(parentEpic);
        }

    }

    public void removeSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        Epic parentEpic = epics.get(subtask.getEpicId());
        if (parentEpic != null) {
            parentEpic.removeSubtaskId(subtask.getId());
            updateEpic(parentEpic);
        }
        subtasks.remove(id);

    }

    public void removeAllSubtasks() {
        subtasks.clear();
        for(Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateEpic(epic);
        }

    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public ArrayList<Subtask> getAllSubtasks() {
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            subtasksList.add(subtask);
        }
        return subtasksList;
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic parentEpic = epics.get(subtask.getEpicId());
        if (parentEpic != null) {
            parentEpic.addSubtaskId(subtask.getId());
            updateEpic(parentEpic);
        }
    }

    public ArrayList<Subtask> getEpicsSubtasks(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskIds()) {
            subtasksList.add(subtasks.get(subtaskId));
        }
        return subtasksList;
    }


    //методы эпиков
    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void removeEpic(int id) {
        Epic epic = epics.get(id);
        if (epic == null) {
            return;
        }
        for (Integer subtaskId : epic.getSubtaskIds()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(id);
    }

    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epicsList = new ArrayList<>();
        for (Epic epic : epics.values()) {
            epicsList.add(epic);
        }
        return epicsList;
    }

    public void updateEpic(Epic epic) {
        if (epic.getSubtaskIds().isEmpty()) {
            epic.setStatus(TaskStatusList.NEW);
            epics.put(epic.getId(),epic);
            return;
        }


        ArrayList<Subtask> subtasksEpic = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskIds()) {
            subtasksEpic.add(subtasks.get(subtaskId));
        }

        boolean subtasksDone = true;
        boolean subtasksAllNew = true;

        for (Subtask subtask : subtasksEpic) {
            if (subtask.getStatus() != TaskStatusList.DONE) {
                subtasksDone = false;
                break;
            }
        }

        for (Subtask subtask : subtasksEpic) {
            if (subtask.getStatus() != TaskStatusList.NEW) {
                subtasksAllNew = false;
                break;
            }
        }

        if (subtasksDone) {
            epic.setStatus(TaskStatusList.DONE);
            epics.put(epic.getId(),epic);

        } else if(subtasksAllNew) {
            epic.setStatus(TaskStatusList.NEW);
            epics.put(epic.getId(),epic);
        } else {
            epic.setStatus(TaskStatusList.IN_PROGRESS);
            epics.put(epic.getId(),epic);
        }

    }


}
