# Projet de Java

Amine BOUKALA et Victor XU

## Commandes de compilation

```
>>> mvn compile
>>> mvn package
```

## Commande d'exécution du générateur de niveau

```
>>> java -jar target/phineloops-1.0-jar-with-dependencies.jar --generate [w]x[h] --output [file]
```
Remplacer [w] par la longueur du jeu, [h] par la largeur du jeu et [file] par le nom du fichier de sortie en format txt.

## Commande d'exécution du solveur de niveau

```
>>> java -jar target/phineloops-1.0-jar-with-dependencies.jar --solve [file] --output [file2]
```
Remplacer [file] et [file2] par le nom du fichier (txt) à résoudre et par le nom du fichier (txt) en sortie respectivement.

## Commande d'exécution du vérificateur de solution

```
>>> java -jar target/phineloops-1.0-jar-with-dependencies.jar --check [file]
```
Remplacer [file] par le nom du fichier (txt) à vérifier.

## Commande d'exécution du générateur de probleme inverse

```
>>> java -jar target/phineloops-1.0-jar-with-dependencies.jar --i [w]x[h] --output [file]
```
Remplacer [w] par la longueur du jeu, [h] par la largeur du jeu et [file] par le nom du fichier de sortie en format txt.

## Commande d'exécution du programme avec l'interface graphique

```
>>> java -jar target/phineloops-1.0-jar-with-dependencies.jar --menu [w]x[h]
```
Remplacer [w] par la longueur du jeu, [h] par la largeur du jeu