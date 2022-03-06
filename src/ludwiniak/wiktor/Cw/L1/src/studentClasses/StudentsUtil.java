package ludwiniak.wiktor.Cw.L1.src.studentClasses;

import java.util.Iterator;

public class StudentsUtil {
    private final StudentsList<Student> students;

    public StudentsUtil(StudentsList<Student> students) {
        this.students = students;
    }

    public void printStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void setMarkById(int id, double mark) {
        for (Student student : students) {
            if (id == student.getId()) {
                student.setMark(mark);
            }
        }
    }


    public double calcAVG() {
        int counter = 0;
        double sum = 0.0;

        for (Iterator<Student> it = students.goodIterator(); it.hasNext(); ) {
            counter++;
            sum += it.next().getMark();
        }

        return counter > 0 ? sum / counter : 0;
    }


    public void printBadStudents() {
        for (Iterator<Student> it = students.badIterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }
}
