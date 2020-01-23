package ua.kharkiv.storage.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "locality")
public class Locality extends AbstractEntity {

    private static final long serialVersionUID = 0L;

    public Locality() {
    }

    @Column(name = "name")
    private String name;
}
