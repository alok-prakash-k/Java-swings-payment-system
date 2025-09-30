import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import static java.awt.Color.*;

class Payment extends JFrame implements ActionListener, ItemListener{
    JPasswordField pass_word, rg_password;
    JTextField tf_user_name, tf_email, tf_amount, tf_mobile_no, tf_account_no, rg_tf_user_name;
    JLabel lb_user_name, lb_password, lb_email, lb_amount, lb_mobile_no, lb_account_no, rg_user_name, lb_rg_password;
    JButton loginButton, resetButton, registerButton, sign_up;
    static final String DB_URL = "jdbc:mysql://localhost:3306/myappdb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";
    JFrame f, f_register;
    Font fh = new Font("Trebuchet MS", Font.BOLD, 12);

    public Payment() {

        f = new JFrame("Payment gateway");
        f.setSize(480, 550);
        Border blackline = BorderFactory.createLineBorder(black, 2);
        lb_user_name = new JLabel("Username");
        f.add(lb_user_name);
        lb_user_name.setFont(fh);
        lb_user_name.setBounds(30, 20, 200, 100);
        lb_user_name.setForeground(white);
        tf_user_name = new JTextField(20);
        tf_user_name.setBorder(blackline);
        tf_user_name.setBounds(100, 55, 300, 30);
        f.add(tf_user_name);
        tf_user_name.setFont(new Font("",Font.PLAIN,14));
        lb_password = new JLabel("Password");
        f.add(lb_password);
        lb_password.setBounds(30, 80, 200, 100);
        lb_password.setForeground(white);
        lb_password.setHorizontalTextPosition(JPasswordField.LEFT);
        pass_word = new JPasswordField(20);
        pass_word.setFont(new Font("",Font.PLAIN,14));
        f.add(pass_word);
        pass_word.setBounds(100, 115, 300, 30);
        pass_word.setBorder(blackline);
        loginButton = new JButton("LOGIN");
        f.add(loginButton);
        loginButton.setBounds(130, 190, 80, 30);
        loginButton.addActionListener(this);
        loginButton.setBackground(new Color(233, 242, 162));
        resetButton = new JButton("RESET");
        resetButton.setBounds(230, 190, 80, 30);
        f.add(resetButton);
        resetButton.addActionListener(this);
        resetButton.setBackground(new Color(156, 211, 217));
        registerButton = new JButton("Register");
        f.add(registerButton);
        registerButton.setBackground(new Color(3, 140, 76));
        registerButton.setForeground(WHITE);
        registerButton.setBounds(170, 250, 100, 31);
        registerButton.addActionListener(this);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

//        f.setResizable(false);

        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setIconImage(new ImageIcon("src/credit-cards.png").getImage());
        f.getContentPane().setBackground(new Color(0xFF361049, true));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void register() {
        f_register = new JFrame("Sign Up");
        f_register.setBackground(BLACK);
        f_register.setIconImage(new ImageIcon("src/credit-cards.png").getImage());
        f_register.setSize(480, 550);
        Border blackline = BorderFactory.createLineBorder(orange, 3);
        rg_user_name = new JLabel("Username");
        f_register.add(rg_user_name);
        rg_user_name.setBounds(30, 20, 200, 100);
        rg_user_name.setForeground(white);
        rg_tf_user_name = new JTextField(20);
        rg_tf_user_name.setBorder(blackline);
        rg_tf_user_name.setBounds(100, 55, 300, 30);
        f_register.add(rg_tf_user_name);
        lb_rg_password = new JLabel("Password");
        f_register.add(lb_rg_password);
        lb_rg_password.setBounds(30, 80, 200, 100);
        lb_rg_password.setForeground(white);
        rg_password = new JPasswordField(20);
        f_register.add(rg_password);
        rg_password.setBounds(100, 115, 300, 30);
        rg_password.setBorder(blackline);
        lb_email = new JLabel("Email id:");
        f_register.add(lb_email);
        lb_email.setBounds(30, 140, 200, 100);
        lb_email.setForeground(white);
        tf_email = new JTextField(20);
        f_register.add(tf_email);
        tf_email.setBorder(blackline);
        tf_email.setBounds(100, 175, 300, 30);

        lb_mobile_no = new JLabel("Mobile No:");
        f_register.add(lb_mobile_no);
        lb_mobile_no.setBounds(30, 200, 200, 100);
        lb_mobile_no.setForeground(white);
        tf_mobile_no = new JTextField(20);
        f_register.add(tf_mobile_no);
        tf_mobile_no.setBorder(blackline);
        tf_mobile_no.setBounds(100, 235, 300, 30);
        tf_mobile_no.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9'))
                        && (c != '\b')) {
                    e.consume();
                }
            }
        });
        lb_account_no = new JLabel("Account No:");
        f_register.add(lb_account_no);
        lb_account_no.setForeground(white);
        lb_account_no.setBounds(30, 260, 200, 100);
        tf_account_no = new JTextField(20);
        tf_account_no.setBorder(blackline);
        f_register.add(tf_account_no);
        tf_account_no.setBounds(100, 295, 300, 30);
        tf_account_no.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9'))
                        && (c != '\b')) {
                    e.consume();
                }
            }
        });


        lb_amount = new JLabel("Balance ");
        f_register.add(lb_amount);
        lb_amount.setForeground(white);
        lb_amount.setBounds(30, 320, 200, 100);
        tf_amount = new JTextField(20);
        tf_amount.setBorder(blackline);
        f_register.add(tf_amount);
        tf_amount.setBounds(100, 355, 300, 30);
        tf_amount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9'))
                        && (c != '\b')) {
                    e.consume();
                }
            }
        });


        sign_up = new JButton("Submit");
        sign_up.setBounds(180, 420, 120, 34);
        f_register.add(sign_up);
        sign_up.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sign_up.setBackground(new Color(255, 252, 0));
        sign_up.addActionListener(this);


        lb_password.setForeground(white);
        lb_password.setHorizontalTextPosition(JPasswordField.LEFT);
        pass_word = new JPasswordField(20);
        f_register.add(pass_word);
        f_register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f_register.setLocationRelativeTo(null);
        f_register.setLayout(null);
        f_register.getContentPane().setBackground(new Color(0xFF0E2729, true));
        f_register.setVisible(true);

    }

    JLabel hello;
    JFrame lg_frame;
    JButton credit_card, paypal, bank_account;

    public void login() {

        lg_frame = new JFrame("Payment gateway");
        lg_frame.setSize(480, 550);
        lg_frame.setIconImage(new ImageIcon("src/credit-cards.png").getImage());
        lg_frame.setLayout(null);
        hello = new JLabel("welcome " + tf_user_name.getText() + "!", SwingConstants.CENTER);
        hello.setForeground(white);
        lg_frame.add(hello);
        hello.setBounds(90, 40, 300, 50);
        hello.setFont(new Font("Serif", Font.BOLD, 26));
        credit_card = new JButton("Credit card Payment");
        lg_frame.add(credit_card);
        credit_card.addActionListener(this);
        credit_card.setBackground(yellow);
        credit_card.setBounds(160, 140, 150, 70);
        credit_card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bank_account = new JButton("Bank Transfer");
        lg_frame.add(bank_account);
        bank_account.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bank_account.addActionListener(this);
        bank_account.setBackground(GREEN);
        bank_account.setBounds(160, 240, 150, 70);
        paypal = new JButton("Paypal Payment");
        paypal.setBackground(orange);
        paypal.setBounds(160, 340, 150, 70);
        paypal.setCursor(new Cursor(Cursor.HAND_CURSOR));
        paypal.addActionListener(this);
        lg_frame.add(paypal);
        lg_frame.setLocationRelativeTo(null);
        lg_frame.setVisible(true);
        lg_frame.setLayout(null);
        lg_frame.getContentPane().setBackground(new Color(0xFF361049, true));
        lg_frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    JFrame cc_frame;
    JLabel name, payment_method, current_balance, account_lb, mobile_lb, lb_sent_amount, lb_receiver;
    JTextField sent_amount, receiver;
    JButton pay,logout_cc;
    String balance;

    public void payment_credit_card() {
        Border line = BorderFactory.createLineBorder(black, 2);
        cc_frame = new JFrame("Payment gateway");
        cc_frame.setSize(480, 550);
        cc_frame.setLayout(null);
        cc_frame.setIconImage(new ImageIcon("src/credit-cards.png").getImage());

        name = new JLabel("welcome " + tf_user_name.getText() + "!");
        name.setFont(new Font("Serif", Font.BOLD, 28));
        name.setForeground(white);
        name.setBounds(20, 30, 300, 25);
        cc_frame.add(name);

        payment_method = new JLabel("Payment Method : Credit card");
        cc_frame.add(payment_method);
        payment_method.setBounds(20, 80, 300, 40);
        payment_method.setFont(new Font("Arial", Font.PLAIN, 18));
        payment_method.setForeground(white);

        current_balance = new JLabel();
        cc_frame.add(current_balance);
        current_balance.setBounds(20, 130, 300, 40);
        current_balance.setFont(new Font("Arial", Font.PLAIN, 18));
        current_balance.setForeground(white);

        account_lb = new JLabel();
        cc_frame.add(account_lb);
        account_lb.setBounds(20, 180, 300, 40);
        account_lb.setFont(new Font("Arial", Font.PLAIN, 18));
        account_lb.setForeground(white);

        mobile_lb = new JLabel();
        cc_frame.add(mobile_lb);
        mobile_lb.setBounds(20, 230, 300, 40);
        mobile_lb.setFont(new Font("Arial", Font.PLAIN, 18));
        mobile_lb.setForeground(white);
        String username = tf_user_name.getText();

        lb_sent_amount = new JLabel("Transfer Amount");
        lb_sent_amount.setForeground(white);
        cc_frame.add(lb_sent_amount);
        lb_sent_amount.setFont(new Font("Arial", Font.PLAIN, 18));
        lb_sent_amount.setBounds(20, 280, 200, 40);
        sent_amount = new JTextField();
        sent_amount.setBounds(190, 285, 220, 30);
        sent_amount.setFont(new Font("Arial", Font.PLAIN, 16));
        sent_amount.setBorder(line);
        cc_frame.add(sent_amount);
        lb_sent_amount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9'))
                        && (c != '\b')) {
                    e.consume();
                }
            }

        });

        lb_receiver = new JLabel("Recipient Username");
        lb_receiver.setBounds(20, 340, 200, 40);
        lb_receiver.setForeground(white);
        cc_frame.add(lb_receiver);
        lb_receiver.setFont(new Font("Arial", Font.PLAIN, 18));
        receiver = new JTextField();
        receiver.setFont(new Font("Arial", Font.PLAIN, 16));
        receiver.setBounds(190, 345, 220, 30);
        cc_frame.add(receiver);
        receiver.setBorder(line);

        pay = new JButton("Pay");
        cc_frame.add(pay);
        pay.addActionListener(this);
        pay.setBounds(210, 420, 150, 40);
        pay.setBackground(new Color(1, 42, 83, 255));
        pay.setFont(new Font("", Font.BOLD, 18));
        pay.setForeground(white);
        Border pa = BorderFactory.createLineBorder(white, 2);
        pay.setBorder(pa);
        pay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout_cc= new JButton("Log Out");
        cc_frame.add(logout_cc);
        logout_cc.addActionListener(this);
        logout_cc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout_cc.setBorder(pa);
        logout_cc.setBounds(70, 422, 100, 35);
        logout_cc.setFont(new Font("", Font.BOLD, 18));
        logout_cc.setForeground(white);
        logout_cc.setOpaque(true);
        logout_cc.setContentAreaFilled(false);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement smt = con.prepareStatement("SELECT amount, account, mobile FROM users WHERE username = ?");
            smt.setString(1, username);

            ResultSet r = smt.executeQuery();
            if (r.next()) {
                balance = r.getString("amount");
                String account_no = r.getString("account");
                String mobile_no = r.getString("mobile");
                account_lb.setText("Account Number :  " + account_no);
                current_balance.setText("Current Balance :  ₹" + balance);
                mobile_lb.setText("Mobile Number :  " + mobile_no);
            }


            r.close();
            smt.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            current_balance.setText("Error loading balance");
        }

        cc_frame.setLocationRelativeTo(null);
        cc_frame.setVisible(true);
        cc_frame.getContentPane().setBackground(new Color(0xFF361049, true));
        cc_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JFrame pb_frame;
    JLabel email_lb;
    JTextField receiver_email;
    JButton pay_pal;
    JButton logout;
    public void setPaypal() {

        Border line = BorderFactory.createLineBorder(new Color(243, 146, 3), 3);
        pb_frame = new JFrame("Payment gateway");
        pb_frame.setSize(480, 550);
        pb_frame.setLayout(null);
        pb_frame.setIconImage(new ImageIcon("src/credit-cards.png").getImage());

        name = new JLabel("welcome " + tf_user_name.getText() + "!");
        name.setFont(new Font("Serif", Font.BOLD, 28));
        name.setForeground(white);
        name.setBounds(20, 30, 300, 25);
        pb_frame.add(name);

        payment_method = new JLabel("Payment Method : PayPal");
        pb_frame.add(payment_method);
        payment_method.setBounds(20, 80, 300, 40);
        payment_method.setFont(new Font("Arial", Font.PLAIN, 18));
        payment_method.setForeground(white);

        current_balance = new JLabel();
        pb_frame.add(current_balance);
        current_balance.setBounds(20, 130, 300, 40);
        current_balance.setFont(new Font("Arial", Font.PLAIN, 18));
        current_balance.setForeground(white);

        account_lb = new JLabel();
        pb_frame.add(account_lb);
        account_lb.setBounds(20, 180, 300, 40);
        account_lb.setFont(new Font("Arial", Font.PLAIN, 18));
        account_lb.setForeground(white);

        email_lb = new JLabel();
        pb_frame.add(email_lb);
        email_lb.setBounds(20, 230, 300, 40);
        email_lb.setFont(new Font("Arial", Font.PLAIN, 18));
        email_lb.setForeground(white);

        String username = tf_user_name.getText();

        lb_sent_amount = new JLabel("Transfer Amount");
        lb_sent_amount.setForeground(white);
        pb_frame.add(lb_sent_amount);
        lb_sent_amount.setFont(new Font("Arial", Font.PLAIN, 18));
        lb_sent_amount.setBounds(20, 280, 200, 40);

        sent_amount = new JTextField();
        sent_amount.setBounds(190, 285, 220, 30);
        sent_amount.setFont(new Font("Arial", Font.PLAIN, 16));
        sent_amount.setBorder(line);
        pb_frame.add(sent_amount);

        lb_sent_amount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != '\b')) {
                    e.consume();
                }
            }
        });

        lb_receiver = new JLabel("Recipient Email id");
        lb_receiver.setBounds(20, 340, 200, 40);
        lb_receiver.setForeground(white);
        pb_frame.add(lb_receiver);
        lb_receiver.setFont(new Font("Arial", Font.PLAIN, 18));

        receiver_email = new JTextField();
        receiver_email.setFont(new Font("Arial", Font.PLAIN, 16));
        receiver_email.setBounds(190, 345, 220, 30);
        pb_frame.add(receiver_email);
        receiver_email.setBorder(line);

        pay_pal = new JButton("Pay");
        pb_frame.add(pay_pal);
        pay_pal.addActionListener(this);
        pay_pal.setBounds(220, 420, 150, 40);
        pay_pal.setBackground(new Color(1, 42, 83, 255));
        pay_pal.setFont(new Font("", Font.BOLD, 18));
        pay_pal.setForeground(white);
        Border pa = BorderFactory.createLineBorder(white, 2);
        pay_pal.setBorder(pa);
        pay_pal.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logout= new JButton("Log Out");
        pb_frame.add(logout);
        logout.addActionListener(this);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setBorder(pa);
        logout.setBounds(70, 422, 100, 35);
        logout.setFont(new Font("", Font.BOLD, 18));
        logout.setForeground(white);
        logout.setOpaque(true);
        logout.setContentAreaFilled(false);


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement smt = con.prepareStatement("SELECT amount, account, email FROM users WHERE username = ?");
            smt.setString(1, username);

            ResultSet r = smt.executeQuery();
            if (r.next()) {
                balance = r.getString("amount");
                String account_no = r.getString("account");
                String email = r.getString("email");
                account_lb.setText("Account Number :  " + account_no);
                current_balance.setText("Current Balance :  ₹" + balance);
                email_lb.setText("Email Id :  " + email);
            }


            r.close();
            smt.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            current_balance.setText("Error loading balance");
        }
        pb_frame.setLocationRelativeTo(null);
        pb_frame.setVisible(true);
        pb_frame.getContentPane().setBackground(new Color(0xFF361049, true));
        pb_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    JFrame bt;
    JButton pay_account,logout_bt;
    JTextField receiver_account;

    public void bank_transfer() {

        Border line = BorderFactory.createLineBorder(new Color(243, 146, 3), 3);
        bt = new JFrame("Payment gateway");
        bt.setSize(480, 550);
        bt.setLayout(null);
        bt.setIconImage(new ImageIcon("src/credit-cards.png").getImage());

        name = new JLabel("welcome " + tf_user_name.getText() + "!");
        name.setFont(new Font("Serif", Font.BOLD, 28));
        name.setForeground(white);
        name.setBounds(20, 30, 300, 25);
        bt.add(name);

        payment_method = new JLabel("Payment Method : Bank Transfer");
        bt.add(payment_method);
        payment_method.setBounds(20, 80, 300, 40);
        payment_method.setFont(new Font("Arial", Font.PLAIN, 18));
        payment_method.setForeground(white);

        current_balance = new JLabel();
        bt.add(current_balance);
        current_balance.setBounds(20, 130, 300, 40);
        current_balance.setFont(new Font("Arial", Font.PLAIN, 18));
        current_balance.setForeground(white);

        account_lb = new JLabel();
        bt.add(account_lb);
        account_lb.setBounds(20, 180, 300, 40);
        account_lb.setFont(new Font("Arial", Font.PLAIN, 18));
        account_lb.setForeground(white);

        email_lb = new JLabel();
        bt.add(email_lb);
        email_lb.setBounds(20, 230, 300, 40);
        email_lb.setFont(new Font("Arial", Font.PLAIN, 18));
        email_lb.setForeground(white);

        String username = tf_user_name.getText();

        lb_sent_amount = new JLabel("Transfer Amount");
        lb_sent_amount.setForeground(white);
        bt.add(lb_sent_amount);
        lb_sent_amount.setFont(new Font("Arial", Font.PLAIN, 18));
        lb_sent_amount.setBounds(20, 280, 200, 40);

        sent_amount = new JTextField();
        sent_amount.setBounds(210, 285, 220, 30);
        sent_amount.setFont(new Font("Arial", Font.PLAIN, 16));
        sent_amount.setBorder(line);
        bt.add(sent_amount);

        lb_sent_amount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != '\b')) {
                    e.consume();
                }
            }
        });

        lb_receiver = new JLabel("Recipient Account No:");
        lb_receiver.setBounds(20, 340, 200, 40);
        lb_receiver.setForeground(white);
        bt.add(lb_receiver);
        lb_receiver.setFont(new Font("Arial", Font.PLAIN, 18));

        receiver_account = new JTextField();
        receiver_account.setFont(new Font("Arial", Font.PLAIN, 16));
        receiver_account.setBounds(210, 345, 220, 30);
        bt.add(receiver_account);
        receiver_account.setBorder(line);

        pay_account = new JButton("Pay");
        bt.add(pay_account);
        pay_account.addActionListener(this);
        pay_account.setBounds(220, 420, 150, 40);
        pay_account.setBackground(new Color(1, 42, 83, 255));
        pay_account.setFont(new Font("", Font.BOLD, 18));
        pay_account.setForeground(white);
        Border pa = BorderFactory.createLineBorder(white, 2);
        pay_account.setBorder(pa);
        pay_account.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement smt = con.prepareStatement("SELECT amount, account, email FROM users WHERE username = ?");
            smt.setString(1, username);

            ResultSet r = smt.executeQuery();
            if (r.next()) {
                balance = r.getString("amount");
                String account_no = r.getString("account");
                String email = r.getString("email");
                account_lb.setText("Account Number :  " + account_no);
                current_balance.setText("Current Balance :  ₹" + balance);
                email_lb.setText("Email Id :  " + email);
            }

            r.close();
            smt.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            current_balance.setText("Error loading balance");
        }

        bt.setLocationRelativeTo(null);
        bt.setVisible(true);
        bt.getContentPane().setBackground(new Color(0xFF361049, true));
        bt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logout_bt= new JButton("Log Out");
        bt.add(logout_bt);
        logout_bt.addActionListener(this);
        logout_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout_bt.setBorder(pa);
        logout_bt.setBounds(70, 422, 100, 35);
        logout_bt.setFont(new Font("", Font.BOLD, 18));
        logout_bt.setForeground(white);
        logout_bt.setOpaque(true);
        logout_bt.setContentAreaFilled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String name = tf_user_name.getText();
            char[] password = pass_word.getPassword();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, name);
                smt.setString(2, new String(password));
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {

                    f.dispose();
                    login();
                } else {
                    JOptionPane.showMessageDialog(this, " Invalid username or password.");
                }

                con.close();

            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                throw new RuntimeException(ex);

            }


        } else if (e.getSource() == registerButton) {
            f.dispose();
            register();

        } else if (e.getSource() == sign_up) {
            String name = rg_tf_user_name.getText().trim();
            String email = tf_email.getText().trim();
            String mobile = tf_mobile_no.getText().trim();
            String amount_str = tf_amount.getText().trim();
            String account = tf_account_no.getText().trim();

            char[] pass_word = rg_password.getPassword();
            if (name.isEmpty() || pass_word.length == 0 || email.isEmpty() || mobile.isEmpty() || amount_str.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
            } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                JOptionPane.showMessageDialog(this, "Invalid email address");

            } else if (!mobile.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Mobile number must be 10 digits");

            } else {
                try {
                    int amount = Integer.parseInt(amount_str);
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                    String sql = "INSERT INTO users(username,password,email,mobile,amount,account) VALUES (?,?,?,?,?,?)";
                    PreparedStatement smt = con.prepareStatement(sql);
                    smt.setString(1, name);
                    smt.setString(2, new String(pass_word));
                    smt.setString(3, email);
                    smt.setString(4, mobile);
                    smt.setInt(5, amount);
                    smt.setString(6, account);
                    smt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "done");
                    con.close();
                    f_register.dispose();
                    new Payment();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }catch (SQLException dupEx) {
                    JOptionPane.showMessageDialog(this, "Username or email or Account  already exists!");
                }

            }


        } else if (e.getSource() == resetButton) {
            tf_user_name.setText("");
            pass_word.setText("");




        } else if (e.getSource() == credit_card) {
            lg_frame.dispose();
            payment_credit_card();
        } else if (e.getSource() == pay) {
            String sender_amount = sent_amount.getText().trim();
            String receiver_user = receiver.getText().trim();
            if (sender_amount.isEmpty() || receiver_user.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
            }
            try {
                int sendAmountValue = Integer.parseInt(sender_amount);
                int currentBalance = Integer.parseInt(balance);
                if (sendAmountValue < 0) {
                    JOptionPane.showMessageDialog(this, "Value must be greater than 0");
                    return;
                }
                if (sendAmountValue > currentBalance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance");
                    return;
                }
                int new_balance = currentBalance - sendAmountValue;
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement("SELECT username FROM users WHERE username = ? ");
                smt.setString(1, receiver_user);
                ResultSet receiverResult = smt.executeQuery();
                if (!receiverResult.next()) {
                    JOptionPane.showMessageDialog(this, "Receiver is not found");
                    con.close();
                }
                PreparedStatement sender_update = con.prepareStatement("UPDATE users SET amount=? WHERE username = ? ");
                sender_update.setInt(1, new_balance);
                sender_update.setString(2, tf_user_name.getText().trim());
                PreparedStatement receiver_update = con.prepareStatement("UPDATE users SET amount = amount+ ? WHERE username = ?");
                receiver_update.setInt(1, new_balance);
                receiver_update.setString(2, receiver_user);
                int senderUpdated = sender_update.executeUpdate();
                int receiverUpdated = receiver_update.executeUpdate();
                if (senderUpdated > 0 && receiverUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Payment Successful!");
                    current_balance.setText("Current Balance :  ₹" + new_balance);
                    sent_amount.setText("");
                    receiver.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Payment Failed");
                }

                con.close();

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }


        } else if (e.getSource() == pay_pal) {
            String sender_amount = sent_amount.getText().trim();
            String receiver_mail = receiver_email.getText().trim();
            if (sender_amount.isEmpty() || receiver_mail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
            }
            try {
                int sendAmountValue = Integer.parseInt(sender_amount);
                int currentBalance = Integer.parseInt(balance);
                if (sendAmountValue < 0) {
                    JOptionPane.showMessageDialog(this, "Value must be greater than 0");
                    return;
                }
                if (sendAmountValue > currentBalance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance");
                    return;
                }
                int new_balance = currentBalance - sendAmountValue;
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement("SELECT email FROM users WHERE email = ? ");
                smt.setString(1, receiver_mail);
                ResultSet receiverResult = smt.executeQuery();
                if (!receiverResult.next()) {
                    JOptionPane.showMessageDialog(this, "Receiver is not found");
                    con.close();
                }
                PreparedStatement sender_update = con.prepareStatement("UPDATE users SET amount=? WHERE username = ? ");
                sender_update.setInt(1, new_balance);
                sender_update.setString(2, tf_user_name.getText().trim());
                PreparedStatement receiver_update = con.prepareStatement("UPDATE users SET amount = amount+ ? WHERE email = ?");
                receiver_update.setInt(1, new_balance);
                receiver_update.setString(2, receiver_mail);
                int senderUpdated = sender_update.executeUpdate();
                int receiverUpdated = receiver_update.executeUpdate();
                if (senderUpdated > 0 && receiverUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Payment Successful!");
                    current_balance.setText("Current Balance :  ₹" + new_balance);
                    sent_amount.setText("");
                    receiver_email.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Payment Failed");
                }

                con.close();

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == paypal) {
            lg_frame.dispose();
            setPaypal();

        } else if (e.getSource() == pay_account) {
            String sender_amount = sent_amount.getText().trim();
            String receiver_account_no = receiver_account.getText().trim();
            if (sender_amount.isEmpty() || receiver_account_no.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
            }
            try {
                int sendAmountValue = Integer.parseInt(sender_amount);
                int currentBalance = Integer.parseInt(balance);
                if (sendAmountValue < 0) {
                    JOptionPane.showMessageDialog(this, "Value must be greater than 0");
                    return;
                }
                if (sendAmountValue > currentBalance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance");
                    return;
                }
                int new_balance = currentBalance - sendAmountValue;
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement("SELECT account FROM users WHERE account = ? ");
                smt.setString(1, receiver_account_no);
                ResultSet receiverResult = smt.executeQuery();
                if (!receiverResult.next()) {
                    JOptionPane.showMessageDialog(this, "Receiver is not found");
                    con.close();
                }
                PreparedStatement sender_update = con.prepareStatement("UPDATE users SET amount=? WHERE username = ? ");
                sender_update.setInt(1, new_balance);
                sender_update.setString(2, tf_user_name.getText().trim());
                PreparedStatement receiver_update = con.prepareStatement("UPDATE users SET amount = amount+ ? WHERE account = ?");
                receiver_update.setInt(1, new_balance);
                receiver_update.setString(2, receiver_account_no);
                int senderUpdated = sender_update.executeUpdate();
                int receiverUpdated = receiver_update.executeUpdate();
                if (senderUpdated > 0 && receiverUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Payment Successful!");
                    current_balance.setText("Current Balance :  ₹" + new_balance);
                    sent_amount.setText("");
                    receiver_account.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Payment Failed");
                }

                con.close();

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }


        } else if (e.getSource()==bank_account) {
            lg_frame.dispose();
            bank_transfer();
        } else if (e.getSource()==logout) {
            pb_frame.dispose();
             new Payment();

        }else if(e.getSource()==logout_bt)
        {
            bt.dispose();
            new Payment();
        }else if(e.getSource()==logout_cc)
        {
            cc_frame.dispose();
            new Payment();
        }


    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }



    public static void main(String[] args) {
        new Payment();
    }


}




