package com.agilstore.controller;

import com.agilstore.model.Product;
import com.agilstore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private ProductRepository productRepository;
    private Scanner scanner;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.scanner = new Scanner(System.in);
    }

    public void addProduct() {
        System.out.println("-------- Bem-vindo a Agil Store ---------");
        System.out.println("Digite o nome do produto: ");
        String name = scanner.nextLine();
        System.out.println("Digite a categoria do produto: ");
        String category = scanner.nextLine();
        System.out.println("Digite a quantidade do produto: ");
        int quantity = scanner.nextInt();
        System.out.println("Digite o preço do produto: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        String id = String.valueOf(System.currentTimeMillis());

        Product product = new Product(id, name, category, quantity, price);
        productRepository.addProduct(product);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void listProducts() {
        List<Product> products = productRepository.listProducts();
        if (products.isEmpty()) {
            System.out.println("Nenhum produto encontrado");
        } else {
            for (Product product : products) {
                product.displayProduct();
                System.out.println("---------");
            }
        }
    }

    public void searchProduct() {
        System.out.println("Digite o ID ou nome do produto para buscar:");
        String searchTerm = scanner.nextLine();

        Product product = productRepository.getProductById(searchTerm);
        if (product == null) {
            List<Product> foundProducts = searchByName(searchTerm);
            if (foundProducts.isEmpty()) {
                System.out.println("Produto não encontrado!");
            } else {
                for (Product p : foundProducts) {
                    p.displayProduct();
                    System.out.println("---------");
                }
            }
        } else {
            product.displayProduct();
        }
    }

    private List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productRepository.listProducts()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    public void updateProduct() {
        System.out.println("Digite o ID do produto para atualizar:");
        String id = scanner.nextLine();
        Product product = productRepository.getProductById(id);
        if (product != null) {
            System.out.println("Digite o novo nome do produto:");
            String name = scanner.nextLine();
            System.out.println("Digite a nova categoria do produto:");
            String category = scanner.nextLine();
            System.out.println("Digite a nova quantidade do produto:");
            int quantity = scanner.nextInt();
            System.out.println("Digite o novo preço do produto:");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Product updatedProduct = new Product(id, name, category, quantity, price);
            if (productRepository.updateProduct(id, updatedProduct)) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado!");
            }
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    public void deleteProduct() {
        System.out.println("Digite o ID do produto para excluir:");
        String id = scanner.nextLine();
        if (productRepository.deleteProduct(id)) {
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }
}