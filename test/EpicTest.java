import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    void testEpicCannotBeItsOwnSubtask() {
        Managers managers = new Managers();
        TaskManager manager = Managers.getDefault();
        Epic epic = new Epic(managers.ganerateId(), "Epic 1", "Description", TaskStatusList.NEW);
        manager.addEpic(epic);

        Subtask subtask = new Subtask(managers.ganerateId(), "Subtask 1", "Description", TaskStatusList.NEW, epic.getId());
        manager.addSubtask(subtask);

        assertThrows(IllegalArgumentException.class, () -> {
            manager.addSubtask(new Subtask(managers.ganerateId(), "Invalid Subtask", "Description", TaskStatusList.NEW, epic.getId()));
        }, "Эпик не может быть добавлен в самого себя как подзадача.");
    }
}