# Changelog team-07:

## Erste Abgabe:

### Graphische Oberfläche

> - Nutzer können sich mit einem frei gewählten Namen anmelden.
> - Die graphische Oberfläche konfiguriert ein Azul Spiel gemäß der angemeldeten Spielerzahl.
> - Die Auswahl von Steinen erfolgt durch Klicken auf einen Stein in einer Fabrik, oder in der Tischmitte.
> - Die Auswahl der Reihen zum Setzen der Steine erfolgt ebenfalls durch Klicken auf eine Reihe, oder, alternativ, durch Klicken auf die Bodenlinie.
> - Eine Regelverletzung bei der Auswahl der Reihen wird als pop-up Fenster agezeigt und die Auswahl muss wiederholt werden.
> - Ziehen der Steine ist nicht möglich.

### Funktionalität des Modells

> - Die Methode zur Auswahl der Steine aus der Fabrik implementiert auch gleich die Zuordnung der Steine der nicht gewählten Farbe in die Tischmitte. 
> - In einer fabrik werden alle Steine der angeklickten Farbe ausgewählt.
> - Die Methode zur Auswahl aus der Tischmitte ordnet den Strafstein automatisch dem ersten Spieler zu, der aus der Tischmitte Steine zieht.
> - Am Rundenende wird automatisch der score für alle Spieler gemäß Anleitung ermittelt.


## Zweite Abgabe:

### Addes
> - Serverimplementierung mit SpringBoot und Websockets.
> - Neue Farb Themes stehen zur Auswahl.
> - Background der einzelnen Fenster wurden durch Bilder ersetzt.
> - Playfenster kann von der größe Variiert werden. 
> - Mitteilung, wenn neue Runde beginnt.


## Changed
> - Refactoring der View und des Models.
> - Model und View Farben stimmen überein. 
> - Anzeige des Players welcher am Zug ist, ist deutlicher.

## Fixed
> - Penalty tile wurde überschrieben.
> - Doppelaufrufe von Methoden. 
> - Indicies haben nicht mehr zwei verschiedene zählzweisen.
> - NullPointerExecption in der Playingview.
> - Löschen von Spielern wirft keine Exception mehr. 

