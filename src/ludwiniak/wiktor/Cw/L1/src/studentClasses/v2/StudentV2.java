package ludwiniak.wiktor.Cw.L1.src.studentClasses.v2;


import ludwiniak.wiktor.Cw.L1.src.studentClasses.WithValue;

import java.util.ArrayList;
import java.util.List;

public class StudentV2 implements WithValue {
    private final int id;
    private final String name;
    private final String surname;
    private final List<Double> marks = new ArrayList<>();


    public StudentV2(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void addMark(double mark) {
        this.marks.add(mark);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getAVG() {
        int counter = 0;
        double sum = 0;

        for(double mark : marks) {
            counter++;
            sum += mark;
        }

        return counter > 0 ? sum/counter : 0;
    }

    @Override
    public String toString() {
        return String.format("%3d %8s %8s %3f", id, name, surname, getAVG());
    }

    @Override
    public double getValue() {
        return getAVG();
    }
}
