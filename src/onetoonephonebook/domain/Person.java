package onetoonephonebook.domain;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Me
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    
    
    @OneToOne ( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
    private Phone phone;

    public Person() {
    }

    public Person(String Name, Phone phone) {
        this.name = Name;
        this.phone = phone;
    }

    public Person(String Name) {
        this.name = Name;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", Name=" + name + ", phone=" + phone + '}';
    }
    
    

}