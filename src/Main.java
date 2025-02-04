

public class Main {

    public static void main(String[] args) {
    TaskManager tm = new TaskManager();
    Task task = new Task(tm.ganerateId(), "task", "description", TaskStatusList.NEW);
    Task task2 = new Task(tm.ganerateId(), "task2", "description", TaskStatusList.NEW);
    Epic epic = new Epic(tm.ganerateId(), "epic", "description", TaskStatusList.NEW);
    Subtask subtask = new Subtask(tm.ganerateId(), "subtask", "description", TaskStatusList.NEW, epic.getId());
    Subtask subtask2 = new Subtask(tm.ganerateId(), "subtask2", "description", TaskStatusList.NEW, epic.getId());
    Epic epic2 = new Epic(tm.ganerateId(), "epic2", "description", TaskStatusList.NEW);
    Subtask subtask3 = new Subtask(tm.ganerateId(), "subtask3", "description", TaskStatusList.NEW, epic2.getId());

    tm.addEpic(epic2);
    tm.addEpic(epic);
    tm.addSubtask(subtask);
    tm.addSubtask(subtask2);
    tm.addSubtask(subtask3);
    tm.addTask(task);
    tm.addTask(task2);

    System.out.println(tm.getAllTasks());
    System.out.println(tm.getAllEpics());
    System.out.println(tm.getAllSubtasks());

    task.setStatus(TaskStatusList.DONE);
    tm.updateTask(task);

    subtask.setStatus(TaskStatusList.DONE);
    tm.updateSubtask(subtask);

    epic.setStatus(TaskStatusList.DONE);
    tm.updateEpic(epic);


    tm.removeTask(task.getId());
    tm.removeSubtask(subtask.getId());
    tm.removeEpic(epic2.getId());


    }
}
