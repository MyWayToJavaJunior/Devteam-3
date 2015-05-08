package com.epam.task6.domain.user;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private boolean available;
    private String qualification;
	private String email;
	private String password;
    private Role role;




    public User(){
        super();
    }
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}


    public User(int id, String firstName, String lastName, String qualification, String email, String password, Role role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }
    public String getEmail() {
		return email;
	}

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getQualification() {
        return qualification;
    }

    public Role getRole() {
        return role;
    }

    public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)){
            return false;
        }

        User user = (User) obj;

        if (available != user.available) {
            return false;
        }
        if (id != user.id) {
            return false;
        }
        if (role != user.role) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null){
            return false;
        }
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) {
            return false;
        }
        if (password != null ? !password.equals(user.password) : user.password != null) {
            return false;
        }
        if (qualification != null ? !qualification.equals(user.qualification)
                : user.qualification != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        if (firstName != null) {
            result = 31 * result + firstName.hashCode();
        }
        if (lastName != null) {
            result = 31 * result + lastName.hashCode();
        }
        if (available) {
            result = 31 * result + 1;
        }
        if (qualification != null) {
            result = 31 * result + qualification.hashCode();
        }
        if (email != null) {
            result = 31 * result + email.hashCode();
        }
        if (password != null) {
            result = 31 * result + password.hashCode();
        }
        if (role != null) {
            result = 31 * result + role.hashCode();
        }
        return result;
    }

}
