package onetoonephonebook.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Me
 */
@Entity
public class Phone {

    @Id
    private String phoneNumber;

    private String operator;

    public Phone() {
    }

    public Phone(String phoneNumber, String operator) {
        this.phoneNumber = phoneNumber;
        this.operator = operator;
    }
    
    

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Phone{" + "phoneNumber=" + phoneNumber + ", operator=" + operator + '}';
    }
    
    

}