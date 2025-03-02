import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final InMemoryHistoryManager historyManager = new InMemoryHistoryManager();


    // Метод задач
    @Override
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void removeTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasksList = new ArrayList<>();
        for (Task task : tasks.values()) {
            tasksList.add(task);
        }
        return tasksList;
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    //методы подзадач
    @Override
    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic parentEpic = epics.get(subtask.getEpicId());
        if (parentEpic != null) {
            parentEpic.addSubtaskId(subtask.getId());
            updateEpic(parentEpic);
        }

    }

    @Override
    public void removeSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        Epic parentEpic = epics.get(subtask.getEpicId());
        if (parentEpic != null) {
            parentEpic.removeSubtaskId(subtask.getId());
            updateEpic(parentEpic);
        }
        subtasks.remove(id);

    }

    @Override
    public void removeAllSubtasks() {
        subtasks.clear();
        for(Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateEpic(epic);
        }

    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            historyManager.add(subtask);

        }
        return subtask;

    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            subtasksList.add(subtask);
        }
        return subtasksList;
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Epic parentEpic = epics.get(subtask.getEpicId());
        if (parentEpic != null) {
            parentEpic.addSubtaskId(subtask.getId());
            updateEpic(parentEpic);
        }
    }

    @Override
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
    @Override
    public void addEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    @Override
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

    @Override
    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            historyManager.add(epic);
        }
        return epic;
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> epicsList = new ArrayList<>();
        for (Epic epic : epics.values()) {
            epicsList.add(epic);
        }
        return epicsList;
    }

    @Override
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

   public List<Task> getHistory() {
        return historyManager.getHistory();
    }



}
