import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private double grade;
    private int course;

    public Student(String name, double grade, int course) {
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public int getCourse() {
        return course;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", 78.5, 1),
                new Student("Bob", 65.0, 2),
                new Student("Charlie", 90.0, 1),
                new Student("David", 75.0, 2),
                new Student("Emily", 82.0, 1),
                new Student("Frank", 70.0, 2)

        );

        Map<Integer, Double> averageGradeByCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse,
                        Collectors.averagingDouble(Student::getGrade)));

        System.out.println("Середній бал за курс:");
        averageGradeByCourse.forEach((course, averageGrade) ->
                System.out.println("Курс " + course + ": " + averageGrade));
    }
}
