package com.br.dmn.crud.run;

import br.com.dmn.crud.data.Produto;
import br.com.dmn.dao.ProdutoDAO;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
        
        int opcao = -1;
        
        do {
            try {
                System.out.println("Selecione uma opção:");
                System.out.println("\t[1] Cadastrar produto");
                System.out.println("\t[2] Buscar produto");
                System.out.println("\t[3] Editar produto");
                System.out.println("\t[4] Deletar produto");
                System.out.println("\t[0] Sair");
                System.out.print("Opção: ");
                opcao = Integer.parseInt(reader.nextLine());
                
                switch (opcao) {
                    default:
                        System.err.println("\nOpção inválida.\n");
                    break;
                    
                    case 0:
                        System.out.println("\n\n[Programa finalizado]\n\n");
                    break;
                    
                    case 1:
                        System.out.println("\nInsira os dados do produto:");
                        System.out.print("\tNome: ");
                        String nome = reader.nextLine();
                        System.out.print("\tMarca: ");
                        String marca = reader.nextLine();
                        System.out.print("\tQuantidade para estoque: ");
                        int quantidade = Integer.parseInt(reader.nextLine());
                        System.out.print("\tValor unitário: R$");
                        double valor_unitario = Double.parseDouble(reader.nextLine());
                        ProdutoDAO.addProduto(new Produto(nome, marca, quantidade, valor_unitario));
                        System.out.println("\n[Dados do produto foram salvos com sucesso]\n");
                    break;

                    case 2:
                        System.out.println("\nSelecione uma opção: \n\t[1] Lista geral \n\t[2] Produto específico");
                        System.out.print("Opção: ");
                        opcao = Integer.parseInt(reader.nextLine());
                        
                        if (opcao == 1) {
                            if (!ProdutoDAO.listar().isEmpty()) {
                                System.out.println("\n[Produto(s) encontrado(s)]");
                                for (Produto p : ProdutoDAO.listar()) {
                                    System.out.println("\tID: "+ p.getId());
                                    System.out.println("\tNome: "+ p.getNome());
                                    System.out.println("\tMarca: "+ p.getMarca());
                                    System.out.println("\tQuantidade: "+ p.getQuantidade());
                                    System.out.printf("\tValor unitário: R$%.2f \n", p.getValor_unitario());
                                    System.out.printf("\tTotal disponí­vel: R$%.2f \n\n", p.getQuantidade() * p.getValor_unitario());
                                }
                            }
                            else {
                                System.err.println("\n\n[Lista de produtos está vazia no momento]\n\n");
                            }
                        }
                        else if (opcao == 2) {
                            System.out.println("\nInsira o nome do produto que deseja buscar: ");
                            System.out.print("\tNome: ");
                            String nome_busca = reader.nextLine();

                            List<Produto> produto_encontrado = ProdutoDAO.buscar(nome_busca);

                            if (produto_encontrado != null && !produto_encontrado.isEmpty()) {
                                System.out.println("\n[Produto(s) encontrado(s)]");
                                for (Produto p : produto_encontrado) {
                                    System.out.println("\tID: "+ p.getId());
                                    System.out.println("\tNome: "+ p.getNome());
                                    System.out.println("\tMarca: "+ p.getMarca());
                                    System.out.println("\tQuantidade: "+ p.getQuantidade());
                                    System.out.printf("\tValor unitÃ¡rio: R$%.2f \n", p.getValor_unitario());
                                    System.out.printf("\tTotal disponí­vel: R$%.2f \n\n", p.getQuantidade() * p.getValor_unitario());
                                }
                            }
                            else {
                                System.out.println("\n\n[Produto não encontrado]\n\n");
                            }
                        }
                    break;
                    
                    case 3:
                        System.out.println("\nInsira o ID do produto que deseja editar os dados:");
                        System.out.println("\tID: ");
                        long id_editar = Long.parseLong(reader.nextLine());
                        
                        Produto produto_editar = ProdutoDAO.buscarId(id_editar);
                        
                        if (produto_editar != null) {
                            System.out.println("\n[Produto encontrado - dados atuais]");
                            System.out.println("\tNome: "+ produto_editar.getNome());
                            System.out.println("\tMarca: "+ produto_editar.getMarca());
                            System.out.println("\tQuantidade: "+ produto_editar.getQuantidade());
                            System.out.printf("\tValor unitário: R$%.2f \n\n", produto_editar.getValor_unitario());
                            
                            System.out.println("\n[Insira os novos dados para este produto]");
                            System.out.print("\tNome: ");
                            String nome_editar = reader.nextLine();
                            System.out.print("\tMarca: ");
                            String marca_editar = reader.nextLine();
                            System.out.print("\tQuantidade: ");
                            int quantidade_editar = Integer.parseInt(reader.nextLine());
                            System.out.print("\tValor unitário: R$");
                            double valor_editar = Double.parseDouble(reader.nextLine());
                            
                            produto_editar.setNome(nome_editar);
                            produto_editar.setMarca(marca_editar);
                            produto_editar.setQuantidade(quantidade_editar);
                            produto_editar.setValor_unitario(valor_editar);
                            
                            ProdutoDAO.editarProduto(produto_editar);
                            System.out.println("\n[Dados atualizados com sucesso]\n");
                        }
                        else {
                            System.out.println("\n[Produto não encontrado]\n");
                        }
                    break;
                    
                    case 4:
                        System.out.println("\nInsira o ID do produto que deseja deletar:");
                        System.out.print("\tID: ");
                        int id_deletar = Integer.parseInt(reader.nextLine());
                        
                        if (id_deletar > 0 && id_deletar <= ProdutoDAO.listar().size()) {
                            ProdutoDAO.excluir(id_deletar);
                            System.out.println("\n[Produto deletado com sucesso]\n");
                        }
                        else {
                            System.out.println("\n[Erro ao deletar produto - ID inválido]\n");
                        }
                    break;
                    
                }
            }
            catch (Exception e) {
                System.err.println("\n\nErro: "+ e.getMessage()+ "\n\n");
            }
        }
        while (opcao != 0);
        
    }
}
