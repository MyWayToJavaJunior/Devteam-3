package com.epam.task6.domain.project;

/**
 * Created by olga on 19.04.15.
 */
public class Project {
    private int id;
    private String name;
    private Spetification specification;
    private int spetification_id;
    private String time;
    private int employees;
    private int manager;
    private int status;

    public Project(){
        super();
    }

    public Project(int id, String name, int spetification_id, int manager) {
        this.id = id;
        this.name = name;
        this.spetification_id = spetification_id;
        this.manager = manager;
    }

    public Project(int id, String name, int spetification_id, String time, int employees, int manager, int status) {
        this.id = id;
        this.name = name;
        this.spetification_id = spetification_id;
        this.time = time;
        this.employees = employees;
        this.manager = manager;
        this.status = status;
    }

    public Project(String name, int spetification_id, String time, int employees, int manager, int status) {
        this.name = name;
        this.spetification_id = spetification_id;
        this.time = time;
        this.employees = employees;
        this.manager = manager;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Spetification getSpecification() {
        return specification;
    }

    public String getTime() {
        return time;
    }

    public int getEmployees() {
        return employees;
    }

    public int getManager() {
        return manager;
    }

    public int getStatus() {
        return status;
    }

    public int getSpetification_id() {
        return spetification_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecification(Spetification specification) {
        this.specification = specification;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSpetification_id(int spetification_id) {
        this.spetification_id = spetification_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)){
            return false;
        }

        Project project = (Project) obj;

        if (employees != project.employees){
            return false;
        }
        if (id != project.id){
            return false;
        }
        if (manager != project.manager){
            return false;
        }
        if (spetification_id != project.spetification_id){
            return false;
        }
        if (status != project.status){
            return false;
        }
        if (name != null ? !name.equals(project.name) : project.name != null) {
            return false;
        }
        if (specification != null ? !specification.equals(project.specification)
                : project.specification != null) {
            return false;
        }
        if (time != null ? !time.equals(project.time) : project.time != null){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + employees;
        result = 31 * result + manager;
        result = 31 * result + status;
        result = 31 * result + spetification_id;
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        if (specification != null) {
            result = 31 * result + specification.hashCode();
        }
        if (time != null) {
            result = 31 * result + time.hashCode();
        }
        return result;
    }
}
