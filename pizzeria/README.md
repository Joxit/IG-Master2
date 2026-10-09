## Sujet: Introduction à Spring et Hibernate

Pour executer ce projet, vous aurez besoin de :

- [Java JDK 25 ou +](https://adoptopenjdk.net/) (n'oubliez pas de configurer votre JAVA_HOME)
- [docker](https://docs.docker.com/install/#supported-platforms) (n'oubliez pas de lancer la commande `sudo usermod -aG docker $(whoami)` puis redémarrez votre ordinateur)
- [docker-compose](https://docs.docker.com/compose/install/)
- [nodejs](https://nodejs.org/en/download/package-manager/)

1.  Pour lancer votre base de données (ici ça sera MySQL). J'utilise `docker-compose` pour vous éviter d'installer MySQL sur votre machine et avoir la configuration complète directement. `docker-compose` va vous cacher toutes les commandes docker qui sont dérrière. L'option `-d` ici permet de lancer le container en arrière-plan.

  `docker-compose up -d mysql-db`

2.  Build le backend pizzeria-boot à la main :

    1.  Build du projet avec gradle, la commande vous fait entrer dans le dossier pizzeria (`cd pizzeria`), build le projet (`./gradlew clean build`) et revient là où vous étiez (`cd -`).

      `cd pizzeria && ./gradlew clean build; cd -`

    2.  Lancer le projet à la main avec la commande java. C'est un serveur, cela veut dire qu'il ne s'arrête pas tout seul.

      `java -jar pizzeria/pizzeria-boot/build/libs/pizzeria-boot.jar`. Tout devrait fonctionner, s'il y a un problème essayez de le régler.

    3.  Quand tout est bon et que vous pouvez accéder à http://127.0.0.1:8080/api, vous pouvez quitter pour le faire via votre IDE. `CTRL + C`

3. Lancer le backend pizzeria-boot via votre IDE

    1. Importez le projet (les mêmes étapes que pour OSM)
    2. Ouvrez le projet `pizzeria` -> `pizzeria-boot` -> `src` -> `main` -> `java` -> `io.github.joxit.pizzeria`. Faites un clique droit sur Application puis `Run Application.main()`.
    3. Vous devre voir des log. Le serveur est lancé sur le port 8080, essayez d'aller sur http://127.0.0.1:8080/api

4.  Lancement de l'interface utilisateur :

    1.  Lancer le frontend. Pareil ici, docker compose va vous éviter de construire l'image qui est disponible dans le docker hub.

    `docker-compose up -d ui`

5.  Pour utiliser l'interface utilisateur, il faut mettre comme query param `http://127.0.0.1/?url=http://127.0.0.1:8080/api` par exemple.
