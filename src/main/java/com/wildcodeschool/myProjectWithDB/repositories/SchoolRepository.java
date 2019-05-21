package com.wildcodeschool.myProjectWithDB.repositories;

import com.wildcodeschool.myProjectWithDB.entities.School;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;


public class SchoolRepository {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "Booba92izi*";


    public static School selectById(int id) {
        try(
                Connection connection = DriverManager.getConnection(
                        DB_URL, DB_USER, DB_PASSWORD
                );
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM school WHERE id=?"
                );
        ) {
            statement.setInt(1, id);

            try(
                    ResultSet resulSet = statement.executeQuery();
            ) {
                if(resulSet.next()){
                    String firstName = resulSet.getString("name");
                    int capacity = resulSet.getInt("capacity");
                    String country = resulSet.getString("country");

                    return new School(id,firstName,capacity,country);
                }
                else {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR, "unknown id in table school"
                    );
                }
            }
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }

    public static int insert(
            String schoolName,
            int schoolCapacity,
            String schoolCountry
    ){
        try (
                Connection connection = DriverManager.getConnection(
                        DB_URL,DB_USER,DB_PASSWORD
                );
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO school (name, capacity, country) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ){
            statement.setString(1,schoolName);
            statement.setInt(2,schoolCapacity);
            statement.setString(3,schoolCountry);

            if(statement.executeUpdate() != 1) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, "failed to insert data"
                );
            }

            try(
                    ResultSet generatedKeys = statement.getGeneratedKeys()
                    ) {
                if (generatedKeys.next()) {
                    return generatedKeys.getByte(1);
                } else {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR, "failed to get inserted id"
                    );
                }
            }
        } catch (SQLException e) {
            throw  new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "failed to get inserted id"
                    );
        }
    }

    public static int delete(int id) {
        try(
                Connection connection = DriverManager.getConnection(
                        DB_URL, DB_USER, DB_PASSWORD
                );
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM school WHERE id=?"

                );
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }
}
