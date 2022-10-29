package org.amanda.tarefa.filtro;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.amanda.tarefa.model.Prioridade;
import org.amanda.tarefa.model.Responsavel;
import org.amanda.tarefa.model.Situacao;

@Getter
@Setter
public class TarefaFiltro {
	
	private Long idEquals;
	private String tituloEquals;
	private String tituloLike;
	private String descricaoEquals;
	private String descricaoLike;
	private Responsavel responsavelEquals;
	private Prioridade prioridadeEquals;
	private Date deadLineEquals;
	private Situacao situacaoEquals;

	@Override
	public String toString() {
		return "TarefaFiltro{" +
				"idEquals=" + idEquals +
				", tituloEquals='" + tituloEquals + '\'' +
				", tituloLike='" + tituloLike + '\'' +
				", descricaoEquals='" + descricaoEquals + '\'' +
				", descricaoLike='" + descricaoLike + '\'' +
				", responsavelEquals=" + responsavelEquals +
				", prioridadeEquals=" + prioridadeEquals +
				", deadLineEquals=" + deadLineEquals +
				", situacaoEquals=" + situacaoEquals +
				'}';
	}
}
