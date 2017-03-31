package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.util.Util;
import br.ifsul.edu.modelo.Especialidade;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author crisley
 */
@ManagedBean(name = "controleEspecialidade")
@SessionScoped
public class ControleEspecialidade implements Serializable{
    
    private EspecialidadeDAO dao;
    private Especialidade objeto;
    
    public ControleEspecialidade(){
        dao = new EspecialidadeDAO();
    }
    
    public String listar(){
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Especialidade();
        return "formulario";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public EspecialidadeDAO getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }
    
    
    
}
