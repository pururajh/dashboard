package com.dashboard.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.dashboard.constant.DashboardConstants;
import com.dashboard.vo.Employee;
import com.dashboard.vo.EmployeeVo;
import com.dashboard.vo.ProjectVo;
import com.dashboard.vo.TrainingAttaine;
import com.dashboard.vo.TrainingConducted;
import com.dashboard.vo.TrainingSchedule;
import com.dashboard.vo.TrainingVo;

public class DashboardDaoImpl {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createEmployee(EmployeeVo empvo) throws Exception {
		Session session = null;
		try {
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			session.save(empvo);
			session.flush();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public List<ProjectVo> getProjects() throws Exception {

		Session session = null;
		try {
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			Criteria pcriteria = session.createCriteria(ProjectVo.class);
			List<ProjectVo> pList = pcriteria.list();
			return pList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public List<Employee> getEmployees() throws Exception {

		Session session = null;
		try {

			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			Query query = session.getNamedQuery("findEmployeeNativeSQL");
			List<Employee> empList = new ArrayList<Employee>();
			List eList = query.list();
			Iterator iter = eList.iterator();
			Employee empoutput = null;

			while (iter.hasNext()) {
				Object[] object = (Object[]) iter.next();
				empoutput = new Employee();
				empoutput.setEmpId((Integer) object[0]);
				empoutput.setEmpName((String) object[1]);
				empoutput.setProjName((String) object[2]);
				empoutput.setTcsExp((Float) object[3]);
				empoutput.setOveralExp((Float) object[4]);
				empList.add(empoutput);
			}

			return empList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public ProjectVo findProjectById(int id) throws Exception {
		Session session = null;
		try {
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			Criteria pcriteria = session.createCriteria(ProjectVo.class);
			pcriteria.add(Restrictions.like("id", id));

			List<ProjectVo> pList = pcriteria.list();

			return pList != null ? pList.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public TrainingSchedule saveTrainingSchedule(TrainingSchedule schedule)
			throws Exception {
		Session session = null;
		try {
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			session.save(schedule);
			session.flush();
			return schedule;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Map<String, Integer> getTrainingDetailsOfEmpl() throws Exception {

		Session session = null;
		try {

			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			Map<String, Integer> pieMap = new HashMap<String, Integer>();

			Query gt15query = session.getNamedQuery("findNoofTrainingGt15");
			List gt15List = gt15query.list();
			if (CollectionUtils.isNotEmpty(gt15List)) {
				Object[] object = (Object[]) gt15List.get(0);
				System.out.println(">15 years have taken trainings "
						+ (Integer) object[0]);
				pieMap.put(DashboardConstants.GT15, (Integer) object[0]);
			} else {
				pieMap.put(DashboardConstants.GT15, DashboardConstants.Zero);
			}

			Query bm1015query = session
					.getNamedQuery("findNoofTrainingBn10t15");
			List bm1015List = bm1015query.list();
			if (CollectionUtils.isNotEmpty(bm1015List)) {
				Object[] bm1015object = (Object[]) bm1015List.get(0);
				pieMap.put(DashboardConstants.IN_BETN_10_15,
						(Integer) bm1015object[0]);
				System.out.println("10 - 15 years have taken trainings "
						+ (Integer) bm1015object[0]);
			} else {
				pieMap.put(DashboardConstants.IN_BETN_10_15,
						DashboardConstants.Zero);
			}

			Query bm510query = session.getNamedQuery("findNoofTrainingBn5t10");
			List bm510List = bm510query.list();
			if (CollectionUtils.isNotEmpty(bm510List)) {
				Object[] bm510object = (Object[]) bm510List.get(0);
				pieMap.put(DashboardConstants.IN_BETN_5_10,
						(Integer) bm510object[0]);
				System.out.println("5 - 10 years have taken trainings "
						+ (Integer) bm510object[0]);
			} else {
				pieMap.put(DashboardConstants.IN_BETN_5_10,
						DashboardConstants.Zero);
			}

			Query lt5query = session.getNamedQuery("findNoofTrainingLt5");
			List lt5List = lt5query.list();
			if (CollectionUtils.isNotEmpty(lt5List)) {
				Object[] lt5object = (Object[]) lt5List.get(0);
				pieMap.put(DashboardConstants.LT5, (Integer) lt5object[0]);
				System.out.println("<5 years have taken trainings "
						+ (Integer) lt5object[0]);
			} else {
				pieMap.put(DashboardConstants.LT5, DashboardConstants.Zero);
			}

			return pieMap;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public List<TrainingSchedule> getTrainingSchedule() throws Exception {

		Session session = null;
		try {

			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			Query trainingSchedulequery = session
					.getNamedQuery("findTrainingSchedule");
			List trainingScheduleList = trainingSchedulequery.list();
			TrainingSchedule ts = null;
			List<TrainingSchedule> tsList = new ArrayList<TrainingSchedule>();
			if (CollectionUtils.isNotEmpty(trainingScheduleList)) {
				for (Iterator iterator = trainingScheduleList.iterator(); iterator
						.hasNext();) {
					Object[] object = (Object[]) iterator.next();
					ts = new TrainingSchedule();
					ts.setTrainingName((String) object[0]);
					ts.setStartDate((Date) object[1]);
					ts.setEndDate((Date) object[2]);
					tsList.add(ts);

				}
			}
			System.out.println("Training Schedule-->" + tsList);
			return tsList;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<TrainingVo> getTrainingList() throws Exception {
		Session session = null;
		try {

			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			Criteria ct = session.createCriteria(TrainingVo.class);
			return ct.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	public Map<String,List> fetchtrainingAttaindAndCondectedList() throws Exception {
		Session session = null;
		try {
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			session.setFlushMode(FlushMode.COMMIT);
			Query conductQuery = session.getNamedQuery("fetchTrainingConuctedList");
			Query attaineeQuery = session.getNamedQuery("fetchTrainingAttendList");
			List trainingConducted = conductQuery.list();
			List<TrainingConducted> trainingConductedList = new ArrayList<TrainingConducted>();
			TrainingConducted tc = null;
			
			if (CollectionUtils.isNotEmpty(trainingConducted)) {
				for (Iterator iterator = trainingConducted.iterator(); iterator
						.hasNext();) {
					Object[] object = (Object[]) iterator.next();
					tc = new TrainingConducted();
					tc.setProject((String) object[0]);
					tc.setNumberOfEmployee((Integer)object[1]);
					trainingConductedList.add(tc);

				}
			}
			
			
			//Training Attaine List
			List trainingAttainee = attaineeQuery.list();
			List<TrainingAttaine> trainingAttaineeList = new ArrayList<TrainingAttaine>();
			TrainingAttaine ta = null;
			
			if (CollectionUtils.isNotEmpty(trainingAttainee)) {
				for (Iterator iterator = trainingAttainee.iterator(); iterator
						.hasNext();) {
					Object[] object = (Object[]) iterator.next();
					ta = new TrainingAttaine();
					ta.setProject((String) object[0]);
					ta.setCountOfEmployee((Integer)object[1]);
					trainingAttaineeList.add(ta);

				}
			}
			
			Map<String,List> map = new HashMap();
			map.put("conductList", trainingConductedList);
			map.put("attaineList", trainingAttaineeList);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
