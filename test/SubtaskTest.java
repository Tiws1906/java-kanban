import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    void testSubtaskEqualityById() {
        Subtask subtask1 = new Subtask(1, "Subtask 1", "Description 1", TaskStatusList.NEW, 10);
        Subtask subtask2 = new Subtask(1, "Subtask 2", "Description 2", TaskStatusList.DONE, 20);

        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым id должны быть равны.");
    }
    @Test
    void testSubtaskCannotBeItsOwnEpic() {
        TaskManager manager = Managers.getDefault();
        Epic epic = new Epic(1, "Epic 1", "Description", TaskStatusList.NEW);
        manager.addEpic(epic);

        Subtask subtask = new Subtask(2, "Subtask 1", "Description", TaskStatusList.NEW, epic.getId());
        manager.addSubtask(subtask);

        assertThrows(IllegalArgumentException.class, () -> {
            subtask.setEpicId(subtask.getId()); // Попытка сделать подзадачу своим эпиком
        }, "Подзадача не может быть своим эпиком.");
    }
}