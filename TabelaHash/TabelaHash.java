package TabelaHash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabelaHash<K, V> implements Hash<K, V> {
    private static final double FATOR_CARGA_MAX = 0.5;

    public static final int LINEAR_PROBING = 0;
    public static final int HASHING_DUPLO = 1;

    private Entry<K, V>[] tabela;
    private int quantidadeElementos;
    private int capacidade;
    private int q;
    private int modo;

    @SuppressWarnings("unchecked")
    public TabelaHash(int capacidadeInicial, int modo) {
        if (capacidadeInicial <= 0) {
            throw new IllegalArgumentException("A capacidade inicial deve ser maior que zero.");
        }

        if (modo != LINEAR_PROBING && modo != HASHING_DUPLO) {
            throw new IllegalArgumentException("Modo inválido. Use LINEAR_PROBING ou HASHING_DUPLO.");
        }

        this.capacidade = nextPrimo(capacidadeInicial);
        this.tabela = new Entry[this.capacidade];
        this.quantidadeElementos = 0;
        this.modo = modo;
        this.q = primoMenorQue(this.capacidade);
    }

    private boolean isPrimo(int numero) {
        if (numero < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    private int nextPrimo(int numero) {
        while (!isPrimo(numero)) {
            numero++;
        }

        return numero;
    }

    private int primoMenorQue(int numero) {
        for (int i = numero - 1; i >= 2; i--) {
            if (isPrimo(i)) {
                return i;
            }
        }

        return 2;
    }

    private int codigoHash(K key) {
        return Math.abs(key.hashCode());
    }

    private int primaryFunction(K key) {
        return codigoHash(key) % this.capacidade;
    }

    private int secondaryFunction(K key) {
        return this.q - (codigoHash(key) % this.q);
    }

    private int getIndex(K key, int tentativa) {
        if (this.modo == HASHING_DUPLO) {
            return (primaryFunction(key) + tentativa * secondaryFunction(key)) % this.capacidade;
        }

        return (primaryFunction(key) + tentativa) % this.capacidade;
    }

    private double fatorDeCarga() {
        return (double) this.quantidadeElementos / this.capacidade;
    }

    @Override
    public int size() {
        return this.quantidadeElementos;
    }

    @Override
    public boolean isEmpty() {
        return this.quantidadeElementos == 0;
    }

    @Override
    public V findElement(K key) {
        if (key == null) {
            throw new IllegalArgumentException("A chave não pode ser nula.");
        }

        for (int tentativa = 0; tentativa < this.capacidade; tentativa++) {
            int indice = getIndex(key, tentativa);
            Entry<K, V> entrada = this.tabela[indice];

            if (entrada == null) {
                return null;
            }

            if (entrada != Entry.AVAILABLE && entrada.getKey().equals(key)) {
                return entrada.getValue();
            }
        }

        return null;
    }

    @Override
    public void insertItem(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("A chave não pode ser nula.");
        }

        if (fatorDeCarga() >= FATOR_CARGA_MAX) {
            rehash();
        }

        int primeiroDisponivel = -1;

        for (int tentativa = 0; tentativa < this.capacidade; tentativa++) {
            int indice = getIndex(key, tentativa);
            Entry<K, V> entrada = this.tabela[indice];

            if (entrada == null) {
                if (primeiroDisponivel != -1) {
                    indice = primeiroDisponivel;
                }

                this.tabela[indice] = new Entry<>(key, value);
                this.quantidadeElementos++;
                return;
            }

            if (entrada == Entry.AVAILABLE && primeiroDisponivel == -1) {
                primeiroDisponivel = indice;
            }
        }

        if (primeiroDisponivel != -1) {
            this.tabela[primeiroDisponivel] = new Entry<>(key, value);
            this.quantidadeElementos++;
            return;
        }

        rehash();
        insertItem(key, value);
    }

    @Override
    public V removeElement(K key) {
        if (key == null) {
            throw new IllegalArgumentException("A chave não pode ser nula.");
        }

        for (int tentativa = 0; tentativa < this.capacidade; tentativa++) {
            int indice = getIndex(key, tentativa);
            Entry<K, V> entrada = this.tabela[indice];

            if (entrada == null) {
                return null;
            }

            if (entrada != Entry.AVAILABLE && entrada.getKey().equals(key)) {
                V valorRemovido = entrada.getValue();
                this.tabela[indice] = getAvailable();
                this.quantidadeElementos--;
                return valorRemovido;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V> getAvailable() {
        return (Entry<K, V>) Entry.AVAILABLE;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] tabelaAntiga = this.tabela;

        this.capacidade = nextPrimo(this.capacidade * 2);
        this.tabela = new Entry[this.capacidade];
        this.q = primoMenorQue(this.capacidade);
        this.quantidadeElementos = 0;

        for (Entry<K, V> entrada : tabelaAntiga) {
            if (entrada != null && entrada != Entry.AVAILABLE) {
                insertItem(entrada.getKey(), entrada.getValue());
            }
        }
    }

    @Override
    public Iterator<K> keys() {
        List<K> chaves = new ArrayList<>();

        for (Entry<K, V> entrada : this.tabela) {
            if (entrada != null && entrada != Entry.AVAILABLE) {
                chaves.add(entrada.getKey());
            }
        }

        return chaves.iterator();
    }

    @Override
    public Iterator<V> elements() {
        List<V> valores = new ArrayList<>();

        for (Entry<K, V> entrada : this.tabela) {
            if (entrada != null && entrada != Entry.AVAILABLE) {
                valores.add(entrada.getValue());
            }
        }

        return valores.iterator();
    }

    public void imprimirTabela() {
        System.out.println("Capacidade: " + this.capacidade);
        System.out.println("Quantidade de elementos: " + this.quantidadeElementos);
        System.out.println("Fator de carga: " + fatorDeCarga());

        for (int i = 0; i < this.capacidade; i++) {
            Entry<K, V> entrada = this.tabela[i];

            if (entrada == null) {
                System.out.println(i + ": vazio");
            } else if (entrada == Entry.AVAILABLE) {
                System.out.println(i + ": AVAILABLE");
            } else {
                System.out.println(i + ": (" + entrada.getKey() + ", " + entrada.getValue() + ")");
            }
        }
    }
}