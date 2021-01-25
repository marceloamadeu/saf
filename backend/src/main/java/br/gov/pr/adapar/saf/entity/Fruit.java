package br.gov.pr.adapar.saf.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="fruit", schema = "public")
public class Fruit implements Serializable { 
    
	private static final long serialVersionUID = -8795274013444946425L;
	
    @Id    
    @SequenceGenerator(name="FRUIT_ID_SEQ", sequenceName="seq_fruit_id", schema = "public", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="FRUIT_ID_SEQ")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique=true)
    @NotNull(message="{fruit.name.notnull}")
    @Size(min=3, max=128, message="{fruit.name.size}")
    private String name;
    

    public Fruit() {

    }

    public Fruit(Integer id, String name) {
        this.id = id;
        this.name = name;        
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fruit other = (Fruit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fruit [id=" + id + ", name=" + name + "]";
	}
    
}
