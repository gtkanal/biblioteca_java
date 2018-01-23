package br.biblioteca.livros.beans;

import com.sun.istack.internal.Nullable;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Emprestimo {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Nullable
	private Date dataEmprestimo;

    @Temporal(TemporalType.TIMESTAMP)
	@Nullable
	private Date dataDevolucao;
	
	@ManyToOne
	private Livro livro;

    @ManyToOne
    private User user;

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
	

}
