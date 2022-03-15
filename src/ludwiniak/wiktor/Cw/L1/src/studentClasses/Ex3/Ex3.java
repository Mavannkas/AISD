package ludwiniak.wiktor.Cw.L1.src.studentClasses.Ex3;

import ludwiniak.wiktor.Cw.L1.src.studentClasses.Student;
import ludwiniak.wiktor.Cw.L1.src.studentClasses.StudentsListIterator;

import java.util.*;


public class Ex3 {
    public static StudentsListIterator<Student> addStudent(StudentsListIterator<Student> studentsIterator, Student newStudent) {
            LinkedList<Student> outputStudents = new LinkedList<>();
            while(studentsIterator.hasNext()) {
                outputStudents.add(studentsIterator.next());
            }
            outputStudents.add(newStudent);

            return new StudentsListIterator<>(outputStudents.toArray(Student[]::new));
    }

    public static StudentsListIterator<Student> removeStudent(StudentsListIterator<Student> studentsIterator, Student studentToRemove) {
            LinkedList<Student> outputStudents = new LinkedList<>();
            while(studentsIterator.hasNext()) {
                Student student = studentsIterator.next();
                if(!student.equals(studentToRemove)) {
                    outputStudents.add(student);
                }
            }
            return new StudentsListIterator<>(outputStudents.toArray(Student[]::new));
    }

    public static StudentsListIterator<Student> sort(StudentsListIterator<Student> studentsIterator) {
            LinkedList<Student> outputStudents = new LinkedList<>();
            while(studentsIterator.hasNext()) {
                outputStudents.add(studentsIterator.next());
            }

            for (int i = 0; i < outputStudents.size(); i++) {
                for (int j = 0; j < outputStudents.size() - 1; j++) {
                    if(outputStudents.get(j).compareTo(outputStudents.get(j + 1)) < 0) {
                        Student student = outputStudents.get(j);
                        outputStudents.set(j, outputStudents.get(j + 1));
                        outputStudents.set(j + 1, student);
                    }
                }
            }
            return new StudentsListIterator<>(outputStudents.toArray(Student[]::new));
    }
}
