package br.biblioteca.livros.beans;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 2, max = 100)
	private String username;

	@NotNull
	@NotBlank
	private String passsword;

	@OneToMany(mappedBy="usuario")
	private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy="usuario")
    private List<Role> roles = new ArrayList<>();

    private String passwordConfirm;


    public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}
	
	public User() { }

	public User(String username, String passsword) {
		this.username = username;
		this.passsword = passsword;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", passsword=" + passsword + ", roles=" + roles + "]";
	}

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
