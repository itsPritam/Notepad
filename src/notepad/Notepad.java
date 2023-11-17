package notepad;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.filechooser.*;


public class Notepad extends JFrame implements ActionListener {

    JTextArea area;
    String text;

    public Notepad() {

        setTitle("Notepad");

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notes.png"));
        Image icon = ic.getImage();
        setIconImage(icon);

        JMenuBar menubar = new JMenuBar();
        
        menubar.setBackground(Color.WHITE);

        JMenu file = new JMenu("File");

        file.setFont(new Font("AERIAL", Font.PLAIN, 14));

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");

        edit.setFont(new Font("AERIAL", Font.PLAIN, 14));

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        JMenu about = new JMenu("About");
        about.addActionListener(this);
        about.setFont(new Font("AERIAL", Font.PLAIN, 14));

        JMenuItem aboutMenu = new JMenuItem("About");
        aboutMenu.addActionListener(this);
        aboutMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        about.add(aboutMenu);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(about);
        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        add(area);

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);

    }

    public static void main(String[] args) {

        new Notepad();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            area.setText("");
        } else if (e.getActionCommand().equals("Open")) {

            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt file", "txt");
            chooser.addChoosableFileFilter(restrict);
            int action = chooser.showOpenDialog(this);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File file = chooser.getSelectedFile();

            //this buufer reader is used to read the existing file
            try {

                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);

            } catch (Exception exc) {
                exc.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Save")) {
            JFileChooser saveAs = new JFileChooser();
            saveAs.setApproveButtonText("Save");

            int action = saveAs.showOpenDialog(this);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File filename = new File(saveAs.getSelectedFile() + ".txt");
            BufferedWriter outputFile = null;
            try {
                outputFile = new BufferedWriter(new FileWriter(filename));
                area.write(outputFile);
            } catch (Exception exc) {
                exc.printStackTrace();
            }

        } else if (e.getActionCommand().equals("Exit")) {

            System.exit(0);
        } else if (e.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        } else if (e.getActionCommand().equals("Copy")) {

            text = area.getSelectedText();

        } else if (e.getActionCommand().equals("Paste")) {

            area.insert(text, area.getCaretPosition());

        } else if (e.getActionCommand().equals("Cut")) {

            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());

        } else if (e.getActionCommand().equals("Select All")) {
            area.selectAll();
        } else if (e.getActionCommand().equals("About")) {

            new About();

        }

    }

}
