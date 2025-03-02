

public class Main {

    public static void main(String[] args) {
        Managers manager = new Managers();
        InMemoryTaskManager tm = (InMemoryTaskManager) manager.getDefault();;

        Task task = new Task(manager.ganerateId(), "task", "description", TaskStatusList.NEW);
        Task task2 = new Task(manager.ganerateId(), "task2", "description", TaskStatusList.NEW);
        Epic epic = new Epic(manager.ganerateId(), "epic", "description", TaskStatusList.NEW);
        Subtask subtask = new Subtask(manager.ganerateId(), "subtask", "description", TaskStatusList.NEW, epic.getId());
        Subtask subtask2 = new Subtask(manager.ganerateId(), "subtask2", "description", TaskStatusList.NEW, epic.getId());
        Epic epic2 = new Epic(manager.ganerateId(), "epic2", "description", TaskStatusList.NEW);
        Subtask subtask3 = new Subtask(manager.ganerateId(), "subtask3", "description", TaskStatusList.NEW, epic2.getId());

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



        tm.getEpic(epic.getId());
        tm.getSubtask(subtask.getId());
        tm.getTask(task.getId());
        System.out.println("All tasks");
        System.out.println("______");
        printAllTasks(tm);
        System.out.println("Вывожу историю");
        System.out.println(tm.getHistory());



    }

    private static void printAllTasks(InMemoryTaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicsSubtasks(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}