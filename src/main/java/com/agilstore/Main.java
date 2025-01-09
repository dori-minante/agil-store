package com.agilstore;


import com.agilstore.controller.ProductController;
import com.agilstore.repository.ProductRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ProductController productController = new ProductController(productRepository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n-------- Bem-vindo à Agil Store ---------\n");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Pesquisar produto");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Excluir Produto");
            System.out.println("6 - Sair\n");
            System.out.println("------------------------------------------\n");
            System.out.print("Escolha uma opção: \n");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    productController.addProduct();
                    break;
                case 2:
                    productController.listProducts();
                    break;
                case 3:
                    productController.searchProduct();
                    break;
                case 4:
                    productController.updateProduct();
                    break;
                case 5:
                    productController.deleteProduct();
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
