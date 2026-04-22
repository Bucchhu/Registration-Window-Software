import javax.swing.*;  
// Swing library import kar rahe hain taaki GUI components (JFrame, JButton, JLabel etc.) use kar sakein

import java.sql.*;  
// SQL package import kar rahe hain taaki MySQL database connect ho sake


public class AdminLogin {

    JFrame frame;  
    // JFrame object declare kiya jo admin login window ko represent karega


    public AdminLogin() {

        frame = new JFrame("Admin Login");  
        // admin login ke liye naya window create kiya title ke saath


        JLabel userLabel = new JLabel("Username:");  
        // Username label create kiya

        userLabel.setBounds(50, 50, 100, 30);  
        // label ki position aur size set kiya


        JTextField userField = new JTextField();  
        // username input lene ke liye text field

        userField.setBounds(150, 50, 150, 30);


        JLabel passLabel = new JLabel("Password:");  
        // Password label create kiya

        passLabel.setBounds(50, 100, 100, 30);


        JPasswordField passField = new JPasswordField();  
        // password secure input ke liye special field (hidden characters show karta hai)

        passField.setBounds(150, 100, 150, 30);


        JCheckBox showPassword = new JCheckBox("Show Password");  
        // checkbox create kiya password visible / hidden toggle karne ke liye

        showPassword.setBounds(150, 130, 150, 30);


        JButton loginBtn = new JButton("Login");  
        // login button create kiya jo admin credentials verify karega

        loginBtn.setBounds(120, 200, 120, 40);


        JButton backBtn = new JButton("Back");  
        // back button create kiya main screen par wapas jane ke liye

        backBtn.setBounds(120, 250, 120, 40);


        frame.add(userLabel);  
        // username label frame me add kiya

        frame.add(userField);  
        // username input field add ki

        frame.add(passLabel);  
        // password label add kiya

        frame.add(passField);  
        // password input field add ki

        frame.add(showPassword);  
        // show password checkbox add ki

        frame.add(loginBtn);  
        // login button add kiya

        frame.add(backBtn);  
        // back button add kiya


        frame.setSize(400, 350);  
        // window ka size set kiya

        frame.setLayout(null);  
        // manual layout enable kiya taaki exact positioning control kar sakein

        frame.setLocationRelativeTo(null);  
        // window screen ke center me open hogi

        frame.setVisible(true);  
        // window ko visible banaya


        // ===== SHOW / HIDE PASSWORD TOGGLE LOGIC =====

        showPassword.addActionListener(e -> {

            if (showPassword.isSelected()) {

                passField.setEchoChar((char) 0);  
                // agar checkbox select hai to password visible ho jayega

            } else {

                passField.setEchoChar('*');  
                // agar checkbox unselect hai to password dobara hidden ho jayega

            }

        });


        // ===== LOGIN BUTTON DATABASE VERIFICATION LOGIC =====

        loginBtn.addActionListener(e -> {

            String username = userField.getText();  
            // username field se input read kiya

            String password = new String(passField.getPassword());  
            // password field se input read kiya secure format me


            try {

                Class.forName("com.mysql.cj.jdbc.Driver");  
                // MySQL JDBC driver load kar rahe hain

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/registration_db",
                        "root",
                        "123456"
                );  
                // database se connection establish ho raha hai


                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM admin WHERE id=1 AND username=? AND password=?"
                );  
                // SQL query prepare kar rahe hain jo admin credentials verify karegi


                ps.setString(1, username);  
                // username parameter query me set kiya

                ps.setString(2, password);  
                // password parameter query me set kiya


                ResultSet rs = ps.executeQuery();  
                // query execute kar rahe hain aur result store kar rahe hain


                if (rs.next()) {

                    JOptionPane.showMessageDialog(frame,
                            "Admin Login Successful");  
                    // agar credentials match ho gaye to success message show hoga


                    new AdminDashboard();  
                    // admin dashboard window open hogi


                    frame.dispose();  
                    // current login window close ho jayegi

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Invalid Credentials please do properly");  
                    // agar credentials match nahi hue to error message show hoga

                }


                con.close();  
                // database connection close karna important hota hai memory leak avoid karne ke liye

            }

            catch (Exception ex) {

                ex.printStackTrace();  
                // error console me print hoga debugging ke liye

                JOptionPane.showMessageDialog(frame,
                        "Database connection error!");  
                // user ko error message show hoga agar DB connect nahi hua

            }

        });


        // ===== BACK BUTTON NAVIGATION LOGIC =====

        backBtn.addActionListener(e -> {

            new Softwareregistration01();  
            // main login screen dobara open karega

            frame.dispose();  
            // current admin login window close karega

        });

    }
}