package ludwiniak.wiktor.Cw.L1.src.studentClasses;


import java.util.Objects;

public class Student implements WithValue, Comparable{
    private final int id;
    private final String name;
    private final String surname;
    private double mark = -0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Double.compare(student.mark, mark) == 0 && Objects.equals(name, student.name) && Objects.equals(surname, student.surname);
    }

    public Student(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return String.format("%3d %8s %8s %3s", id, name, surname, mark);
    }

    @Override
    public double getValue() {
        return getMark();
    }

    @Override
    public int compareTo(Object o) {
        Student student = (Student) o;
        return (int) (mark - student.mark);
    }
}
