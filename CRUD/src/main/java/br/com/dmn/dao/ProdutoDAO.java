package br.com.dmn.dao;

import br.com.dmn.crud.data.Produto;
import br.com.dmn.crud.persistence.JPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    public static void addProduto(Produto _produto) {
        EntityManager manager = JPA.getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(_produto);
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            System.err.println("\n\nErro: "+ e.getMessage()+ "\n\n0");
        }
        finally {
            JPA.closeEntityManager();
        }
    }
    
    public static List<Produto> buscar(String _filtro) {
        EntityManager manager = JPA.getEntityManager();
        
        List produtos = null;
        
        try {
            String textQuery = "SELECT e FROM estoque e WHERE (:nome IS NULL OR e.nome LIKE :nome)";
            
            Query query = manager.createQuery(textQuery);
            
            query.setParameter("nome", _filtro.isEmpty() ? null : "%" + _filtro + "%");
            
            produtos = query.getResultList();
        }
        finally {
            JPA.closeEntityManager();
        }
        return produtos;
    }
    
    public static Produto buscarId(Long _id) {
        EntityManager manager = JPA.getEntityManager();
        Produto produto = null;
        try {
            produto = manager.find(Produto.class, _id);
        }
        finally {
            JPA.closeEntityManager();
        }
        return produto;
    }
    
    public static List<Produto> listar() {
        EntityManager manager = JPA.getEntityManager();
        
        List<Produto> produtoList = new ArrayList<Produto>();
        
        try {
            Query query = manager.createQuery("SELECT e FROM estoque e");
            produtoList = query.getResultList();
        }
        catch (Exception e) {
            manager.getTransaction().rollback();
        }
        finally {
            JPA.closeEntityManager();
        }
        return produtoList;
    }
    
    public static void excluir(Integer _id) {
        EntityManager manager = JPA.getEntityManager();
        try {
            manager.getTransaction().begin();
            Produto produto = manager.find(Produto.class, _id);
            if (produto != null) {
                manager.remove(produto);
            }
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println("\n\nErro: "+ e.getMessage()+ "\n\n");
        }
        finally {
            JPA.closeEntityManager();
        }
    }
    
    public static void editarProduto(Produto _produto) {
        EntityManager manager = JPA.getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(_produto);
            manager.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println("\nErro: "+ e.getMessage()+ "\n");
        }
        finally {
            JPA.closeEntityManager();
        }
    }
    
}
