package com.epam.task6.domain.project;

/**
 * Created by olga on 19.04.15.
 */
public class Job {
    private int id;
    private String name;
    private int specialist;
    private String qualification;
    private int specification;
    private int cost;
    private String time;

    public Job(){
        super();
    }

    public Job(String name, int specialist, String qualification, int specification, int cost, String time) {
        this.name = name;
        this.specialist = specialist;
        this.qualification = qualification;
        this.specification = specification;
        this.cost = cost;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSpecialist() {
        return specialist;
    }

    public String getQualification() {
        return qualification;
    }

    public int getSpecification() {
        return specification;
    }

    public int getCost() {
        return cost;
    }

    public String getTime() {
        return time;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialist(int specialist) {
        this.specialist = specialist;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setSpecification(int specification) {
        this.specification = specification;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        if (!super.equals(obj)){
            return false;
        }

        Job job = (Job) obj;

        if (cost != job.cost){
            return false;
        }
        if (id != job.id){
            return false;
        }
        if (specialist != job.specialist){
            return false;
        }
        if (specification != job.specification) {
            return false;
        }
        if (name != null ? !name.equals(job.name) : job.name != null){
            return false;
        }
        if (qualification != null ? !qualification.equals(job.qualification) :
                job.qualification != null){
            return false;
        }
        if (time != null ? !time.equals(job.time) : job.time != null){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + specialist;
        result = 31 * result + specification;
        result = 31 * result + cost;
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        if (qualification != null) {
            result = 31 * result + qualification.hashCode();
        }
        if (time != null) {
            result = 31 * result + time.hashCode();
        }
        return result;
    }
}
