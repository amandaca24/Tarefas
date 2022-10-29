package org.amanda.tarefa.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.amanda.tarefa.filtro.TarefaFiltro;
import org.amanda.tarefa.manager.EntityManagerProvider;
import org.amanda.tarefa.model.Responsavel;
import org.amanda.tarefa.model.Situacao;
import org.amanda.tarefa.model.Tarefa;
import org.primefaces.model.JpaLazyDataModel;
import org.primefaces.model.LazyDataModel;

import lombok.Data;

@Data
@Named
@ViewScoped
public class TarefaView implements Serializable {

    private String string;
    private TarefaFiltro filtro;
    private Tarefa tarefa;
    private Responsavel responsavel;

    @Inject
    private EntityManager em;
    private LazyDataModel<Tarefa> lazyDataModel;
    private List<Tarefa> tarefas = Collections.emptyList();
    private LazyDataModel<Responsavel> respLazyDataModel;
    private EntityManagerProvider emp;

    @PostConstruct
    public void init() {
        string = "Gestor de Tarefas";

        lazyDataModel = new JpaLazyDataModel<>(Tarefa.class, () -> em);
        respLazyDataModel = new JpaLazyDataModel<>(Responsavel.class, () -> em);
        filtro = new TarefaFiltro();
        tarefa = new Tarefa();
        responsavel = new Responsavel();
    }

    public LazyDataModel<Tarefa> getLazyDataModel() {
        return lazyDataModel;
    }
    public LazyDataModel<Responsavel> getRespLazyDataModel(){ return respLazyDataModel; }

    public List<Tarefa> buscarTarefasPorFiltro(){
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        System.out.println("######## FILTRO => " + filtro.toString());
        String select = "SELECT t FROM Tarefa t WHERE";

        StringBuilder whereClause = new StringBuilder();

        if(filtro.getIdEquals() != null){
            whereClause.append(" (t.id = " + ":id");
            paramaterMap.put("id", filtro.getIdEquals());
        }
        if(filtro.getTituloLike() != null){
            whereClause.append(" t.titulo LIKE " + ":titulo ");
            paramaterMap.put("titulo", filtro.getTituloLike());
        }

        String query = select + whereClause;
        System.out.println("QUERY => " + query);
        Query jpa = em.createQuery(query);

        for(String key :paramaterMap.keySet()){
            jpa.setParameter(key, paramaterMap.get(key));
        }

        tarefas = jpa.getResultList();
        return tarefas;

    }

    public String salvarTarefa(){
        em.getTransaction().begin();
        tarefa.setSituacao(Situacao.EM_ANDAMENTO);
        tarefa.setUltimaAtualizacao(new Date());
        em.persist(tarefa);
        em.getTransaction().commit();
        return "tarefa.xhtml?faces-redirect=true";
    }

    public String salvarResponsavel(){
        em.getTransaction().begin();
        responsavel.setUltimaAtualizacao(new Date());
        em.persist(responsavel);
        em.getTransaction().commit();
        return "tarefa.xhtml?faces-redirect=true";
    }

}
