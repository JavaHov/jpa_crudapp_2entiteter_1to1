
package onetoonephonebook;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import onetoonephonebook.domain.Person;
import onetoonephonebook.domain.Phone;


public class OneToOnePhoneBook {

    
    static Scanner sc = new Scanner(System.in);
    static boolean loop = true;
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public static void main(String[] args) {
        
        while(loop) {
            
            menu();
        }
        
        
    }
    
        public static void menu() {
        System.out.println("\n=================================");
        System.out.println("              Menu               ");
        System.out.println("=================================");
        System.out.println("1.Show all records.");
        System.out.println("2.Show all names");
        System.out.println("3.Show all phonenumbers.");
        System.out.println("4.Show owner of a phonenumber.");
        System.out.println("5.Show phonenumber by name of owner.");
        System.out.println("6.Show phonenumber by id of owner.");
        System.out.println("7.Add a phone.");
        System.out.println("8.Add a person.");
        System.out.println("9.Connect exisiting number to existing person.");
        System.out.println("10.Add a person and phonenumber.");
        System.out.println("11.Show person by name");
        System.out.println("12.Show person by Id");
        System.out.println("13.Disconnect phone from person");
        System.out.println("14.Delete person,");
        System.out.println("\n0.Exit");
        System.out.println("=================================");

        System.out.print("\nMake your choice:");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                showAllRecords();
                break;

            case 2:
                showAllNames();
                break;

            case 3:
                showAllPhoneNumbers();
                break;

            case 4:
                showOwnerOfPhoneNr();

                break;

            case 5:
                showPhoneNrOfAPersonByName();
                break;

            case 6:
                showPhoneNrOfAPersonById();
                break;

            case 7:
                addPhone();

                break;

            case 8:
                addPerson();
                break;

            case 9:
                connectExistingPhoneToExistingPerson();

                break;

            case 10:
                addPhoneRecord();
                break;

            case 11:
                findPersonByName();
                break;

            case 12:
                findPersonById();
                break;

            case 13:
                disconnectPhonefromPerson();
                break;
            case 14:
                deletePerson();
                break;

            case 0:
                loop = false;
                sc.close();
                emf.close();
                break;

            default:
                System.out.println("No such choice. Try again!");

        }

    }

        
    public static void addPhone() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Phone number:");
        String phonenumber = sc.nextLine();
        
        System.out.println("Operator:");
        String operator = sc.nextLine();
        
        em.getTransaction().begin();
        em.persist(new Phone(phonenumber, operator));
        em.getTransaction().commit();

        em.close();
        
    }
        
    public static void addPerson() {
        
        EntityManager em = emf.createEntityManager();
        System.out.println("Name:");
        String name = sc.nextLine();
        
        em.getTransaction().begin();
        em.persist(new Person(name));
        em.getTransaction().commit();
        
        em.close();
    }

    public static void showAllRecords() {
        
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        
        persons.forEach(p -> System.out.println(p));
        em.close();
    }

    public static void connectExistingPhoneToExistingPerson() {
        
        EntityManager em = emf.createEntityManager();
        System.out.println("Person ID:");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Phonenumber:");
        String phonenumber = sc.nextLine();
        
        
        Person person = em.find(Person.class, id);
        
        Phone phone = em.find(Phone.class, phonenumber);
        
        em.getTransaction().begin();
        person.setPhone(phone);
        em.getTransaction().commit();
        
        em.close();
        
    }

    public static void disconnectPhonefromPerson() {
        
        EntityManager em = emf.createEntityManager();
        System.out.println("Person ID:");
        int id = sc.nextInt();
        sc.nextLine();
        
        Person person = em.find(Person.class, id);
        
        em.getTransaction().begin();
        person.setPhone(null);
        em.getTransaction().commit();
        
        em.close();
    }

    public static void showAllNames() {
        
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<String> query = em.createQuery("SELECT p.name FROM Person p", String.class);
        
        List<String> names = query.getResultList();
        
        names.forEach(name -> System.out.println(name));
    }

    public static void showAllPhoneNumbers() {
        
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<String> query = em.createQuery("SELECT p.phoneNumber FROM Phone p", String.class);
        
        List<String> numbers = query.getResultList();
        
        numbers.stream().forEach(System.out::println);
    }

    public static void showPhoneNrOfAPersonById() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("ID:");
        int id = sc.nextInt();
        sc.nextLine();
        
        Person person = em.find(Person.class, id);
        
        System.out.println(person);
        
    }

    public static void showPhoneNrOfAPersonByName() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Name of Person:");
        String name = sc.nextLine();
        
        
        // Man kan inte ha mellanslag här under efter =: ... då blir det Exception.
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name=:n", Person.class);
        query.setParameter("n", name);
        
        List<Person> resultList = query.getResultList();
        
        resultList.stream().forEach(System.out::println);
        
        em.close();
    }

    public static void showOwnerOfPhoneNr() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Enter phone number:");
        String number = sc.nextLine();
       
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.phone.phoneNumber=:number", Person.class);
        query.setParameter("number", number);
        
        Person p = query.getSingleResult();
        
        System.out.println(p);
        em.close();
    }

    public static void addPhoneRecord() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Name:");
        String name = sc.nextLine();
        
        System.out.println("Phone number:");
        String number = sc.nextLine();
        
        System.out.println("Operator:");
        String operator = sc.nextLine();
        
        Person person = new Person(name);
        Phone phone = new Phone(number, operator);
        
        person.setPhone(phone);
        
        em.getTransaction().begin();
        
        em.persist(person);
        em.persist(phone);
        // person.setPhone(phone); // Funkar också...
   
        em.getTransaction().commit();
        
        em.close();
    }

    public static void deletePerson() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("ID:");
        int id = sc.nextInt();
        sc.nextLine();
        Person p = em.find(Person.class, id);
        
        em.getTransaction().begin();
        
        em.remove(p);
        
        em.getTransaction().commit();
        em.close();
        
    }

    public static void findPersonByName() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Name:");
        String n = sc.nextLine();
        
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name=:name", Person.class);
        query.setParameter("name", n);
        
        List<Person> resultlist = query.getResultList();
        resultlist.forEach(p -> System.out.println(p));
        
        em.close();
    }

    public static void findPersonById() {
        
        EntityManager em = emf.createEntityManager();
        
        System.out.println("ID:");
        int id = sc.nextInt();
        sc.nextLine();
        
        Person p = em.find(Person.class, id);
        System.out.println(p);
        em.close();
    }

    
}
