package org.comstudy21.ex06;

import java.util.Objects;
import java.util.Vector;

public class SaramDTO {
    private int idx;
    private String name;
    private String location;


    private String gend;
    private String email;
    private String phone;


    public SaramDTO() {
        this(0, "", "", "", "", "");
    }

    public SaramDTO(int idx, String name, String location, String gend, String email, String phone) {
        this.idx = idx;
        this.name = name;
        this.location = location;
        this.gend = gend;
        this.email = email;
        this.phone = phone;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGend() {
        return gend;
    }

    public void setGend(String gend) {
        this.gend = gend;
    }

    @Override
    public String toString() {
        return "[" + idx + ", " + name + ", " + location + ", " + gend + ", " + email + ", " + phone + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaramDTO saramDTO = (SaramDTO) o;
        return idx == saramDTO.idx && Objects.equals(name, saramDTO.name) && Objects.equals(location, saramDTO.location) && Objects.equals(gend, saramDTO.gend) && Objects.equals(email, saramDTO.email) && Objects.equals(phone, saramDTO.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, name, location, gend, email, phone);
    }

    public Vector toVector() {
        Vector vector = new Vector();
        vector.add(idx);
        vector.add(name);
        vector.add(location);
        vector.add(gend);
        vector.add(email);
        vector.add(phone);
        return vector;
    }
}
