import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    @Test
    void testHistoryPreservesTaskState() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task(1, "Task 1", "Description", TaskStatusList.NEW);

        historyManager.add(task);
        task.setStatus(TaskStatusList.IN_PROGRESS);

        List<Task> history = historyManager.getHistory();
        assertEquals(TaskStatusList.NEW, history.get(0).getStatus(), "История должна сохранять предыдущее состояние задачи.");
    }
}