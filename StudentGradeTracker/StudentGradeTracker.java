import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeTracker extends JFrame {

    JTextField nameField, marksField;
    JTextArea outputArea;
    ArrayList<Student> students = new ArrayList<>();

    public StudentGradeTracker() {

        setTitle("Student Grade Tracker");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        
        JLabel title = new JLabel("Student Grade Tracker", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

       
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();

        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();

        inputPanel.add(marksField);

     
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Generate Report");

        buttonPanel.add(addButton);
        buttonPanel.add(reportButton);

     
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.WEST);

   
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Summary Report"));

        add(scrollPane, BorderLayout.CENTER);


        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int marks = Integer.parseInt(marksField.getText());

                students.add(new Student(name, marks));
                outputArea.append("Added → " + name + " : " + marks + "\n");

                nameField.setText("");
                marksField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid details!");
            }
        });

        reportButton.addActionListener(e -> {

            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No student data available!");
                return;
            }

            int total = 0;
            int highest = students.get(0).marks;
            int lowest = students.get(0).marks;

            outputArea.append("\n------ STUDENT SUMMARY ------\n");
            outputArea.append("Name\tMarks\n");

            for (Student s : students) {
                outputArea.append(s.name + "\t" + s.marks + "\n");
                total += s.marks;

                if (s.marks > highest) highest = s.marks;
                if (s.marks < lowest) lowest = s.marks;
            }

            double average = (double) total / students.size();

            outputArea.append("------------------------------\n");
            outputArea.append("Average : " + average + "\n");
            outputArea.append("Highest : " + highest + "\n");
            outputArea.append("Lowest  : " + lowest + "\n");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentGradeTracker();
    }
}

