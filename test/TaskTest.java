import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void testTaskEqualityById() {
        Task task1 = new Task(1, "Task 1", "Description 1", TaskStatusList.NEW);
        Task task2 = new Task(1, "Task 2", "Description 2", TaskStatusList.IN_PROGRESS);

        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны.");
    }
}