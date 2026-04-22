import javax.swing.*;  
// Swing GUI components use karne ke liye import (JFrame, JButton, JTable etc.)

import javax.swing.table.DefaultTableModel;  
// JTable ke andar dynamic rows add karne ke liye table model import kiya

import java.sql.*;  
// MySQL database se connect karne ke liye JDBC SQL package import


public class AdminDashboard 
// yeh class admin dashboard window banati hai jahan admin ko saare users ka data dikhega
{

    JFrame frame;  
    // main window object jo dashboard screen represent karega

    JTable table;  
    // JTable object jo database ka data table format me show karega


    public AdminDashboard() {

        frame = new JFrame("Admin Dashboard");  
        // admin dashboard ke liye naya window create kiya


        table = new JTable();  
        // empty JTable create kiya jisme baad me data fill hoga


        DefaultTableModel model = new DefaultTableModel();  
        // table model create kiya jo rows aur columns manage karega


        model.setColumnIdentifiers(
                new String[]{"ID","Name","Email","Password"}
        );  
        // table ke column headings define kiye (yeh DB columns ke naam se match hone chahiye)


        table.setModel(model);  
        // table ko model assign kiya taaki data dynamic add ho sake


        JScrollPane scrollPane = new JScrollPane(table);  
        // scroll pane add kiya taaki table bada ho to scroll ho sake


        scrollPane.setBounds(30,30,520,200);  
        // table ki screen par position aur size set ki


        frame.add(scrollPane);  
        // scrollable table frame me add kiya


        JButton BackBtn = new JButton("Back");  
        // back button create kiya main screen par return karne ke liye

        BackBtn.setBounds(220,250,120,40);


        frame.add(BackBtn);  
        // back button frame me add kiya


        frame.setSize(600,350);  
        // dashboard window ka size set kiya

        frame.setLayout(null);  
        // manual layout enable kiya taaki exact positioning control kar sakein

        frame.setLocationRelativeTo(null);  
        // window screen ke center me open hogi

        frame.setVisible(true);  
        // window visible banayi user ke liye


        loadData(model);  
        // method call kiya jo MySQL database se data fetch karke table me fill karega


        BackBtn.addActionListener(e -> {

            new Softwareregistration01();  
            // main login panel dobara open karega

            frame.dispose();  
            // current dashboard window close karega

        });
    }



    void loadData(DefaultTableModel model) 
    // yeh method database se data fetch karke JTable me insert karta hai
    {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/registration_db",
                    "root",
                    "123456"
            );  
            // MySQL database se connection establish kiya


            Statement stmt = con.createStatement();  
            // SQL query run karne ke liye statement object create kiya


            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM userstableofsoftware1"
            );  
            // users table ka pura data fetch kar rahe hain


            while(rs.next()) 
            // jab tak next row available hai tab tak loop chalega
            {

                model.addRow(new Object[]{

                        rs.getInt("id"),  
                        // id column ka data read karke table me add kar rahe hain

                        rs.getString("name"),  
                        // name column ka data add kar rahe hain

                        rs.getString("email"),  
                        // email column ka data add kar rahe hain

                        rs.getString("password")  
                        // password column ka data add kar rahe hain

                });

            }


            con.close();  
            // database connection close kar diya memory leak avoid karne ke liye


        } catch(Exception ex) {

            ex.printStackTrace();  
            // agar error aata hai to console me print hoga debugging ke liye

        }

    }
}