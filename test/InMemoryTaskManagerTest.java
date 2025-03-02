import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    TaskManager taskManager = Managers.getDefault();
    HistoryManager historyManager = Managers.getDefaultHistory();
    @Test
    void testHistory() {

        Task task = new Task(1, "Task 1", "Description", TaskStatusList.NEW);
        taskManager.addTask(task);

        taskManager.getTask(1); // Просмотр задачи
        List<Task> history = taskManager.getHistory();

        assertEquals(1, history.size(), "История должна содержать одну задачу.");
        assertEquals(task, history.get(0), "Задачи в истории не совпадают.");
    }

    @Test
    void testAddAndFindTasks() {
        TaskManager manager = Managers.getDefault();

        Task task = new Task(1, "Task 1", "Description", TaskStatusList.NEW);
        Epic epic = new Epic(2, "Epic 1", "Description", TaskStatusList.NEW);
        Subtask subtask = new Subtask(3, "Subtask 1", "Description", TaskStatusList.NEW, epic.getId());

        manager.addTask(task);
        manager.addEpic(epic);
        manager.addSubtask(subtask);

        assertEquals(task, manager.getTask(1), "Задача должна быть найдена по id.");
        assertEquals(epic, manager.getEpic(2), "Эпик должен быть найден по id.");
        assertEquals(subtask, manager.getSubtask(3), "Подзадача должна быть найдена по id.");
    }
    @Test
    void testTaskIdConflict() {

        TaskManager manager = Managers.getDefault();

        Task task1 = new Task(1, "Task 1", "Description", TaskStatusList.NEW);
        Task task2 = new Task(manager.ganerateId(), "Task 2", "Description", TaskStatusList.NEW);

        manager.addTask(task1);
        manager.addTask(task2);

        assertNotEquals(task1.getId(), task2.getId(), "Задачи не должны иметь одинаковый id.");
    }
    @Test
    void testTaskImmutability() {
        TaskManager manager = Managers.getDefault();
        Task task = new Task(1, "Task 1", "Description", TaskStatusList.NEW);

        manager.addTask(task);
        Task savedTask = manager.getTask(1);

        assertEquals(task.getName(), savedTask.getName(), "Имя задачи не должно измениться.");
        assertEquals(task.getDescription(), savedTask.getDescription(), "Описание задачи не должно измениться.");
        assertEquals(task.getStatus(), savedTask.getStatus(), "Статус задачи не должен измениться.");
    }
}
