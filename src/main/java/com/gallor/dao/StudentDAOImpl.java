package com.gallor.dao;

import com.gallor.model.Student;
import com.gallor.utilities.DAOUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gallor on 11/4/15.
 */

public class StudentDAOImpl implements StudentDAO {

    // Constants
    private static final String SQL_FIND_BY_ID = "SELECT * FROM student WHERE studentId = ?";
    private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM student ORDER BY studentId";
    private static final String SQL_INSERT = "INSERT INTO student (studentId, firstName, lastName, phone) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE student SET firstName = ?, lastName = ?, phone = ? WHERE studentId = ?";
    private static final String SQL_DELETE = "DELETE FROM student WHERE studentId = ?";
    private static final String SQL_EXIST_NAME = "SELECT * FROM student WHERE firstName = ? OR lastName = ?";

    // Vars

    private DataSource dataSource = null;

    // Constructor
    StudentDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Student find(String sql, Object... values) {
        Student student = null;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = DAOUtil.preparedStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery();
        ) {
            if(resultSet.next()) {
                student = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return student;
    }

    @Override
    public Student find(int studentId) throws DAOException {
        return find(SQL_FIND_BY_ID, studentId);
    }

    @Override
    public Student find(String name) throws DAOException {
        return find(SQL_EXIST_NAME, name);
    }

    @Override
    public Student find(String firstName, String lastName) throws DAOException {
        return find(SQL_EXIST_NAME, firstName, lastName);
    }

    public void createStudent(Student student) throws IllegalArgumentException{

        if (student.getStudentId() != 0) {
            throw new IllegalArgumentException("Student is already created, the student ID is not null");
        }
        Object[] values = {
            student.getFirstName(),
            student.getLastName(),
            student.getPhone()
        };

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = DAOUtil.preparedStatement(connection, SQL_INSERT, true, values);
                ){
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating Student failed. No rows affected");
            }

            try ( ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setStudentId(generatedKeys.getInt(1));
                } else {
                    throw new DAOException("Creating Student failed. No generated key obtained");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    public void updateStudent(Student student) throws IllegalArgumentException, DAOException {
        if(student.getStudentId() == 0) {
            throw new IllegalArgumentException("User is not yet created. The Student ID is null");
        }

        Object[] values = {
            student.getStudentId(),
            student.getFirstName(),
            student.getLastName(),
            student.getPhone()
        };

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = DAOUtil.preparedStatement(connection, SQL_UPDATE, false, values);
            ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Student update failed. No rows affected");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void deleteStudent(Student student) throws DAOException {
        if(student.getStudentId() == 0) {
            throw new IllegalArgumentException("Student does not exist so cannot be deleted. Student ID is null");
        }

        Object[] value = {
            student.getStudentId()
        };

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = DAOUtil.preparedStatement(connection, SQL_DELETE, false, value);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting Student failed. No rows were affected");
            } else {
                student.setStudentId(0);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Student> listStudents() throws DAOException {
        List<Student> students = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
            ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                students.add(map(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return students;
    }

    // Helpers
    private static Student map(ResultSet resultSet) throws SQLException {
        Student student = new Student(
                resultSet.getInt("studentId"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getLong("Phone")
        );
        return student;
    };
}
