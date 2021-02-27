# BattaileNavale
## IN205

### Gabriel Pires Sobreira 

## Instructions:
Il faut utilisé maven dans Ubuntu pour executér le projet. 

Pour instaler maven sur Ubuntu:

    sudo apt-get install maven 

Pour executer le code:
    
    1- Ouvrir le tarminal dans le fichier:

        BattaileNavale/bataille-navale

    2- Executer le commande:

        mvn clean install exec:java

## Le jeu

Le jeu au début demandera au joueur de choisir la taille du tableau, 
suivi du nom du joueur. Alors le joueur positionnera ses bateaux sur le tableau. 
Après ça le jeu commencera contre l'intelligence artificielle, le joueur lancera une 
frappe sur le tableau de l'adversaire, si vous frappez un bateau un << X >> rouge sera 
marqué sur votre table de frappe et vous pouvez lancer une autre frappe sur l'adversaire, 
si le coup détruit un ennemi navire affichera un message indiquant de quoi il s'agissait. 
S'il manque, un << X >>  blanc apparaîtra sur le tableau des frappes et passera le tour à l'autre 
joueur, lorsque l'autre joueur attaque s'il frappe l'un de votre navires, il sera marqué en 
rouge dans la position, s'il rate il marquera un << X >> blanc dans la position qui a été attaquée 

### Commandes dans le jeu:

    1- coordones pour aficher un bateau doit être:

        A0 E

    2- coordones pour aficher une frappe doit être:
        
        A0

## Considerations

Considérations prises tout au long de la programmation du jeu:
    
    1- Les colonnes sont representé par lettres

    2- Les lignes sont representé par nombres

    3- La premiere ligne est la ligne 0

