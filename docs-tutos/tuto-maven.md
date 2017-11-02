#### Maven : qu'est ce que c'est ?

Maven permet de faciliter et d'automatiser certaines tâches de la gestion d'un projet Java. Il permet notamment :

   * d'automatiser certaines tâches : compilation, tests unitaires et déploiement des applications qui composent le projet ;
   * de gérer des dépendances vis à vis des bibliothèques nécessaires au projet ;
   * de générer des documentations concernant le projet.



### installation 


## pour GNU/Linux 

utiliser le gestionnaire de paquet

## pour windows 

 télécharger ici : <http://maven.apache.org/download.html>, dézipper

utliliser l'assistant

## MAC ==

utiliser le gestionnaire de paquet Homebrew : 

    $ brew install maven

ou macPorts

    $ sudo port install maven3
    
    
### Utilisation 

se placer dans le fichier puis lancer la commande : 
    
    mvn package

on peut ensuite lancer l'application créée avec : 
    
    java -jar ./target/battleship-online-1.0.jar
    
### ajouter une dépendance 

la liste des dépendances se trouve dans le pom.xml 

## ajouter une dépendance avec netbeans 

la solution la plus simple pour ajouter une dépendance est d'utiliser un IDE.

 * aller dans l'arborescence du projet
 * 
    

### Arborescence d'un projet Maven 

Voici l'arborescence minimum d'un projet Maven.

**mon_projet** 
 * un dossier qui va contenir toutes les données du projet
**mon_projet/src/** 
 * ce dossier va contenir tous les fichiers sources créés par les développeurs pour le projet
**mon_projet/src/main**
**mon_projet/src/main/java** 
  *  contient les codes sources Java, rangés selon le hiérarchie des packages. (par exemple, mon_projet/src/main/java/org/wikibooks/fr/Exemple.java pour la classe org.wikibooks.fr.Exemple)
**mon_projet/src/main/resources** 
  *  les ressources nécessaires au code Java. Fichiers de configuration, icônes, fichiers de données...
**mon_projet/src/test**
  *  contient toutes les données nécessaire pour tester l'application
**mon_projet/src/test/java **
  *  contient les codes sources des tests
**mon_projet/src/test/resources **
  *  les ressources qui sont utilisées dans les tests
**mon_projet/pom.xml **
  *  un fichier XML qui décrit le projet pour permettre à Maven d'interagir avec lui. 

### plugins maven 

Il existe un grand nombre de plugins maven dont certains peuvent être trèsn utiles. Il serait cependant souhaitable d'en discuter à plusieurs avant d'en utiliser pour eviter de partir dans tous les sens. 

### pour plus d'infos 

<https://fr.wikibooks.org/wiki/D%C3%A9velopper_en_Java/Introduction_%C3%A0_Apache_Maven#Arborescence_d.27un_projet_Maven>
