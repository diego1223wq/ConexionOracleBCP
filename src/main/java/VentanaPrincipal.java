import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class VentanaPrincipal extends JFrame {

    private JButton btnListar;
    private JTextArea areaTexto;

    public VentanaPrincipal() {
        setTitle("Clientes Web - Conexión Oracle");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        btnListar = new JButton("Listar Clientes");
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        add(btnListar, BorderLayout.NORTH);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarDatos();
            }
        });
    }

    private void listarDatos() {
    String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
    String usuario = "SYSTEM";
    String clave = "SYSTEM";
       
    try (Connection con = DriverManager.getConnection(url, usuario, clave);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT CODIGO, NOMBRE, APELLIDO, TIPO_DOCUMENTO, DOCUMENTO FROM CLIENTE_WEB")) {

        areaTexto.setText(""); 
        while (rs.next()) {
            String fila = rs.getString("CODIGO") + " - " +
                          rs.getString("NOMBRE") + " " +
                          rs.getString("APELLIDO") + " - " +
                          rs.getString("TIPO_DOCUMENTO") + ": " +
                          rs.getString("DOCUMENTO") + "\n";
            areaTexto.append(fila);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}
  

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new LoginFrame().setVisible(true);
    });
}
}
