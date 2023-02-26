import controller.TaskController;
import jobscheduling.SequenceJob;
import model.JobModel;
import model.JobTaskModel;
import model.TaskModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartJob  extends JFrame {

    int count = 0;
    TaskController taskController = new TaskController();
    JButton back;
    JLabel label;
    JButton button;
    JScrollPane jtf;
    String text="";
    JButton stop;
    ScrollableLabel t;





    StartJob(){
        setTitle("Start Job");
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



        label = new JLabel("Select Job");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60, 200, 200, 20);
        label.setForeground(Color.black);
        add(label);

        label = new JLabel("Job Here");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setBounds(60,230,200,30);
        add(label);


        t=new ScrollableLabel(text);
        t.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        jtf=new JScrollPane(t);
        jtf.setBounds(60,280,350,35);
        jtf.setVisible(true);
        add(jtf);

        button = new JButton("Start Job");
        button.setBounds(60,400,160,30);
        button.setForeground(Color.black);
        button.setFocusPainted(false);
        button.setBackground(new Color(252, 209, 171));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(button);


        button = new JButton("Job Completed");
        button.setBounds(240,400,200,30);
        button.setForeground(Color.black);
        button.setFocusPainted(false);
        button.setBackground(new Color(252, 209, 171));
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "job started");
                try {
                    jobShedule();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });



    }
    public void topoSort(int jobId){
//        int jobId = Integer.parseInt(String.valueOf(jobs.getSelectedItem()));

        JobModel jobModel = taskController.fetchJobBYId(jobId);
        int vertixes = jobModel.getNumOfTask();
        System.out.println("i am vertix: "+vertixes);
        Graph graph = new Graph(vertixes);

        ArrayList<JobTaskModel> jtm = taskController.fetchTaskModels(jobId);
        System.out.println("I am array list of task");
        for(int i=0; i<jtm.size(); i++){
            graph.addEdge(jtm.get(i).getSource(),jtm.get(i).getDestination());
        }

        int[] tasks=graph.topologicalSort();

        for (int i=0; i<tasks.length; i++){
            TaskModel tm =new TaskModel();
            tm=taskController.getTaskById(tasks[i]);
            System.out.println(tm.getTask());
            text+= tm.getTask()+"->";
            t.setText(text);

        }



    }

    public void jobShedule() throws InterruptedException {

        ArrayList<JobModel> jobModels = taskController.fetchJob();
        SequenceJob sequenceJob = new SequenceJob();

        ArrayList<jobscheduling.JobModel> jminsc = new ArrayList<>();

        //creating job model from the job model retreved from database
        for(int i=0; i<jobModels.size(); i++){
            jobscheduling.JobModel jobm = new jobscheduling.JobModel(jobModels.get(i).getJobId(),jobModels.get(i).getProfit(),jobModels.get(i).getTime());
            jminsc.add(jobm);
        }

        List<Integer> scheduledJobs = new ArrayList<Integer>();
        scheduledJobs=sequenceJob.scheduling(jminsc);
        int[] allTask =  new int[scheduledJobs.size()];
        int[] allTime = new int[scheduledJobs.size()];

        for(int i=0; i<scheduledJobs.size(); i++){
            JobModel JM = taskController.fetchJobBYId(scheduledJobs.get(i));
            allTime[i]=JM.getTime();
            allTask[i]=JM.getJobId();

        }
        //created list of executables tasks
        System.out.println(Arrays.toString(allTask));
        Runnable[] TASKS = new Runnable[allTask.length];
        for(int tim = 0; tim<allTime.length;tim++) {

            int finalTim = tim;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    text="";
                    t.setText(text);

                    System.out.println("run task");
                    JobModel job = taskController.fetchJobBYId(allTask[finalTim]);

                    String jobName = job.getJobName();
                    System.out.println(jobName);
                    label.setText(jobName); ///name label or text field?
                    topoSort(allTask[finalTim]);


                }
            };
            TASKS[tim]=task;
        }
        for(int i=0; i<allTime.length; i++){

        }

        //list of date object of when task will execute
        ArrayList<Calendar> TIMES = new ArrayList<>();
        for(int timeTo=0; timeTo<allTime.length;timeTo++){
            Calendar date = Calendar.getInstance();
            date.set(Calendar.HOUR_OF_DAY,11);
            date.set(Calendar.MINUTE,allTime[timeTo]);
            date.set(Calendar.SECOND,0);
            TIMES.add(date);
            System.out.println(date.getTime());
        }


        //executing tasks in the given time
        Timer timer = new Timer();
        for(int i=0; i<TASKS.length; i++){
            final int index=i;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    TASKS[index].run();;
                }
            };
            timer.schedule(task,TIMES.get(i).getTime());
        }







    }

    public static void main(String[] args) {
        new StartJob().setVisible(true);
    }


}
