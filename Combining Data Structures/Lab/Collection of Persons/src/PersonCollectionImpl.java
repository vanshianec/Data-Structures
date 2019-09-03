import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    private HashMap<String, Person> peopleByEmail = new HashMap<>();
    private HashMap<String, TreeSet<Person>> peopleByEmailDomain = new HashMap<>();
    private HashMap<String, TreeSet<Person>> peopleByNameAndTown = new HashMap<>();
    private TreeMap<Integer, TreeSet<Person>> peopleByAge = new TreeMap<>();
    private HashMap<String, TreeMap<Integer, TreeSet<Person>>> peopleByTownAndAge = new HashMap<>();

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (!peopleByEmail.containsKey(email)) {
            Person person = new Person(email, name, age, town);
            peopleByEmail.put(email, person);
            addPeopleByEmailDomain(email, person);
            addPeopleByNameAndTown(name, town, person);
            addPeopleByAge(age, person);
            addPeopleByTownAndAge(age, town, person);
            return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        return peopleByEmail.size();
    }

    @Override
    public Person findPerson(String email) {
        if (peopleByEmail.containsKey(email)) {
            return peopleByEmail.get(email);
        }
        return null;
    }

    @Override
    public boolean deletePerson(String email) {
        Person person = peopleByEmail.remove(email);
        if (person == null) {
            return false;
        }
        String emailDomain = getEmailDomain(email);
        peopleByEmailDomain.get(emailDomain).remove(person);
        String nameAndTown = person.getName() + "|" + person.getTown();
        peopleByNameAndTown.get(nameAndTown).remove(person);
        int age = person.getAge();
        peopleByAge.get(age).remove(person);
        String town = person.getTown();
        peopleByTownAndAge.get(town).get(age).remove(person);
        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        if (peopleByEmailDomain.containsKey(emailDomain)) {
            return peopleByEmailDomain.get(emailDomain);
        }
        return new ArrayList<>();
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        String nameAndTown = name + "|" + town;
        if (peopleByNameAndTown.containsKey(nameAndTown)) {
            return peopleByNameAndTown.get(nameAndTown);
        }
        return new ArrayList<>();
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        SortedMap<Integer, TreeSet<Person>> peopleInAgeRange = peopleByAge.subMap(startAge, endAge + 1);
        List<Person> result = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<Person>> entry : peopleInAgeRange.entrySet()) {
            for (Person person : entry.getValue()) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        if (peopleByTownAndAge.containsKey(town)) {
            SortedMap<Integer, TreeSet<Person>> peopleInAgeRange = peopleByTownAndAge.get(town).subMap(startAge, endAge + 1);
            List<Person> result = new ArrayList<>();
            for (Map.Entry<Integer, TreeSet<Person>> entry : peopleInAgeRange.entrySet()) {
                for (Person person : entry.getValue()) {
                    result.add(person);
                }
            }
            return result;
        }
        return new ArrayList<>();
    }

    private void addPeopleByNameAndTown(String name, String town, Person person) {
        String nameAndTown = name + "|" + town;
        if (!peopleByNameAndTown.containsKey(nameAndTown)) {
            peopleByNameAndTown.put(nameAndTown, new TreeSet<>());
        }
        peopleByNameAndTown.get(nameAndTown).add(person);
    }

    private void addPeopleByEmailDomain(String email, Person person) {
        String emailDomain = getEmailDomain(email);
        if (!peopleByEmailDomain.containsKey(emailDomain)) {
            peopleByEmailDomain.put(emailDomain, new TreeSet<>());
        }
        peopleByEmailDomain.get(emailDomain).add(person);
    }

    private void addPeopleByAge(int age, Person person) {
        if (!peopleByAge.containsKey(age)) {
            peopleByAge.put(age, new TreeSet<>());
        }
        peopleByAge.get(age).add(person);
    }

    private void addPeopleByTownAndAge(int age, String town, Person person) {
        if (!peopleByTownAndAge.containsKey(town)) {
            peopleByTownAndAge.put(town, new TreeMap<>());
        }
        if (!peopleByTownAndAge.get(town).containsKey(age)) {
            peopleByTownAndAge.get(town).put(age, new TreeSet<>());
        }
        peopleByTownAndAge.get(town).get(age).add(person);
    }

    private String getEmailDomain(String email) {
        return email.split("@")[1];
    }
}
