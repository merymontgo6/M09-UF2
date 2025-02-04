## M09-UF2
Aquesta activitat és la introducció  on es tracta de profunditzar amb la gestió de fils de Java, practicant coses com la comunicació amb els fils.

Avancem amb la directriu wait i notifyAll per estudiar la com esperar fins que algú altre et notifiqui. 

Un organitzador d'esdeveniments vol gestionar les reserves d'assistents per a un esdeveniment amb capacitat limitada. 
Cada esdeveniment té un màxim de 10 places, i els assistents poden fer reserves o cancel·lar-les. 
Quan un assistent intenta fer una reserva i no hi ha places disponibles, ha d'esperar fins que alguna es alliberi. 
De la mateixa manera, quan un assistent cancel·la una reserva, ha de notificar als altres assistents que hi ha places disponibles. 

### Requeriments Esdeveniment: 
- List<Assistent> 

![alt text](imatges/image.png)

- Un constructor que accepti el número de places màxim

![alt text](imatges/image1.png)

- Un mètode ferReserva (Assistent) que afegeixi a l'assistent a la llista i resti un a placesDisponibles 

![alt text](imatges/image2.png)

- Un mètode cancelaReserva (Assistent) que si Assistent està a la llista l'elimini i incrementi el número de places Disponibles

![alt text](imatges/image3.png)

### Requeriments Assistent: 
- té un Esdeveniment 

![alt text](imatges/image4.png)

- Constructor amb nom i Esdeveniment 

![alt text](imatges/image5.png)

- En l'execució ha de executar eternament: amb 50% de probabilitat fa una reserva i amb 50% de probabilitat cancel·la una reserva,espera un temps aleatori de entre 0 i 1s.

![alt text](imatges/image10.png)

### Requeriments Organitzador: 
- Crea un Esdeveniment amb 5 places màxim

![alt text](imatges/image6.png)

- Crea 10 assistents

![alt text](imatges/image7.png)

- Els inicia

![alt text](imatges/image8.png)


### Sortida del programa:

![alt text](imatges/image9.png)

## M09-UF2
### Preguntes teòriques
#### 1. Per què s'atura l'execució al cap d'un temps?

#### 2. Què passaria si en lloc de una probabilitat de 50%-50% fora de 70%  (ferReserva) -30% (cancel·lar)? I si foren al revés les probabilitats? 
→ Mostra la porció de codi modificada i la sortida resultant en cada un dels 2 casos 
#### 3. Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves? 