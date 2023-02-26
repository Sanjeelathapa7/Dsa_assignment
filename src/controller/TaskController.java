package controller;

import database.DatabaseConnection;
import model.JobModel;
import model.JobTaskModel;
import model.TaskModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public int addTask(TaskModel taskModel) {
        try {
            String query = "insert into Taskk(Task,Task_Id) values(?,?)";
            PreparedStatement st = databaseConnection.connection.prepareStatement(query);
            st.setString(1, taskModel.getTask());
            st.setInt(2, taskModel.getTaskId());
            return databaseConnection.manipulate(st);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int addJob(JobModel jobModel) {
        try {
            String query = "insert into table_Job(Job_Id,Job_Name,Job_Profit,Deadline,No_of_Task) values(?,?,?,?,?)";
            PreparedStatement st = databaseConnection.connection.prepareStatement(query);
            st.setInt(1, jobModel.getJobId());
            st.setString(2, jobModel.getJobName());
            st.setInt(3, jobModel.getProfit());
            st.setInt(4, jobModel.getTime());
            st.setInt(5, jobModel.getNumOfTask());
            return databaseConnection.manipulate(st);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public ArrayList<Integer> fetchTask() {
        TaskModel taskModel = null;
        ArrayList<Integer> idList = new ArrayList<>();

        try {
            String query = "select * from Taskk";
            PreparedStatement ps = databaseConnection.connection.prepareStatement(query);
            ResultSet resultSet = databaseConnection.retrieve(ps);

            while (resultSet.next()) {
                idList.add(resultSet.getInt("Task_Id"));
                taskModel = new TaskModel();
                taskModel.setTaskId(resultSet.getInt("Task_Id"));
                taskModel.setTask(resultSet.getString("Task"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idList;
    }

    public int addJobTask(JobTaskModel jobTaskModel) {
        try {
            String query = "insert into tableJobTask(Job_Id, Source_Task, Destination_Task) values(?,?,?)";
            PreparedStatement st = databaseConnection.connection.prepareStatement(query);
            st.setInt(1, jobTaskModel.getJobId());
            st.setInt(2, jobTaskModel.getSource());
            st.setInt(3, jobTaskModel.getDestination());
            return databaseConnection.manipulate(st);
        } catch (SQLException e) {
            e.printStackTrace();
            ;
            return 0;
        }
    }

    public JobModel fetchJobBYId(Integer id) {
        JobModel jobModel = null;
        try {
            String query = "select * from table_Job where Job_Id=?";
            PreparedStatement ps = databaseConnection.connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = databaseConnection.retrieve(ps);
            while (resultSet.next()) {

                jobModel = new JobModel();
                jobModel.setJobId(resultSet.getInt("Job_Id"));
                jobModel.setJobName(resultSet.getString("Job_Name"));
                jobModel.setNumOfTask(resultSet.getInt("No_of_Task"));
                jobModel.setTime(resultSet.getInt("Deadline"));
                jobModel.setProfit(resultSet.getInt("Job_Profit"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobModel;
    }

    public ArrayList<JobModel> fetchJob() {
        JobModel jobModel = null;
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<JobModel> job = new ArrayList<>();

        try {
            String query = "select * from table_Job";
            PreparedStatement ps = databaseConnection.connection.prepareStatement(query);
            ResultSet resultSet = databaseConnection.retrieve(ps);

            while (resultSet.next()) {
                idList.add(resultSet.getInt("Job_Id"));
                jobModel = new JobModel();
                jobModel.setJobId(resultSet.getInt("Job_Id"));
                jobModel.setJobName(resultSet.getString("Job_Name"));
                jobModel.setNumOfTask(resultSet.getInt("No_of_Task"));
                jobModel.setProfit(resultSet.getInt("Job_Profit"));
                jobModel.setTime(resultSet.getInt("Deadline"));

                job.add(jobModel);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }

    public ArrayList<JobTaskModel> fetchTaskModels(int jobId) {
        ArrayList<JobTaskModel> list = new ArrayList<>();
        try {
            String query = "select * from tableJobTask where Job_Id=?";
            PreparedStatement ps = databaseConnection.connection.prepareStatement(query);
            ps.setInt(1, jobId);
            ResultSet resultSet = databaseConnection.retrieve(ps);


            while (resultSet.next()) {
                JobTaskModel jtm = new JobTaskModel();
                jtm.setJobId(resultSet.getInt("Job_Id"));
                jtm.setSource(resultSet.getInt("Source_Task"));
                jtm.setDestination(resultSet.getInt("Destination_Task"));
                list.add(jtm);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public TaskModel getTaskById(Integer id) {
        TaskModel taskModel = new TaskModel();
        try {
            String query = "select * from Taskk where Task_Id=?";
            PreparedStatement ps = databaseConnection.connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = databaseConnection.retrieve(ps);
            while (resultSet.next()) {
                taskModel.setTaskId(resultSet.getInt("Task_Id"));
                taskModel.setTask(resultSet.getString("Task"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskModel;
    }

    public List<JobModel> getAllJob() {
        ArrayList<JobModel> jobs = new ArrayList<>();

        try {
            String query = "select * from table_Job";
            PreparedStatement ps = databaseConnection.connection.prepareStatement(query);
            ResultSet resultSet = databaseConnection.retrieve(ps);
            while (resultSet.next()) {
                JobModel j = new JobModel();
                j.setJobId(resultSet.getInt("Job_Id"));
                j.setJobName(resultSet.getString("Job_Name"));
                j.setNumOfTask(resultSet.getInt("No_of_Task"));
                j.setProfit(resultSet.getInt("Job_Profit"));
                j.setTime(resultSet.getInt("Deadline"));
                jobs.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}

