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

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="mesorregiao", schema = "public")
public class Mesorregiao implements Serializable { 
    
	private static final long serialVersionUID = -8795274013444946425L;
	
    @Id    
    @SequenceGenerator(name="MESORREGIAO_ID_SEQ", sequenceName="seq_mesoregiao_id", schema = "public", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="MESORREGIAO_ID_SEQ")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descricaomesorregiao", nullable = false, unique=true)
    @NotNull(message="{mesorregiao.descricaoMesorregiao.notnull}")
    @Size(min=3, max=128, message="{mesorregiao.descricaoMesorregiao.size}")
    private String descricaoMesorregiao;
        

    public Mesorregiao() {

    }

    public Mesorregiao(Integer id, String descricaoMesorregiao) {
        this.id = id;
        this.descricaoMesorregiao = descricaoMesorregiao;        
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoMesorregiao() {
        return this.descricaoMesorregiao;
    }

    public void setDescricaoMesorregiao(String descricaoMesorregiao) {
        this.descricaoMesorregiao = descricaoMesorregiao;
    }    

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        Mesorregiao other = (Mesorregiao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

	@Override
	public String toString() {
		return "Mesorregiao [id=" + id + ", descricaomesorregiao=" + descricaoMesorregiao + "]";
	}
    
}
