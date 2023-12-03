import java.util.List;
import java.util.ArrayList;

class Animal {
    // Базовий клас
    String species;

    public Animal(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }
}

class Cat extends Animal {
    // Підклас для котів
    String color;

    public Cat(String species, String color) {
        super(species);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

class Dog extends Animal {
    // Підклас для собак
    int age;

    public Dog(String species, int age) {
        super(species);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
class Main {
    public static void printSubclasses(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            if (animal instanceof Cat cat) {
                System.out.println("Кіт: Вид - " + cat.getSpecies() + ", Колір - " + cat.getColor());
            } else if (animal instanceof Dog dog) {
                System.out.println("Собака: Вид - " + dog.getSpecies() + ", Вік - " + dog.getAge());
            } else {
                System.out.println("Тварина: Вид - " + animal.getSpecies());
            }
        }
    }


    public static void main(String[] args) {
        List<Animal> animalsList = new ArrayList<>();
        animalsList.add(new Cat("Домашній кіт", "Сірий"));
        animalsList.add(new Dog("Домашня собака", 5));
        animalsList.add(new Cat("Лісовий кіт", "Рудий"));
        animalsList.add(new Animal("Не визначено"));

        printSubclasses(animalsList);
    }
}
