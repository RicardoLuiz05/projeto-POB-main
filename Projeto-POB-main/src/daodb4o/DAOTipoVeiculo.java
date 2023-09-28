
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.TipoVeiculo;

public class DAOTipoVeiculo  extends DAO<TipoVeiculo>{

	public TipoVeiculo read (Object chave){
		String nome = (String) chave;	
		Query q = manager.query();
		q.constrain(TipoVeiculo.class);
		q.descend("Tipo").constrain(nome);
		List<TipoVeiculo> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

//	public void create(TipoVeiculo obj){
//		int novoid = super.gerarId();  	//gerar novo id da classe
//		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
//		manager.store( obj );
//	}

	//--------------------------------------------
	//  consultas de Aluguel
	//--------------------------------------------

//	public List<TipoVeiculo> alugueisModelo(String modelo){
//		Query q;
//		q = manager.query();
//		q.constrain(TipoVeiculo.class);
//		q.descend("carro").descend("modelo").constrain(modelo);
//		return q.execute();
//	}
//
//	public List<TipoVeiculo> alugueisFinalizados(){
//		Query q = manager.query();
//		q.constrain(TipoVeiculo.class);
//		q.descend("finalizado").constrain(true);
//		return q.execute();
//	}
}

