package com.dashboard.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.dashboard.bo.DashboardBo;
import com.dashboard.constant.DashboardConstants;
import com.dashboard.vo.Employee;
import com.dashboard.vo.EmployeeVo;
import com.dashboard.vo.ProjectVo;
import com.dashboard.vo.TrainingSchedule;

@ManagedBean
@SessionScoped
public class DashboardBean implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 2908287846590722119L;
	private double number;
	private Date date;
	private String employeeName;
	
	private EmployeeVo empvo = new EmployeeVo();
	private ProjectVo projvo = new ProjectVo();
	
	
	@Autowired
	private DashboardBo dashboardBo;
	
	private List<Employee> emplList;
	private List<ProjectVo> projectList;

	private PieChartModel pieModel;
	private DashboardModel model;
	private BarChartModel barModel;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	@PostConstruct
	public void init() {
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();

		column1.addWidget("employee");
		column1.addWidget("technology");

		column2.addWidget("experience");
		column2.addWidget("projects");

		column3.addWidget("training");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);

		
		//dashboardBo = (DashboardBo) FacesContext.getCurrentInstance().getgetBean("docAppBean");
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		dashboardBo = (DashboardBo) ctx.getBean("dashboardBo");
		try {
			emplList = dashboardBo.getEmployeeList();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		createCalender();
		
		createPieModel();

		createAnimatedModels();

	}

	public String go() {
		
		return ("show-test-data");
	}
	
	public String createEmployee(ActionEvent actionEvent) {
		try{
			System.out.println("Create employee started..");
			dashboardBo.createEmployee(empvo);
		} catch (Exception e){
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Saved Failed!!".concat(e.getMessage()),  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Saved Successfully!!",  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        return null;
	}


	public List<ProjectVo> completeProjectname(String query) {
		//List<ProjectVo> projectList = null;
	
		try{
			if(projectList==null || projectList.size()==0){
				projectList = dashboardBo.getProjects();
				
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute(DashboardConstants.PROJ_SESSION_KEY, projectList);
				
			}
			System.out.println( "List of project -->"+projectList);
		} catch(Exception e){
			e.printStackTrace();
		}
        List<ProjectVo> filteredProjects = new ArrayList<ProjectVo>();
        
        for (int i = 0; i < projectList.size(); i++) {
            ProjectVo selected = projectList.get(i);
            if(selected.getName().toLowerCase().startsWith(query)) {
            	filteredProjects.add(selected);
            }
        }
        return filteredProjects;
		
	}

	public void manageReOrder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex()
				+ ", Column index: " + event.getColumnIndex()
				+ ", Sender index: " + event.getSenderColumnIndex());

		addMessage(message);

	}

	private void createAnimatedModels() {
		barModel = initBarModel();
		barModel.setTitle(DashboardConstants.BAR_CHART_TITLE);
		barModel.setAnimate(true);
		barModel.setLegendPosition("ne");
		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(250);
	}

	private void createPieModel() {
		pieModel = new PieChartModel();
		try {
			Map<String, Integer> empTrainingMap = dashboardBo.getPieChartMap();
			if (empTrainingMap != null && empTrainingMap.size() > 0) {
				pieModel.set(">15 Years",
						empTrainingMap.get(DashboardConstants.GT15));
				pieModel.set("10-15 Years",
						empTrainingMap.get(DashboardConstants.IN_BETN_10_15));
				pieModel.set("5-10 Years",
						empTrainingMap.get(DashboardConstants.IN_BETN_5_10));
				pieModel.set("<5 Years",
						empTrainingMap.get(DashboardConstants.LT5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		pieModel.setTitle(DashboardConstants.PIE_CHART_TITLE);
		pieModel.setLegendPosition("w");
		//pieModel.setFill(true);
		pieModel.setMouseoverHighlight(true);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(300);
	}
	
	private void createCalender() {

		eventModel = new DefaultScheduleModel();
	
		try {
			List<TrainingSchedule> tslist = dashboardBo.getTrainingSchedule();
			for (TrainingSchedule trainingSchedule : tslist) {
				eventModel.addEvent(new DefaultScheduleEvent(trainingSchedule.getTrainingName(),
						trainingSchedule.getStartDate(), trainingSchedule.getEndDate()));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Training Conducted");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Training Attended");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 135);
		girls.set("2008", 120);

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
																	// day of
																	// month

		return date.getTime();
	}

	public Date getInitialDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY,
				calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar.getTime();
	}

	private Calendar today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar;
	}

	/*private Date previousDay8Pm() {
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 8);

		return t.getTime();
	}*/


	public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
  
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     

     
    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public double getNumber() {
		return (number);
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public Date getDate() {
		return (date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DashboardModel getModel() {
		return model;
	}

	public void setModel(DashboardModel model) {
		this.model = model;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	
	

	public List<Employee> getEmplList() {
		return emplList;
	}

	public void setEmplList(List<Employee> emplList) {
		this.emplList = emplList;
	}

	public DashboardBo getDashboardBo() {
		return dashboardBo;
	}

	public void setDashboardBo(DashboardBo dashboardBo) {
		this.dashboardBo = dashboardBo;
	}

	public List<ProjectVo> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectVo> projectList) {
		this.projectList = projectList;
	}

	public EmployeeVo getEmpvo() {
		return empvo;
	}

	public void setEmpvo(EmployeeVo empvo) {
		this.empvo = empvo;
	}

	public ProjectVo getProjvo() {
		return projvo;
	}

	public void setProjvo(ProjectVo projvo) {
		this.projvo = projvo;
	}
	
	
}
