package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.util.Util;
import br.ifsul.edu.modelo.Aluno;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author crisley
 */
public class AlunoDAO {
    
    private String mensagem = "";
    private EntityManager em;
    
    public AlunoDAO(){
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Aluno> getLista(){
        return em.createQuery("from aluno order by nome").getResultList();
    }
    
    public boolean salvar(Aluno obj){
        
        try {
            
            em.getTransaction().begin();
            if(obj.getId() == null){
                em.persist(obj);
            }else{
                em.merge(obj);
            }
            
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso";
            return true;
            
        } catch (Exception e) {
            
            if(em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto: "+Util.getMensagemErro(e);
            return false;
        }
        
    }
    
    public boolean remover(Aluno obj){
        
        try {
            
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso";
            return true;
            
        } catch (Exception e) {
            
            if(em.getTransaction().isActive() == false){
                em.getTransaction().begin();
            }
            
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto: "+Util.getMensagemErro(e);
            return false;
        }
        
    }
    
    public Aluno localizar(Integer id){
        return em.find(Aluno.class, id);
    }
    
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
    
}
