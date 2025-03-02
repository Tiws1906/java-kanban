

public class Main {

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        Task task = new Task(manager.ganerateId(), "task", "description", TaskStatusList.NEW);
        Task task2 = new Task(manager.ganerateId(), "task2", "description", TaskStatusList.NEW);
        Epic epic = new Epic(manager.ganerateId(), "epic", "description", TaskStatusList.NEW);
        Subtask subtask = new Subtask(manager.ganerateId(), "subtask", "description", TaskStatusList.NEW, epic.getId());
        Subtask subtask2 = new Subtask(manager.ganerateId(), "subtask2", "description", TaskStatusList.NEW, epic.getId());
        Epic epic2 = new Epic(manager.ganerateId(), "epic2", "description", TaskStatusList.NEW);
        Subtask subtask3 = new Subtask(manager.ganerateId(), "subtask3", "description", TaskStatusList.NEW, epic2.getId());

        manager.addEpic(epic2);
        manager.addEpic(epic);
        manager.addSubtask(subtask);
        manager.addSubtask(subtask2);
        manager.addSubtask(subtask3);
        manager.addTask(task);
        manager.addTask(task2);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        task.setStatus(TaskStatusList.DONE);
        manager.updateTask(task);

        subtask.setStatus(TaskStatusList.DONE);
        manager.updateSubtask(subtask);

        epic.setStatus(TaskStatusList.DONE);
        manager.updateEpic(epic);



        manager.getEpic(epic.getId());
        manager.getSubtask(subtask.getId());
        manager.getTask(task.getId());
        System.out.println("All tasks");
        System.out.println("______");
        printAllTasks(manager);
        System.out.println("Вывожу историю");
        System.out.println(manager.getHistory());



    }

    private static void printAllTasks(TaskManager manager) {
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