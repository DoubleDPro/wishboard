package ru.usachev.wishboard.entities;

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
@Table(name = "period_type")
public class PeriodType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodType")
  private List<Event> events;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodType that = (PeriodType) o;
    return id == that.id && name.equals(that.name) && Objects.equals(events, that.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, events);
  }

  @Override
  public String toString() {
    return "PeriodType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", events=" + events +
        '}';
  }
}