# Projet java  
***
## Lancement 
Il faut aller dans la classe "Start" et lance le programme (main).
## Structure de donnée
Création d'une classe de Chanson pour simplifier l'accès aux données avec des getters, etc.
## Traitement de donnée
Pour traiter le csv, comme la structure du csv est pas correct. J'ai divisé le traitement en partie.
D'abord en sélectionnant l'ID et les doubles, c'est-à-dire les notes de style. Par la suite,
on peut sélectionner le titre et l'artiste (car il y a de virgule dedans) puis l'album. 
Pour les genres, je regarde juste si le dernier a le même id, si oui j'ajoute ces genres dans la chanson precedent sinon je crée une nouvelle chanson.
## TimerTask pour le compteur et pour changer la musique  
J'ai utilisé TimerTask qui est une classe fille de la classe Threads.
Cela permet de mieux comprendre le code, mais aussi et d'être plus efficace dans le code.
J'utilise en parallèle les SwingUtilities pour modifier dynamique les valeurs et à attendre les données. 
## Interface
J'ai réalisé une interface simple pour simplifier le format des données, mais aussi je la trouve elegant.
Le compteur est en haut à gauche. De plus, on a appris un peu tard les layouts. En effet, j'avais déjà terminé le projet.
##  La chanson suivante
J'ai utilisé un stream pour sélectionner une musique avec SwingUtilities. Cela permet d'attendre les données.
## Bouton précédant
J'ai mis en place une arraylist de chanson qui permet de garder les musiques déjà jouer.
J'ai créé des méthodes qui ajoute dans la liste et qui permet de simuler une pile.
## Bouton suivant
Je lance une musique aléatoire dans la liste de donnée (en vérifiant si elle n'a pas été déjà joué). Si elle a été déjà jouer ou s'il n'y a plus de musique.
Le logiciel lance une musique aléatoire.
## Bug
###### _(Si il y a un bug, il faudra relancer l'application)_
