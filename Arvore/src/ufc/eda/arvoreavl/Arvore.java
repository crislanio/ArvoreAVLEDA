package ufc.eda.arvoreavl;


/**
 * 
 * @author Rafael Caixeta
 */
public class Arvore {

    private No raiz;

    public Arvore() {
        this.setRaiz(null);
    }

    /**
     * @return the raiz
     */
    public No getRaiz() {
        return raiz;
    }

    /**
     * @param raiz
     * 
     */
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    /**
     * Metodo completar de inserçao de um no na arvore
     * 
     * @param no
     * @param valor
     */
    public void insere(No no, int valor) {
        // testa se o valor existe na arvore
        if (no.getValor() == valor) {
            System.out.println("O no com esse valor ja existe na arvore");
        } else {
            // testa se o valor a ser inserido e menor que a raiz
            if (valor < no.getValor()) {
                if (no.getfilhoEsquerda() == null) {
                    No novo = new No(valor);
                    no.setfilhoEsquerda(novo);
                } else {
                    insere(no.getfilhoEsquerda(), valor);
                }
            } else {
                // testa se o valor a ser inserido e maior que a raiz
                if (no.getfilhoDireita() == null) {
                    No novo = new No(valor);
                    no.setfilhoDireita(novo);
                } else {
                    insere(no.getfilhoDireita(), valor);
                }
            }

        }
    }

    /**
     * Metodo para inserir um valor na arvore
     * 
     * @param valor
     */
    public void insere(int valor) {
        // testa se nao existe nenhum no entao insere o valor
        if (this.getRaiz() == null) {
            this.raiz = new No(valor);
        } else {
            // Senao usa o metodo de inserçao complementar
            insere(this.raiz, valor);
        }
    }

    /**
     * metodo para remover um no avaliando todos os casos necessarios
     * 
     * @param no
     * @param pai
     * @param valor
     */
    public void remove(No no, No pai, int valor) {
        // se o no em questao nao possui o valor a ser removido
        if (no.getValor() != valor) {
            if (valor < no.getValor()) {
                if (no.getfilhoEsquerda() != null) {
                    remove(no.getfilhoEsquerda(), no, valor);
                } else {
                    System.out.println("O valor " + valor
                            + " nao esta na arvore");
                }
            } else {
                if (no.getfilhoDireita() != null) {
                    remove(no.getfilhoDireita(), no, valor);
                } else {
                    System.out.println("O valor " + valor
                            + " nao esta na arvore");
                }
            }

        }
        // se o no em questao possui o valor a ser removido
        else {
            No aux;
            // caso de remocao de folha
            if (no.getfilhoDireita() == null && no.getfilhoEsquerda() == null) {

                // se o no a ser removido for filho direito do pai
                if (pai.getfilhoDireita() == no) {
                    pai.setfilhoDireita(null);
                } else {
                    pai.setfilhoEsquerda(null);
                }
            }
            // caso onde o no possui 1 filho
            else if (no.getfilhoDireita() == null
                    || no.getfilhoEsquerda() == null) {

                // se nao houver subarvore a  direita, pegue o antecessor
                if (no.getfilhoEsquerda() != null) {
                    if (no == this.raiz) {
                        this.raiz = no.getfilhoEsquerda();
                    } else {
                        // faltou ver se o nó era filho da esquerda ou direita
                        if (no.getfilhoEsquerda() != null) {
                            if (pai.getfilhoDireita() == no) {
                                pai.setfilhoDireita(no.getfilhoEsquerda());
                            } else {
                                pai.setfilhoEsquerda(no.getfilhoEsquerda());
                            }
                        } else {
                            if (pai.getfilhoDireita() == no) {
                                pai.setfilhoDireita(no.getfilhoDireita());
                            } else {
                                pai.setfilhoEsquerda(no.getfilhoDireita());
                            }
                        }

                    }
                }
                // se nao houver subArvore a  esquerda, pegue o sucessor
                else {
                    if (no == this.raiz)
                        this.raiz = no.getfilhoDireita();
                    else {
                        if (no.getfilhoDireita() != null) {
                            // faltou ver se o nó era filho da esquerda ou direita
                            if (pai.getfilhoDireita() == no) {
                                pai.setfilhoDireita(no.getfilhoDireita());
                            } else {
                                pai.setfilhoEsquerda(no.getfilhoDireita());
                            }
                        } else {
                            if (pai.getfilhoDireita() == no) {
                                pai.setfilhoDireita(no.getfilhoEsquerda());
                            } else {
                                pai.setfilhoEsquerda(no.getfilhoEsquerda());
                            }
                        }
                    }
                }
            }
            /*
             * caso de remocao de no com dois filhos: copie o sucessor para o no
             * a ser removido e remova o sucessor
             */
            else {
                aux = no.sucessor(no);
                no.setValor(aux.getValor());
                remove(no.getfilhoDireita(), no, aux.getValor());
            }
        }
    }

    /**
     * Metodo para remocao de no
     * 
     * @param valor
     */
    public void remove(int valor) {
        // caso em que a arvore esta vazia
        if (this.getRaiz() == null)
            ;
        // caso em que haja apenas a raiz
        else if (this.getRaiz().getValor() == valor
                && this.getRaiz().getfilhoEsquerda() == null
                && this.getRaiz().getfilhoDireita() == null) {
            this.raiz = null;
        } else {
            remove(this.getRaiz(), null, valor);
        }
    }

    /**
     * Metodo densevolvido para pesquisar um determinado valor em um No usando
     * recursao para fazer a pesquisa
     * 
     * @param valor
     * @param atual
     * @return
     */
    public No pesquisar(int valor, No atual) {
        if (valor == atual.getValor())
            return atual;
        else if (atual == null)
            return null;
        else if (valor < atual.getValor())
            return pesquisar(valor, atual.getfilhoEsquerda());
        else
            return pesquisar(valor, atual.getfilhoDireita());
    }

    /**
     * Imprime a arvore de forma PreFixada de acordo com o caminho percorrido
     * Metodo em Recursao
     * 
     * @param no
     */
    public void preOrdem(No no) {
        System.out.print("<");
        if (no != null) {

            System.out.print(no.getValor());
            preOrdem(no.getfilhoEsquerda());
            preOrdem(no.getfilhoDireita());
        }
        System.out.print(">");
    }

    /**
     * Imprime a arvore de forma PosFixada de acordo com o caminho percorrido
     * Metodo em Recursao
     * 
     * @param no
     */
    public void posOrdem(No no) {
        System.out.print("<");
        if (no != null) {

        	posOrdem(no.getfilhoEsquerda());
        	posOrdem(no.getfilhoDireita());
            System.out.print(no.getValor());

        }
        System.out.print(">");
    }

    /**
     * Imprime a arvore EmOrdem de acordo com o caminho percorrido Metodo em
     * Recursao
     * 
     * @param no
     */
    public void emOrdem(No no) {

        System.out.print("<");
        if (no != null) {
            emOrdem(no.getfilhoEsquerda());
            System.out.print(no.getValor() + "");
            emOrdem(no.getfilhoDireita());
        }
        System.out.print(">");

    }

}