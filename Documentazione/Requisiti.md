#Requisiti base

- L'applicativo deve essere scritto in Java o JS
	- JAVA:
		- deve esistere sito con descrizione software
		- deve essere possibile scaricare il software
	- JS:
		- deve esserci un sito web che ospiti l'applicativo
- I tagli del triangolo devono poter essere fatti con il mouse
- Deve esserci un'interfaccia grafica
- L'area di lavoro deve essere un triangolo
- Deve essere possibile resettare i punti registrati
- Deve esistere il tasto genera fiocco
- La generazione deve avvenire in tempo reale - P2
- I punti devono poter essere spostati o rimossi - P2
- Il lavoro deve poter essere salvato
- Il salvataggio deve essere fatto in formato PNG o SVG
- Il salvataggio deve avere dimensioni definite dall'utente
- Deve essere possibile salvare i punti di taglio



#Domande
- Priorità del sito nel progetto? -> Per Java non prioritario, per JS serve da subito
- Finestra ridimensionabile? Dimensioni minime? -> Si, ridimensionabile. Sì, minimo 1024x768
- Colori del sito? -> Evitare i colori fastidiosi. Attenzione ai colori per i daltonici.
- Nel sito JAVA cosa deve esserci esattamente? -> Lista req. di sistema, JRE, guida con screenshots
- Sito JS? -> Browser supportati, dimensione minima
- Rendering Live, opzione o abilitato da subito? -> A scelta
- Quanti poligoni di taglio si possono avere? -> Uno o più poligoni
- Salvataggio percorso; file o db? -> A scelta
- Scermata di caricamento, come deve essere fatta? -> Ev. miniatura, a scelta
- All'avvio cosa deve essere mostrato; subito il triangolo o una home? -> A scelta
- Salvataggio dimensioni definite; raster o vettoriale? -> Vettoriale:No, Raster: corrente, 500 o 1000
- Salvataggio dimensioni minime e massime? -> non rilevante, vedi domanda precedente
- Salvataggio, scelta formato o entrambe? -> l'applicazione deve mostrare entrambe le opzioni, e l'utente sceglie di volta in volta
- In che tipo di file vanno salvati i punti di taglio? -> non vincolante, si consigliano JSON, CSV, o serializzazione di oggetti
- I punti salvati dovranno poter essere caricati, come? -> se file: esplora file o pagina che mostra direttamente tutti i file e/o minuature, se db, idem. I dati vengono poi letti per la creazione dei punti su schermo
- Modifica e eleminiazione punti; come gestire i click? -> pressione, commutazione tramite pulsanti, click normale con rilevamento dei contenuti (++)
- Esiste un numero limite di punti? -> no
- Che caratteristiche deve avere il sito? -> risposto sopra
- Cosa significa la generazione in tempo reale? -> che ad ogni click/inserimento di punti, viene generata automaticamente l'immagine finale
- Il tasto genera, salva subito o visualizza un'anteprima? -> Anteprima
- Nella versione Java, come deve essere fornito il file? .class o jar? -> class per github, JAR per l'esecuzione
- Il triangolo può variare di dimensioni? Dimensioni minime? -> Sì, deve ridimensionarsi. Dimensioni iniziali 50% finestra iniziale
- Il triangolo deve sempre essere centrato? -> Sì
- Deve esistere una barra di menu; in alto? -> A scelta
- Prima del salvataggio definizione della cartella? -> A scelta, meglio se utente
- Deve esistere un file di configurazione? Cosa contiene? -> Se necessario
- Devo fare delle miniature per il caricamento in Java? -> Opzionale
- Quali browser devo supportare? Req minimi? -> Explorer >11, Firefox 6, Chrome 76
- Sito Java, CMS, Git o uno specifico? -> A scelta, anche git va bene
- Se apposta Java, posso tenere il Jar nel git? -> Sì
- Posso cambiare il linguaggio scelto durante il progetto? -> Sconsigliato!!
- Log? -> non richiesto
