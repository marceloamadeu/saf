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
@Table(name="endereco", schema = "public")
public class Endereco  implements Serializable {
	
	private static final long serialVersionUID = 7495066762428578160L;
	
    @Id
    @SequenceGenerator(name="ENDERECO_ID_SEQ", sequenceName="seq_endereco_id_endereco", schema = "public")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="ENDERECO_ID_SEQ")
    @Column(name = "id", nullable = false)
	private Integer id;
    
    @Column(name = "logradouro", nullable = false, length = 128)
    @NotNull(message="{endereco.logradouro.notnull}")
	@Size(min=5, max=128, message="{endereco.logradouro.size}")
    private String logradouro;
    
    @Column(name = "numero", nullable = false, length = 16)
    @NotNull(message="{endereco.numero.notnull}")
   	@Size(min=1, max=16, message="{endereco.numero.size}")
    private String numero;
    
    @Column(name = "complemento", length = 32)
    private String complemento;
    
    @Column(name = "bairro", nullable = false, length = 64)
    @NotNull(message="{endereco.bairro.notnull}")
	@Size(min=5, max=64, message="{endereco.bairro.size}")
    private String bairro;
    
    @Column(name = "cidade", nullable = false, length = 128)
    @NotNull(message="{endereco.cidade.notnull}")
   	@Size(min=5, max=128, message="{endereco.cidade.size}")
    private String cidade;
    
    @Column(name = "uf", nullable = false, length = 2)
    @NotNull(message="{endereco.uf.notnull}")
   	@Size(max=2, message="{endereco.uf.size}")
    private String uf;
    
    @Column(name = "cep", nullable = false, precision = 8, scale = 0)
    @NotNull(message="{endereco.cep.notnull}")
    private Integer cep;
    

    //public Endereco() {
    //}
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return this.logradouro;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }    
    
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getUf() {
        return this.uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getCep() {
        return this.cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    @Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
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
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", cep="
				+ cep + "]";
	}

    
}
