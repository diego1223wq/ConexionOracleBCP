import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        btnLogin = new JButton("Ingresar");
        add(new JLabel()); // espacio
        add(btnLogin);

        btnLogin.addActionListener(e -> validarLogin());
    }

    private void validarLogin() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtPassword.getPassword());

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ? AND ESTADO = 'A'")) {

            stmt.setString(1, usuario);
            stmt.setString(2, clave);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dispose(); 
                new VentanaPrincipal().setVisible(true); 
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
        }
    }
}
