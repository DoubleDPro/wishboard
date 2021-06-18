package ru.usachev.wishboard.entities;

import java.util.Date;
import java.util.Objects;

@Entity
@Table (name = "Person")

public class Person {
    int id;
    String name;
    String email;
    Date regDate;
    String psswd;
    String psswd_salt;

    public Person (String name , String email, Date regDate, String psswd, String psswd_salt){
        this.name = name;
        this.email = email;
        this.regDate = regDate;
        this.psswd = psswd;
        this.psswd_salt = psswd_salt;
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public  void setEmail(String email){
        this.email = email;

    }
    public Date getRegDate(){
        return regDate;
    }

    public void setRegDate(Date regDate){
        this.regDate = regDate;
    }

    public String getPsswd(){
        return psswd;
    }

    public  void setPsswd (String psswd){
        this.psswd = psswd;
    }

    public  String getPsswd_salt(){
        return psswd_salt;
    }

    public void setPsswd_salt(String psswd_salt){
        this.psswd_salt = psswd_salt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, regDate, psswd, psswd_salt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id && name.equals(person.name) && email.equals(person.email) && regDate.equals(person.regDate) && psswd.equals(person.psswd) && psswd_salt.equals(person.psswd_salt);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", regDate=" + regDate +
                ", psswd='" + psswd + '\'' +
                ", psswd_salt='" + psswd_salt + '\'' +
                '}';
    }

}


