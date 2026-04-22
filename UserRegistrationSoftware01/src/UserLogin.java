import javax.swing.*;     // Swing GUI components use karne ke liye import
import java.sql.*;        // MySQL connection ke liye JDBC import

public class UserLogin {

    public UserLogin() {

        JFrame frame = new JFrame("User Registration");  
        // naya window create ho raha hai jisme registration form dikhega


        JTextField name = new JTextField();  
        // name input lene ke liye text field create ki

        name.setBounds(150,50,150,30);  
        // screen par position set kar rahe hain (x, y, width, height)


        JTextField email = new JTextField();  
        // email input lene ke liye text field

        email.setBounds(150,100,150,30);


        JPasswordField password = new JPasswordField();  
        // password secure input ke liye special field

        password.setBounds(150,150,150,30);


        JButton submit = new JButton("Submit");  
        // submit button create kiya jo data database me save karega

        submit.setBounds(150,220,120,40);


        JButton backBtn = new JButton("Back");  
        // back button create kiya main screen par wapas jane ke liye

        backBtn.setBounds(150,280,120,40);


        frame.add(new JLabel("Name")).setBounds(50,50,100,30);  
        // Name label screen par add kiya

        frame.add(name);


        frame.add(new JLabel("Email")).setBounds(50,100,100,30);  
        // Email label add kiya

        frame.add(email);


        frame.add(new JLabel("Password")).setBounds(50,150,100,30);  
        // Password label add kiya

        frame.add(password);


        frame.add(submit);  
        // submit button frame me add kiya

        frame.add(backBtn);  
        // back button frame me add kiya


        frame.setSize(400,400);  
        // window ka size set kiya

        frame.setLayout(null);  
        // manual layout enable kiya (absolute positioning)

        frame.setLocationRelativeTo(null);  
        // window screen ke center me open hogi

        frame.setVisible(true);  
        // window visible banayi


        // ================= SUBMIT BUTTON LOGIC =================

        submit.addActionListener(e -> {

            String userName = name.getText().trim();  
            // name field ka text read kiya aur extra spaces remove kiye

            String userEmail = email.getText().trim();  
            // email field ka text read kiya

            String userPassword = new String(password.getPassword()).trim();  
            // password secure field se value read ki


            // ====== VALIDATION: blank fields allowed nahi ======

            if(userName.isEmpty()) {

                JOptionPane.showMessageDialog(frame,
                        "Name empty nahi ho sakta!");

                return;  
                // execution yahin stop ho jayega agar blank mila

            }

            if(userEmail.isEmpty()) {

                JOptionPane.showMessageDialog(frame,
                        "Email empty nahi ho sakta!");

                return;

            }

            if(userPassword.isEmpty()) {

                JOptionPane.showMessageDialog(frame,
                        "Password empty nahi ho sakta!");

                return;

            }


            // ====== EMAIL FORMAT BASIC VALIDATION ======

            if(!userEmail.contains("@") || !userEmail.contains(".")) {

                JOptionPane.showMessageDialog(frame,
                        "Valid email enter karo!");

                return;

            }


            // ====== DATABASE INSERT START ======

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
                        "INSERT INTO userstableofsoftware1(name,email,password) VALUES(?,?,?)"
                );  
                // SQL insert query prepare ki


                ps.setString(1, userName);  
                // name column me value set

                ps.setString(2, userEmail);  
                // email column me value set

                ps.setString(3, userPassword);  
                // password column me value set


                int rowsInserted = ps.executeUpdate();  
                // query execute ho rahi hai aur result return ho raha hai


                if(rowsInserted > 0) {

                    JOptionPane.showMessageDialog(frame,
                            "User Successfully Register ho gaya!");

                    name.setText("");  
                    // name field clear

                    email.setText("");  
                    // email field clear

                    password.setText("");  
                    // password field clear

                }


                con.close();  
                // database connection close kiya (important)

            }

            catch(Exception ex){

                ex.printStackTrace();  
                // error console me print hoga debugging ke liye

                JOptionPane.showMessageDialog(frame,
                        "Database insert failed!");

            }

        });


        // ================= BACK BUTTON LOGIC =================

        backBtn.addActionListener(e -> {

            new Softwareregistration01();  
            // main screen dubara open karega

            frame.dispose();  
            // current window close karega

        });

    }
}