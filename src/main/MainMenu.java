package main;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;
import model.MenuItem;
import model.Order;
import model.OrderDetail;
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
            	saveOrderToFile(order, df); // a implémenter
            }
			
		} else {
			System.out.println("Je n'ai pas saisi votre demande! Veuillez saisir un nombre entier!");
		}
		
		scan.close();
	}
	
	

}
