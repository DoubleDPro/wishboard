package ru.usachev.wishboard.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "pass")
  private String pass;

  @Column(name = "pass_salt")
  private String passSalt;

  @Column(name = "created_date")
  private Date createdDate;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
  private List<Wish> wishes;

  public Person(int id) {
    this.id = id;
  }

  public Person() {
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

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getPassSalt() {
    return passSalt;
  }

  public void setPassSalt(String pass_salt) {
    this.passSalt = pass_salt;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<Wish> getWishes() {
    return wishes;
  }

  public void setWishes(List<Wish> wishes) {
    this.wishes = wishes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return id == person.id && name.equals(person.name) && email.equals(person.email) && pass.equals(
        person.pass) && passSalt.equals(person.passSalt) && createdDate.equals(person.createdDate)
        && Objects.equals(wishes, person.wishes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, pass, passSalt, createdDate, wishes);
  }

  @Override
  public String toString() {
    return "Person{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", pass='" + pass + '\'' +
        ", pass_salt='" + passSalt + '\'' +
        ", createdDate=" + createdDate +
        ", wishes=" + wishes +
        '}';
  }
}