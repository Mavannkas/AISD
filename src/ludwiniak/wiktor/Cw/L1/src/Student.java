package ludwiniak.wiktor.Cw.L1.src;


public class Student {
    private final int id;
    private final String name;
    private final String surname;
    private final String mark;

    public Student(int id, String name, String surname, String mark) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMark() {
        return mark;
    }
}
