# Guide d’utilisation Git & GitHub

----
## Nouveau sur Git 
* [Télécharger Git](https://git-scm.com/downloads) Bash/CMD/GUI (Mac, Windows, Linux).
* [Tutoriel en français](https://youtu.be/V6Zo68uQPqE) hyper complet pour apprendre à utiliser Git et GitHub.
* [Ecrire de la documentation](https://dillinger.io/) en Markdown (.md).

### Ouverture de la console
Pour se placer directement dans le bon répertoire avec GitBash, clic-droit dans le dossier puis « Git Bash Here ».

### Pour commencer
Version du logiciel :

    $ git --version

Créer son identité : 

    $ git config --global user.name “Prenom NOM”
    $ git config --global user.email “mail@adressemail.com”

Pour afficher sa configuration :

    $ git config -l 

----
## Rappels – Navigation
Naviguer dans les répertoires :

    cd %var%

Dossier parent :

    cd ..

Racine du disque : 

    cd \

Une variable contenant un fichier : 

    cd %temp%

Changer de disque ('C:', 'D:', etc.) : 

    cd /d %var%

----
## Git – Procédé et méthode
Les étapes de travail sont définies comme suit : 
* 1.Pull du projet à partir du repository GitHub
* 2a.Création d'une nouvelle branche (uniquement si nouvelle fonctionnalité)
* 2b.Versioning des fichiers locaux (*add* et *commit*)
* 3.Push des fichiers locaux et pull request

>**IMPORTANT** : NE PAS OUBLIER DE PULL A NOUVEAU LE PROJET UNE FOIS LE PUSH EFFECTUE (SURTOUT APRES LA PULL REQUEST) !

Initialisation du repository local Git :

    $ git init

Status du repository Git, état actuel : 

    $ git status

On a déjà un repository existant, on crée un remote :

    $ git remote add <remote> git@github.com:<username>/<project>.git

*Dans notre cas : `$ git remote add origin git@github.com:alexballet/LO23-Battleship-Online.git`*

Visualiser les remotes disponibles :

    $ git remote
    $ git remote -v

Supprimer un remote :

    $ git remote remove <remote>

---
### 1. Pull du projet

**Récupérer les fichiers du repository github :**
>Avant de pull le projet, ne pas oublier de régler ses paramètres de [clé SSH](https://help.github.com/articles/connecting-to-github-with-ssh/) localement et sur GitHub.

    $ git pull <remote> <branch>
    
*Par exemple : `$ git pull origin master`*

---
### 2a. Création/suppression d’une branche
>Une branche = une fonctionnalité.

**Créer une branche :**

    $ git branch my-feature

**Positionnement sur la branche *my-feature* :**

    $ git checkout my-feature

Visualiser les branches du projet :

    $ git branch
    $ git br  /*version courte*/

Ce qui diffère entre la branche *master* et la branche *my-feature* :

    $ git diff master..my-feature

Suppression d’une branche :

    $ git branch -d my-feature
    $ git br –d my-feature  /*version courte*/

Suppression d’une branche qu’on ne veut pas merger (suppression forcée) :

    $ git branch -D my-feature

---
### 2b. Versioning sur une branche
**Add :**

    $ git add <file>  /*ajout d'un seul fichier*/
    $ git add .  /*tout ajouter d'un coup*/

**Commit :**

    $ git commit --message “message de commit”
    $ git commit -m “message”  /*version courte*/

Modifier le message d’un commit :

    $ git commit --amend -m “message corrigé”

Visualiser l'historique des commits depuis le début du projet :

    $ git log

Pour savoir ce qui a changé dans le repository par rapport au dernier commit (au sein d’une meme branche) :

    $ git diff
    $ git diff <file>  /*changement sur un seul fichier*/

---
### 3. Push des fichiers locaux et pull request
**Envoyer ses fichiers :**

    $ git push <remote> <branch>
   
*Par exemple : `$ git push origin master`*

Faire ensuite valider sa *pull request* par son équipe.

---
### 4. NE PAS OUBLIER DE PULL AVANT DE TRAVAILLER A NOUVEAU SUR LE PROJET !
