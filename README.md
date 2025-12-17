## Restaurant Java

#### Présentation
Application console Java permettant de prendre des commandes dans un restaurant.
Le programme permet de choisir des menus (entrée, plat, accompagnement, boisson, dessert), de calculer le prix total, de sauvegarder la commande dans une base MariaDB via un pattern DAO, ainsi que dans un fichier order.txt

#### Fonctionnalités
- Saisie du nombre de menus à commander
- Choix de chaque élément du menu
- Gestion des prix des articles (stockés en BDD)
- Calcul du total pour chaque menu et le total général de chaque commande
- Numérotation automatique des commandes (1,2,3,...)
- Sauvergarde des commandes dans:
  - la base de données
  - un fichier texte order.txt
- Possibilité de ne rien prendre pour chaque catégorie (options Aucun(e))

#### Structure du projet
- **src/**
  - **main/**
      - *MainMenu.java*: point d'entrée du programme, logique principale de saisie et affichage
      - *Menus.java*: méthodes utilistaires pour poser les question et récupérer les choix (via la BDD)
  - **model/**
      - *MenuItem.java*: représente un article du menu (nom, catégorie, prix…)
      - *Order.java*: une commande (numéro, date, total, liste de détails)
      - *OrderDetail.java*: le détail d’un menu dans une commande (entrée, plat,... + total du menu)
  - **dao/**
      - *MenuItemDAO.java*: interface d’accès aux articles du menu
      - *OrderDAO.java*: interface d’accès aux commandes
  - **dao/impl/**
      - *MenuItemDAOImpl.java*: implémentation JDBC de MenuItemDAO
      - *OrderDAOImpl.java*: implémentation JDBC de OrderDAO (insert commande + détails, ...)
  - **db/**
      - *DatabaseConnection.java*: gestion de la connexion JDBC à MariaDB
  
  Un fichier order.txt est généré à la racine du projet pour conserver un récapitulatif texte des commandes

#### Prérequis
- Java 8 (ou supérieur)
- MariaDB installé et en cours d’exécution 
- Un utilisateur MariaDB dédié à l’application (ici restaurant_user)
- Driver JDBC MariaDB dans le classpath

#### Exécution
1. Cloner le projet ou copier les sources
2. Créer la base de données et les tables
3. Insérer les données de menu
4. Configurer l'utilisateur MariaDB
5. Compiler le projet
6. Lancer le programme:

        java -cp "chemin/vers/classes:chemin/vers/mariadb-java-client.jar" main.MainMenu

Le programme demande d’abord combien de menus sont souhaités, puis propose les choix pour chaque partie du menu. À la fin, le récapitulatif s’affiche en console et la commande est sauvegardée en base de données et dans order.txt

  
