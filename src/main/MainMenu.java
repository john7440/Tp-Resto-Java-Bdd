package main;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.MenuItem;
import model.Order;
import model.OrderDetail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		System.out.println("Bonjour, combien de menus désirez-vous commander? ");
		Scanner scan = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0.00");
		
		
		if (scan.hasNextInt()) {
			int choice = scan.nextInt();
			scan.nextLine();
			
			OrderDAO orderDAO = new OrderDAOImpl();
			int nextOrderNumber = orderDAO.getLastOrderNumber();
			
			Order order = new Order(nextOrderNumber, 0.0);
			double totalCommande = 0.0;
			
			for (int i = 1; i <= choice; i++) {
				System.out.println("\n============ Menu #" + i + " ============");
				
				MenuItem entry = Menus.appetizerChoice();
                MenuItem mainCourse = Menus.mainCourseChoice();
                MenuItem side = Menus.sideChoice();
                MenuItem drink = Menus.drinkChoice();
                MenuItem dessert = Menus.dessertChoice();
                
                OrderDetail detail = new OrderDetail(i, entry, mainCourse, side, drink, dessert);
                order.addOrderDetail(detail);
                totalCommande += detail.getMenuTotal();
			}
			
			order.setTotalPrice(totalCommande);
			
			//notre affichage
			System.out.println("\n========== Récapitulatif de la commande n°" + nextOrderNumber + " ==========");
			for (OrderDetail detail : order.getOrderDetails()) {
				System.out.println("\n------ Menu #" + detail.getMenuNumber() + " ------" + "\n");
				System.out.println("- Entrée : " + detail.getAppetizer().getName() + 
                        " (" + df.format(detail.getAppetizer().getPrice()) + "€)");
				System.out.println("- Plat : " + detail.getMainCourse().getName() + 
                        " (" + df.format(detail.getMainCourse().getPrice()) + "€)");
				System.out.println("- Accompagnement : " + detail.getSide().getName() + 
                        " (" + df.format(detail.getSide().getPrice()) + "€)");
				System.out.println("- Boisson : " + detail.getDrink().getName() + 
                        " (" + df.format(detail.getDrink().getPrice()) + "€)");
				System.out.println("- Dessert : " + detail.getDessert().getName() + 
                        " (" + df.format(detail.getDessert().getPrice()) + "€)");
				System.out.println("Total du menu : " + df.format(detail.getMenuTotal()) + "€");
			}
			System.out.println("\n=====================================");
            System.out.println("TOTAL: " + df.format(totalCommande) + "€");
            System.out.println("=====================================");
            
            //sauvegarde bdd
            int orderId = orderDAO.saveOrder(order);
            
            //sauvegarde fichier
            if (orderId > 0) {
            	saveOrderToFile(order, df);
            }
			
		} else {
			System.out.println("Je n'ai pas saisi votre demande! Veuillez saisir un nombre entier!");
		}
		
		scan.close();
	}
	
	private static void saveOrderToFile(Order order, DecimalFormat df) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("order.txt", true))){
			
			writer.write("=== Résumé de la commande n°" + order.getOrderNumber() + " ===");
			writer.newLine();
			writer.write("Date: " + new java.util.Date());
            writer.newLine();
            writer.newLine();
            
            for (OrderDetail detail : order.getOrderDetails()) {
            	writer.write("------ Menu #" + detail.getMenuNumber() + " ------");
            	writer.newLine();
            	writer.write("- Entrée : " + detail.getAppetizer().getName() + 
                        " (" + df.format(detail.getAppetizer().getPrice()) + "€)");
            	writer.newLine();
            	writer.write("- Plat : " + detail.getMainCourse().getName() + 
                        " (" + df.format(detail.getMainCourse().getPrice()) + "€)");
            	writer.newLine();
            	writer.write("- Accompagnement : " + detail.getSide().getName() + 
                        " (" + df.format(detail.getSide().getPrice()) + "€)");
            	writer.newLine();
            	writer.write("- Boisson : " + detail.getDrink().getName() + 
                        " (" + df.format(detail.getDrink().getPrice()) + "€)");
            	writer.newLine();
            	writer.write("- Dessert : " + detail.getDessert().getName() + 
                        " (" + df.format(detail.getDessert().getPrice()) + "€)");
            	writer.newLine();
            	writer.write("Total du menu : " + df.format(detail.getMenuTotal()) + "€");
                writer.newLine();
                writer.newLine();
            }
            
            writer.write("TOTAL: " + df.format(order.getTotalPrice()) + "€");
            writer.newLine();
            writer.write("=====================================");
            writer.newLine();
            writer.newLine();
            
            System.out.println("\nCommande n°" + order.getOrderNumber() + " sauvegardée dans order.txt");
            
		} catch (IOException e) {
			System.err.println("Erreur lors de l'écriture du fichier: " + e.getMessage());
		}
	}
		

}
