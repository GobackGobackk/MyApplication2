package com.example.name.model;

public class Spinnerrr {
    private String contact_name;
    private String contact_id;

    public Spinnerrr() {
    }

    public Spinnerrr(String contact_name, String contact_id) {
        this.contact_name = contact_name;
        this.contact_id = contact_id;
    }

    public String getSpinnerrr_name() {
        return contact_name;
    }

    public void setSpinnerrr_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getSpinnerrr_id() {
        return contact_id;
    }

    public void setSpinnerrr_id(String contact_id) {
        this.contact_id = contact_id;
    }
    @Override
    public String toString() {
        return contact_name;
    }
}
