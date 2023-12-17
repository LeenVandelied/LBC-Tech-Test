
# Documentation

## Introduction

Ce document présente la documentation technique pour l'application Android développée dans le cadre d'un test technique. L'application, développée en Kotlin, affiche une liste d'albums avec leurs titres et images, en utilisant une architecture propre et moderne.

## Choix Techniques

### Langage et Frameworks:

-   **Kotlin**: Choisi pour sa sécurité de type, sa concision et sa pleine compatibilité avec l'écosystème Android.
-   **Jetpack Compose**: Utilisé pour la création d'interfaces utilisateur modernes et réactives.
-   **Material 3**: Adopté pour un design cohérent et conforme aux dernières directives de Material Design.

### Architecture et Patterns:

-   **Clean Architecture**: Assure la séparation des responsabilités et la facilité de maintenance.
-   **MVVM (Model-View-ViewModel)**: Fournit une structure claire pour le binding des données et la séparation des préoccupations.

### Librairies:

-   **Koin**: Pour l'injection de dépendances, facilitant la gestion et le test des composants. Simplicité de prise en main pour les petits projets
-   **Room**: Utilisé pour la persistance des données et la gestion de la base de données locale.
-   **Paging3**: Gère efficacement le chargement et la pagination des données.
-   **Glide**: Pour le chargement et la mise en cache des images.
-   **Retrofit**: Pour les appels réseau et la gestion des données distantes.
-   **Junit5 et Mockito-Kotlin**: Pour les tests unitaires.

## Modules du Projet

### 1. Module `App`

-   **MainActivity et MainApplication**: Points d'entrée de l'application. `MainActivity` sert de conteneur pour les écrans de navigation, tandis que `MainApplication` initialise les composants globaux.

### 2. Module `Core`

-   **Modèles de Base**: Contient les entités et les modèles de données utilisés côté Vue de l'application.
-   **Extensions**: Contient les extensions utiles pour faciliter l'utilisation de certain composants.

### 3. Module `Data`

-   **Modèles Database et Remote**: Contient les entités pour la gestion des données locales et distantes.
-   **Gestion Dao de Room**: Interface pour l'accès et la manipulation des données de la base de données.
-   **DataStore et LocalStore**: Mécanismes pour récupérer et stocker les données locales et distantes.
-   **ApiService**: Définit les endpoints pour les appels réseau.

### 4. Module `Domain`

-   **Repositories**: Couche intermédiaire entre les données et la logique métier.
-   **Base des Usecases**: Logique métier et opérations sur les données génériques.
-   **Extensions Usecases**: Contient des extensions permettant de mieux gérer les threads et les erreurs.

### 5. Module `Design-system`

-   **Composants Material en Compose**: Ensemble de composants UI réutilisables.
-   **Thème et Strings**: Gestion des ressources visuelles et textuelles.

### 6. Module `Home`

-   **Écran Principal**: Implémentation de l'interface utilisateur de l'écran principal.
-   **Navigation Compose**: Définition des routes de l'écran principal.
-   **Stateful**: Gestion de l'état de l'UI, la logique de présentation et les actions utilisateur.
-   **ViewModel**: Gestion des appels au ressources dans le domain. Gestion des états des StateFlow.
-   **Actioner**: Gestion des différentes actions possibles sur la Vue.
-   **Usecases Spécifiques**: Opérations liées spécifiquement à l'écran principal.

## Architecture

![Schema](https://i.postimg.cc/8zW5Pync/Capture-d-e-cran-2023-12-17-a-14-17-08.png)

## Tests et Performance

Des tests unitaires ont été écrits pour chaque composant clé en utilisant Junit5 et Mockito-Kotlin, assurant la robustesse et la fiabilité de l'application. Les performances de l'application ont été optimisées grâce à une gestion efficace des ressources avec Room et Paging3, permettant la pagination des résultats. De plus Glide peut gérer naturellement la mise en cache des images.

## Fonctionnalités

- SplashScreen animé, ne fonctionne qu'au lancement de l'app depuis le Smartphone, pas depuis le Run Android Studio
- Gestion Hors ligne, si les données sont présentes on continue d'afficher les données déjà chargées
- Gestions des statuts des images et affichage de placeholders
- PullRefresh sur la liste ( Pas de mise à jour car données statiques )
- Possibilité de recharger sur les écrans d'erreur
- 

## Choix généraux

- Commentaires en Anglais et documentation en Français dans le cadre du test.
- A chaque lancement les données Room sont actualisées en quête de changement
- Les données sont récupérés de Room à chaque fois, l'appel distant n'est là que pour mettre à jour la BDD
- Formatage Kotlin Style Guide
- J'ai commencé le projet avec PaperDB et la gestion du cache, je me suis formé sur Room pour les besoins de performance du test
- J'ai choisi dans le cadre du test de ne pas gérer la synchronisation des données distantes, en effet si la requête venait à ne retourner que 100 éléments, Room aurait toujours en mémoire les 5000. Deux solutions possibles, la première, supprimer toute la BDD avant chaque rafraichissement de donnée, méthode simple mais couteuse en performance. La seconde synchroniser de manière intelligente en comparant les données récupérée et celles déjà stockées pour affecter seulement les différences.
- Compose Navigation est mis en place malgré l'unique destination. Simplement pour donner un exemple.
- Mise en place d'un Design system simple pour la démo, largement sous utilisé dans le cadre de ce test
- Spark Design system n'a pas été choisi, le temps d'adaptation et le fait qu'il ne soit plus maintenu depuis fin 2022 m'ont poussé à faire l'impasse dessus dans le cadre de ce test.

## Alternatives

- Dagger / Hilt pour injection de dépendances : Plus complexe et adapté aux plus gros projets
- Coil pour la gestion des images : Alternative possible
- PaperDB / Cache : Plus simple à mettre en place mais ne gère pas la pagination, performance moindres
