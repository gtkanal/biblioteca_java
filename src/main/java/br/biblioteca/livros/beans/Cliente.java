package br.biblioteca.livros.beans;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="CAD_CLIENTE")
public class Cliente {
	
	@Id
	@GeneratedValue
	private Long idCliente;


	@NotNull
    @NotBlank
	@Size(min = 2, max = 100)
	private String nome;

	private String endereco;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Lob
	private String observacao;
	
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
	

}
