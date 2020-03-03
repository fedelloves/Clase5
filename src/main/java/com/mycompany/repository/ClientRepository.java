/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.repository;

import com.mycompany.model.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientRepository {
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            
          
            insert();
            update();
            delete();
        } catch (Exception e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public List<Client> getClients() {
        
        List<Client> clients = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/servicio", "root", "")) {
            
            String sql = "SELECT * FROM client";

            // Utilizamos un Statement para ejecutar nuestra consulta
            Statement statement = connection.createStatement();

            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    // Podemos acceder por nombre o por índice, empezando desde 1
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String lastName = rs.getString("lastName");
                    int age = rs.getInt("age");
                    
                    Client c = new Client();
                    
                    c.setId(id);
                    c.setName(name);
                    c.setLastName(lastName);
                    c.setAge(age);
                    
                    clients.add(c);

                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }

    private static void insert() throws SQLException {
        try (Connection connection = DriverManager.getConnection("", "root", "")) {
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO alumnos (nombre, apellido, dni, fecha_nacimiento) VALUES ('" + "Name" + "', '" + "LastName" + "', '" + "12345678" + "', '1985-06-05')";

            /*
			 * Cuando ejecutamos el query podemos pasar el segundo parámetro como "Statement.RETURN_GENERATED_KEYS" 
			 * para obtener el ID auto-generado, siempre y cuando el motor soporte esto, podemos verificar esto
			 * por medio de DatabaseMetaData, si no pasamos el segundo parámetro no podemos obtener
			 * el ID auto-generado
             */
            int rowsAffected = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            System.out.println(rowsAffected + " Row Inserted");
            while (generatedKeys.next()) {
                System.out.println("Last Id: " + generatedKeys.getInt(1));
            }
        }
    }

    private static void update() throws SQLException {
        try (Connection connection = DriverManager.getConnection("", "root", "")) {

            Statement statement = connection.createStatement();

            int maxId = getMaxId(statement);

            String sql = "UPDATE alumnos SET nombre = '" + "NewName" + "', apellido = '" + "NewLastName" + "' WHERE id_alumno = " + maxId;

            int rowsAffected = statement.executeUpdate(sql);
            System.out.println(rowsAffected + " Row Updated");
        }
    }

    private static void delete() throws SQLException {
        try (Connection connection = DriverManager.getConnection("", "root", "")) {

            Statement statement = connection.createStatement();

            int maxId = getMaxId(statement);

            String sql = "DELETE FROM alumnos WHERE id_alumno = " + maxId;

            int rowsAffected = statement.executeUpdate(sql);
            System.out.println(rowsAffected + " Row Deleted");
        }
    }

    private static int getMaxId(Statement statement) throws SQLException {
        int maxId = -1;
        ResultSet rs = statement.executeQuery("SELECT MAX(id_alumno) AS max_id FROM alumnos");
        while (rs.next()) {
            maxId = rs.getInt("max_id");
        }
        return maxId;
    }

}
