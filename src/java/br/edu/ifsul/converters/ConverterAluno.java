
package br.edu.ifsul.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.ifsul.edu.modelo.Aluno;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

/**
 *
 * @author crisley
 */
@FacesConverter(value = "converterAluno")
public class ConverterAluno implements Serializable, Converter{

    
    //Converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }
        
        try {
            EntityManager em = EntityManagerUtil.getEntityManager();
            return em.find(Aluno.class, Integer.parseInt(string));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null){
            return null;
        }
        
        Aluno obj = (Aluno) o;
        return obj.getId().toString();
    }
    
    
    
}
