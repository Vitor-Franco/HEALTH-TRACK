package br.com.healthtrack.teste;

import java.util.List;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.dao.AlimentoDAO; 
import br.com.healthtrack.exception.DBException;
import br.com.healthtrack.factory.DAOFactory;

public class TesteAlimento {
	public static void main(String[] args) {
		AlimentoDAO aliDAO = DAOFactory.getAlimentoDAO();

		Alimento alimento = new Alimento(0, "Banana", 180, "Carboidratos l�quido 9.52 g, Carboidratos 10.32 g 3.44%, Prote�nas 0.52 g 0.17%");

		try {			
			aliDAO.insert(alimento);
		} catch (DBException e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Get All ============================================");

		List<Alimento> aliList = aliDAO.getAll();		
		for(Alimento ali : aliList) {
			System.out.println(ali);
		}

		System.out.println("Get By ID ============================================");

		Alimento aliGet = aliDAO.getById(2);
		if(aliGet != null) {			
			System.out.println(aliGet);
		}


		System.out.println("Editar ============================================");

		aliGet.setCalorias(99);
		try {
			aliDAO.update(aliGet);
			System.out.println("Registro editado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("Get Atualizado  ============================================");

		Alimento aliGet2 = aliDAO.getById(1);
		if(aliGet2 != null) {			
			System.out.println(aliGet2);
		}


		System.out.println("Deletar ============================================");
		try {
			aliDAO.delete(1);
			System.out.println("Deletado com sucesso!");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
