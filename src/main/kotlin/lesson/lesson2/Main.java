package lesson.lesson2;

public class Main {
    public static void main(String[] args) {
        testUser();
        System.out.println("------------------------------------------------------------");
        testPerson();
        System.out.println("------------------------------------------------------------");
        testUtils();
        System.out.println("------------------------------------------------------------");
        testCalculator();
        System.out.println("------------------------------------------------------------");
        testDataClass();
        System.out.println("------------------------------------------------------------");
        testResult();
    }

    public static void testUser() {
        User4J user = new User4J("Alice", 30);
        System.out.println(user.getName());
        user.setAge(31);
        user.greet();
    }

    public static void testPerson() {
        Person4J person = new Person4J();
        System.out.println(person.age);
        person.age = 30;
        System.out.println(person.age);
    }

    public static void testUtils() {
        Utils4J.printMessage();
    }

    public static void testCalculator() {
        Calculator4J calculator = new Calculator4J();
        System.out.println(calculator.add(1));
        System.out.println(calculator.add(1, 2));
    }

    public static void testDataClass() {
        UserDTO4J dto = new UserDTO4J("Alice", 30);
        System.out.println(dto.getName());
        System.out.println(dto.getAge());
        System.out.println(dto);
        System.out.println(dto.hashCode());
        System.out.println(dto.equals(new UserDTO4J("Alice", 30)));
    }

    public static void testResult() {
        Result4J res = new Result4J.Success("Data loaded");
        switch (res) {
            case Result4J.Success success -> System.out.println(success.getData());
            case Result4J.Error error -> System.out.println(error.getMessage());
            default -> System.out.println("Error");
        }
    }
}
