import java.awt.Point;
import java.util.*;

/**
 * Classe de exemplo para a implementação de um Jogador para o Jogo Snake.
 * Nesta implementação, a próxima direção da cobra é escolhida aleatoriamente
 * entre as direções possíveis (que não geram colisões).
 * <p>
 * Use esta classe como base inicial para a sua solução do jogo. Basicamente
 * você precisará reimplementar o método {@code getDirecao}.
 * 
 * @author Horácio
 */

public class SnakeJogador {
    private Snake jogo;
    
    /**
     * Cria um novo jogador para o jogo passado como parâmetro.
     * @param jogo jogo snake.
     */
    public SnakeJogador(Snake jogo) {
        this.jogo = jogo;
    }

    /**
     * Executado pelo método {@link Snake#inicia()} a cada 'tick' do jogo para
     * perguntar qual a próxima direção da cobra ('C'ima, 'D'ireita, 'B'aixo,
     * 'E'squerda ou 'N'enhum).
     *
     * @return a próxima direção da cobra.
     */
    public char getDirecao() {
        /*
         * IMPLEMENTE AQUI A SUA SOLUÇÃO PARA O JOGO
         */

        ArrayList<Character> possiveisDirecoes = new ArrayList<Character>(4);
        Point cabeca = jogo.getSegmentos().getFirst();
        char dirAtual = jogo.getDirecaoAtual();

        // Adiciona as possíveis direções na lista
        if (jogo.isCelulaLivre(cabeca.x, cabeca.y-1)) possiveisDirecoes.add('C'); // Cima
        if (jogo.isCelulaLivre(cabeca.x+1, cabeca.y)) possiveisDirecoes.add('D'); // Direita
        if (jogo.isCelulaLivre(cabeca.x, cabeca.y+1)) possiveisDirecoes.add('B'); // Baixo
        if (jogo.isCelulaLivre(cabeca.x-1, cabeca.y)) possiveisDirecoes.add('E'); // Esquerda

        // Não existe mais nenhuma direção disponível
        if (possiveisDirecoes.size() == 0) return 'N';
        System.out.println("Cabeça: " + jogo.getSegmentos().getFirst());

        System.out.println("Comida: " + jogo.getComida());

        // ======= VERIFICAR SE A CABEÇA ESTÁ NA MESMA COLUNA OU LINHA DA COMIDA ==========

        double cabecaX, cabecaY, comidaX, comidaY;
        cabecaX = this.jogo.getSegmentos().getFirst().getX();
        cabecaY = this.jogo.getSegmentos().getFirst().getY();
        comidaX = this.jogo.getComida().getX();
        comidaY = this.jogo.getComida().getY();
        // MESMA LINHA
        if(cabecaY == comidaY){
            if(cabecaX < comidaX) return 'D';
            return 'E';
        }

        // MESMA COLUNA
        if(cabecaX == comidaX){
            if(cabecaY < comidaY){
                if(possiveisDirecoes.contains('B'))
                    return 'B';
            }else{
                if(possiveisDirecoes.contains('C'))
                    return 'C';
            }
        }

        // ======= IR PARA A BORDA =========

        //Ir para a borda de cima
        if(possiveisDirecoes.contains('C') && dirAtual == 'C') return 'C';

        //Ir para a borda da direita
        if(possiveisDirecoes.contains('D') && dirAtual == 'D') return 'D';

        //Ir para a borda de baixo
        if(possiveisDirecoes.contains('B') && dirAtual == 'B') return 'B';

        //Ir para a borda da esquerda
        if(possiveisDirecoes.contains('E') && dirAtual == 'E') return 'E';

        //Se não tem mais borda para ir, move no sentido horário
        if(possiveisDirecoes.size() == 2) return 'B';

        // Pega um número aleatório entre 0 e o tamanho da lista e retorna a direção
        int posicao = new Random().nextInt(possiveisDirecoes.size());
        return possiveisDirecoes.get(posicao);
    }
    
}
