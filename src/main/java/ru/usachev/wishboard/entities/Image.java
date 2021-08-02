package ru.usachev.wishboard.entities;

import java.util.Arrays;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Lob
  @Column(name = "data")
  private byte[] data;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "wish_id", referencedColumnName = "id")
  private Wish wish;

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public Wish getWish() {
    return wish;
  }

  public void setWish(Wish wish) {
    this.wish = wish;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return id == image.id && Arrays.equals(data, image.data) && wish.equals(image.wish);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, wish);
    result = 31 * result + Arrays.hashCode(data);
    return result;
  }

  @Override
  public String toString() {
    return "Image{" +
        "id=" + id +
        ", data=" + Arrays.toString(data) +
        ", wish=" + wish +
        '}';
  }
}
