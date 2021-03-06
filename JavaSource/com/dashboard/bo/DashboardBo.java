package com.dashboard.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.dao.DashboardDaoImpl;
import com.dashboard.vo.Employee;
import com.dashboard.vo.EmployeeVo;
import com.dashboard.vo.ProjectVo;
import com.dashboard.vo.TrainingSchedule;
import com.dashboard.vo.TrainingVo;

@Service
public class DashboardBo {
	@Autowired
	private DashboardDaoImpl dashboardDao;

	public List<Employee> getEmployeeList() throws Exception {
		return dashboardDao.getEmployees();

	}

	public Map<String, Integer> getPieChartMap() throws Exception {
		return dashboardDao.getTrainingDetailsOfEmpl();

	}

	public List<ProjectVo> getProjects() throws Exception {
		return dashboardDao.getProjects();

	}

	public List<TrainingSchedule> getTrainingSchedule() throws Exception {
		return dashboardDao.getTrainingSchedule();

	}

	public TrainingSchedule saveTrainingSchedule(TrainingSchedule schedule)
			throws Exception {
		return dashboardDao.saveTrainingSchedule(schedule);

	}

	public List<TrainingVo> getTrainingList() throws Exception {
		return dashboardDao.getTrainingList();
	}

	public ProjectVo findProjectById(int id) throws Exception {
		return dashboardDao.findProjectById(id);

	}

	public void createEmployee(EmployeeVo empvo) throws Exception {
		dashboardDao.createEmployee(empvo);

	}

	public DashboardDaoImpl getDashboardDao() {
		return dashboardDao;
	}

	public void setDashboardDao(DashboardDaoImpl dashboardDao) {
		this.dashboardDao = dashboardDao;
	}
	
	public Map<String,List> fetchtrainingAttaindAndCondectedList() throws Exception {
		return dashboardDao.fetchtrainingAttaindAndCondectedList();
	}
	
	public List<EmployeeVo> fetchEmployeeVo() throws Exception{
		return dashboardDao.fetchEmployeeVo();
	}

}
