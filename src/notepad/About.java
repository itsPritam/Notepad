package notepad;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class About extends JFrame implements ActionListener {

    public About() {

        setTitle("About");
        setResizable(false);

        setBounds(500, 150, 600, 600);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows-11.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon = new JLabel(i3);
        headerIcon.setBounds(150, 50, 300, 80);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notes.png"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel ic = new JLabel(i6);
        ic.setBounds(30, 180, 70, 80);

        JLabel text = new JLabel("<html><h1>About Pritam's NotePad </h1> <br> <h4>Version 1.0 </h4><br>Welcome to Pritam's Notepad, a simple and intuitive text editor built using <br>"
                + "Java Swing and AWT. This lightweight application provides a clean and <br>"
                + "user-friendly interface for all your note-taking needs.<br>"
                + "<h1>Contact:</h1><br>We value your feedback! If you have any questions, suggestions, or encounter <br>any issues while using Pritam's Notepad, please feel free to contact us at <br> [pritamkadam171@gmail.com].<br><br>"
                + "Thank you for choosing Pritam's Notepad for your text editing needs!  </html>");
        text.setBounds(120, 60, 600, 520);

        add(text);

        JButton b1 = new JButton("OK");
        b1.setBounds(400, 500, 120, 30);
        
        b1.addActionListener(this);
        add(b1);
        add(headerIcon);
        add(ic);
        setVisible(true);

    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);

    }
}
