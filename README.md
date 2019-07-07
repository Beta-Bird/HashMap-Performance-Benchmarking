# HashMap-Performance-Benchmarking
Mit diesem auf JMH basierenden Benchmarking-Test kann die Performance verschiedener HashMap-Implementierungen vermessen und verglichen verden.
In diesem Test wird ausschließlich die Metrik AverageTime, also Durchschnittliche Zeit verwendet, um die durchschnittliche Geschwindigkeit beim Ausführen verschiedener Performance-relevanter Aktionen beurteilen und vergleichen zu können.
Die Zeiteinheit bei @OutputTimeUnit(TimeUnit.NANOSECONDS) kann nach gewünschter Genauigkeit innerhalb des Quellcodes angepasst werden.
Im ersten Teil der Klasse MyBenchmark wird die HashMap initialisiert und in unserem Beispielfall auf 10.000 Objekte ausgelegt. Dieser Wert kann aber nach Belieben erhöht oder verringert werden, je nach Zielsystem in dessen Kontext die Performance-kritische Komponente implementiert ist.
Während der Schlüssel sequentiell (in unserem Fall 0 - 10.000) erzeugt wird, ist der Wert, auf welchen er verweist mithilfe einer Zufallsfunktion erzeugt worden (in unserem Fall ein zufälliger Wert zwischen 0 & 50.000).
Nun folgt eine Abfrage und Messung der Dauer ebenjener, der Werte a) sequentiell (1, 2, 3, ... 9.999, 10.000) und b) chaotisch, hier wieder mit Zufallsfunktion.
Zuletzt wird die durchschnittliche Dauer beim  löschen der Werte ermittelt, indem die Schlüssel-Werte sequentiell durchgegangen weden und bei jedem Eintrag die Remove-Funktion zum Einsatz kommt.
