package ru.usachev.wishboard.entities;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "period_frequency")
  private int periodFrequency;

  @Column(name = "recurring_count")
  private int recurringCount;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "event")
  private Task task;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "period_type_id")
  private PeriodType periodType;


}
