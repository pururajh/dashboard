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
import com.dashboard.vo.ProjectVo;
import com.dashboard.vo.TrainingVo;

@FacesConverter("trainingConverter")
public class TrainingConverter implements Converter {

private List<TrainingVo> trainingVos;
	
	@Autowired
	private DashboardBo  dashboardbo;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try{
		if (value != null) {
			HttpSession session = (HttpSession) context.getCurrentInstance().getExternalContext().getSession(true);
			List<TrainingVo> training = (List<TrainingVo>) session.getAttribute(DashboardConstants.TRAIN_SESSION_KEY);
			for (TrainingVo trainingVo : training) {
				if(trainingVo.getIdTraining() == Integer.parseInt(value)){
					System.out.println("selected project-->"+trainingVo);
					return trainingVo;
				}
			}
			System.out.println("selected value List-->"+trainingVos);
			
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
				name = String.valueOf(((TrainingVo) value).getIdTraining());

			} catch (Exception e) {
				e.printStackTrace();
			}
			return name;
			
		}
		else 
			return null;
	}

	public List<TrainingVo> getTrainingVos() {
		return trainingVos;
	}

	public void setTrainingVos(List<TrainingVo> trainingVos) {
		this.trainingVos = trainingVos;
	}

	
}
