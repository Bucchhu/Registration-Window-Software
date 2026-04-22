import javax.swing.*;   // Swing GUI components use karne ke liye import
import java.sql.*;      // MySQL database connection ke liye JDBC import

public class ReEditPage {

    JFrame frame;   // main edit window object

    JTextField idField, nameField, emailField;  
    // user ID, name aur email input ke liye text fields

    JPasswordField passField;  
    // password secure input ke liye special field

    JButton fetchBtn, updateBtn, backBtn;  
    // teen buttons: data fetch, update aur back navigation


    public ReEditPage() {

        frame = new JFrame("Edit Your Details");  
        // edit details ke liye new window create ho rahi hai


        // ===== USER ID INPUT =====
        JLabel idLabel = new JLabel("Enter ID:");
        idLabel.setBounds(50,40,120,30);  
        // ID label ki position set

        idField = new JTextField();
        idField.setBounds(180,40,150,30);  
        // ID input field create


        // ===== PASSWORD INPUT =====
        JLabel passLabel = new JLabel("Enter Password:");
        passLabel.setBounds(50,90,120,30);

        passField = new JPasswordField();
        passField.setBounds(180,90,150,30);  
        // password verification ke liye field


        // ===== FETCH BUTTON =====
        fetchBtn = new JButton("Fetch Data");
        fetchBtn.setBounds(140,140,150,40);  
        // DB se existing data load karne ke liye button


        // ===== NAME FIELD =====
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50,200,120,30);

        nameField = new JTextField();
        nameField.setBounds(180,200,150,30);  
        // updated name input ke liye field


        // ===== EMAIL FIELD =====
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50,250,120,30);

        emailField = new JTextField();
        emailField.setBounds(180,250,150,30);  
        // updated email input ke liye field


        // ===== UPDATE BUTTON =====
        updateBtn = new JButton("Update");
        updateBtn.setBounds(140,300,150,40);  
        // updated values DB me save karne ke liye button


        // ===== BACK BUTTON =====
        backBtn = new JButton("Back");
        backBtn.setBounds(140,360,150,40);  
        // main menu par return karne ke liye button


        // ===== FRAME COMPONENT ADD =====
        frame.add(idLabel);
        frame.add(idField);

        frame.add(passLabel);
        frame.add(passField);

        frame.add(fetchBtn);

        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(emailLabel);
        frame.add(emailField);

        frame.add(updateBtn);
        frame.add(backBtn);


        frame.setSize(450,470);  
        // window size set

        frame.setLayout(null);  
        // manual layout positioning enable

        frame.setLocationRelativeTo(null);  
        // screen center me open hogi window

        frame.setVisible(true);  
        // window visible banayi


        // ================= FETCH EXISTING DATA =================
        fetchBtn.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/registration_db",
                        "root",
                        "123456"
                );  
                // DB connection establish kar rahe hain


                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM userstableofsoftware1 WHERE id=? AND password=?"
                );  
                // verify kar rahe hain ki ID aur password DB me exist karta hai ya nahi


                ps.setInt(1, Integer.parseInt(idField.getText()));  
                // entered ID query me set

                ps.setString(2, new String(passField.getPassword()));  
                // entered password query me set


                ResultSet rs = ps.executeQuery();  
                // query execute kar rahe hain


                if(rs.next()) {

                    // agar record mil gaya to existing DB values auto-fill ho jayengi
                    nameField.setText(rs.getString("name"));
                    emailField.setText(rs.getString("email"));

                    JOptionPane.showMessageDialog(frame,
                            "Existing Data Loaded Successfully!");

                }
                else {

                    JOptionPane.showMessageDialog(frame,
                            "Invalid ID or Password");

                }

                con.close();  
                // DB connection close

            } catch(Exception ex) {

                ex.printStackTrace();  
                // error debugging ke liye console me show hoga

            }

        });


        // ================= UPDATE DATA WITH VALIDATION =================
        updateBtn.addActionListener(e -> {

            try {

                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/registration_db",
                        "root",
                        "123456"
                );  
                // DB connection establish


                // ===== EXISTING RECORD FETCH FOR COMPARISON =====
                PreparedStatement checkStmt = con.prepareStatement(
                        "SELECT name, email FROM userstableofsoftware1 WHERE id=? AND password=?"
                );

                checkStmt.setInt(1, Integer.parseInt(idField.getText()));
                checkStmt.setString(2, new String(passField.getPassword()));

                ResultSet rs = checkStmt.executeQuery();


                if(rs.next()) {

                    String oldName = rs.getString("name");  
                    // DB me existing name store

                    String oldEmail = rs.getString("email");  
                    // DB me existing email store


                    String newName = nameField.getText().trim();  
                    // user entered new name

                    String newEmail = emailField.getText().trim();  
                    // user entered new email


                    // ===== BLANK DATA VALIDATION =====
                    if(newName.isEmpty()) {

                        JOptionPane.showMessageDialog(frame,
                                "Name blank nahi ho sakta");

                        return;
                    }

                    if(newEmail.isEmpty()) {

                        JOptionPane.showMessageDialog(frame,
                                "Email blank nahi ho sakta");

                        return;
                    }


                    // ===== SAME DATA UPDATE BLOCK =====
                    if(newName.equals(oldName) && newEmail.equals(oldEmail)) {

                        JOptionPane.showMessageDialog(frame,
                                "Koi change detect nahi hua. New data enter karein.");

                        return;
                    }


                    // ===== UPDATE QUERY EXECUTION =====
                    PreparedStatement updateStmt = con.prepareStatement(
                            "UPDATE userstableofsoftware1 SET name=?, email=? WHERE id=? AND password=?"
                    );

                    updateStmt.setString(1, newName);
                    updateStmt.setString(2, newEmail);
                    updateStmt.setInt(3, Integer.parseInt(idField.getText()));
                    updateStmt.setString(4, new String(passField.getPassword()));


                    int updated = updateStmt.executeUpdate();  
                    // update query execute ho rahi hai


                    if(updated > 0) {

                        JOptionPane.showMessageDialog(frame,
                                "Details Successfully Updated!");

                    }
                    else {

                        JOptionPane.showMessageDialog(frame,
                                "Update Failed");

                    }

                }
                else {

                    JOptionPane.showMessageDialog(frame,
                            "Invalid ID or Password");

                }

                con.close();  
                // DB connection close

            } catch(Exception ex) {

                ex.printStackTrace();

            }

        });


        // ================= BACK BUTTON =================
        backBtn.addActionListener(e -> {

            new Softwareregistration01();  
            // main menu reopen karega

            frame.dispose();  
            // current edit window close karega

        });

    }
}