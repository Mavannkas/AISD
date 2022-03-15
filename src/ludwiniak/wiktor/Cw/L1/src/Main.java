package ludwiniak.wiktor.Cw.L1.src;

import ludwiniak.wiktor.Cw.L1.src.studentClasses.Ex3.Ex3;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.Student;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.StudentsList;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.StudentsListIterator;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.StudentsUtil;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.v2.StudentV2;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.v2.StudentsUtilV2;

public class Main {
    public static void main(String[] args) {
//        FibonacciIterator iterator = new FibonacciIterator(50);
//        while (iterator.hasNext()) {
//            int i = iterator.next();
//            System.out.println(i);
//        }
        test3();
    }

    private static void test1() {
        Student[] students = getStudents();
        StudentsList<Student> studentsList = new StudentsList<Student>(students);
        StudentsUtil studentsUtil = new StudentsUtil(studentsList);
        studentsUtil.printStudents();
        studentsUtil.setMarkById(0, 4);
        studentsUtil.setMarkById(1, 3.5);
        studentsUtil.setMarkById(2, 6);
        studentsUtil.setMarkById(3, 2);
        studentsUtil.setMarkById(4, 2.5);
        System.out.println(studentsUtil.calcAVG());
        studentsUtil.printBadStudents();
    }
    private static void test2() {
        StudentV2[] students = getStudentsV2();
        StudentsList<StudentV2> studentsList = new StudentsList(students);
        StudentsUtilV2 studentsUtil = new StudentsUtilV2(studentsList);
        studentsUtil.printStudents();
        studentsUtil.setMarkById(0, 4);
        studentsUtil.setMarkById(0, 2);
        studentsUtil.setMarkById(1, 3.5);
        studentsUtil.setMarkById(1, 6);
        studentsUtil.setMarkById(2, 6);
        studentsUtil.setMarkById(2, 3);
        studentsUtil.setMarkById(4, 2);
        studentsUtil.setMarkById(4, 2);
        System.out.println(studentsUtil.calcAVG());
        studentsUtil.printBadStudents();
    }

    private static void test3() {
        Student[] students = getStudents();
        StudentsList<Student> studentsList = new StudentsList<Student>(students);
        StudentsUtil studentsUtil = new StudentsUtil(studentsList);
        studentsUtil.setMarkById(0, 4);
        studentsUtil.setMarkById(1, 3.5);
        studentsUtil.setMarkById(2, 6);
        studentsUtil.setMarkById(3, 2);
        studentsUtil.setMarkById(4, 2.5);
        StudentsListIterator<Student> studentStudentsListIterator = (StudentsListIterator<Student>) studentsList.iterator();
        System.out.println(1);
        printIterator((StudentsListIterator<Student>) studentsList.iterator());
        System.out.println(2);
        printIterator(Ex3.sort((StudentsListIterator<Student>) studentsList.iterator()));

    }

    private static Student[] getStudents() {
        return new Student[] {
            new Student(0, "Adam", "Goza"),
            new Student(1, "Albert", "Goza"),
            new Student(2, "Euzebiusz", "Goza"),
            new Student(3, "Jan", "Goza"),
            new Student(4, "Janusz", "Goza")
        };
    }

    private static StudentV2[] getStudentsV2() {
        return new StudentV2[] {
            new StudentV2(0, "Adam", "Goza"),
            new StudentV2(1, "Albert", "Goza"),
            new StudentV2(2, "Euzebiusz", "Goza"),
            new StudentV2(3, "Jan", "Goza"),
            new StudentV2(4, "Janusz", "Goza")
        };
    }

    private static void printIterator(StudentsListIterator<Student> students) {
        while (students.hasNext()) {
            Student student = students.next();
            System.out.println(student);

        }
    }
}
