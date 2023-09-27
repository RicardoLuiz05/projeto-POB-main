package Regras_negocio;

import daodb4o.DAOVeiculo;
import daodb4o.DAO;
import daodb4o.DAORegistro;
import daodb4o.DAOTipoVeiculo;
import daodb4o.DAOUsuario;
import modelo.Veiculo;
import modelo.Registro;
import modelo.TipoVeiculo;
import modelo.Usuario;

public class Fachada {
	private static DAOVeiculo daoveiculo = new DAOVeiculo();  
	private static DAORegistro daoregistro = new DAORegistro(); 
	private static DAOTipoVeiculo daotipoveiculo = new DAOTipoVeiculo();
	private static Usuario logado;
	
	public static void inicializar() {
		DAO.open();
	}
	public static void finalizar() {
		DAO.close();
	}
	
	public static Veiculo cadastrarVeiculo(String placa, TipoVeiculo tipoVeiculo) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo!=null)
			throw new Exception("carro ja cadastrado:" + placa);
		veiculo = new Veiculo(placa, tipoVeiculo);

		daoveiculo.create(veiculo);
		DAO.commit();
		return veiculo;
	}
	
	public static Registro cadastrarRegistro(String datahora, Veiculo veiculo, String operacao) throws Exception{
		DAO.begin();
		Registro registro = daoregistro.read(datahora);
		if (veiculo!=null)
			throw new Exception(" ja cadastrado:" + placa);
		veiculo = new Veiculo(placa, tipoVeiculo);

		daoveiculo.create(veiculo);
		DAO.commit();
		return veiculo;
	}
	
	
	
	

}
