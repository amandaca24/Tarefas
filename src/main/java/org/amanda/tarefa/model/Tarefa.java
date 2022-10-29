package org.amanda.tarefa.model;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name="titulo")
    private String titulo;

    @Column(name="descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    @Column(name="prioridade")
    private Prioridade prioridade;

    @Column(name="dead_line")
    @Temporal(TemporalType.DATE)
    private Date deadLine;

    @Column(name="situacao")
    private Situacao situacao;
    
    @Column(name="ultatual")
    @Temporal(TemporalType.DATE)
    private Date ultimaAtualizacao;

    public String getSituacaoFormatada(Situacao situacao){
        String novaSituacao = "";
        if(situacao.equals(Situacao.EM_ANDAMENTO)){
            novaSituacao = "Em andamento";
        } else {
            novaSituacao = "Concluída";
        }
        return novaSituacao;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", responsavel=" + responsavel
				+ ", prioridade=" + prioridade + ", deadLine=" + deadLine + ", situacao=" + situacao
				+ ", ultimaAtualizacao=" + ultimaAtualizacao + "]";
	}
   
}
