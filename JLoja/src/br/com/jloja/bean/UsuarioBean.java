package br.com.jloja.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.jloja.DAO.UsuarioDAO;
import br.com.jloja.entity.UsuarioEntity;
import br.com.jloja.util.MsgUtil;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioBean {
	
private List<UsuarioEntity> listaUsuarios;
private List<UsuarioEntity> listaUsuariosFiltrados;
private UsuarioEntity usuarioEntity;
private Long codigo;

private UsuarioEntity usuarioLogado;

public UsuarioEntity getUsuarioLogado(){
	if (usuarioLogado == null){
		usuarioLogado = new UsuarioEntity();
	}
	return usuarioLogado;
}
public void setUsuarioLogado(UsuarioEntity usuarioLogado) {
	this.usuarioLogado = usuarioLogado;
}
public List<UsuarioEntity> getListaUsuarios() {
	return listaUsuarios;
}
public void setListaUsuarios(List<UsuarioEntity> listaUsuarios) {
	this.listaUsuarios = listaUsuarios;
}
public List<UsuarioEntity> getListaUsuariosFiltrados() {
	return listaUsuariosFiltrados;
}
public void setListaUsuariosFiltrados(List<UsuarioEntity> listaUsuariosFiltrados) {
	this.listaUsuariosFiltrados = listaUsuariosFiltrados;
}
public UsuarioEntity getUsuarioEntity() {
	return usuarioEntity;
}
public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
	this.usuarioEntity = usuarioEntity;
}
public Long getCodigo() {
	return codigo;
}
public void setCodigo(Long codigo) {
	this.codigo = codigo;
}
public UsuarioBean(){
	this.usuarioEntity = new UsuarioEntity();
}
public void listarUsuario(){
	try{
		UsuarioDAO usuDAO = new UsuarioDAO();
		listaUsuarios = usuDAO.listar();
		
	}catch(Exception ex){
		throw ex;
	}
}
public void adicionarUsuario(){
	try{
		UsuarioDAO usudao = new UsuarioDAO();
		usudao.adicionar(usuarioEntity);
		usuarioEntity = new UsuarioEntity();
		MsgUtil.msgInfo("Usuario gravado com sucesso");
		
	}catch(Exception ex){
		MsgUtil.msgErro("Erro ao gravar usuario" + ex.getMessage());
	}
  }
public void buscarUsuarioCodigo(Long codigo){
	try{
		UsuarioDAO fabDAO = new UsuarioDAO();
		usuarioEntity = fabDAO.buscarPorCodigo(codigo);
	}catch(Exception ex){
		throw ex;
	}
  }
public void editarUsuario(){
	try{
		UsuarioDAO fabdao = new UsuarioDAO();
		fabdao.editar(usuarioEntity);
		usuarioEntity = new UsuarioEntity();
		MsgUtil.msgInfo("Usuario editado com sucesso!");
	}catch(Exception ex){
		MsgUtil.msgErro("Erro ao tentar editar um usuario" + ex.getMessage());
	}
 }
public void excluirUsuario(){
	try{
		UsuarioDAO fabdao = new UsuarioDAO();
		fabdao.excluir(usuarioEntity);
		usuarioEntity = new UsuarioEntity();
		MsgUtil.msgInfo("Usuariio excluido com sucesso!");
	}catch(Exception ex){
		MsgUtil.msgErro("Erro ao tentar excluir usuario: " + ex.getMessage());
	}
  }
public void autenticar(){
	try{
		UsuarioDAO usuDAO = new UsuarioDAO();
		
		usuarioLogado = usuDAO.autenticar(usuarioLogado.getLogin(), usuarioLogado.getSenha());
		
		if (usuarioLogado == null){
			MsgUtil.msgErro("Usuario e/ ou senha invalidos");
			usuarioEntity = new UsuarioEntity();
		}else{
			if(usuarioLogado.getSituacao() == 'N'){
				MsgUtil.msgErro("Este usuario esta inativo no sistema!");
				usuarioLogado = null;
			} else{
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect("/jloja/index.xhtml");
			}
		}
	}catch (Exception ex){
		MsgUtil.msgErro("Erro ao Autenticar usuário!");
	}
}
 public void verificaLogado(){
	 try{
		 if (usuarioLogado.getIdusuario() == null){
			 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			 ec.redirect("/jloja/view/login.xhtml");
		 }
	 }catch(IOException e){
	 MsgUtil.msgErro("Erro ao verificar usuario!");
		 
	 }
 }
 public void sair(){
	 try{
		 usuarioLogado = null;
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		 ec.redirect("jloja/view/login.xhtml");
	 }catch(IOException e){
		 MsgUtil.msgErro("Erro ao sair do sistema");
	 }
 }
}
