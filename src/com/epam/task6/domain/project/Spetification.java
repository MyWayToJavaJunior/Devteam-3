package com.epam.task6.domain.project;

/**
 * Created by olga on 19.04.15.
 */
public class Spetification {
    private int id;
    private int user_id;
    private int jobs;
    private String name;
    private int status;

    public Spetification(){
        super();
    }

    public Spetification(String name, int user_id, int jobs, int status) {
        this.name = name;
        this.user_id = user_id;
        this.jobs = jobs;
        this.status = status;
    }

    public Spetification( String name, int jobs){

        this.name = name;
        this.jobs = jobs;
    }

    public Spetification(int id, int jobs, String name) {
        this.id = id;
        this.jobs = jobs;
        this.name = name;
    }

    public Spetification(int jobs, int user_id, String name, int status) {
        this.jobs = jobs;
        this.user_id = user_id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getJobs() {
        return jobs;
    }

    public String getName() {
        return name;
    }

    public int  getStatus() {
        return status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobs(int jobs) {
        this.jobs = jobs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        if (!super.equals(obj)){
            return false;
        }

        Spetification that = (Spetification) obj;

        if (id != that.id){
            return false;
        }
        if (jobs != that.jobs){
            return false;
        }
        if (status != that.status){
            return false;
        }
        if (user_id != that.user_id){
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id;
        result = 31 * result + jobs;
        if (name!=null){
            result = 31 * result + name.hashCode();
        }
        else {
            result = 31 * result;
        }
        //result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
