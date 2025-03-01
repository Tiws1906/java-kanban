import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    void testEpicCannotBeItsOwnSubtask() {
        TaskManager manager = Managers.getDefault();
        Epic epic = new Epic(manager.ganerateId(), "Epic 1", "Description", TaskStatusList.NEW);
        manager.addEpic(epic);

        Subtask subtask = new Subtask(manager.ganerateId(), "Subtask 1", "Description", TaskStatusList.NEW, epic.getId());
        manager.addSubtask(subtask);

        assertThrows(IllegalArgumentException.class, () -> {
            manager.addSubtask(new Subtask(manager.ganerateId(), "Invalid Subtask", "Description", TaskStatusList.NEW, epic.getId()));
        }, "Эпик не может быть добавлен в самого себя как подзадача.");
    }
}