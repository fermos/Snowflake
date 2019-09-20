1. [Introduzione](#introduzione)

  - [Informazioni sul progetto](#informazioni-sul-progetto)

  - [Abstract](#abstract)

  - [Scopo](#scopo)

1. [Analisi](#analisi)

  - [Analisi del dominio](#analisi-del-dominio)

  - [Analisi dei mezzi](#analisi-dei-mezzi)

  - [Analisi e specifica dei requisiti](#analisi-e-specifica-dei-requisiti)

  - [Use case](#use-case)

  - [Pianificazione](#pianificazione)

1. [Progettazione](#progettazione)

  - [Design dell’architettura del sistema](#design-dell’architettura-del-sistema)

  - [Design dei dati e database](#design-dei-dati-e-database)

1. [Implementazione](#implementazione)

1. [Test](#test)

  - [Protocollo di test](#protocollo-di-test)

  - [Risultati test](#risultati-test)

  - [Mancanze/limitazioni conosciute](#mancanze/limitazioni-conosciute)

1. [Consuntivo](#consuntivo)

1. [Conclusioni](#conclusioni)

  - [Sviluppi futuri](#sviluppi-futuri)

  - [Considerazioni personali](#considerazioni-personali)

1. [Sitografia](#sitografia)

1. [Allegati](#allegati)


## Introduzione

### Informazioni sul progetto


  -   Allievo: Mosè Ferrazzini
  -   Docente/Committente: Geo Petrini

  -   Scuola Arti e Mestieri Trevano , Informatica, Modulo 306

  -   3	settembre	2019	–	20	dicembre	2019

### Abstract

  Alla SAM di Trevano gli allievi di terza della sezione informatica hanno ricevuto come progetto individuale per il primo semestre. Cioè un software che crea un fiocco di neve a partire da un triangolo ritagliabile dall'utente. Per farlo il software è stato implementato in Java. Il risultato è la simulazione della creazione di un fiocco di neve con un foglio di carta piegato e tagliato con le forbici.

### Scopo

  Lo scopo del progetto è quello di creare un programma in Java che partendo da un triangolo con dei tagli (come un foglio piegato) simuli l'apertura del foglio e quindi il fiocco di neve da esso derivato.


## Analisi

### Analisi del dominio

  Il prodotto dovrà funzionare sulle piattaforme che supportano java e dovrà creare un fiocco di neve a partire da un triangolo tagliato. Funzionerà come con la carta e le forbici, gli utenti saranno vari e l'uso del software sarà per svago e non per lavoro. Perciò il software sarà intuitivo e semplice da utilizzare per permettere agli utenti meno esperti di usufruirne.

### Analisi e specifica dei requisiti

  Il committente ha bisogno che il software sia funzionante e semplice da utilizzare.
  Il prodotto deve creare un fiocco di neve partendo da un triangolo ritagliato.
  L'utente dovrebbe ritagliare il triangolo da cui il software creerà il fiocco di neve.
  Per farlo verrà utilizzato un mouse o un touchscreen/touchpad.
  L'interfaccia dovrebbe mostrare un triangolo ritagliabile nella parte sinistra del software e il fiocco di neve risultante da esso sulla destra.
  Il programma deve almeno permettere di ritagliare il triangolo e mostrare il fiocco di neve.
  Il software non necessita di sicurezze particolari dato che non utilizza la rete.


  |**ID**	|**Nome**			|**Priorità**|**Vers**|**Note**  |
  |----|------------|--------|----|------|
  |Req-01|Il programma dovrà essere in Java|1|1.0|...|
  |Req-02|La finestra dovrà essere ridimensionabile|1|1.0|...|
  |Req-03|Si dovrà poter resettare o aggiungere i punti|1|1.0|...|
  |Req-04|Si dovranno poter creare uno o più poligoni|1|1.0|...|
  |Req-05|Ci dovrà essere un tasto per generare il fiocco di neve|1|1.0|...|
  |Req-06|Si deve poter salvare il fiocco di neve come immagine|1|1.0|...|
  |Req-07|La dimensione dell'immagine dovrà essere 500x500 Raster|1|1.0|...|
  |Req-08|Si deve poter salvare la posizione dei punti|1|1.0|...|
  |Req-09|Salvataggio in un file|1|1.0|...|
  |Req-10| Formato salvataggio punti --> JSON, CSV, o serializzazione di oggetti|1|1.0|...|
  |Req-11|Per importare esplora file o pagina che mostra direttamente tutti i file|1|1.0|...|
  |Req-12|Dovrà esserci un sito con download e descrizione del progetto|1|1.0|...|
  |Req-13|Nel sito dovranno esserci Lista req. di sistema, JRE, guida con screenshots|1|1.0|...|
  |Req-14|Fornire class per github, JAR per l'esecuzione|1|1.0|...|



### Use case

I casi d’uso rappresentano l’interazione tra i vari attori e le
funzionalità del prodotto.

### Pianificazione

Prima di stabilire una pianificazione bisogna avere almeno una vaga idea
del modello di sviluppo che si intende adottare. In questa sezione
bisognerà inserire il modello concettuale di sviluppo che si seguirà
durante il progetto. Gli elementi di riferimento per una buona
pianificazione derivano da una scomposizione top-down della problematica
del progetto.

La pianificazione può essere rappresentata mediante un diagramma di
Gantt.

Se si usano altri metodi di pianificazione (es scrum), dovranno apparire
in questo capitolo.

### Analisi dei mezzi

 - 1 PC fornito dalla scuola con i tool necessari preinstallati

 - Portatile personale (Dell Vostro)

- JDK 12, Netbeans 11.1

Il software verrà eseguito su qualsiasi piattaforma supporti Java.

## Progettazione

Il programma avrà un triangolo ritagliabile tramite dei click del mouse o touchscreen/touchpad. Partendo da questo triangolo il software dovrà creare un fiocco di neve come se il triangolo fosse stato aperto (come con carta e forbici).

### Design dell’architettura del sistema

Descrive:

-   La struttura del programma/sistema lo schema di rete...

-   Gli oggetti/moduli/componenti che lo compongono.

-   I flussi di informazione in ingresso ed in uscita e le
    relative elaborazioni. Può utilizzare *diagrammi di flusso dei
    dati* (DFD).

-   Eventuale sitemap

### Design dei dati e database

Descrizione delle strutture di dati utilizzate dal programma in base
agli attributi e le relazioni degli oggetti in uso.

### Schema E-R, schema logico e descrizione.

Se il diagramma E-R viene modificato, sulla doc dovrà apparire l’ultima
versione, mentre le vecchie saranno sui diari.

### Design delle interfacce

Descrizione delle interfacce interne ed esterne del sistema e
dell’interfaccia utente. La progettazione delle interfacce è basata
sulle informazioni ricavate durante la fase di analisi e realizzata
tramite mockups.

### Design procedurale

Descrive i concetti dettagliati dell’architettura/sviluppo utilizzando
ad esempio:

-   Diagrammi di flusso e Nassi.

-   Tabelle.

-   Classi e metodi.

-   Tabelle di routing

-   Diritti di accesso a condivisioni …

Questi documenti permetteranno di rappresentare i dettagli procedurali
per la realizzazione del prodotto.

## Implementazione

In questo capitolo dovrà essere mostrato come è stato realizzato il
lavoro. Questa parte può differenziarsi dalla progettazione in quanto il
risultato ottenuto non per forza può essere come era stato progettato.

Sulla base di queste informazioni il lavoro svolto dovrà essere
riproducibile.

In questa parte è richiesto l’inserimento di codice sorgente/print
screen di maschere solamente per quei passaggi particolarmente
significativi e/o critici.

Inoltre dovranno essere descritte eventuali varianti di soluzione o
scelte di prodotti con motivazione delle scelte.

Non deve apparire nessuna forma di guida d’uso di librerie o di
componenti utilizzati. Eventualmente questa va allegata.

Per eventuali dettagli si possono inserire riferimenti ai diari.

## Test

### Protocollo di test

Definire in modo accurato tutti i test che devono essere realizzati per
garantire l’adempimento delle richieste formulate nei requisiti. I test
fungono da garanzia di qualità del prodotto. Ogni test deve essere
ripetibile alle stesse condizioni.


|Test Case      | TC-001                               |
|---------------|--------------------------------------|
|**Nome**       |Import a card, but not shown with the GUI |
|**Riferimento**|REQ-012                               |
|**Descrizione**|Import a card with KIC, KID and KIK keys with no obfuscation, but not shown with the GUI |
|**Prerequisiti**|Store on local PC: Profile\_1.2.001.xml (appendix n\_n) and Cards\_1.2.001.txt (appendix n\_n) |
|**Procedura**     | - Go to “Cards manager” menu, in main page click “Import Profiles” link, Select the “1.2.001.xml” file, Import the Profile - Go to “Cards manager” menu, in main page click “Import Cards” link, Select the “1.2.001.txt” file, Delete the cards, Select the “1.2.001.txt” file, Import the cards |
|**Risultati attesi** |Keys visible in the DB (OtaCardKey) but not visible in the GUI (Card details) |


### Risultati test

Tabella riassuntiva in cui si inseriscono i test riusciti e non del
prodotto finale. Se un test non riesce e viene corretto l’errore, questo
dovrà risultare nel documento finale come riuscito (la procedura della
correzione apparirà nel diario), altrimenti dovrà essere descritto
l’errore con eventuali ipotesi di correzione.

### Mancanze/limitazioni conosciute

Descrizione con motivazione di eventuali elementi mancanti o non
completamente implementati, al di fuori dei test case. Non devono essere
riportati gli errori e i problemi riscontrati e poi risolti durante il
progetto.

## Consuntivo

Consuntivo del tempo di lavoro effettivo e considerazioni riguardo le
differenze rispetto alla pianificazione (cap 1.7) (ad esempio Gannt
consuntivo).

## Conclusioni

Quali sono le implicazioni della mia soluzione? Che impatto avrà?
Cambierà il mondo? È un successo importante? È solo un’aggiunta
marginale o è semplicemente servita per scoprire che questo percorso è
stato una perdita di tempo? I risultati ottenuti sono generali,
facilmente generalizzabili o sono specifici di un caso particolare? ecc

### Sviluppi futuri
  Migliorie o estensioni che possono essere sviluppate sul prodotto.

### Considerazioni personali
  Cosa ho imparato in questo progetto? ecc

## Bibliografia

### Bibliografia per articoli di riviste
1.  Cognome e nome (o iniziali) dell’autore o degli autori, o nome
    dell’organizzazione,

2.  Titolo dell’articolo (tra virgolette),

3.  Titolo della rivista (in italico),

4.  Anno e numero

5.  Pagina iniziale dell’articolo,

### Bibliografia per libri


1.  Cognome e nome (o iniziali) dell’autore o degli autori, o nome
    dell’organizzazione,

2.  Titolo del libro (in italico),

3.  ev. Numero di edizione,

4.  Nome dell’editore,

5.  Anno di pubblicazione,

6.  ISBN.

### Sitografia

1.  URL del sito (se troppo lungo solo dominio, evt completo nel
    diario),

2.  Eventuale titolo della pagina (in italico),

3.  Data di consultazione (GG-MM-AAAA).

**Esempio:**

-   http://standards.ieee.org/guides/style/section7.html, *IEEE
    Standards Style Manual*, 07-06-2008.

## Allegati

Elenco degli allegati, esempio:

-   Diari di lavoro

-   Codici sorgente/documentazione macchine virtuali

-   Istruzioni di installazione del prodotto (con credenziali
    di accesso) e/o di eventuali prodotti terzi

-   Documentazione di prodotti di terzi

-   Eventuali guide utente / Manuali di utilizzo

-   Mandato e/o Qdc

-   Prodotto

-   …
