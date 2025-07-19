package edu.ventas.conexionoraclebcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String usuario = "SYSTEM"; // 
        String contraseña = "SYSTEM"; //
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conexión exitosa a Oracle 19c!");
            conexion.close();
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}
