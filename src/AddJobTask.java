import controller.TaskController;
import model.JobTaskModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class AddJobTask extends JFrame{
    JButton back;
    JLabel label;
    JTextField textId;
    JComboBox box1;
    JComboBox box2;
    JButton button;


    AddJobTask(){
        setTitle("Select Task For Job");
        setSize(1280, 720 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(new Color(215, 245, 215));
        getContentPane();
        initilize();
    }

    void initilize(){
        Color c = new Color(215, 245, 215);
        getContentPane().setBackground(c);
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

        TaskController taskController = new TaskController();
        ArrayList<Integer> idtaskList = taskController.fetchTask();
        String[] idOfTaskList=new String[idtaskList.size()+1];
        System.out.println(idtaskList);


        for(int i=0; i<idtaskList.size(); i++){

            System.out.println(idtaskList.get(i));
            idOfTaskList[i]=idtaskList.get(i).toString();


        }

        System.out.println(Arrays.toString(idOfTaskList));

        label = new JLabel("Job Id");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 140, 100, 20);
        label.setForeground(Color.black);
        add(label);


        textId = new JTextField();
        textId.setBounds(180, 140, 120, 25);
        add(textId);


        label = new JLabel("Current Task");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 200, 150, 20);
        label.setForeground(Color.black);
        add(label);



        box1= new JComboBox(idOfTaskList);
        box1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        box1.setBounds(180,200,150,30);
        add(box1);

        label = new JLabel("Dependent Task");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 260, 200, 25);
        label.setForeground(Color.black);
        add(label);



        box2 = new JComboBox(idOfTaskList);
        box2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        box2.setBounds(200,260,200,30);
        add(box2);

        button = new JButton("Add Task");
        button.setBounds(80,330,150,35);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setBackground(new Color(0xC99F70));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(button);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jobId;
                int source;
                int destination;

                if(textId.getText().isEmpty()||String.valueOf(box1.getSelectedItem()).isEmpty()){
                    JOptionPane.showMessageDialog(null, "blank fields", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    jobId=Integer.parseInt(textId.getText());
                    source=Integer.parseInt(String.valueOf(box2.getSelectedItem()));
                    destination=Integer.parseInt(String.valueOf(box1.getSelectedItem()));

                    JobTaskModel jobTaskModel = new JobTaskModel(jobId,source,destination);
                    TaskController taskController1 = new TaskController();
                    int insert = taskController.addJobTask(jobTaskModel);

                    if(insert>0){
                        JOptionPane.showMessageDialog(null, "Task added Successfully");
                    }else{
                        JOptionPane.showMessageDialog(null, "Filed to add Task");
                    }


                }
            }
        });

    }





    public static void main(String[] args)
    {
        new AddJobTask().setVisible(true);
    }





}
