package br.gov.pr.adapar.saf.vo;

import java.io.Serializable;

import br.gov.pr.adapar.saf.entity.Fruit;
import io.quarkus.runtime.annotations.RegisterForReflection;


@RegisterForReflection
public class FruitVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;

    public FruitVO() {
    }
    
    /**
	 * Construtor que transforma um objeto Entity em um objeto VO.
	 */
    public FruitVO(Fruit fruit) {
    	this.id = fruit.getId();
    	this.name = fruit.getName();
    }
    
    /**
	 * Transforma um objeto VO em um objeto Entity.
	 */
    public Fruit toEntity() {
    	Fruit s = new Fruit();
    	s.setName(name);
    	s.setId(this.id);
    	
    	return s;
    }
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FruitVO [id=" + id + ", nome=" + name + "]";
	}
    
}
