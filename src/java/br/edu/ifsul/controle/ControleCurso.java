package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.util.Util;
import br.ifsul.edu.modelo.Curso;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author crisley
 */
@ManagedBean(name = "controleCurso")
@SessionScoped
public class ControleCurso implements Serializable{

    public InstituicaoDAO getDaoInstituicao() {
        return daoInstituicao;
    }

    public void setDaoInstituicao(InstituicaoDAO daoInstituicao) {
        this.daoInstituicao = daoInstituicao;
    }
    
    private CursoDAO dao;
    private Curso objeto;
    private InstituicaoDAO daoInstituicao;
    
    public ControleCurso(){
        dao = new CursoDAO();
        daoInstituicao = new InstituicaoDAO();
    }
    
    public String listar(){
        return "/privado/curso/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Curso();
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

    public CursoDAO getDao() {
        return dao;
    }

    public void setDao(CursoDAO dao) {
        this.dao = dao;
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }
    
    
    
}
