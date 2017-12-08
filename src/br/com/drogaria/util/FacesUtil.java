package br.com.drogaria.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtil {

	public static void adicionarMsgInfo(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);

		facesContext.addMessage(null, facesMessage);
	}

	public static void adicionarMsgError(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);

		facesContext.addMessage(null, facesMessage);

		facesContext.addMessage(null, facesMessage);
	}

	public static String getParam(String nome) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map<String, String> parametro = externalContext.getRequestParameterMap();
		String valor = parametro.get(nome);
		return valor;
	}

}
