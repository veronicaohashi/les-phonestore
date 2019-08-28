package les.domain;

import java.util.Date;

public class DomainEntity implements IEntity{
	private Integer id;
	private Date dtCadastro;
	private Date dtAlteracao;
	
	public DomainEntity() {
		
	}
	
	public DomainEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}	
}
