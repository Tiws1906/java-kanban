public class Managers {
    private int nextId = 1;


    public int ganerateId() {
        return nextId++;
    }
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }


}