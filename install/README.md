# Installer le projet marche-fermier
Nécéssaire : 
- Java 8
- Maven 3.3.9
- Accès aux commandes d'administration sur sa machine
## Installer Java 8 & Maven sur sa machine
##### Linux
Dans un terminal, taper les commandes suivante

```sudo apt update && sudo apt upgrade```

```sudo apt-get install openjdk-8-jdk```
```sudo apt-get install openjdk-8-jre```
```sudo apt-get install openjfx```

```sudo apt-get install maven```

##### Windows
Installer Java 8 [ici](https://www.java.com/fr/download/help/windows_manual_download.xml)
Suivre ce [tutoriel](https://www.mkyong.com/maven/how-to-install-maven-in-windows/)

## Installation de git

##### Linux
```sudo apt-get install git```


##### Windows

Installer git [ici](https://git-scm.com/), puis lancer le terminal (git.exe).

## Récupérer & lancer le projet

##### Windows & Linux

```git clone https://github.com/thomas-miceli/marche-fermier.git```

```cd marche-fermier```

```mvn clear install```

```cp target/marche-fermier-1.0-SNAPSHOT.jar marche-fermier.jar```

```java -jar marche-fermier.jar```