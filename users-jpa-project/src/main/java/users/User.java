package users;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private Role role;

	public User() {
	}

	public User(Long id, String name, String password, Role role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public User(String name, String password, Role role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}
}
