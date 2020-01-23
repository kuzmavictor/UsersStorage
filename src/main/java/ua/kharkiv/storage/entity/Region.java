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
@DynamicUpdate
@DynamicInsert
@Table(name = "region")
public class Region extends AbstractEntity {

    private static final long serialVersionUID = 0L;

    public Region() {
    }

    @Column(name = "name")
    private String name;
}
