package ru.usachev.wishboard.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import ru.usachev.wishboard.json.WishDeserializer;

@Entity
@Table(name = "wish")
@JsonDeserialize(using = WishDeserializer.class)
public class Wish {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "due_date")
  private Date dueDate;

  @Column(name = "is_goal")
  private boolean isGoal;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "wish")
  private Image image;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "wish")
  private List<Task> tasks;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  public Wish() {
  }

  public Wish(String name, int personId) {
    this.name = name;
    this.person = new Person(personId);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public boolean isGoal() {
    return isGoal;
  }

  public void setGoal(boolean goal) {
    isGoal = goal;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wish wish = (Wish) o;
    return id == wish.id && isGoal == wish.isGoal && name.equals(wish.name) && dueDate.equals(
        wish.dueDate) && image.equals(wish.image) && Objects.equals(tasks, wish.tasks)
        && person.equals(wish.person);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, dueDate, isGoal, image, tasks, person);
  }

  @Override
  public String toString() {
    return "Wish{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", dueDate=" + dueDate +
        ", isGoal=" + isGoal +
        ", image=" + image +
        ", tasks=" + tasks +
        ", person=" + person +
        '}';
  }
}
