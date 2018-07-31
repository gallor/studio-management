package com.gallor.model;

/**
 * Created by gallor on 11/3/15.
 */

public final class Student {

    private static final long serialversionUID = 1L;

    private int studentId;
    private String firstName;
    private String lastName;
    private long phone;


    // Constructor

    public Student(int studentId, String firstName, String lastName, long phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.studentId = studentId;
    }

    public Student(String firstName, String lastName, long phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.studentId = 0;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    };

    public int getStudentId() {
        return studentId;
    }

    public long getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("Student[studentId=%d,firstName=%s,lastName=%s,phone=%d]",
                studentId, firstName, lastName, phone);
    }


}
