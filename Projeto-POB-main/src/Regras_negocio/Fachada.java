package Regras_negocio;

import daodb4o.DAOVeiculo;

import java.util.List;

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
	private static DAOUsuario daousuario = new DAOUsuario();
	public static Usuario logado;
	
	public static void inicializar() {
		DAO.open();
	}
	public static void finalizar() {
		DAO.close();
	}
	
	public static Veiculo pegarVeiculo(String placa) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("Veiculo da placa: " + placa + "não existe");
		
		return veiculo;
		
	};
	
	public static Veiculo cadastrarVeiculo(String placa, TipoVeiculo tipoVeiculo) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo!=null)
			throw new Exception("Veiculo ja cadastrado: " + placa);
		veiculo = new Veiculo(placa, tipoVeiculo);

		daoveiculo.create(veiculo);
		DAO.commit();
		return veiculo;
	}
	
	public static void removerVeiculo(String placa, TipoVeiculo tipoVeiculo) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo==null)
			throw new Exception("Veiculo não cadastrado: " + placa);

		daoveiculo.create(veiculo);
		DAO.commit();
	}
	
	public static Veiculo MudarVeiculo(String atual, String novo) throws Exception  {
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(atual);
		if (veiculo==null)
			throw new Exception("O veiculo não existe: ");

		veiculo.setPlaca(novo);
		DAO.commit();
		return veiculo;

		
	}

	public static Registro pegarRegistro(int id) throws Exception{
		DAO.begin();
		Registro registro = daoregistro.read(id);
		if (registro==null)
			throw new Exception("ID: " + id + "não existe");
		
		return registro;
	}
	
	
	public static List<Registro> listarRegistrosdeTalCarro(String placa) throws Exception {
		DAO.begin();
		List<Registro> registros = daoregistro.registroModelo(placa);
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo == null)
			throw new Exception("Este veículo não existe");
		if (registros.isEmpty())
			throw new Exception("Nao ha registros com este veiculo");
		
		
		return daoregistro.registroModelo(placa);
	}
	
	public static TipoVeiculo cadastrarTipo(String nome) throws Exception {
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo!=null)
			throw new Exception("Tipo de veiculo já cadastrado");
		tipo = new TipoVeiculo(nome);

		daotipoveiculo.create(tipo);
		DAO.commit();
		return tipo;
	}
	
	public static TipoVeiculo mudarTipo(String nome) throws Exception{
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo==null)
			throw new Exception("O tipo não existe");

		tipo.setNome(nome);
		DAO.commit();
		return tipo;

			
		}
	
	
	public static void removerTipo(String nome) throws Exception {
		DAO.begin();
		TipoVeiculo tipo = daotipoveiculo.read(nome);
		if (tipo==null)
			throw new Exception("Tipo de veiculo não existe para ser removido");

		daotipoveiculo.delete(tipo);
		DAO.commit();
	}
	
	public static void saidaVeiculo(String placa) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo ==null)
			throw new Exception("Veiculo não existente" + placa);
		List<Registro> registros = daoregistro.registroModelo(placa);
		if (registros != null)
			for (Registro r: registros) 
				if (r.getOperacao() == "saida")
					throw new Exception("O veículo já saiu do estacionament");
					
					
		Registro reg = new Registro("13/02/2023 14:00", veiculo, "saida");
		daoregistro.create(reg);
		DAO.commit();
				
			}
		
	public static void entradaVeiculo(String placa) throws Exception{
		DAO.begin();
		Veiculo veiculo = daoveiculo.read(placa);
		if (veiculo ==null)
			throw new Exception("Veiculo não existente" + placa);
		List<Registro> registros = daoregistro.registroModelo(placa);
		if (registros != null)
			for (Registro r: registros) 
				if (r.getOperacao() == "entrada")
					throw new Exception("O veículo já entrou no estacionament");
						
						
		Registro reg = new Registro("13/02/2023 14:00", veiculo, "entrada");
		daoregistro.create(reg);
		DAO.commit();
					
				}
	
	public static List<Veiculo>  listarVeiculos(){
		DAO.begin();
		List<Veiculo> resultados =  daoveiculo.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Registro>  listarRegistros(){
		DAO.begin();
		List<Registro> resultados =  daoregistro.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<TipoVeiculo> listarAlugueis(){
		DAO.begin();
		List<TipoVeiculo> resultados =  daotipoveiculo.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Usuario>  listarUsuarios(){
		DAO.begin();
		List<Usuario> resultados =  daousuario.readAll();
		DAO.commit();
		return resultados;
	} 
	
	
	
	
	//USUARIO
	
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu==null)
			return null;
		if (! usu.getSenha().equals(senha))
			return null;
		return usu;
	}
	}
