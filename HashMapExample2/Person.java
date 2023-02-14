public class Person {

    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    // hashCode = hashFunction
    @Override
    public int hashCode() {
        // it calculates an array index based on keys (keys == Person objects)
        // avoid clustering with prime numbers
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + age;
        return result;
    }

    // THERE ARE SOME RULES:
    // 1.) if two objects are equal, then they must have the same hash code
    // 2.) if two objects have the same hash code, they may or may not be equal

    // because in a hashmap it may happen (collisions)
    // that we hve to find the item (Person) in a Linked List
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (age != other.age)
            return false;
        return true;
    } 
}
