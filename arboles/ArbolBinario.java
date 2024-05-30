public class ArbolBinario {
    private Nodo ruta;

    private static class Nodo {
        int valor;
        Nodo izquierda;
        Nodo derecha;

        Nodo(int valor) {
            this.valor = valor;
            izquierda = null;
            derecha = null;
        }
    }

    public void insertar(int valor) {
        ruta = insertarvalor(ruta, valor);
    }

    private Nodo insertarvalor(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }

        if (valor < actual.valor) {
            actual.izquierda = insertarvalor(actual.izquierda, valor);
        } else if (valor > actual.valor) {
            actual.derecha = insertarvalor(actual.derecha, valor);
        } else {
            // value already exists
            return actual;
        }

        return actual;
    }

    public void eliminar(int valor) {
        ruta = eliminarvalor(ruta, valor);
    }

    private Nodo eliminarvalor(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }

        if (valor < actual.valor) {
            actual.izquierda = eliminarvalor(actual.izquierda, valor);
        } else if (valor > actual.valor) {
            actual.derecha = eliminarvalor(actual.derecha, valor);
        } else {
            // value found
            if (actual.izquierda == null && actual.derecha == null) {
                // leaf node
                return null;
            } else if (actual.izquierda == null) {
                // one child on the right
                return actual.derecha;
            } else if (actual.derecha == null) {
                // one child on the left
                return actual.izquierda;
            } else {
                // two children
                int menorvalor = buscarvalormenor(actual.derecha);
                actual.valor = menorvalor;
                actual.derecha = eliminarvalor(actual.derecha, menorvalor);
            }
        }

        return actual;
    }

    private int buscarvalormenor(Nodo nodo) {
        return nodo.izquierda == null ? nodo.valor : buscarvalormenor(nodo.izquierda);
    }

    public boolean contiene(int valor) {
        return contienevalor(ruta, valor);
    }

    private boolean contienevalor(Nodo actual, int valor) {
        if (actual == null) {
            return false;
        }

        if (valor < actual.valor) {
            return contienevalor(actual.izquierda, valor);
        } else if (valor>actual.valor) {
            return contienevalor(actual.derecha, valor);
        } else {
            return true;
        }
    }

    public void recorrer() {
        recorrervalor(ruta);
        System.out.println();
    }

    private void recorrervalor(Nodo nodo) {
        if (nodo != null) {
            recorrervalor(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            recorrervalor(nodo.derecha);
        }
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.insertar(6);
        arbol.insertar(4);
        arbol.insertar(8);
        arbol.insertar(3);
        arbol.insertar(5);
        arbol.insertar(7);
        arbol.insertar(9);

        System.out.println("Arbol binario despues de insertar valores");
        arbol.recorrer();

        System.out.println("El arbol contiene 5? " + arbol.contiene(5));
        System.out.println("El Arbol contiene 10? " + arbol.contiene(10));

        arbol.eliminar(4);
        System.out.println("el arbol despues de eliminar el 4:");
        arbol.recorrer();
    }
}