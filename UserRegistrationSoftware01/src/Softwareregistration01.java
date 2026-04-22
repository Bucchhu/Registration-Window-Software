import javax.swing.*;  
// Swing library import kar rahe hain taaki GUI components (JFrame, JButton, JLabel etc.) use kar sakein


public class Softwareregistration01 {

    public static void main(String[] args) {

        new Softwareregistration01();  
        // program execution yahin se start hota hai
        // yeh constructor call karta hai jo main login screen open karta hai

    }


    JFrame frame;  
    // JFrame object declare kiya jo main window ko represent karega


    public Softwareregistration01() {

        frame = new JFrame("SOFTWARE REGISTRATION SYSTEM");  
        // main application window create ho rahi hai title ke saath


        JLabel title = new JLabel("LOGIN PANEL", SwingConstants.CENTER);  
        // ek label create kiya jo screen ke center me LOGIN PANEL text show karega

        title.setBounds(100, 30, 300, 40);  
        // label ki exact position aur size set kar rahe hain


        JButton userBtn = new JButton("User Login");  
        // User Login button create kiya

        userBtn.setBounds(150, 100, 150, 40);  
        // button ki screen par position aur size set kiya

                        // ===== Edit Details button create kar rahe hain (secondary feature ke liye) =====
                JButton editBtn = new JButton("Edit Details");

                // right corner me position set kar rahe hain (main buttons se separate placement)
                editBtn.setBounds(330, 300, 130, 30);

                // light olive green background color apply kar rahe hain
                editBtn.setBackground(new java.awt.Color(189, 204, 153));

                // text readable rahe isliye black foreground set kiya
                editBtn.setForeground(java.awt.Color.BLACK);

                // focus border remove kar rahe hain cleaner UI look ke liye
                editBtn.setFocusPainted(false);



        JButton adminBtn = new JButton("Admin Login");  
        // Admin Login button create kiya

        adminBtn.setBounds(150, 160, 150, 40);


        JButton exitBtn = new JButton("Exit");  
        // Exit button create kiya jo program band karega

        exitBtn.setBounds(150, 220, 150, 40);


        
        frame.add(editBtn);
        // button frame me add kar rahe hain taaki user ko dikhe

        frame.add(title);  
        // title label frame me add kiya

        frame.add(userBtn);  
        // user login button frame me add kiya

        frame.add(adminBtn);  
        // admin login button frame me add kiya

        frame.add(exitBtn);  
        // exit button frame me add kiya


        frame.setSize(500, 400);  
        // window ka overall size set kiya (width, height)

        frame.setLayout(null);  
        // manual layout enable kiya taaki hum khud positioning control kar sakein

        frame.setLocationRelativeTo(null);  
        // window screen ke center me open hogi

        frame.setVisible(true);  
        // frame ko visible banaya taaki user ko screen dikhe


        userBtn.addActionListener(e -> {

            new UserLogin();  
            // UserLogin.java ka registration window open karega

            frame.dispose();  
            // current main screen close karega taaki duplicate windows na bane

        });


        adminBtn.addActionListener(e -> {

            new AdminLogin();  
            // AdminLogin.java ka login window open karega

            frame.dispose();  
            // current window close karega jab admin login screen open hogi

        });


        exitBtn.addActionListener(e -> System.exit(0));  
        // Exit button click karte hi pura program terminate ho jayega

                        // ===== Edit Details button click logic =====
                editBtn.addActionListener(e -> {

                    new ReEditPage();   // ReEditPage window open karega

                    frame.dispose();    // current main screen close karega

                });

    }

}