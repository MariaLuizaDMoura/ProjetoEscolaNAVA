package br.com.nava.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nava.entity.Turma;
import br.com.nava.repository.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	TurmaRepository turmaRepository;
	// Criar metodo para Listar todas as turmas
	public List<Turma> listaTodasTurma(){
		return turmaRepository.findAll();
	}
	
	
	public Page<Turma> buscaPorPaginação(int pagina,int linhasPorPagina,String direction,String orderBy){
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direction), orderBy);
		return new PageImpl<>(turmaRepository.findAll(),pageRequest,linhasPorPagina);
		}
	
	//Criar metodo para trazer uma turma por ID
	public Turma buscaPorId(Integer Id) throws ObjectNotFoundException {
		Optional<Turma> turma = turmaRepository.findById(Id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(null, "Turma não encontrada"));
	}
	// Criar um metodo para inserir a turma
	public Turma salvar(Turma turma) {
		return turmaRepository.save(turma);
	}
	//Criar um metodo para fazer um Update/Alterar da turma
	public Turma alterar(Turma objturma) {
		Turma turma = buscaPorId(objturma.getId());
		turma.setNome(objturma.getNome());
		return salvar(turma);
	}
	//Criar metodo para excluir turma
	public void excluir(Integer id) {
		turmaRepository.deleteById(id);
	}
}
