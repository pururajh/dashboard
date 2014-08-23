package com.dashboard.helper;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.dashboard.bo.DashboardBo;
import com.dashboard.constant.DashboardConstants;
import com.dashboard.dao.DashboardDaoImpl;
import com.dashboard.vo.ProjectVo;


@FacesConverter("projectConverter")
public class ProjectConverter implements Converter{
	
	private List<ProjectVo> proVos;
	
	@Autowired
	private DashboardBo  dashboardbo;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try{
		if (value != null) {
			System.out.println("selected value-->"+value);
			HttpSession session = (HttpSession) context.getCurrentInstance().getExternalContext().getSession(true);
			List<ProjectVo> projs = (List<ProjectVo>) session.getAttribute(DashboardConstants.PROJ_SESSION_KEY);
			for (ProjectVo projectVo : projs) {
				if(projectVo.getId() == Integer.parseInt(value)){
					System.out.println("selected project-->"+projectVo);
					return projectVo;
				}
			}
			System.out.println("selected value List-->"+projs);
			
		}
		}catch (Exception e){
			e.printStackTrace();
		}

        return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			String name = null;
			try {
				name = String.valueOf(((ProjectVo) value).getId());

			} catch (Exception e) {
				e.printStackTrace();
			}
			return name;
			
		}
		else 
			return null;
	}

	public List<ProjectVo> getProVos() {
		return proVos;
	}

	public void setProVos(List<ProjectVo> proVos) {
		this.proVos = proVos;
	}
	
	

}
