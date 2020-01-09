package com.anma.comercial.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.anma.comercial.model.ItemPedido;
import com.anma.comercial.model.Produto;
import com.anma.comercial.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtos;

	public Produto atualizar(Long id, Produto produto) {
		Produto produtoSalvo = buscarProduto(id);
		
		BeanUtils.copyProperties(produto, produtoSalvo, "id");
		
		return produtos.save(produtoSalvo);
	}
	
	public void atualizarPropriedadeAtivo(Long id,Boolean ativo) {
		Produto produtoSalvo = buscarProduto(id);
		produtoSalvo.setAtivo(ativo);
		produtos.save(produtoSalvo);
	}

	private Produto buscarProduto(Long id) {
		Produto produtoSalvo = produtos.findOne(id);
		
		if (produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return produtoSalvo;
	}
	
	public boolean atualizarSaldo(List<ItemPedido> itensPedido,String tipo) {
		Produto produto;
		for (ItemPedido item : itensPedido) {
			produto = item.getProduto();
			if(tipo.equals("S")) {
				System.out.println("- "+tipo);
				produto.setSaldo(produto.getSaldo()-item.getQuantidade());
			}else {
				System.out.println("+ "+tipo);
				produto.setSaldo(produto.getSaldo()+item.getQuantidade());
			}
			produtos.save(produto);
		}
		
		return false;
	}
	

}
