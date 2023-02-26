import controller.TaskController;
import model.TaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddTaskPage extends JFrame {

    JLabel background;
    JLabel label;
    JTextField Task;
    JTextField textId;
    JButton button;
    JButton button1;





    public AddTaskPage() {
        initialize();
        uIInitialize();



    }



    public void initialize () {
        Color c = new Color(215, 245, 215);
        getContentPane().setBackground(c);
        setTitle("Add Your Task");
        setSize(1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0x040505));
        getContentPane();

    }
    public void uIInitialize(){

        label = new JLabel("Task Id");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        label.setBounds(820, 250, 100, 25);
        label.setForeground(Color.black);
        add(label);


        textId = new JTextField();
        textId.setBounds(980, 250, 200, 30);
        add(textId);

        label = new JLabel("AddTask");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        label.setBounds(820, 320, 400, 40);
        label.setForeground(Color.black);
        add(label);


        Task= new JTextField();
        Task.setBounds(980, 320, 200, 30);
        add(Task);


        button = new JButton("Add Task");
        button.setBounds(850,400,150,35);
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(0xC99F70));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(button);

        button1 = new JButton("Create Job");
        button1.setBounds(1050,400,150,35);
        button1.setForeground(Color.BLACK);

        button1.setBackground(new Color(0xC99F70));
        button1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(button1);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateJob createJob = new CreateJob();
                createJob.show();
                dispose();
            }
        });
    }


    private void addTask(){
        String task=Task.getText();
        int taskId = Integer.parseInt(textId.getText());

        if(task.isEmpty()||textId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all the fields");

        }

        else{
            TaskModel taskModel = new TaskModel(task,taskId);

            TaskController taskController = new TaskController();
            int insert =taskController.addTask(taskModel);

            if (insert > 0) {
                JOptionPane.showMessageDialog(null, "Task Added Successfully");


            } else {
                JOptionPane.showMessageDialog(null, "Failed to Add Task");
            }
        }
    }





    public static void main(String[] args) {
        new AddTaskPage().setVisible(true);
    }
}