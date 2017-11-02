package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	public void salvar(){
		FacesUtil.adicionarMsgInfo("Fabricante salvo com sucesso!!!");
	}
}
