package get.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the departman database table.
 * 
 */
@Entity
@NamedQuery(name="Departman.findAll", query="SELECT d FROM Departman d")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departman implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTMAN_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMAN_ID_GENERATOR")
	private Integer id;

	private String naziv;

	private String oznaka;

	//bi-directional many-to-one association to Fakultet
	@ManyToOne
	@JoinColumn(name="fakultet")
	private Fakultet fakultetBean;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="departmanBean")
	@JsonIgnore
	private List<Student> students;

	public Departman() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Fakultet getFakultetBean() {
		return this.fakultetBean;
	}

	public void setFakultetBean(Fakultet fakultetBean) {
		this.fakultetBean = fakultetBean;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setDepartmanBean(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setDepartmanBean(null);

		return student;
	}

}