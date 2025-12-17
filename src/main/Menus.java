package main;

import dao.MenuItemDAO;
import dao.impl.MenuItemDAOImpl;
import model.MenuItem;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Menus {
	private static Scanner scan = new Scanner(System.in);
	private static MenuItemDAO menuItemDAO = new MenuItemDAOImpl();
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public static MenuItem getChoice(String question, String category) {
		List<MenuItem> items = menuItemDAO.getAllbyCategory(category);
		
		if (items.isEmpty()) {
			System.out.println("Aucun article disponible dans cette catégorie!");
			return null;
		}
		
		System.out.println(question);
		for (int i = 0; i < items.size(); i++) {
			MenuItem item = items.get(i);
			System.out.println((i+1) + ". " + item.getName() + " (" + df.format(item.getPrice()) + "€)");
		}
		
		int choice = -1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("\nVotre choix (de 1 à " + items.size() + ") : ");
			if (scan.hasNextInt()) {
				choice = scan.nextInt();
				scan.nextLine();
				if (choice >= 1 && choice <= items.size()) {
					validInput = true;
				} else {
					System.out.println("Saisie incorrect! Veuillez saisir un nombre entre 1 et " + items.size());
				} 
					
			} else {
				System.out.println("Entrée invalide! Veuillez entrer un nombre valide!");
				scan.nextLine();
			}
			
		}	
		
		return items.get(choice - 1);
	}
	
	
	public static MenuItem appetizerChoice() {
        return getChoice("Que souhaitez-vous comme entrée :", "ENTREE");
    }
	
	
	public static MenuItem mainCourseChoice() {
        return getChoice("Que souhaitez-vous comme plat :", "PLAT");
    }
	
	
	public static MenuItem sideChoice() {
        return getChoice("Que souhaitez-vous comme accompagnement :", "ACCOMPAGNEMENT");
    }
	
	
	public static MenuItem drinkChoice() {
        return getChoice("Que souhaitez-vous comme boisson :", "BOISSON");
    }
	
	
	public static MenuItem dessertChoice() {
        return getChoice("Que désirez-vous comme dessert :", "DESSERT");
    }
}
