package com.nttdata.main;

import com.nttdata.interfacciafunzionale.Parlante;
import com.nttdata.interfacciafunzionale.Salutante;
import com.nttdata.interfacciafunzionale.Scontatore;
import com.nttdata.model.Persona;

public class Main {

	public static void main(String[] args) {
		/*
		 * Lambda
		 * 
		 * Cosa sono le Lambda in Java? Sono delle espressioni nella forma:
		 * 
		 * (parametri in input) -> { funzione da eseguire }
		 * 
		 * Per capire meglio cosa e' una Lambda expression, possiamo partire da una
		 * definizione tecnica: e' un modo lineare per fare dichiarazioni anonime su
		 * interfacce funzionali. Sono espressioni che usano come input gli argomenti
		 * del metodo dell'interfaccia funzionale, e che producono un output. In ultima
		 * analisi, sono una scorciatoia per implementare l'unico metodo abstract che
		 * troviamo in un'interfaccia funzionale.
		 * 
		 * Cos'e' un'interfaccia funzionale?
		 * 
		 * A livello storico, e' uno dei modi coi quali Java cerca di adattarsi al
		 * paradigma della programmazione funzionale. Dalla versione 8 in poi, nel
		 * tentativo di espandere le potenzialita' del linguaggio, Java ha introdotto
		 * alcune novita' sulle interfacce. Quelle che riguardano la programmazione
		 * funzionale sono due: i metodi di default e la possibilita' di dichiarare
		 * un'interfaccia come funzionale.
		 * 
		 * I metodi di default sono dei metodi concreti che e' possibile implementare
		 * direttamente all'interno dell'interfaccia (grazie per l'appunto alla parola
		 * chiave 'default'). Tali metodi POSSONO essere ridefiniti dalle classi che
		 * implementano l'interfaccia, MA NON E' OBBLIGATORIO farlo (a differenza dei
		 * metodi abstract, che e' obbligatorio ridefinire). Questa soluzione si e' resa
		 * necessaria per garantire la retrocompatibilita' (cambiare interfacce vecchie
		 * di 20 anni aggiungendo di punto in bianco un metodo abstract avrebbe
		 * significato obbligare tutti i programmi che usano quelle interfacce a
		 * riscrivere le classi che le implementano).
		 * 
		 * L'interfaccia funzionale e' un'interfaccia che presenta uno e un solo metodo
		 * abstract. E' quindi possibile pensare a simili interfacce come se fossero "il
		 * pretesto" per implementare la funzione che le caratterizza. Normalmente, su
		 * tali interfacce viene apposta l'annotazione @FunctionalInterface ma non e'
		 * obbligatoria. La presenza di tale annotazione avverte il compilatore che in
		 * quella interfaccia ci deve essere uno e un solo metodo abstract.
		 * 
		 * Normalmente, per usare un metodo dichiarato in un'interfaccia si crea una
		 * classe che implementa l'interfaccia, che quindi fornisce un'implementazione
		 * dei metodi in essa dichiarati. Poi, su un'istanza di tale classe, si invoca
		 * il metodo:
		 */

		Persona p = new Persona();
		p.parla();

		/*
		 * Un altro uso estremamente comune e' quello di invocare il metodo all'interno
		 * di un altro metodo (nel nostro esempio, faiParlare) che accetta in input un
		 * oggetto di tipo dell'interfaccia:
		 */

		faiParlare(p);

		/*
		 * Per usare il metodo dell'interfaccia abbiamo quindi avuto bisogno di:
		 * 
		 * 1. creare la classe Persona che implementa l'interfaccia Parlante
		 * 
		 * 2. all'interno di Persona, implementare il metodo parla() dell'interfaccia
		 * 
		 * 3. creare un'istanza della classe
		 * 
		 * 4. passare in qualita' di oggetto Parlante (e' proprio il caso di dirlo)
		 * l'istanza in input ad un metodo che usa il metodo parla()
		 * 
		 * Tutto questo soltanto per usare il metodo parla(). Le Lambda expression ci
		 * consentono di sintetizzare il processo in un modo molto elegante, ma
		 * arriviamoci per step riprendendo la definizione tecnica iniziale: "un modo
		 * lineare per fare dichiarazioni anonime su interfacce funzionali". Una
		 * dichiarazione anonima e' la dichiarazione di una classe alla quale non diamo
		 * un nome perche' creata "al volo", giusto per svolgere un determinato compito.
		 * In questo caso, il compito da svolgere e' proprio quello di fornire
		 * un'implementazione "volante" del metodo parla() da passare al metodo
		 * faiParlare, che vuole in input un oggetto di tipo Parlante:
		 */

		faiParlare(new Parlante() {
			private String s = "Sono una classe anonima e...";

			@Override
			public void parla() {
				System.out.println(s + " sto implementando il metodo parla()!\n");
			}
		});

		/*
		 * Notiamo che la dichiarazione anonima della classe sta tra le parentesi tonde
		 * dell'invocazione al metodo faiParlare: e' a tutti gli effetti un parametro
		 * che stiamo passando in input. Possiamo quindi salvarlo dentro un'istanza di
		 * Parlante:
		 */

		Parlante lambdaParlante = new Parlante() {

			@Override
			public void parla() {
				System.out.println("Sto per diventare una Lambda!\n");
			}
		};
		faiParlare(lambdaParlante);

		/*
		 * Quel che stiamo facendo altro non e' che salvare dentro una variabile non
		 * piu' un valore, ma l'implementazione di un metodo, un pezzo di codice da
		 * eseguire alla bisogna. Questa e' l'essenza delle Lambda. Nel nostro caso, il
		 * metodo e' parla(). Difatti, possiamo sbarazzarci della dichiarazione della
		 * classe:
		 * 
		 * new Parlante() {}
		 * 
		 * Inoltre, siccome stiamo lavorando con un'interfaccia funzionale che contiene
		 * un solo metodo, non e' necessario vedere per esteso il suo nome, quindi via
		 * anche:
		 * 
		 * @Override public void parla
		 * 
		 * Quel che resta sono gli argomenti del metodo: ()
		 * 
		 * e il corpo delle istruzioni da eseguire:
		 * {System.out.println("Sto per diventare una Lambda!\n");}
		 * 
		 * Ecco: se ci mettiamo il simbolo della Lambda in mezzo (la "freccia" fatta col
		 * simbolo del meno seguito dal simbolo del maggiore), abbiamo fatto una Lambda:
		 */

		lambdaParlante = () -> {
			System.out.println("Sono una Lambda!\n");
		};
		faiParlare(lambdaParlante);
		// In quanto istanza di Parlante, posso ovviamente invocare il metodo parla()
		// anche nel seguente modo:
//		lambdaParlante.parla();

		/*
		 * Ed ora alcuni dettagli: se il corpo delle istruzioni contiene una sola
		 * istruzione, e' possibile omettere le parentesi graffe ed il punto e virgola
		 * successivo:
		 */

		lambdaParlante = () -> System.out.println("Sono una one-line Lambda!\n");
		faiParlare(lambdaParlante);

		/*
		 * Se il metodo dell'interfaccia funzionale prende in input un solo parametro,
		 * e' possibile addirittura omettere le parentesi degli argomenti in input al
		 * metodo. Ad esempio, l'interfaccia funzionale Salutante dichiara il metodo
		 * saluta(String nome):
		 */
		Salutante lambdaSalutante = s -> System.out.println("Ciao " + s);
		faiSalutare(lambdaSalutante, "Giulio");

		/*
		 * Come osservato, non e' necessario che il parametro della Lambda si chiami
		 * come il parametro dell'interfaccia. Ad ogni modo, se i parametri sono nessuno
		 * o piu' di uno, vanno messe le parentesi. Inoltre se il metodo
		 * dell'interfaccia funzionale non e' void (vedi interfaccia funzionale
		 * Scontatore), ma restituisce un valore, e' possibile evitare di scrivere la
		 * parola chiave "return" se per restituire il valore e' usata una sola
		 * istruzione:
		 */

		Scontatore lambdaScontatrice = (tot, d) -> tot - (tot * d);
		double prezzoScontato = faiSconto(lambdaScontatrice, 100, 0.2);
		System.out.println(prezzoScontato);

		/*
		 * Tuttavia, se si ha l'esigenza di usare piu' istruzioni, vanno reintrodotte
		 * sia le parentesi graffe del corpo, sia il punto e virgola dopo il corpo, sia
		 * la parola chiave return:
		 */

		lambdaScontatrice = (tot, d) -> {
			double ammontareDaSottrarre = tot * d;
			double prezzoFinale = tot - ammontareDaSottrarre;
			return prezzoFinale;
		};
		prezzoScontato = faiSconto(lambdaScontatrice, 100, 0.4);
		System.out.println(prezzoScontato);

	}

	public static void faiParlare(Parlante parlante) {
		parlante.parla();
	}

	public static void faiSalutare(Salutante salutante, String nome) {
		salutante.saluta(nome);
	}

	public static double faiSconto(Scontatore scontatore, double prezzo, double percentuale) {
		return scontatore.applicaSconto(prezzo, percentuale);
	}

}
