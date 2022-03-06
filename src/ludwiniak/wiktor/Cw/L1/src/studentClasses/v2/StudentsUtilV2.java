package ludwiniak.wiktor.Cw.L1.src.studentClasses.v2;

import ludwiniak.wiktor.Cw.L1.src.studentClasses.Student;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.StudentsList;

import java.util.Iterator;

public class StudentsUtilV2 {
    private final StudentsList<StudentV2> students;

    public StudentsUtilV2(StudentsList<StudentV2> students) {
        this.students = students;
    }

    public void printStudents() {
        for (StudentV2 student : students) {
            System.out.println(student);
        }
    }

    public void setMarkById(int id, double mark) {
        for (StudentV2 student : students) {
            if (id == student.getId()) {
                student.addMark(mark);
            }
        }
    }


    public double calcAVG() {
        int counter = 0;
        double sum = 0.0;

        for (Iterator<StudentV2> it = students.goodIterator(); it.hasNext(); ) {
            counter++;
            sum += it.next().getAVG();
        }

        return counter > 0 ? sum / counter : 0;
    }


    public void printBadStudents() {
        for (Iterator<StudentV2> it = students.badIterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }
}
