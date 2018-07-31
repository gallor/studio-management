package com.gallor.dao;

import com.gallor.model.Student;

import java.util.List;

/**
 * Created by gallor on 11/2/15.
 */

public interface StudentDAO {


    public Student find(int studentId) throws DAOException;

    public Student find(String name) throws DAOException;

    public Student find(String firstName, String lastName) throws DAOException;

    public void createStudent(Student student) throws IllegalArgumentException, DAOException;

    public void updateStudent(Student student) throws IllegalArgumentException, DAOException;

    public void deleteStudent(Student student) throws DAOException;

    public List<Student> listStudents() throws DAOException;

}
