# Projet JEE : Version SpringBoot

Ce projet est un projet pour le module JEE basé sur SpringBoot.

Il contient des fonctionnalités identiques au projet basé pûrement
sur Jakarta EE, avec des vues différentes et une intégration
au framework SpringBoot plus complet.

Pour lancer le projet:

```bash
# Créer le JAR du projet
mvn clean package
```

```bash
# Directement lancer le projet
mvn spring-boot:run
```
> [!IMPORTANT]
> Le projet DOIT avoir une base de données
> MySQL disponible sur localhost:3306, avec
> une base de données "projetjeeentreprise"
> accédée par l'utilisateur "root" et le mot
> de passe "password".
> Cette base de données peut être créée 
> à partir de l'utilitaire Docker Compose
> grâce au fichier compose.yml fourni:

```bash
# Pour les distributions basées sur Debian:
sudo apt install docker-compose
docker compose up
```