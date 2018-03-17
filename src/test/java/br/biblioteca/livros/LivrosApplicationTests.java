package br.biblioteca.livros;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.User;
import br.biblioteca.livros.repository.AutorRepository;
import br.biblioteca.livros.repository.EmprestimoRepository;
import br.biblioteca.livros.repository.LivroRepository;
import br.biblioteca.livros.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivrosApplicationTests {

	@Autowired
	AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmprestimoRepository emprestimoRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void buscaAutoresCadastrados() {

        Page<Autor> autores = this.autorRepository.findAll(new PageRequest(0, 10));
        assertThat(autores.getTotalElements()).isGreaterThan(1L);

    }

    @Test
    public void buscaAutorMAchadoDeAssis() {

        Autor autorNaoEncontrado = this.autorRepository.findByNome("josé");
        assertThat(autorNaoEncontrado).isNull();

        Autor autorEncontrado = this.autorRepository.findByNome("Machado de Assis");
        assertThat(autorEncontrado).isNotNull();
        assertThat(autorEncontrado.getNome()).isEqualTo("Machado de Assis");

    }

    @Test
    public void testeInserirAutor() {
        Autor autor = new Autor();

        autor.setNome("Zé das couves");

        autorRepository.save(autor);

        Autor autorEncontrado = this.autorRepository.findByNome("Zé das couves");

        assertThat(autorEncontrado).isNotNull();

        assertThat(autorEncontrado.getNome()).isEqualTo("Zé das couves");

    }

    @Test
    public void testeAlterarAutor() {
        Autor autorEncontrado = this.autorRepository.findByNome("Carl Sagan");

        assertThat(autorEncontrado).isNotNull();

        autorEncontrado.setNome("Carl2");

        autorRepository.save( autorEncontrado );

        Autor autorDeletado = this.autorRepository.findByNome("Carl2");

        assertThat(autorDeletado).isNotNull();

    }

    @Test
    public void testeDeletarAutor() {
        Autor autorEncontrado = this.autorRepository.findByNome("Graciliano Ramos");

        assertThat(autorEncontrado).isNotNull();

        autorRepository.delete( autorEncontrado );

        Autor autorDeletado = this.autorRepository.findByNome("Graciliano Ramos");

        assertThat(autorDeletado).isNull();

    }


    @Test
    public void buscaLivrosCadastrados() {

        Page<Livro> livros = this.livroRepository.findAll(new PageRequest(0, 10));
        assertThat(livros.getTotalElements()).isGreaterThan(1L);

    }

    @Test
    public void buscaLivroUseACabecaComJava() {

        Livro livroNaoEncontrado = this.livroRepository.findByNome("A marvada pinga");
        assertThat(livroNaoEncontrado).isNull();

        Livro livroEncontrado = this.livroRepository.findByNome("Java web com JSF");
        assertThat(livroEncontrado ).isNotNull();
        assertThat(livroEncontrado .getNome()).isEqualTo("Java web com JSF");

    }

    @Test
    public void testeInserirLivro() {
        Livro livro = new Livro();

        livro.setNome("Livro qualquer");
        livro.setQuantidade((long) 500);

        livroRepository.save(livro);

        Livro livroEncontrado = this.livroRepository.findByNome("Livro qualquer");

        assertThat(livroEncontrado).isNotNull();

    }

    @Test
    public void testeAlterarLivro() {
        Livro livroEncontrado = this.livroRepository.findByNome("Java como programar");

        assertThat(livroEncontrado).isNotNull();

        livroEncontrado.setNome("Javando");

        livroRepository.save( livroEncontrado );

        Livro livroAlterado = this.livroRepository.findByNome("Javando");

        assertThat(livroAlterado).isNotNull();

    }

    @Test
    public void testeDeletarLivro() {
        Livro livroEncontrado = this.livroRepository.findByNome("Use a cabeça Java");

        assertThat(livroEncontrado).isNotNull();

        livroRepository.delete( livroEncontrado );

        Livro livroDeletado = this.livroRepository.findByNome("Use a cabeça Java");

        assertThat(livroDeletado).isNull();

    }

    @Test
    public void testeEmprestarLivro() {

        Livro livro = this.livroRepository.findByNome("manoel bandeira");

        User usuario = userRepository.findByUsername("user");

        Emprestimo emprestimo = new Emprestimo();

        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

        emprestimo.setLivro(livro);
        emprestimo.setUser(usuario);
        emprestimo.setDataEmprestimo(timestamp);
        emprestimo.setDataDevolucao(null);

        emprestimoRepository.save(emprestimo);

        Emprestimo emprestimoEncontrado = this.emprestimoRepository.findByUser_Id((long) 1);

        assertThat(emprestimoEncontrado).isNotNull();
    }

    @Test
    public void testeDevolverLivro() {

        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

        Emprestimo emprestimoEncontrado = this.emprestimoRepository.findByUser_Id((long) 1);

        if(emprestimoEncontrado == null) {
            Livro livro = this.livroRepository.findByNome("Java web com JSF");

            User usuario = userRepository.findByUsername("user");

            Emprestimo emprestimo = new Emprestimo();

            emprestimo.setLivro(livro);
            emprestimo.setUser(usuario);
            emprestimo.setDataEmprestimo(timestamp);
            emprestimo.setDataDevolucao(null);

            emprestimoRepository.save(emprestimo);

            emprestimoEncontrado = this.emprestimoRepository.findByUser_Id((long) 1);
        }

        assertThat(emprestimoEncontrado).isNotNull();

        emprestimoEncontrado.setDataDevolucao(timestamp);

        emprestimoRepository.save(emprestimoEncontrado);

        assertThat(emprestimoEncontrado.getDataDevolucao()).isNotNull();
    }


}
