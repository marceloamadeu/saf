package br.gov.pr.adapar.saf.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="funcaotecnica", schema = "public")
public class FuncaoTecnica implements Serializable { 
    
	private static final long serialVersionUID = -8795274013444946425L;
	
    @Id    
    @SequenceGenerator(name="FUNCAOTECNICA_ID_SEQ", sequenceName="seq_funcaotecnica_id", schema = "public", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="FUNCAOTECNICA_ID_SEQ")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descricao", nullable = false, unique=true)
    @NotNull(message="{funcaotecnica.descricao.notnull}")
    @Size(min=3, max=128, message="{funcaotecnica.descricao.size}")
    private String descricao;

    @Column(name = "datacadastro", nullable = false)
	@NotNull(message="{funcaotecnica.dataCadastro.notnull}")
	@Past(message="{funcaotecnica.dataCadastro.past}")
	private LocalDate dataCadastro;
    

    public FuncaoTecnica() {

    }

    public FuncaoTecnica(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;        
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }   
    
    public LocalDate getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
        FuncaoTecnica other = (FuncaoTecnica) obj;
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
		return "FuncaoTecnica [id=" + id + ", descricaomesorregiao=" + descricao + ", dataCadastro=" + dataCadastro + "]";
	}
    
}
