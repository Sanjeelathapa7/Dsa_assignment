
import controller.TaskController;
import model.JobModel;
import model.TaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;


public class CreateJob extends JFrame {
    JLabel label;
    JTextField JobId;
    JTextField NoTask;
    JTextField Profit;
    JTextField Time;
    JTextField Name;
    JButton button1;
    JButton button2;
    JButton back;

    public CreateJob() {
        initialize();
        uIInitialize();


    }

    public void initialize () {
        Color c = new Color(215, 245, 215);
        getContentPane().setBackground(c);
        setTitle("Add Your Task");
        setBounds(0, 0, 1550, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(0x040505));
        getContentPane();

    }
    public void uIInitialize(){
        back = new JButton("Back");
        back.setBounds(6, 10, 70, 20);
        back.setFont(new Font("Times New Roman", Font.BOLD, 10));
        back.setFocusPainted(false);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskPage addTaskPage = new AddTaskPage();
                addTaskPage.show();
                dispose();
            }
        });
        setBackground(new Color(0xC99F70));
        getContentPane();
        label = new JLabel("Job Id");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 140, 100, 25);
        label.setForeground(Color.black);
        add(label);


        JobId = new JTextField();
        JobId.setBounds(180, 140, 250, 30);
        add(JobId);




        label=new JLabel("Profit");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 185, 100, 25);
        label.setForeground(Color.black);
        add(label);


        Profit = new JTextField();
        Profit.setBounds(180, 185, 250, 30);
        add(Profit);


        label = new JLabel("No. Of Task");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 225, 200, 30);
        label.setForeground(Color.black);
        add(label);


        NoTask = new JTextField();
        NoTask.setBounds(180, 225, 250, 30);
        add(NoTask);


        label= new JLabel("Job name");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 260, 400, 40);
        label.setForeground(Color.black);
        add(label);





        Name = new JTextField();
        Name.setBounds(180, 268, 250, 30);
        add(Name);

        label = new JLabel("End Time");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 310, 100, 25);
        label.setForeground(Color.black);
        add(label);


        Time = new JTextField();
        Time.setBounds(180, 310, 250, 30);
        add(Time);



        button1 = new JButton("Create Job");
        button1.setBounds(60,390,140,30);
        button1.setForeground(Color.black);
        button1.setFocusPainted(false);
        button1.setBackground(new Color(252, 209, 171));
        button1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(button1);


        button2 = new JButton("Add Task");
        button2.setBounds(220,390,140,30);
        button2.setForeground(Color.black);
        button2.setFocusPainted(false);
        button2.setBackground(new Color(252, 209, 171));
        button2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(button2);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJob();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJobTask addJobTask = new AddJobTask();
                addJobTask.show();
                dispose();
            }
        });



    }

    private void addJob(){
        int id=Integer.parseInt(JobId.getText());
        String jobName = Name.getText();
        int numOfTask = Integer.parseInt(NoTask.getText());
        int dead=Integer.parseInt(Time.getText());
        int profit = Integer.parseInt(Profit.getText());

        if(id==0||jobName.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please Enter All Field","Try again",JOptionPane.ERROR_MESSAGE);
        }else{
            JobModel jobModel = new JobModel(id, jobName,numOfTask,dead,profit);
            TaskController taskController = new TaskController();

            int insert = taskController.addJob(jobModel);


            if(insert>0){

                JOptionPane.showMessageDialog(null, "Job Created Successfully");

            }

            else{
                JOptionPane.showMessageDialog(null, "Failed to create a job");
            }
        }

    }








    public static void main(String[] args) {
        new CreateJob().setVisible(true);
    }
}