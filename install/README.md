# Installer le projet marche-fermier
Nécéssaire : 
- Java 8
- Maven 3.3.9
- Accès aux commandes d'administration sur sa machine

Les ```commandes``` doivent être exécutées depuis un terminal (cmd sur Windows)

**Note pour linux : L'installation a été testée sur une machine Debian 9, cela devrait fonctionner avec les versions récentes d'Ubuntu, mais ce n'est pas sur de marcher pour les autres distributions de Linux)**
## Installer Java 8 & Maven sur sa machine
### Linux
Mise à jour de nos sources de paquets

```sudo apt update && sudo apt upgrade```

Installation de Java Development Kit

```sudo apt-get install openjdk-8-jdk```

Installation de Java Runtime Environnment

```sudo apt-get install openjdk-8-jre```

Installation du package de JavaFX

```sudo apt-get install openjfx```

Installation de maven

```sudo apt-get install maven```

### Windows
Installer Java 8 [ici](https://www.java.com/fr/download/help/windows_manual_download.xml)
Suivre ce [tutoriel](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) pour installer Maven et l'ajouter aux variables d'environnement Windows (permet d'exécuter maven depuis un terminal)

## Installation de git

### Linux
Installation de Git

```sudo apt-get install git```

### Windows

Installer git [ici](https://git-scm.com/), puis lancer le terminal (git.exe).

## Récupérer & lancer le projet

### Windows & Linux
Récupération du code source

```git clone https://github.com/thomas-miceli/marche-fermier.git```

Déplacement dans la racine

```cd marche-fermier```

Compilation du projet par Maven

```mvn clean install```

Copie du fichier exécutable

```cp target/marche-fermier-1.0-SNAPSHOT.jar marche-fermier.jar``` (Linux)
```copy target/marche-fermier-1.0-SNAPSHOT.jar marche-fermier.jar``` (Windows)

Exécution du programme

```java -jar marche-fermier.jar```
