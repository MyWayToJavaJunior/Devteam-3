package com.epam.task6.domain.project;

/**
 * Created by olga on 19.04.15.
 */
public class Bill {
    private int id;
    private String description;
    private int cost;
    private int project_id;

    public Bill(){
        super();
    }


    ////добавить описание сюда и в бл
    public Bill(int id, int cost, int project_id) {
        this.id = id;
        this.cost = cost;
        this.project_id = project_id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getProject_id() {
        return project_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
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
        Bill bill = (Bill) obj;
        if (cost != bill.cost){
            return false;
        }
        if (id != bill.id){
            return false;
        }
        if (project_id != bill.project_id){
            return false;
        }
        if (description != null ? !description.equals(bill.description) :
                bill.description != null){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        if (description != null) {
            result = 31 * result + description.hashCode();
        }
        result = 31 * result + cost;
        result = 31 * result + project_id;
        return result;
    }
}
