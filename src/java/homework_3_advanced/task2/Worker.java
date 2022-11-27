package homework_3_advanced.task2;

public class Worker {
    private Store workplace;
    private int journal;

    final private String name;

    Worker(String name, Store workplace) {
        this.name = name;
        this.workplace = workplace;
    }

    public Store getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Store workplace) {
        this.workplace = workplace;
    }

    public int getJournal() {
        return journal;
    }

    public void setJournal(int journal) {
        this.journal = journal;
    }

    public String getName() {
        return name;
    }

    public void destroy(int request) {
        if (request > 0 && workplace.getVodka() - request >= 0) {
            workplace.setVodka(workplace.getVodka() - request);
            System.out.println("Ура, я испортил водку!");
            setJournal(getJournal() + request);
        } else {
            System.out.println("Товара не хватит.");
        }
    }
}
