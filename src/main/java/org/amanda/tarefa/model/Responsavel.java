package org.amanda.tarefa.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="nome")
    private String nome;

    @OneToMany
    @JoinColumn(name = "tarefa_id")
    private Collection<Tarefa> tarefas;
    
    @Column(name="ultatual")
    @Temporal(TemporalType.DATE)
    private Date ultimaAtualizacao;

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
		Responsavel other = (Responsavel) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Responsavel [id=" + id + ", nome=" + nome + ", tarefas=" + tarefas + ", ultimaAtualizacao="
				+ ultimaAtualizacao + "]";
	}
    
}
