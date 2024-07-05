package pl.dmcs.springbootjsp_iwa.model;

import jakarta.persistence.*;

@Entity
public class Prescription {
    @Id
    @GeneratedValue
    private long id;
    private String telephone;
    private String note;



    @ManyToOne
    private Visit visit;



    public long getId() {
        return id;
    }



    public String getTelephone() {
        return telephone;
    }

    public String getNote() {
        return note;
    }

    public Visit getVisit() {
        return visit;
    }



    public void setId(long id) {
        this.id = id;
    }


    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setNote(String note) {
        this.note = note;
    }




    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}

